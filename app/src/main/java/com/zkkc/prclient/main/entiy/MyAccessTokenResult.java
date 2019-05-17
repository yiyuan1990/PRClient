package com.zkkc.prclient.main.entiy;

/**
 * Created by ShiJunRan on 2019/5/17
 */
public class MyAccessTokenResult {

    /**
     * msg : 操作成功!
     * code : 200
     * data : {"expireTime":1553579894266,"AppKey":"4003d2c1caec4aaa8d63e91cfce96a69","accessToken":"at.0k1vlukc9bilorb792q8bb3n4g61tojg-9nxjhmi8l6-1m8tlk2-yirty81hl"}
     */

    private String msg;
    private String code;
    private DataBean data;

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public String getCode() {
        return code;
    }

    public void setCode(String code) {
        this.code = code;
    }

    public DataBean getData() {
        return data;
    }

    public void setData(DataBean data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * expireTime : 1553579894266
         * AppKey : 4003d2c1caec4aaa8d63e91cfce96a69
         * accessToken : at.0k1vlukc9bilorb792q8bb3n4g61tojg-9nxjhmi8l6-1m8tlk2-yirty81hl
         */

        private long expireTime;
        private String AppKey;
        private String accessToken;
        private String url;

        public String getUrl() {
            return url;
        }

        public void setUrl(String url) {
            this.url = url;
        }

        public long getExpireTime() {
            return expireTime;
        }

        public void setExpireTime(long expireTime) {
            this.expireTime = expireTime;
        }

        public String getAppKey() {
            return AppKey;
        }

        public void setAppKey(String AppKey) {
            this.AppKey = AppKey;
        }

        public String getAccessToken() {
            return accessToken;
        }

        public void setAccessToken(String accessToken) {
            this.accessToken = accessToken;
        }
    }
}
