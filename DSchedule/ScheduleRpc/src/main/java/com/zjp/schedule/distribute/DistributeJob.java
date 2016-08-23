package com.zjp.schedule.distribute;

import com.netflix.curator.framework.recipes.locks.InterProcessMutex;
import com.zjp.schedule.core.Config;
import com.zjp.schedule.core.ZkClient;
import com.zjp.schedule.entity.Request;
import org.pinae.rafiki.job.Job;
import org.pinae.rafiki.job.JobException;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.UUID;

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
 * Module Desc:com.zjp.schedule.distribute
 * User: zjprevenge
 * Date: 2016/8/10
 * Time: 16:59
 */

public abstract class DistributeJob implements Job {
    private final Logger log = LoggerFactory.getLogger(getClass());
    private InterProcessMutex lock;
    private ZkClient zkClient;
    private String zkUrl;
    private String appName;
    private String className;
    private String methodName;
    private Request request;

    public DistributeJob() {
    }

    public DistributeJob(String zkUrl, String appName, String className, String methodName) {
        this.setZkUrl(zkUrl);
        this.setAppName(appName);
        this.setClassName(className);
        this.setMethodName(methodName);
        this.request = Request.builder()
                .requestId(UUID.randomUUID().timestamp())
                .className(className)
                .methodName(methodName)
                .build();
        init();
    }

    public void init() {
        zkClient = ZkClient.init(getZkUrl(), Config.ZK_CLIENT_PATH);
        lock = zkClient.getReentrantLock(Config.ZK_CLIENT_PATH + "/" + getAppName() +
                ":" + getClassName() + ":" + getMethodName() + "/lock");

    }

    public String getZkUrl() {
        return zkUrl;
    }

    public void setZkUrl(String zkUrl) {
        this.zkUrl = zkUrl;
    }

    public String getAppName() {
        return appName;
    }

    public void setAppName(String appName) {
        this.appName = appName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public String getMethodName() {
        return methodName;
    }

    public void setMethodName(String methodName) {
        this.methodName = methodName;
    }

    public Request getRequest() {
        return request;
    }

    public boolean execute() throws JobException {
        try {
            //��ȡ���������ʱִ��ҵ�����
            lock.acquire();
            return call();
        } catch (Exception e) {
            log.error(e.getMessage(), e);
            return false;
        } finally {
            //�����ͷ���
            try {
                lock.release();
            } catch (Exception e) {
                log.error("release lock fail:{}", e);
            }
        }
    }

    public abstract boolean call();
}
