package com.yhj.modules.commons.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.ui.Model;

public class BaseController {
    protected Logger logger = LoggerFactory.getLogger(getClass());


    protected Model renderSuccess(Model model, String msg) {
        model.addAttribute("msg", msg);
        model.addAttribute("code", 20000);
        return model;
    }

}
