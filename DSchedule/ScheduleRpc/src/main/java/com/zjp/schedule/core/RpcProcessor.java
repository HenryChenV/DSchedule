package com.zjp.schedule.core;

import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;

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
 * Time: 10:13
 */

public class RpcProcessor {
    private ExecutorService executorService;
    private Exporter exporter;

    public RpcProcessor(Exporter exporter) {
        this.executorService = Executors.newFixedThreadPool(Config.EXECUTOR_THREAD_COUNT);
        this.exporter = exporter;
    }

    public void submit(Runnable task) {
        executorService.submit(task);
    }

    public Object findService(String className) {
        return exporter.findService(className);
    }

    public ExecutorService getExecutorService() {
        return executorService;
    }

    public void setExecutorService(ExecutorService executorService) {
        this.executorService = executorService;
    }

    public Exporter getExporter() {
        return exporter;
    }

    public void setExporter(Exporter exporter) {
        this.exporter = exporter;
    }
}
