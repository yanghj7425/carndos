package com.yhj.modules.commons.entitiy.response;

public class RespBean {
    private Integer status;
    private String msg;
    private Object data;

    private RespBean() {
    }

    public static RespBean build() {
        return new RespBean();
    }

    public static RespBean ok(String msg, Object data) {
        return new RespBean(200, msg, data);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(String msg) {
        return new RespBean(500, msg, null);
    }

    private RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {

        return status;
    }

    public RespBean setStatus(Integer status) {
        this.status = status;
        return this;
    }

    public String getMsg() {
        return msg;
    }

    public RespBean setMsg(String msg) {
        this.msg = msg;
        return this;
    }

    public Object getData() {
        return data;
    }

    public RespBean setData(Object data) {
        this.data = data;
        return this;
    }
}
