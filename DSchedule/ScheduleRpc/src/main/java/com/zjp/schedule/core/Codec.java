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
 * Time: 10:28
 */

public class Codec {

    private static Codec instance;

    private Serialize serialize;

    private Codec() {
        this.serialize = Config.SERIALIZE;
    }

    public static Codec getInstance() {
        if (instance == null) {
            synchronized (instance) {
                if (instance == null) {
                    instance = new Codec();
                }
            }
        }
        return instance;
    }

    /**
     * ����
     *
     * @param bytes
     * @param clazz
     * @param <T>
     * @return
     * @throws IOException
     */
    public <T> T decode(byte[] bytes, Class<T> clazz) throws IOException {
        return serialize.deserialize(bytes, clazz);
    }

    /**
     * ����
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public byte[] encode(Object obj) throws IOException {
        return serialize.serialize(obj);
    }
}
