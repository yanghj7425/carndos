package com.yhj.modules.res;

import com.yhj.config.core.RootConfig;
import com.yhj.modules.res.pojo.ResNode;
import com.yhj.modules.res.service.ResourceService;
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
    private ResourceService resourceService;


    @Test
    public void showTree() {
        List<ResNode> nodeTree = resourceService.queryResourceTree();
        System.out.println();
        for (ResNode resNode : nodeTree) {
            System.out.println(resNode);
            System.out.println("*****************************************");
        }

//        System.out.println(JSON.toJSON(nodeTree));
    }
}


