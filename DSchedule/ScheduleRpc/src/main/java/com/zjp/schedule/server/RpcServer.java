package com.zjp.schedule.server;

import com.zjp.schedule.core.Exporter;
import com.zjp.schedule.core.RpcAccessor;
import com.zjp.schedule.core.RpcProcessor;
import com.zjp.schedule.entity.MachineInfo;
import com.zjp.schedule.entity.MetaInfo;
import com.zjp.schedule.registry.ServerRegistry;
import com.zjp.schedule.transport.NettyRpcAccessor;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.io.IOException;

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
 * Module Desc:com.zjp.schedule.server
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 12:27
 */

public class RpcServer {
    private static final Logger log = LoggerFactory.getLogger(RpcServer.class);

    private RpcAccessor rpcAccessor;

    private Exporter exporter;

    private RpcProcessor processor;

    private String zkServer;

    private String host;

    private int port;

    public RpcServer(String host, int port, Exporter exporter) {
        this.host = host;
        this.port = port;
        this.exporter = exporter;
        rpcAccessor = new NettyRpcAccessor();

        rpcAccessor.setHost(host);
        rpcAccessor.setPort(port);
        processor = new RpcProcessor(exporter);

        rpcAccessor.setProcessor(processor);
        try {
            rpcAccessor.start();
        } catch (IOException e) {
            log.error("start netty server error:{}", e);
        }
    }

    public String getZkServer() {
        return zkServer;
    }

    public void setZkServer(String zkServer) {
        this.zkServer = zkServer;
    }

    /**
     * ע����zookeeper
     *
     * @param metaInfo    Ԫ������Ϣ
     * @param machineInfo ������Ϣ
     */
    public void registry(MetaInfo metaInfo, MachineInfo machineInfo) throws Exception {
        ServerRegistry.getInstance(zkServer)
                .registerServer(metaInfo, machineInfo);
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

    public RpcProcessor getProcessor() {
        return processor;
    }

    public void setProcessor(RpcProcessor processor) {
        this.processor = processor;
    }

    public String getHost() {
        return host;
    }

    public void setHost(String host) {
        this.host = host;
    }

    public int getPort() {
        return port;
    }

    public void setPort(int port) {
        this.port = port;
    }
}
