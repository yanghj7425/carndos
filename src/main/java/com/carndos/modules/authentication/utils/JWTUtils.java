package com.carndos.modules.authentication.utils;

import com.auth0.jwt.JWTSigner;
import com.auth0.jwt.JWTVerifier;
import com.auth0.jwt.internal.com.fasterxml.jackson.databind.ObjectMapper;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.util.HashMap;
import java.util.Map;

@Component
public class JWTUtils {

    @Value("${jwt.secret}")
    private String secret;

    @Value("${jwt.expiration}")
    private double seconds;

    private static final String EXP = "exp";

    private static final String PAYLOAD = "payload";


    //加密，传入一个对象和有效期
    public <T> String encoder(T object) {
        try {
            long maxAge = secondsToMillis(seconds);
            final JWTSigner signer = new JWTSigner(secret);
            final Map<String, Object> claims = new HashMap<>();
            ObjectMapper mapper = new ObjectMapper();
            String jsonString = mapper.writeValueAsString(object);
            claims.put(PAYLOAD, jsonString);
            claims.put(EXP, System.currentTimeMillis() + maxAge);
            return signer.sign(claims);
        } catch (Exception e) {
            return null;
        }
    }


    //解密，传入一个加密后的token字符串和解密后的类型
    public <T> T decoder(String jwt, Class<T> classT) {
        final JWTVerifier verifier = new JWTVerifier(secret);
        try {
            final Map<String, Object> claims = verifier.verify(jwt);
            if (claims.containsKey(EXP) && claims.containsKey(PAYLOAD)) {
                long exp = (Long) claims.get(EXP);
                long currentTimeMillis = System.currentTimeMillis();
                if (exp > currentTimeMillis) {
                    String json = (String) claims.get(PAYLOAD);
                    ObjectMapper objectMapper = new ObjectMapper();
                    return objectMapper.readValue(json, classT);
                }
            }
            return null;
        } catch (Exception e) {
            return null;
        }
    }


    private static long secondsToMillis(double seconds) {
        return (long) (seconds * 60 * 1000L);
    }
}
