package com.zjp.schedule.discovery;

import com.google.common.collect.ArrayListMultimap;
import com.google.common.collect.Lists;
import com.google.common.collect.Maps;
import com.zjp.schedule.entity.MachineInfo;
import org.pinae.rafiki.task.Task;
import org.pinae.rafiki.task.TaskContainer;

import java.util.List;
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
 * Module Desc:com.zjp.schedule.discovery
 * User: zjprevenge
 * Date: 2016/8/11
 * Time: 1:41
 */

public class DistributeMetaInfo {

    private static ArrayListMultimap<String, MachineInfo> machineInfos = ArrayListMultimap.create();

    //�����б�
    private static Map<String, Task> taskMap = Maps.newHashMap();

    //��������
    private static TaskContainer container = new TaskContainer();


    /**
     * �洢������Ϣ
     *
     * @param appName
     * @param machineInfo
     */
    public static synchronized void putMachineInfo(String appName, MachineInfo machineInfo) {
        machineInfos.put(appName, machineInfo);
    }

    /**
     * ��ȡָ��Ӧ�õĻ����б�
     *
     * @param appName
     * @return
     */
    public static List<MachineInfo> getMachineInfo(String appName) {
        return machineInfos.get(appName);
    }


    /**
     * ɾ��ָ���Ļ���
     *
     * @param appName
     * @param machineInfo
     */
    public static synchronized void removeMachineInfo(String appName, MachineInfo machineInfo) {
        machineInfos.remove(appName, machineInfo);
    }

    /**
     * ���ָ������
     *
     * @param key
     * @param task
     */
    public static synchronized void putTask(String key, String group, Task task) throws Exception {
        taskMap.put(key, task);
        addTask(task, group);
    }

    /**
     * ��������
     *
     * @param key
     * @param appName
     * @param task
     * @throws Exception
     */
    public static synchronized void update(String key, String appName, Task task) throws Exception {
        taskMap.put(key, task);
        updateTask(key, appName, task);
    }

    /**
     * ɾ��ָ��������б�
     *
     * @param key
     */
    public static synchronized void remove(String key) throws Exception {
        removeTask(key);
        taskMap.remove(key);
    }

    /**
     * ֹͣ����
     *
     * @param name ������
     * @throws Exception
     */
    public static void pauseTask(String name) throws Exception {
        container.pauseTask(name);
    }

    /**
     * ֹͣ����
     *
     * @param name  ��������
     * @param group �������
     * @throws Exception
     */
    public static void pauseTask(String name, String group) throws Exception {
        container.pauseTask(name, group);
    }

    /**
     * ��������ɾ������
     *
     * @param name ������
     */
    public static void removeTask(String name) throws Exception {
        container.removeTask(name);
    }

    /**
     * ��������ɾ������
     *
     * @param name  ������
     * @param group ����
     * @throws Exception
     */
    public static void removeTask(String name, String group) throws Exception {
        container.removeTask(name, group);
    }

    /**
     * �������������
     *
     * @param task  ����
     * @param group ����
     * @throws Exception
     */
    public static void addTask(Task task, String group) throws Exception {
        container.addTask(task, group);
    }

    /**
     * ����ָ������
     * ��ɾ���������
     *
     * @param key
     * @param group
     * @param task
     * @throws Exception
     */
    public static void updateTask(String key, String group, Task task) throws Exception {
        container.removeTask(key, group);
        container.addTask(task, group);
    }

    /**
     * ������������ѯ����
     *
     * @param name
     * @return
     */
    public static Task queryTask(String name) {
        return taskMap.get(name);
    }

    /**
     * ������������ѯ��������
     *
     * @param group
     * @return
     */
    public static List<Task> queryTaskByGroup(String group) {
        List<Task> tasks = Lists.newArrayList();
        for (Task task : taskMap.values()) {
            if (task.getGroup().getName().equals(group)) {
                tasks.add(task);
            }
        }
        return tasks;
    }

    public static void removeAllTask(String group) throws Exception {
        container.removeGroup(group);
    }

    /**
     * ������������
     *
     * @throws Exception
     */
    public void start() throws Exception {
        container.start();
    }

    /**
     * ����ָ��������
     *
     * @param name
     * @throws Exception
     */
    public void start(String name) throws Exception {
        container.startTask(name);
    }

    /**
     * ����ָ�������µ�����
     *
     * @param name
     * @param group
     * @throws Exception
     */
    public void start(String name, String group) throws Exception {
        container.startTask(name, group);
    }
}
