package com.yhj.modules.commons.entitiy.response;

import com.alibaba.fastjson.JSONObject;
import com.yhj.modules.commons.components.CustomConstantInterface;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.io.PrintWriter;

public class RespBean implements CustomConstantInterface {

    // 状态
    private Integer status;
    // 简单消息
    private String msg;
    // 复杂对象
    private Object complexObj;
    // response 流
    private HttpServletResponse response;


    public static RespBean success(HttpServletResponse response, String msg) {
        return new RespBean(response, SUCCESS_CODE, msg, null);
    }
    public static RespBean success(HttpServletResponse response, Object obj) {
        return new RespBean(response, SUCCESS_CODE, null, obj);
    }

    public static RespBean error(HttpServletResponse response,Integer status, String msg) {
        return new RespBean(response,status, msg, null);
    }

    private  RespBean(HttpServletResponse response,Integer status, String msg, Object complexObj) {
        this.status = status;
        this.msg = msg;
        this.complexObj = complexObj;
        this.response = response;
    }

    /**
     * @Description 通过 response 把数据写到客户端
     * @throws IOException
     */
    public void writeToClient() throws IOException {
        if(null == response){
            return;
        }
        response.setContentType("application/json;charset=utf-8");
        response.getOutputStream().print(JSONObject.toJSONString(this));
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

    public Object getComplexObj() {
        return complexObj;
    }

    public void setComplexObj(Object complexObj) {
        this.complexObj = complexObj;
    }

    public HttpServletResponse getResponse() {
        return response;
    }

    public void setResponse(HttpServletResponse response) {
        this.response = response;
    }
}
