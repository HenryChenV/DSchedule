package com.zjp.schedule.discovery;

import com.zjp.schedule.core.*;
import com.zjp.schedule.distribute.DistributeJob;
import com.zjp.schedule.entity.MachineInfo;
import com.zjp.schedule.entity.MetaInfo;
import com.zjp.schedule.entity.Request;
import com.zjp.schedule.transport.NettyRpcConnector;
import org.pinae.rafiki.task.Task;
import org.pinae.rafiki.task.TaskGroup;
import org.pinae.rafiki.trigger.impl.CronTrigger;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;
import java.util.List;
import java.util.Map;

/**
 * ���������������ް����ӷ𩥩���������
 * ��������������������
 * ���������ߩ��������ߩ�
 * ����������������������
 * ����������������������
 * ���������ש������ס���
 * ����������������������
 * �������������ߡ�������
 * ����������������������
 * ����������������������
 * ������������������stay hungry stay foolish
 * ������������������Code is far away from bug with the animal protecting
 * ��������������������������
 * �������������������������ǩ�
 * ����������������������������
 * �������������������ש�����
 * �������������ϩϡ����ϩ�
 * �������������ߩ������ߩ�
 * �����������������թ�����������
 * Module Desc:com.zjp.schedule.discovery
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 18:08
 */

public class ServiceDiscovery {
    private static final Logger log = LoggerFactory.getLogger(ServiceDiscovery.class);

    private static ServiceDiscovery instance;

    private ZkClient zkClient;

    private ServiceRoute route;

    private String zkUrl;

    public ServiceDiscovery(String zkUrl, ServiceRoute route) {
        this.zkUrl = zkUrl;
        this.zkClient = ZkClient.init(zkUrl, Config.ZK_CLIENT_PATH);
        this.route = route;
    }

    /**
     * �����֣����ط��񲢼�������
     */
    public void discover() {
        zkClient.build(new DataLoaderHandle(),
                new DataMonitorHandle(),
                new ServerMonitorHandle());
    }

    public ZkClient getZkClient() {
        return zkClient;
    }

    public void setZkClient(ZkClient zkClient) {
        this.zkClient = zkClient;
    }

    public String getZkUrl() {
        return zkUrl;
    }

    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    /**
     * ��������
     */
    private class DataLoaderHandle implements DataLoadHandle {

        /**
         * װ������
         *
         * @param metaInfos    Ԫ���ݼ���
         * @param machineInfos �������ݼ���
         */
        public void handle(List<MetaInfo> metaInfos, List<MachineInfo> machineInfos) {
            //װ�ػ�����Ϣ
            for (MachineInfo machineInfo : machineInfos) {
                String appName = machineInfo.getAppName();
                DistributeMetaInfo.putMachineInfo(appName, machineInfo);
            }
            //װ��������Ϣ
            for (MetaInfo metaInfo : metaInfos) {
                final String key = new StringBuilder(metaInfo.getAppName())
                        .append(":")
                        .append(metaInfo.getClass())
                        .append(":")
                        .append(metaInfo.getMethodName())
                        .toString();
                Task task = new Task();
                //���÷���
                task.setGroup(new TaskGroup(metaInfo.getAppName()));
                //������������
                task.setName(key);
                //������ҵ
                task.setJob(new DistributeJob(zkUrl,
                        metaInfo.getAppName(),
                        metaInfo.getClassName(),
                        metaInfo.getClassName()) {
                    @Override
                    public boolean call() {
                        /**
                         *����ִ�У���Ҫ�ǽ���Զ�̵���
                         */
                        rpcRoute(getAppName(), getRequest());
                        return true;
                    }

                    /**
                     * job����
                     * @return
                     */
                    public String getName() {
                        return key;
                    }
                });
                //���ô�����
                try {
                    task.setTrigger(new CronTrigger(metaInfo.getCron()));
                    DistributeMetaInfo.putTask(key, metaInfo.getAppName(), task);
                } catch (Exception e) {
                    log.error("load data error:{}", e);
                }
            }
        }
    }

    /**
     * �������ݽڵ�
     */
    private class DataMonitorHandle implements DataHandle {

