package com.zkkc.prclient.login.entiy;

/**
 * Created by ShiJunRan on 2019/5/5
 */
public class LoginBean {
    private String accessToken;
    private String expireTime;

    public LoginBean() {
    }

    public String getAccessToken() {
        return accessToken;
    }

    public void setAccessToken(String accessToken) {
        this.accessToken = accessToken;
    }

    public String getExpireTime() {
        return expireTime;
    }

    public void setExpireTime(String expireTime) {
        this.expireTime = expireTime;
    }

    @Override
    public String toString() {
        return "LoginBean{" +
                "accessToken='" + accessToken + '\'' +
                ", expireTime='" + expireTime + '\'' +
                '}';
    }
}
