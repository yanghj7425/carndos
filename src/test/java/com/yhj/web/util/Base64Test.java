package com.yhj.web.util;


import com.yhj.web.entity.res.Resource;
import com.yhj.web.util.RSAUtils;
import org.junit.Test;
import org.springframework.util.Base64Utils;

import java.util.Map;

public class Base64Test {




    public void base64Test() throws Exception {


        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        String privateKey = RSAUtils.getPrivateKey(keyMap);
        String publicKey = RSAUtils.getPublicKey(keyMap);
        Resource r = new Resource();
        String resName = "公钥加密——私钥解密";


        System.out.println(privateKey);
        byte[] encodedStr = RSAUtils.encryptByPublicKey(resName.getBytes(), publicKey);


        String str = Base64Utils.encodeToString(encodedStr);

        System.out.println(str);

        byte[] bs = Base64Utils.decodeFromString(str);

        byte[] decodeStr = RSAUtils.decryptByPrivateKey(bs, privateKey);


        System.out.println(new String(decodeStr));
    }


    public void testSign() throws Exception {

        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        String privateKey = RSAUtils.getPrivateKey(keyMap);
        String publicKey = RSAUtils.getPublicKey(keyMap);


        String encodeStr = "私钥加密---公钥解密";


        byte[] encodeBuffer = encodeStr.getBytes();

        byte[] encodedData = RSAUtils.encryptByPrivateKey(encodeBuffer, privateKey);

        System.out.println("target encode privateKey show in base64: \t" + Base64Utils.encodeToString(encodedData));
        System.out.println("target encode show privateKey in String: \t" + new String(encodedData));

        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);

        System.out.println("target decode publicKey show in base64: \t" + Base64Utils.encodeToString(decodedData));

        System.out.println("target decode publicKey show in String: \t" + new String(decodedData));


        System.err.println("私钥签名——公钥验证签名");
        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名:\t" + sign);
        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("验证结果:\t" + status);
    }

    @Test
    public void testHttpSign() throws Exception {

        Map<String, Object> keyMap = RSAUtils.genKeyPair();
        String privateKey = RSAUtils.getPrivateKey(keyMap);
        String publicKey = RSAUtils.getPublicKey(keyMap);


        String param = "id=1&name=张三";
        byte[] encodedData = RSAUtils.encryptByPrivateKey(param.getBytes(), privateKey);
        System.out.println("加密后：" + encodedData);

        byte[] decodedData = RSAUtils.decryptByPublicKey(encodedData, publicKey);
        System.out.println("解密后：" + new String(decodedData));

        String sign = RSAUtils.sign(encodedData, privateKey);
        System.err.println("签名：" + sign);

        boolean status = RSAUtils.verify(encodedData, publicKey, sign);
        System.err.println("签名验证结果：" + status);
    }


}
