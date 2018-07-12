package com.yhj.web;

import com.yhj.config.core.RootConfig;
import com.yhj.web.controller.security.CustomSuccessHandler;
import com.yhj.web.dao.res.CustomAccessDecisionManager;
import com.yhj.web.dao.res.JdbcRequestMapBuilder;
import com.yhj.web.dao.res.ResRoleMapper;
import com.yhj.web.entity.res.ResRole;
import com.yhj.web.entity.res.Resource;
import com.yhj.web.service.res.ResRoleService;
import com.yhj.web.service.res.impl.ResourceServiceI;
import com.yhj.web.util.RSAUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.context.ContextConfiguration;
import org.springframework.test.context.junit4.SpringJUnit4ClassRunner;
import org.springframework.util.Base64Utils;

import java.security.PrivateKey;
import java.util.List;
import java.util.Map;

@RunWith(SpringJUnit4ClassRunner.class)
@ContextConfiguration(classes = RootConfig.class)
public class DaoTest {

    @Autowired
    CustomSuccessHandler customSuccessHandler;


    @Autowired
    JdbcRequestMapBuilder builder;


    @Autowired
    CustomAccessDecisionManager customAccessDecisionManager;


    @Autowired
    ResRoleMapper resRoleMapper;


    @Autowired
    ResRoleService resRoleService;

    @Autowired
    ResourceServiceI resourceService;


    @Test
    public void testResourceService() throws Exception {

//        Resource r = new Resource();
//        String resName = "苏打粉多萨法士大夫撒撒旦法但是撒旦法的分数撒旦";
//        Map<String, Object> keyMap = RSAUtils.genKeyPair();
//
//        String publicKey = RSAUtils.getPublicKey(keyMap);
//        String privateKey = RSAUtils.getPrivateKey(keyMap);
//        String encodedStr = RSAUtils.decryptByPublicKey(resName.getBytes(), publicKey);
//        System.out.println(encodedStr);
//        r.setResName(encodedStr);
//
//
//        System.out.println(r);

//        resourceService.insert(r);
//
//        List<Resource> list = resourceService.selectAll();
//
//
//        for (Resource res : list) {
//            System.out.println(res);
//            if (res.getId() == 1) {
//                String name = res.getResName();
//
//                System.out.println(name);
//
//                byte[] bs = Base64Utils.decodeFromString(name);
//                String result = RSAUtil.RSADecode(privateKey, bs);
//                System.out.println("__________ " + result);
//            }
//        }


    }

    public void testResRoleService() {
        List<ResRole> list = resRoleService.selectAll();
        for (ResRole resRole : list) {
            System.out.println(resRole);
        }


    }


}
