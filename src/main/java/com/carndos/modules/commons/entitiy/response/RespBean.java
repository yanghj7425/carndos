package com.carndos.modules.commons.entitiy.response;

import cn.hutool.core.collection.CollectionUtil;
import com.alibaba.fastjson.JSONObject;
import com.carndos.modules.commons.components.CustomFinalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;


public class RespBean {
    private static Logger logger = LoggerFactory.getLogger(RespBean.class);

    private HttpServletResponse response;

    private Object object;

    public static RespBean ok(HttpServletResponse response, String message) {
        Map<String, Object> map = CollectionUtil.newHashMap();
        map.put(CustomFinalConstant.MSG_KEY, message);
        map.put(CustomFinalConstant.STATUS_KEY, CustomFinalConstant.SUCCESS_CODE);
        return new RespBean(response, map);
    }

    public static RespBean ok(HttpServletResponse response, Map<String, Object> map) {
        map.put(CustomFinalConstant.STATUS_KEY, CustomFinalConstant.SUCCESS_CODE);
        return new RespBean(response, map);
    }

    public static RespBean error(HttpServletResponse response, int status, Map<String, Object> map) {
        map.put(CustomFinalConstant.STATUS_KEY, status);
        return new RespBean(response, map);
    }


    public static RespBean error(HttpServletResponse response, int status, String message) {
        Map<String, Object> map = CollectionUtil.newHashMap();
        map.put(CustomFinalConstant.STATUS_KEY, status);
        map.put(CustomFinalConstant.MSG_KEY, message);
        return new RespBean(response, map);
    }

    private RespBean(HttpServletResponse response, Object o) {
        this.response = response;
        this.object = o;
    }


    public void writeJsonToClient() {
        response.setContentType(CustomFinalConstant.RESPONSE_CONTENT_TYPE_HEADER);
        try {
            response.getWriter().print(JSONObject.toJSONString(object));
        } catch (IOException e) {
            logger.debug(e.getMessage(), e);
        }
    }


}
