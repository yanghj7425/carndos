package com.carndos.modules.res;

import com.alibaba.fastjson.JSON;
import com.carndos.config.core.RootConfig;
import com.carndos.modules.sys.res.service.ResRoleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;

import java.util.List;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)

public class ResourceTest {
    @Autowired
    private ResRoleService resRoleService;

    @Test
    public void showTree() {
        List<Integer> lists = resRoleService.queryResAssignedRoleIds("2");
        for (Integer sysResRole : lists) {
            System.out.println(sysResRole);
        }
        System.out.println("============================");


        System.out.println(JSON.toJSON(lists));
    }
}


