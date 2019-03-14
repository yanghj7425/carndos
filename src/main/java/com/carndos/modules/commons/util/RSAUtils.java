package com.carndos.modules.commons.util;


import org.springframework.util.Base64Utils;

import javax.crypto.Cipher;
import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.security.*;
import java.security.interfaces.RSAPrivateKey;
import java.security.interfaces.RSAPublicKey;
import java.security.spec.PKCS8EncodedKeySpec;
import java.security.spec.X509EncodedKeySpec;
import java.util.HashMap;
import java.util.Map;

/**
 * <p>
 * RSA公钥/私钥/签名工具包
 * </p>
 * <p>
 * 罗纳德·李维斯特（Ron [R]ivest）、阿迪·萨莫尔（Adi [S]hamir）和伦纳德·阿德曼（Leonard [A]dleman）
 * </p>
 * <p>
 * 字符串格式的密钥在未在特殊说明情况下都为BASE64编码格式<br/>
 * 由于非对称加密速度极其缓慢，一般文件不使用它来加密而是使用对称加密，<br/>
 * 非对称加密算法可以用来对对称加密的密钥加密，这样保证密钥的安全也就保证了数据的安全
 * </p>
 *
 * @author IceWee
 * @version 1.0
 * @date 2012-4-26
 */
public class RSAUtils {

    private RSAUtils(){

    }

    /**
     * 加密算法RSA
     */
    private static final String KEY_ALGORITHM = "RSA";

    /**
     * 签名算法
     */
    private static final String SIGNATURE_ALGORITHM = "MD5withRSA";

    /**
     * 获取公钥的key
     */
    private static final String PUBLIC_KEY = "RSAPublicKey";

    /**
     * 获取私钥的key
     */
    private static final String PRIVATE_KEY = "RSAPrivateKey";

    /**
     * RSA最大加密明文大小
     */
    private static final int MAX_ENCRYPT_BLOCK = 117;

    /**
     * RSA最大解密密文大小
     */
    private static final int MAX_DECRYPT_BLOCK = 128;

    /**
     * <p>
     * 生成密钥对(公钥和私钥)
     * </p>
     *
     * @return  Map<String, Object> 公钥和私钥的Map
     *
     * @throws Exception 未知异常
     */
    public static Map<String, Object> genKeyPair() throws Exception {
        KeyPairGenerator keyPairGen = KeyPairGenerator.getInstance(KEY_ALGORITHM);
        keyPairGen.initialize(2048);
        KeyPair keyPair = keyPairGen.generateKeyPair();
        RSAPublicKey publicKey = (RSAPublicKey) keyPair.getPublic();
        RSAPrivateKey privateKey = (RSAPrivateKey) keyPair.getPrivate();
        Map<String, Object> keyMap = new HashMap<>(2);
        keyMap.put(PUBLIC_KEY, publicKey);
        keyMap.put(PRIVATE_KEY, privateKey);
        return keyMap;
    }

