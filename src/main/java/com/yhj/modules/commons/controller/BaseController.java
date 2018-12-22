package com.yhj.modules.commons.controller;

import com.yhj.modules.commons.components.CustomFinalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Map renderSuccess(Map<String, Object> map) {
        map.put(CustomFinalConstant.STATUS_KEY, CustomFinalConstant.SUCCESS_CODE);
        if (logger.isErrorEnabled()) {
            logger.debug("success");
        }
        return map;
    }

}
