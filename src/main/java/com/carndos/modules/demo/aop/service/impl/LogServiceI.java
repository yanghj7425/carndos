package com.carndos.modules.demo.aop.service.impl;

import com.carndos.modules.demo.aop.annotation.Action;
import com.carndos.modules.demo.aop.service.LogService;
import org.springframework.stereotype.Service;

@Service
public class LogServiceI implements LogService {
    @Override
    @Action(name = "LogService")
    public Object queryLog(long... id) {
        return id;
    }
}
