package com.zjp.schedule.entity;

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
 * Module Desc:com.zjp.schedule.entity
 * User: zjprevenge
 * Date: 2016/8/11
 * Time: 23:48
 */

public class TaskVo {
    //�����������
    private String serviceName;
    //��������
    private String taskName;
    //��������Ӧ������
    private String appName;
    //����job
    private JobVo job;
    //���񴥷���
    private TriggerVo trigger;

    public String getServiceName() {
        return serviceName;
    }

    public void setServiceName(String serviceName) {
        this.serviceName = serviceName;
    }

    public String getTaskName() {
        return taskName;
    }

    public void setTaskName(String taskName) {
        this.taskName = taskName;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public JobVo getJob() {
        return job;
    }

    public void setJob(JobVo job) {
        this.job = job;
    }

    public TriggerVo getTrigger() {
        return trigger;
    }

    public void setTrigger(TriggerVo trigger) {
        this.trigger = trigger;
    }
}
