package com.carndos.modules.commons.controller;

import com.google.common.collect.Maps;
import com.carndos.modules.commons.components.CustomFinalConstant;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BaseController {

    protected Logger logger = LoggerFactory.getLogger(getClass());

    /**
     * @return map
     * @description this method be used render success status to client,for more information
     * @see com.carndos.modules.commons.controller.BaseController#renderSuccess(java.lang.String, java.lang.Object)
     */
    protected Map renderSuccess() {
        return renderSuccess(null, null);
    }

    /**
     * @param obj
     * @return Map
     * @description default object`s key is 'KEY'
     * @see BaseController#renderSuccess(java.lang.String, java.lang.Object)
     */
    protected Map renderSuccess(Object obj) {
        return renderSuccess(CustomFinalConstant.RET_KEY, obj);
    }


    /**
     * @param retKey return data key
     * @param obj    return data object
     * @return map
     * @description this method is render success status and data signal information
     */
    protected Map renderSuccess(String retKey, Object obj) {
        Map<String, Object> map = Maps.newHashMap();
        map.put(retKey, obj);
        map.put(CustomFinalConstant.STATUS_KEY, CustomFinalConstant.SUCCESS_CODE);
        if (logger.isErrorEnabled()) {
            logger.debug("success");
        }
        return map;
    }

}
