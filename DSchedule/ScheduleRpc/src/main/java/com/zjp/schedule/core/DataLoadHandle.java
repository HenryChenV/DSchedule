package com.zjp.schedule.core;

import com.zjp.schedule.entity.MachineInfo;
import com.zjp.schedule.entity.MetaInfo;

import java.util.List;

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
 * Time: 22:43
 */

public interface DataLoadHandle {

    /**
     * װ������
     *
     * @param metaInfos    Ԫ���ݼ���
     * @param machineInfos �������ݼ���
     */
    void handle(List<MetaInfo> metaInfos, List<MachineInfo> machineInfos);
}
