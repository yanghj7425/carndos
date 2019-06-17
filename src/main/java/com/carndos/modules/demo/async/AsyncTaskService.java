package com.carndos.modules.demo.async;

import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

@Service
public class AsyncTaskService {

    @Async
    public void executeAsyncTask(Integer i) {
        System.out.println("executor Async Task " + i);
    }

    @Async
    public void executeAsyncTaskPlus(Integer i) {
        System.out.println("executor Async Task plus " + (i + 1));
    }
}
