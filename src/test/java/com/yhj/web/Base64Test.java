package com.yhj.web;


import com.yhj.web.entity.res.Resource;
import com.yhj.web.util.RSAUtils;
import org.junit.Test;

import java.util.Map;

public class Base64Test {


    @Test
    public void base64Test() throws Exception {
        Resource r = new Resource();
        String resName = "苏打粉多萨法士大夫撒撒旦法但是撒旦法的分数撒旦";
        Map<String, Object> keyMap = RSAUtils.genKeyPair();

        String publicKey = RSAUtils.getPublicKey(keyMap);
        String privateKey = RSAUtils.getPrivateKey(keyMap);

        System.out.println("publicKey : " + publicKey);

        System.out.println("privateKey : " + privateKey);



        String encodedStr = RSAUtils.en(resName, privateKey);
        System.out.println(encodedStr);

        r.setResName(encodedStr);

        String decodeStr = RSAUtils(encodedStr, publicKey);



        System.out.println(decodeStr);
    }

}
