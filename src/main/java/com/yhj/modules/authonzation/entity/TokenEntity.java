package com.yhj.modules.authonzation.entity;

public class TokenEntity {
    //用户id
    private long userId;

    //随机生成的uuid
    private String token;


    public TokenEntity(long userId, String token) {
        this.userId = userId;
        this.token = token;
    }


    public long getUserId() {
        return userId;
    }

    public void setUserId(long userId) {
        this.userId = userId;
    }

    public String getToken() {
        return token;
    }

    public void setToken(String token) {
        this.token = token;
    }

    @Override
    public String toString() {
        return "TokenEntity{" +
                "userId=" + userId +
                ", token='" + token + '\'' +
                '}';
    }
}
