package com.zjp.schedule.core;

import java.util.Map;

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
 * Time: 18:31
 */

public interface DataHandle {

    /**
     * �����ڵ����ݱ仯
     *
     * @param data
     * @param action
     */
    void handle(Map<String, Object> data, ZKNodeAction action);
}
