package com.carndos.modules.commons.util;

import cn.hutool.core.convert.Convert;

import javax.servlet.http.HttpServletRequest;

public class HttpServletUtils {

    public static <T> T getParameter(HttpServletRequest request, String key, T defaultVal) {
        String parameter = request.getParameter(key);
        return Convert.convert(defaultVal.getClass(), parameter,defaultVal);
    }

}
