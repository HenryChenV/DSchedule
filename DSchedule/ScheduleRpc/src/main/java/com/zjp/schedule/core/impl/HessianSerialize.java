package com.zjp.schedule.core.impl;

import com.caucho.hessian.io.Hessian2Input;
import com.caucho.hessian.io.Hessian2Output;
import com.zjp.schedule.core.Serialize;

import java.io.ByteArrayInputStream;
import java.io.ByteArrayOutputStream;
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
 * Module Desc:com.zjp.schedule.core.impl
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 10:33
 */

public class HessianSerialize implements Serialize {

    /**
     * ���л�
     *
     * @param obj
     * @return
     * @throws IOException
     */
    public <T> byte[] serialize(T obj) throws IOException {
        ByteArrayOutputStream baos = new ByteArrayOutputStream();
        Hessian2Output output = new Hessian2Output(baos);
        output.writeObject(obj);
        output.flush();
        return baos.toByteArray();
    }

    /**
     * �����л�
     *
     * @param bytes
     * @param clazz
     * @return
     * @throws IOException
     */
    public <T> T deserialize(byte[] bytes, Class<T> clazz) throws IOException {
        Hessian2Input input = new Hessian2Input(new ByteArrayInputStream(bytes));
        T t = (T) input.readObject(clazz);
        return t;
    }
}
