package com.carndos.modules.demo.async;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;


@Service
public class AsyncTaskService {

    private static Logger logger = LoggerFactory.getLogger(AsyncTaskService.class);

    @Async
    public void executeAsyncTask(Integer i) {
        logger.info("executor Async Task: {}", i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        logger.info("executor Async Task plus " + (i + 1));
    }
}
