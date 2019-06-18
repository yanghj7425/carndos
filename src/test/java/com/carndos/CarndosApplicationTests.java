package com.carndos;

import com.carndos.modules.commons.pojo.PageParam;
import com.carndos.modules.demo.aop.service.LogService;
import com.carndos.modules.user.entity.SysUser;
import com.carndos.modules.user.service.UserService;
import com.github.pagehelper.PageInfo;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import javax.servlet.http.HttpServletRequest;

@RunWith(SpringRunner.class)
@SpringBootTest
public class CarndosApplicationTests {

    @Autowired
    private LogService logService;


    @Test
    public void contextLoads() {


        logService.queryLog(3,5);

        System.out.println("id : is 0");
//        logService.queryLog(0);

    }


}

