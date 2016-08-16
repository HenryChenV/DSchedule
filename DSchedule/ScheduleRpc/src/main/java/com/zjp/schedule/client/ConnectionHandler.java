package com.zjp.schedule.client;

import com.zjp.schedule.entity.Response;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
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
 * Module Desc:com.zjp.schedule.client
 * User: zjprevenge
 * Date: 2016/8/11
 * Time: 18:19
 */

public class ConnectionHandler extends SimpleChannelInboundHandler<Response> {
    private static final Logger log = LoggerFactory.getLogger(ConnectionHandler.class);
    private RpcFutureUtil futureUtil;

    public ConnectionHandler(RpcFutureUtil futureUtil) {
        this.futureUtil = futureUtil;
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("unexpected exception from downstream.", cause);
        futureUtil.notifyException(new Exception(cause));
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext channelHandlerContext, Response response) throws Exception {
        futureUtil.notifyResponse(response);
    }
}
