package com.carndos;

import com.carndos.modules.demo.aop.service.LogService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarndosApplicationTests {

    @Autowired
    private LogService logService;


    @Test
    public void contextLoads() {


        logService.queryLog(3L,5L);

        System.out.println("id : is 0");
//        logService.queryLog(0);

    }


}

