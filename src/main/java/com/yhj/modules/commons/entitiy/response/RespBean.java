package com.yhj.modules.commons.entitiy.response;

import com.alibaba.fastjson.JSONObject;
import com.google.common.collect.Maps;
import com.yhj.modules.commons.components.CustomConstantInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import javax.servlet.http.HttpServletResponse;
import java.io.IOException;
import java.util.Map;

public class RespBean implements CustomConstantInterface {
    private static Logger logger = LoggerFactory.getLogger(RespBean.class);

    private HttpServletResponse response;

    private Object object;


    public static RespBean ok(HttpServletResponse response, String message) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(MSG_KEY, message);
        map.put(STATUS_KEY, SUCCESS_CODE);
        return new RespBean(response, map);
    }

    private RespBean(HttpServletResponse response, Object o) {
        this.response = response;
        this.object = o;
    }

    public static RespBean ok(HttpServletResponse response, Map<String, Object> map) {
        map.put(STATUS_KEY, SUCCESS_CODE);
        return new RespBean(response, map);
    }

    public void writeJsonToClient() {
        response.setContentType(RESPONSE_CONTENT_TYPE_HEADER);
        try {
            response.getWriter().print(JSONObject.toJSONString(object));
        } catch (IOException e) {
            logger.debug(e.getMessage(), e);
        }
    }


}
