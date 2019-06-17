package com.async;

import com.carndos.config.sync.TaskExecutorConfig;
import com.carndos.modules.demo.async.AsyncTaskService;
import org.springframework.context.annotation.AnnotationConfigApplicationContext;

public class AsyncTaskTest {
    public static void main(String[] args) {
        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
        AsyncTaskService taskService = (AsyncTaskService) context.getBean("asyncTaskService");
        for (int i = 0; i < 20; i++) {
            taskService.executeAsyncTask(i);
            taskService.executeAsyncTaskPlus(i);
        }
    }
}
