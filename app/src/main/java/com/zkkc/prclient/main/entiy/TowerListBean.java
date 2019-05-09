package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class TowerListBean {

    /**
     * code : 200
     * msg : 获取成功
     * count : 2
     * pageNum : 1
     * data : [{"tid":"723086f8af06496a92af85c250898f91","towerNum":"12","mediaId":"ce027745baa94ffb866f38b701204b49","videoData":null,"photoData":null},{"tid":"8f6f01bf4e81480da5139c2895fbba75","towerNum":"13","mediaId":"ce027745baa94ffb866f38b701204b49","videoData":null,"photoData":null}]
     */

    private int code;
    private String msg;
    private int count;
    private int pageNum;
    private List<DataBean> data;

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
        this.code = code;
    }

    public String getMsg() {
        return msg;
    }

    public void setMsg(String msg) {
        this.msg = msg;
    }

    public int getCount() {
        return count;
    }

    public void setCount(int count) {
        this.count = count;
    }

    public int getPageNum() {
        return pageNum;
    }

    public void setPageNum(int pageNum) {
        this.pageNum = pageNum;
    }

    public List<DataBean> getData() {
        return data;
    }

    public void setData(List<DataBean> data) {
        this.data = data;
    }

    public static class DataBean {
        /**
         * tid : 723086f8af06496a92af85c250898f91
         * towerNum : 12
         * mediaId : ce027745baa94ffb866f38b701204b49
         * videoData : null
         * photoData : null
         */

        private String tid;
        private String towerNum;
        private String mediaId;
        private Object videoData;
        private Object photoData;

        public String getTid() {
            return tid;
        }

        public void setTid(String tid) {
            this.tid = tid;
        }

        public String getTowerNum() {
            return towerNum;
        }

        public void setTowerNum(String towerNum) {
            this.towerNum = towerNum;
        }

        public String getMediaId() {
            return mediaId;
        }

        public void setMediaId(String mediaId) {
            this.mediaId = mediaId;
        }

        public Object getVideoData() {
            return videoData;
        }

        public void setVideoData(Object videoData) {
            this.videoData = videoData;
        }

        public Object getPhotoData() {
            return photoData;
        }

        public void setPhotoData(Object photoData) {
            this.photoData = photoData;
        }
    }
}
