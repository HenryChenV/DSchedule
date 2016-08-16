package com.zjp.schedule.core;

import com.zjp.schedule.entity.Request;
import com.zjp.schedule.entity.Response;
import com.zjp.schedule.transport.NettyRpcAccessor;
import io.netty.channel.ChannelHandlerContext;
import io.netty.channel.SimpleChannelInboundHandler;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.lang.reflect.Method;

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
 * Module Desc:com.zjp.schedule.core
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 11:08
 */

public class AccessHandler extends SimpleChannelInboundHandler<Request> {

    private static final Logger log = LoggerFactory.getLogger(AccessHandler.class);

    private NettyRpcAccessor accessor;

    public AccessHandler(NettyRpcAccessor accessor) {
        this.accessor = accessor;
    }

    @Override
    public void channelUnregistered(ChannelHandlerContext ctx) throws Exception {
        log.info("channelUnregistered...");
        ctx.fireChannelUnregistered();
    }

    @Override
    public void channelActive(ChannelHandlerContext ctx) throws Exception {
        log.info("channelActive...");
    }

    @Override
    public void channelReadComplete(ChannelHandlerContext ctx) throws Exception {
        log.info("channelReadComplete...");
        ctx.flush();
    }

    @Override
    public void exceptionCaught(ChannelHandlerContext ctx, Throwable cause) throws Exception {
        log.info("exceptionCaught from stream:{}", cause);
        ctx.close();
    }

    @Override
    protected void channelRead0(ChannelHandlerContext ctx, Request request) throws Exception {
        syncProcess(ctx, request);
    }

    /**
     * �첽��������
     *
     * @param ctx
     * @param msg
     */
    private void syncProcess(final ChannelHandlerContext ctx, final Request msg) {
        accessor.getRpcProcessor().submit(new Runnable() {
            public void run() {
                handle(ctx, msg);
            }
        });
    }

    public void handle(final ChannelHandlerContext ctx, Request msg) {
        Object service = accessor.getRpcProcessor().findService(msg.getClassName());
        if (service != null) {
            Response.Builder builder = Response.builder();
            builder.responseId(msg.getRequestId());
            //ֻ֧���޲η���
            try {
                Method method = service.getClass().getMethod(msg.getMethodName(), null);
                Object result = method.invoke(service, null);
                builder.result(result);
            } catch (Throwable throwable) {
                log.error(String.format("invoke this service:%s method:%s error:%s", msg.getClassName(), msg.getMethodName(), throwable));
                builder.error(throwable);
            }
            ctx.writeAndFlush(builder.build());
        } else {
            throw new IllegalArgumentException(String.format("not found this class %s ", msg.getClassName()));
        }
    }
}
