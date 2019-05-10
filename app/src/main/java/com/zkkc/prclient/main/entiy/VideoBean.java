package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/10
 */
public class VideoBean {

    /**
     * tid : 3cabd2dd40724850a984d4638ff8e60b
     * towerNum : 12
     * mediaId : cd2440736d7c470d923434ebb5d9a57b
     * videoData : {"path":"12/video","fileName":["radar_1000-1001-008-SXXX-171229_12_0_20190320_12.mp4","radar_1000-1001-008-SXXX-171229_12_0_20190320_13.mp4","radar_1000-1001-008-SXXX-171229_12_0_20190320_14.mp4"]}
     * photoData : null
     */

    private String tid;
    private String towerNum;
    private String mediaId;
    private VideoDataBean videoData;

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

    public VideoDataBean getVideoData() {
        return videoData;
    }

    public void setVideoData(VideoDataBean videoData) {
        this.videoData = videoData;
    }

    public static class VideoDataBean {
        /**
         * path : 12/video
         * fileName : ["radar_1000-1001-008-SXXX-171229_12_0_20190320_12.mp4","radar_1000-1001-008-SXXX-171229_12_0_20190320_13.mp4","radar_1000-1001-008-SXXX-171229_12_0_20190320_14.mp4"]
         */

        private String path;
        private List<String> fileName;

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
