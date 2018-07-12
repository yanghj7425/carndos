package com.yhj.web.util;

import sun.misc.BASE64Decoder;
import sun.misc.BASE64Encoder;

import java.io.IOException;

/**
 * @description Base64 工具类
 */
public class JDKBase64Utils {

    private static BASE64Encoder encoder = new BASE64Encoder();

    private static BASE64Decoder decoder = new BASE64Decoder();


    /**
     * @param bytes
     * @return String 编码后的 字符串
     * @description byte 数组编码为字符串
     */
    public static String encod(byte[] bytes) {

        return encoder.encode(bytes);
    }


    /**
     * @param str
     * @return
     * @description base64编码字符解码为 byte数组
     */
    public byte[] decod(String str) {
        try {
            return decoder.decodeBuffer(str);
        } catch (IOException e) {
            throw new IllegalArgumentException(e.getMessage());
        }
    }


}
