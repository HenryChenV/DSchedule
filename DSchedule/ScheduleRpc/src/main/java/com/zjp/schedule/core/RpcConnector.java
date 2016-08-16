package com.zjp.schedule.core;

import com.zjp.schedule.entity.Request;
import com.zjp.schedule.entity.Response;

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
 * Module Desc:com.zjp.schedule.core
 * User: zjprevenge
 * Date: 2016/8/11
 * Time: 18:00
 */

public interface RpcConnector {

    /**
     * ����rpc����
     *
     * @param request
     * @return
     * @throws IOException
     */
    Response invoke(Request request) throws IOException;

    /**
     * ����host
     *
     * @param host
     */
    void setHost(String host);

    /**
     * ���ö˿�
     *
     * @param port
     */
    void setPort(int port);

    /**
     * ����
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * ֹͣ
     *
     * @throws IOException
     */
    void stop() throws IOException;
}
