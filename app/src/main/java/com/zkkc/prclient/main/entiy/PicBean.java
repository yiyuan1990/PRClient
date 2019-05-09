package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class PicBean {

    /**
     * tid : 3cabd2dd40724850a984d4638ff8e60b
     * towerNum : 12
     * mediaId : cd2440736d7c470d923434ebb5d9a57b
     * videoData : null
     * photoData : {"path":"12/photo","fileName":["camera_1000-1001-008-SXXX-171229_12_0_20190320_12.jpg","camera_1000-1001-008-SXXX-171229_12_0_20190320_13.jpg","camera_1000-1001-008-SXXX-171229_12_0_20190320_14.jpg"]}
     */

    private String tid;
    private String towerNum;
    private String mediaId;
    private Object videoData;
    private PhotoDataBean photoData;

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

    public PhotoDataBean getPhotoData() {
        return photoData;
    }

    public void setPhotoData(PhotoDataBean photoData) {
        this.photoData = photoData;
    }

    public static class PhotoDataBean {
        /**
         * path : 12/photo
         * fileName : ["camera_1000-1001-008-SXXX-171229_12_0_20190320_12.jpg","camera_1000-1001-008-SXXX-171229_12_0_20190320_13.jpg","camera_1000-1001-008-SXXX-171229_12_0_20190320_14.jpg"]
         */
        private String mUrl;
        private String path;
        private List<String> fileName;

        public String getmUrl() {
            return mUrl;
        }

        public void setmUrl(String mUrl) {
            this.mUrl = mUrl;
        }

        public String getPath() {
            return path;
        }

        public void setPath(String path) {
            this.path = path;
        }

        public List<String> getFileName() {
            return fileName;
        }

        public void setFileName(List<String> fileName) {
            this.fileName = fileName;
        }
    }
}
