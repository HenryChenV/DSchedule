package com.zjp.schedule.registry;

import com.zjp.schedule.core.ZkClient;
import com.zjp.schedule.entity.MachineInfo;
import com.zjp.schedule.entity.MetaInfo;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

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
 * Module Desc:com.zjp.schedule.registry
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 12:39
 * zookeeperע������
 */

public class ServerRegistry {
    private static final Logger log = LoggerFactory.getLogger(ServerRegistry.class);

    private ZkClient zkClient;

    private static ServerRegistry instance;

    public static ServerRegistry getInstance(String zkAddress) {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new ServerRegistry(zkAddress);
                }
            }
        }
        return instance;
    }

    private ServerRegistry() {
    }

    private ServerRegistry(String zkAddress) {
        this.zkAddress = zkAddress;
        this.zkClient = ZkClient.init(zkAddress, null);
    }


    private String zkAddress;

    public String getZkAddress() {
        return zkAddress;
    }

    public void setZkAddress(String zkAddress) {
        this.zkAddress = zkAddress;
    }


    /**
     * ע�����
     *
     * @param metaInfo
     * @param machineInfo
     */
    public void registerServer(MetaInfo metaInfo, MachineInfo machineInfo) throws Exception {
        zkClient.registerServer(metaInfo, machineInfo);
    }

}
