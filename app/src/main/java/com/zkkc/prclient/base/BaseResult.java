package com.zkkc.prclient.base;

/**
 * Created by ShiJunRan on 2019/5/5
 */
public class BaseResult<T> {

    /**
     * code : 200
     * flag : true
     * msg : 登录成功
     * data : {"accessToken":"1","expireTime":1800000}
     */

    private int code;
    private boolean flag;
    private String msg;
    private T data;

    public BaseResult() {
    }

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public boolean isFlag() {
        return flag;
    }

    public void setFlag(boolean flag) {
        this.flag = flag;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public T getData() {
        return data;
    }

    public void setData(T data) {
        this.data = data;
    }
}
