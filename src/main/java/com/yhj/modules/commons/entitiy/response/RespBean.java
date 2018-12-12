package com.yhj.modules.commons.entitiy.response;

import com.alibaba.fastjson.JSONObject;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RespBean {

    // 状态
    private Integer status;
    // 简单消息
    private String msg;
    // 复杂对象
    private Object data;
    // response 流
    private HttpServletResponse response;

    public static RespBean ok(String msg, Object data) {
        return new RespBean(200, msg, data);
    }

    public static RespBean ok(String msg) {
        return new RespBean(200, msg, null);
    }


    public static RespBean ok(HttpServletResponse response, String msg) {
        return new RespBean(response, 200, msg, null);
    }

    public static RespBean error(String msg, Object obj) {
        return new RespBean(500, msg, obj);
    }

    public static RespBean error(HttpServletResponse response, String msg) {
        return new RespBean(response,500, msg, null);
    }

    /**
     * @Description 通过 response 把数据写到客户端
     * @throws IOException
     */
    public void writeToClient() throws IOException {
        response.setContentType("application/json;charset=utf-8");
        PrintWriter out = response.getWriter();
        out.write(JSONObject.toJSONString(this));
        out.flush();
        out.close();
    }


    public RespBean(HttpServletResponse response, int status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
        this.response = response;
    }

    private RespBean(Integer status, String msg, Object data) {
        this.status = status;
        this.msg = msg;
        this.data = data;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public Object getData() {
        return data;
    }

    public void setData(Object data) {
        this.data = data;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
