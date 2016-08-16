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
 * Date: 2016/8/9
 * Time: 9:51
 */

public class Response {

    //��Ӧid
    private long responseId;
    //��Ӧ�쳣
    private Throwable error;
    //��Ӧ���
    private Object result;

    public Response() {
    }

    public Response(Builder builder) {
        this.responseId = builder.responseId;
        this.error = builder.error;
        this.result = builder.result;
    }

    public static Builder builder() {
        return new Builder();
    }

    public static class Builder {
        //��Ӧid
        private long responseId;
        //��Ӧ�쳣
        private Throwable error;
        //��Ӧ���
        private Object result;

        public Builder responseId(long responseId) {
            this.responseId = responseId;
            return this;
        }

        public Builder error(Throwable error) {
            this.error = error;
            return this;
        }

        public Builder result(Object result) {
            this.result = result;
            return this;
        }

        public Response build() {
            return new Response(this);
        }
    }

    public long getResponseId() {
        return responseId;
    }

    public Throwable getError() {
        return error;
    }

    public Object getResult() {
        return result;
    }
}
