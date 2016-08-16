package com.zjp.schedule.transport;

import com.zjp.schedule.core.*;
import com.zjp.schedule.entity.Request;
import io.netty.bootstrap.ServerBootstrap;
import io.netty.channel.ChannelInitializer;
import io.netty.channel.ChannelOption;
import io.netty.channel.EventLoopGroup;
import io.netty.channel.nio.NioEventLoopGroup;
import io.netty.channel.sctp.nio.NioSctpServerChannel;
import io.netty.channel.socket.SocketChannel;
import io.netty.handler.logging.LogLevel;
import io.netty.handler.logging.LoggingHandler;
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
 * Module Desc:com.zjp.schedule.transport
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 10:12
 */

public class NettyRpcAccessor implements RpcAccessor {

    private static final Logger log = LoggerFactory.getLogger(NettyRpcAccessor.class);

    private String host;

    private int port;

    private RpcProcessor rpcProcessor;

    private Exporter exporter;

    private EventLoopGroup bossGroup;

    private EventLoopGroup workerGroup;

    public RpcProcessor getRpcProcessor() {
        return rpcProcessor;
    }

    public void setRpcProcessor(RpcProcessor rpcProcessor) {
        this.rpcProcessor = rpcProcessor;
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }

    /**
     * ��ʼ��������
     *
     * @throws IOException
     */
    public void init() throws IOException {
        bossGroup = new NioEventLoopGroup();
        workerGroup = new NioEventLoopGroup();
        try {
            ServerBootstrap bootstrap = new ServerBootstrap();
            bootstrap.group(bossGroup, workerGroup)
                    .channel(NioSctpServerChannel.class)
                    .option(ChannelOption.SO_BACKLOG, 100)
                    .handler(new LoggingHandler(LogLevel.INFO))
                    .childHandler(new ChannelInitializer<SocketChannel>() {
                        @Override
                        protected void initChannel(SocketChannel sc) throws Exception {
                            sc.pipeline().addLast(
                                    new Encoder(),
                                    new Decoder(Request.class),
                                    new AccessHandler(NettyRpcAccessor.this));
                        }
                    });
            bootstrap.bind(host, port).sync();
        } catch (InterruptedException e) {
            log.error("start the server error:{}", e);
        }
    }

    /**
     * ����ip
     *
     * @param host
     */
    public void setHost(String host) {
        this.host = host;
    }

    /**
     * ���ö˿�
     *
     * @param port
     */
    public void setPort(int port) {
        this.port = port;
    }

    /**
     * ��������
     *
     * @throws IOException
     */
    public void start() throws IOException {
        init();
    }

    /**
     * ֹͣ����
     *
     * @throws IOException
     */
    public void stop() throws IOException {
        bossGroup.shutdownGracefully();
        workerGroup.shutdownGracefully();
    }

    /**
     * ����������
     *
     * @param processor
     */
    public void setProcessor(RpcProcessor processor) {
        this.rpcProcessor = processor;
    }
}
