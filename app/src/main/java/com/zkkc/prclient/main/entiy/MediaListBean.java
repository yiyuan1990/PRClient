package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 */
public class MediaListBean {

    @Override
    public String toString() {
        return "MediaListBean{" +
                "downUrl='" + downUrl + '\'' +
                ", playUrl='" + playUrl + '\'' +
                ", list=" + list +
                '}';
    }

    /**
     * downUrl : http://172.16.1.152:8182/
     * list : [{"id":"8288b5d9e2a14007970b7829e4912c6a","baseUrl":"/cspid-ES-01-007_20190320/","deviceNum":"cspid-ES-01-007","lineNum":"123","date":"1213121312130","lineName":"12312","deviceName":"设备005"},{"id":"cd2440736d7c470d923434ebb5d9a57b","baseUrl":"/cspid-ES-01-007_20190320/","deviceNum":"cspid-ES-01-007","lineNum":"123","date":"1213121312131","lineName":"12312","deviceName":"设备005"}]
     * playUrl : http://172.16.1.152:180/
     */

    private String downUrl;
    private String playUrl;
    private List<ListBean> list;

    public String getDownUrl() {
        return downUrl;
    }

    public void setDownUrl(String downUrl) {
        this.downUrl = downUrl;
    }

    public String getPlayUrl() {
        return playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public List<ListBean> getList() {
        return list;
    }

    public void setList(List<ListBean> list) {
        this.list = list;
    }

    public static class ListBean {
        /**
         * id : 8288b5d9e2a14007970b7829e4912c6a
         * baseUrl : /cspid-ES-01-007_20190320/
         * deviceNum : cspid-ES-01-007
         * lineNum : 123
         * date : 1213121312130
         * lineName : 12312
         * deviceName : 设备005
         */
        private String playUrl;
        private String id;
        private int isSelected;
        private String baseUrl;
        private String deviceNum;
        private String lineNum;
        private String date;
        private String lineName;
        private String deviceName;

        public String getPlayUrl() {
            return playUrl;
        }

        public void setPlayUrl(String playUrl) {
            this.playUrl = playUrl;
        }

        public int getIsSelected() {
            return isSelected;
        }

        public void setIsSelected(int isSelected) {
            this.isSelected = isSelected;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getBaseUrl() {
            return baseUrl;
        }

        public void setBaseUrl(String baseUrl) {
            this.baseUrl = baseUrl;
        }

        public String getDeviceNum() {
            return deviceNum;
        }

        public void setDeviceNum(String deviceNum) {
            this.deviceNum = deviceNum;
        }

        public String getLineNum() {
            return lineNum;
        }

        public void setLineNum(String lineNum) {
            this.lineNum = lineNum;
        }

        public String getDate() {
            return date;
        }

        public void setDate(String date) {
            this.date = date;
        }

        public String getLineName() {
            return lineName;
        }

        public void setLineName(String lineName) {
            this.lineName = lineName;
        }

        public String getDeviceName() {
            return deviceName;
        }

        public void setDeviceName(String deviceName) {
            this.deviceName = deviceName;
        }
    }
}
