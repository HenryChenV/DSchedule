package com.zjp.schedule.core;

import io.netty.buffer.ByteBuf;
import io.netty.channel.ChannelHandlerContext;
import io.netty.handler.codec.MessageToByteEncoder;

import java.io.Serializable;

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
 * Time: 10:25
 */

public class Encoder extends MessageToByteEncoder<Serializable> {

    private Codec codec;

    public Encoder() {
        codec = Codec.getInstance();
    }

    @Override
    protected void encode(ChannelHandlerContext channelHandlerContext,
                          Serializable obj,
                          ByteBuf byteBuf) throws Exception {
        byte[] bytes = codec.encode(obj);
        byteBuf.writeInt(bytes.length);
        byteBuf.writeBytes(bytes);
    }
}