        /**
         * �������ݽڵ�仯
         *
         * @param data
         * @param action
         */
        public void handle(Map<String, Object> data, ZKNodeAction action) {
            try {
                Integer type = (Integer) data.get("type");
                if (type == 0) {
                    MetaInfo metaInfo = (MetaInfo) data.get("metaInfo");
                    final String key = new StringBuilder(metaInfo.getAppName())
                            .append(":")
                            .append(metaInfo.getClass())
                            .append(":")
                            .append(metaInfo.getMethodName())
                            .toString();

                    switch (action) {
                        case NODE_ADDED://�ڵ�����
                            Task task = new Task();
                            //���÷���
                            task.setGroup(new TaskGroup(metaInfo.getAppName()));
                            //������������
                            task.setName(key);
                            //������ҵ
                            task.setJob(new DistributeJob(zkUrl,
                                    metaInfo.getAppName(),
                                    metaInfo.getClassName(),
                                    metaInfo.getClassName()) {
                                @Override
                                public boolean call() {
                                    /**
                                     *����ִ�У���Ҫ�ǽ���Զ�̵���
                                     */
                                    rpcRoute(getAppName(), getRequest());
                                    return true;
                                }

                                /**
                                 * job����
                                 * @return
                                 */
                                public String getName() {
                                    return key;
                                }
                            });
                            //���ô�����
                            task.setTrigger(new CronTrigger(metaInfo.getCron()));
                            DistributeMetaInfo.putTask(key, metaInfo.getAppName(), task);
                            break;
                        case NODE_REMOVED://�ڵ�ɾ��
                            DistributeMetaInfo.removeTask(key);
                            break;
                        case NODE_UPDATED:
                            Task newTask = new Task();
                            //���÷���
                            newTask.setGroup(new TaskGroup(metaInfo.getAppName()));
                            //������������
                            newTask.setName(key);
                            //������ҵ
                            newTask.setJob(new DistributeJob(zkUrl,
                                    metaInfo.getAppName(),
                                    metaInfo.getClassName(),
                                    metaInfo.getClassName()) {
                                /**
                                 * ����ִ�У���Ҫ�ǽ���Զ�̵���
                                 * @return
                                 */
                                @Override
                                public boolean call() {
                                    rpcRoute(getAppName(), getRequest());
                                    return true;
                                }

                                /**
                                 *job����
                                 * @return
                                 */
                                public String getName() {
                                    return key;
                                }
                            });
                            //���ô�����
                            newTask.setTrigger(new CronTrigger(metaInfo.getCron()));
                            //��������
                            DistributeMetaInfo.update(key, metaInfo.getAppName(), newTask);
                            break;
                        default:
                            break;
                    }
                }
            } catch (Exception e) {
                log.error("handle zk message error:{}", e);
            }
        }
    }

    /**
     * ������·����ָ������
     *
     * @param appName
     * @param request
     */
    public void rpcRoute(String appName, Request request) {
        //·�ɵ�ָ������
        MachineInfo machineInfo = route.select(appName);
        //����rpc����
        NettyRpcConnector connector = new NettyRpcConnector();
        connector.setHost(machineInfo.getHost());
        connector.setPort(machineInfo.getPort());
        try {
            //��������
            connector.init();
            //rpcԶ�̵���
            connector.invoke(request);
        } catch (IOException e) {
            log.error("rpc invoke error:{}", e);
        } finally {
            try {
                //�ر�����
                connector.stop();
            } catch (IOException e) {
                log.error("stop connector error:{}", e);
            }
        }
    }

    /**
     * �����ڵ����
     */
    private class ServerMonitorHandle implements DataHandle {
        /**
         * ����server�ڵ�仯
         *
         * @param data
         * @param action
         */
        public void handle(Map<String, Object> data, ZKNodeAction action) {
            Integer type = (Integer) data.get("type");
            if (type == 1) {
                MachineInfo machineInfo = (MachineInfo) data.get("machineInfo");
                String appName = machineInfo.getAppName();
                switch (action) {
                    case NODE_ADDED:
                        DistributeMetaInfo.putMachineInfo(appName, machineInfo);
                        break;
                    case NODE_REMOVED:
                        DistributeMetaInfo.removeMachineInfo(appName, machineInfo);
                        break;
                    case NODE_UPDATED:
                        break;
                    default:
                        break;
                }
            }
        }
    }
}
