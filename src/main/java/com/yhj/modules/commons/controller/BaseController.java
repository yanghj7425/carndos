package com.yhj.modules.commons.controller;

import com.yhj.modules.commons.components.CustomConstantInterface;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.Map;

public class BaseController implements CustomConstantInterface {
    protected Logger logger = LoggerFactory.getLogger(getClass());

    protected Map renderSuccess(Map<String, Object> map) {
        map.put(STATUS_KEY, SUCCESS_CODE);
        return map;
    }

}
