package com.zkkc.prclient.main.entiy;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class PicShowBean {
    private String picUrl;
    private String picName;
    private boolean isErr;

    @Override
    public String toString() {
        return "PicShowBean{" +
                "picUrl='" + picUrl + '\'' +
                ", picName='" + picName + '\'' +
                ", isErr=" + isErr +
                '}';
    }

    public boolean isErr() {
        return isErr;
    }

    public void setErr(boolean err) {
        isErr = err;
    }

    public String getPicUrl() {
        return picUrl;
    }

    public void setPicUrl(String picUrl) {
        this.picUrl = picUrl;
    }

    public String getPicName() {
        return picName;
    }

    public void setPicName(String picName) {
        this.picName = picName;
    }
}
