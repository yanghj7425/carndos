package com.async;

import org.junit.Test;

import java.util.concurrent.CountDownLatch;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
import java.util.concurrent.TimeUnit;

public class AsyncTaskTest {


    @Test
    public void asyncTest() throws InterruptedException {
//        AnnotationConfigApplicationContext context = new AnnotationConfigApplicationContext(TaskExecutorConfig.class);
//        AsyncTaskService taskService = (AsyncTaskService) context.getBean("asyncTaskService");
//        for (int i = 0; i < 20; i++) {
//            taskService.executeAsyncTask(i);
//            taskService.executeAsyncTaskPlus(i);
//        }


        CountDownLatch countDownLatch = new CountDownLatch(2);

        ExecutorService executorService = Executors.newFixedThreadPool(2);

        executorService.submit(() -> {
            try {
                Thread.sleep(5000);
            } catch (InterruptedException e) {
                e.printStackTrace();
            }
            System.out.println("Thread 1");
            countDownLatch.countDown();
        });

        executorService.submit(() -> {
            System.out.println("Thread 2");
            countDownLatch.countDown();

        });


        executorService.shutdown();
        if (!executorService.isShutdown()) {
            executorService.awaitTermination(10, TimeUnit.SECONDS);
            executorService.shutdownNow();
        }


        System.out.println("execute finish");


    }
}
