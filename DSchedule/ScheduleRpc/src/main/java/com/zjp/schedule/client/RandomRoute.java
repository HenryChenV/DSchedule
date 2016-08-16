package com.zjp.schedule.client;

import com.zjp.schedule.core.ServiceRoute;
import com.zjp.schedule.discovery.DistributeMetaInfo;
import com.zjp.schedule.entity.MachineInfo;

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
 * Module Desc:com.zjp.schedule.client
 * User: zjprevenge
 * Date: 2016/8/10
 * Time: 19:00
 */

public class RandomRoute implements ServiceRoute {

    /**
     * ѡȡָ���Ļ���ִ������
     *
     * @param appName
     * @return
     */
    public MachineInfo select(String appName) {
        List<MachineInfo> machineInfos = DistributeMetaInfo.getMachineInfo(appName);
        int k = (int) (Math.random() * machineInfos.size());
        return machineInfos.get(k);
    }
}
