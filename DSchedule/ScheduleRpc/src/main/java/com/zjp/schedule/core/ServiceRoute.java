package com.zjp.schedule.core;

import com.zjp.schedule.entity.MachineInfo;

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
 * Date: 2016/8/10
 * Time: 18:58
 * <p/>
 * ����·�ɣ��пͻ����ҵ�ָ���ķ��񲢽��е���
 */

public interface ServiceRoute {

    /**
     * ѡȡָ���Ļ���ִ������
     *
     * @param appName
     * @return
     */
    MachineInfo select(String appName);
}
