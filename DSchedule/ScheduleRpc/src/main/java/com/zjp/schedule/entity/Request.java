package com.zjp.schedule.entity;

import java.io.Serializable;

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
 * Date: 2016/8/9
 * Time: 9:42
 */

public class Request implements Serializable {
    //����id
    private long requestId;
    //��������
    private String className;
    //���󷽷���
    private String methodName;

    public Request() {
    }

    public Request(Builder builder) {
        this.requestId = builder.requestId;
        this.className = builder.className;
        this.methodName = builder.methodName;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {

        //����id
        private long requestId;
        //��������
        private String className;
        //���󷽷���
        private String methodName;

        public Builder requestId(long requestId) {
            this.requestId = requestId;
            return this;
        }

        public Builder className(String className) {
            this.className = className;
            return this;
        }

        public Builder methodName(String methodName) {
            this.methodName = methodName;
            return this;
        }

        public Request build() {
            return new Request(this);
        }
    }

    public long getRequestId() {
        return requestId;
    }

    public String getClassName() {
        return className;
    }

    public String getMethodName() {
        return methodName;
    }
}
