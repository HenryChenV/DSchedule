package com.zjp.schedule.handler;

import com.zjp.schedule.parse.ScheduleAnnotationDrivenParser;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.xml.NamespaceHandlerSupport;

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
 * Module Desc:com.zjp.schedule.handler
 * User: zjprevenge
 * Date: 2016/8/9
 * Time: 23:15
 */

public class ScheduleNamespaceHandler extends NamespaceHandlerSupport {

    private static final Logger log = LoggerFactory.getLogger(ScheduleNamespaceHandler.class);

    public void init() {
        log.info("init schedule namespace...");
        registerBeanDefinitionParser("annotation-driven", new ScheduleAnnotationDrivenParser());

    }
}
