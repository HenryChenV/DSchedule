package com.zjp.schedule.core;

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
 * Date: 2016/8/9
 * Time: 10:08
 */

public interface RpcAccessor {

    /**
     * ����ip
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
     * ��������
     *
     * @throws IOException
     */
    void start() throws IOException;

    /**
     * ֹͣ����
     *
     * @throws IOException
     */
    void stop() throws IOException;

    /**
     * ����������
     *
     * @param processor
     */
    void setProcessor(RpcProcessor processor);
}