    /**
     * <p>
     * 用私钥对信息生成数字签名
     * </p>
     *
     * @param signCodeBuffer   已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return String
     *
     * @throws Exception 未知异常
     */
    public static String sign(byte[] signCodeBuffer, String privateKey) throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PrivateKey privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initSign(privateK);
        signature.update(signCodeBuffer);
        return Base64Utils.encodeToString(signature.sign());
    }

    /**
     * <p>
     * 校验数字签名
     * </p>
     *
     * @param verifyCodeBuffer  已加密数据
     * @param publicKey 公钥(BASE64编码)
     * @param sign      数字签名
     *
     * @return boolean
     *
     * @throws Exception  未知异常
     */
    public static boolean verify(byte[] verifyCodeBuffer, String publicKey, String sign)
            throws Exception {

        byte[] keyBytes = Base64Utils.decodeFromString(publicKey);
        X509EncodedKeySpec keySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        PublicKey publicK = keyFactory.generatePublic(keySpec);

        Signature signature = Signature.getInstance(SIGNATURE_ALGORITHM);
        signature.initVerify(publicK);
        signature.update(verifyCodeBuffer);
        return signature.verify(Base64Utils.decodeFromString(sign));
    }

    /**
     * <P>
     * 私钥解密
     * </p>
     *
     * @param decodeBuffer   已加密数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return byte[]
     *
     * @throws Exception 未知异常
     */
    public static byte[] decryptByPrivateKey(byte[] decodeBuffer, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);

        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, privateK);
        // 对数据分段加密
        return encodeGroup(decodeBuffer, cipher, MAX_DECRYPT_BLOCK);
    }

    /**
     * <p>
     * 公钥解密
     * </p>
     *
     * @param decodeBuffer  已加密数据
     * @param publicKey 公钥(BASE64编码)
     *
     * @return byte[]
     *
     * @throws Exception 未知异常
     */
    public static byte[] decryptByPublicKey(byte[] decodeBuffer, String publicKey)
            throws Exception {

        byte[] keyBytes = Base64Utils.decodeFromString(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.DECRYPT_MODE, publicK);

        return encodeGroup(decodeBuffer, cipher, MAX_DECRYPT_BLOCK);
    }

    /**
     * <p>
     * 公钥加密
     * </p>
     *
     * @param encodeBuffer  源数据
     * @param publicKey 公钥(BASE64编码)
     *
     * @return byte[]
     *
     * @throws Exception 未知异常
     */
    public static byte[] encryptByPublicKey(byte[] encodeBuffer, String publicKey)
            throws Exception {

        byte[] keyBytes = Base64Utils.decodeFromString(publicKey);
        X509EncodedKeySpec x509KeySpec = new X509EncodedKeySpec(keyBytes);

        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key publicK = keyFactory.generatePublic(x509KeySpec);

        // 对数据加密
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, publicK);


        // 对数据分段加密
        return encodeGroup(encodeBuffer, cipher, MAX_ENCRYPT_BLOCK);
    }

    /**
     * <p>
     * 私钥加密
     * </p>
     *
     * @param encodeBuffer   源数据
     * @param privateKey 私钥(BASE64编码)
     *
     * @return byte[]
     *
     * @throws Exception  未知异常
     */
    public static byte[] encryptByPrivateKey(byte[] encodeBuffer, String privateKey)
            throws Exception {
        byte[] keyBytes = Base64Utils.decodeFromString(privateKey);
        PKCS8EncodedKeySpec pkcs8KeySpec = new PKCS8EncodedKeySpec(keyBytes);
        KeyFactory keyFactory = KeyFactory.getInstance(KEY_ALGORITHM);
        Key privateK = keyFactory.generatePrivate(pkcs8KeySpec);
        Cipher cipher = Cipher.getInstance(keyFactory.getAlgorithm());
        cipher.init(Cipher.ENCRYPT_MODE, privateK);

        return encodeGroup(encodeBuffer, cipher, MAX_ENCRYPT_BLOCK);
    }

    /**
     *
     * @param encodeBuffer 加密缓冲区
     * @param cipher  Cipher类为加密和解密提供密码功能
     * @param maxBlock 秘钥长度
     * @return byte[]
     * @throws Exception 未知异常
     */
    private static byte[] encodeGroup(byte[] encodeBuffer, Cipher cipher, int maxBlock) throws Exception {
        ByteArrayOutputStream out = new ByteArrayOutputStream();
        byte[] cache;
        int i = 0;
        int offSet = 0;
        int encodeBufferSize = encodeBuffer.length;

        while (encodeBufferSize - offSet > 0) {
            if (encodeBufferSize - offSet > maxBlock) {
                cache = cipher.doFinal(encodeBuffer, offSet, maxBlock);
            } else {
                cache = cipher.doFinal(encodeBuffer, offSet, encodeBufferSize - offSet);
            }
            out.write(cache, 0, cache.length);
            i++;
            offSet = i * maxBlock;
        }

        byte[] byteArray = out.toByteArray();
        try {
            out.close();
        } catch (IOException e) {
            throw new IllegalStateException(e.getMessage());
        }
        return byteArray;
    }


    /**
     * <p>
     * 获取私钥
     * </p>
     *
     * @param keyMap 密钥对
     *
     * @return String
     *
     */
    public static String getPrivateKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PRIVATE_KEY);
        return Base64Utils.encodeToString(key.getEncoded());
    }

    /**
     * <p>
     * 获取公钥
     * </p>
     *
     * @param keyMap 密钥对
     *
     * @return String
     *
     */
    public static String getPublicKey(Map<String, Object> keyMap) {
        Key key = (Key) keyMap.get(PUBLIC_KEY);
        return Base64Utils.encodeToString(key.getEncoded());
    }


    class RSAUtilsException extends Exception{

    }

}