package com.zkkc.prclient.main.entiy;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/6
 * 线路和设备列表
 */
public class LineDeviceListBean {
    /**
     * flag : true
     * msg : 请求成功
     * code : 200
     * data : {"createBy":null,"createDate":1513787646000,"updateBy":"acfc0e9232f54732a5d9ffe9071bf572","updateDate":1555559393000,"id":"acfc0e9232f54732a5d9ffe9071bf572","username":"admin","password":"a66abb5684c45962d887564f08346e8d","age":24,"email":"","photo":"2d4c37c3-c106-4288-9c0d-e7fe1b8adc72.jpeg","realName":"管理员","delFlag":0,"roleList":null,"role":null,"lineList":[{"createBy":null,"createDate":1513787646000,"updateBy":"acfc0e9232f54732a5d9ffe9071bf572","updateDate":1555559393000,"id":"b2c06f6d70654b8bb09c3115f950fe90","lineName":"12312","lineNum":"123","startPoint":"232","endPoint":"142","deviceCount":null,"pipeCount":null,"towerList":[{"charge":0,"gps":[113.248587,23.124097],"tower":"长寿路"},{"charge":0,"gps":[113.252777,23.131612],"tower":"陈家祠"},{"charge":0,"gps":[113.262402,23.131304],"tower":"西门口"},{"charge":0,"gps":[113.270642,23.131645],"tower":"公园前"},{"charge":0,"gps":[113.281777,23.132934],"tower":"农讲所"},{"charge":0,"gps":[113.292185,23.132935],"tower":"烈士陵园"},{"charge":0,"gps":[113.302223,23.130338],"tower":"东山口"},{"charge":0,"gps":[113.314681,23.133238],"tower":"杨箕"},{"charge":0,"gps":[113.328028,23.136921],"tower":"体育西路"},{"charge":0,"gps":[113.334941,23.140748],"tower":"体育中心"},{"charge":0,"gps":[113.331062,23.155823],"tower":"广州东站"},{"charge":0,"gps":[113.276624,22.995217],"tower":"广州南站"}],"deviceList":[{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"26fee0c65a5e40edaf5b67f23f3bcee0","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备005","deviceNum":"cspid-ES-01-007","principal":"2","startPoint":"232","endPoint":"142","electric":"75","state":null,"config":"0,1,2","lat":"113.248587","lng":"23.132935","cameraNum":"678","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"39432b24d2d34508b7806409d6571b8d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备009","deviceNum":"434","principal":"2313","startPoint":"232","endPoint":"142","electric":"90","state":null,"config":"0","lat":"113.252777","lng":"23.131612","cameraNum":"456","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"432aa3ec309c49198a2bafe1a3c4f474","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备名称","deviceNum":"1000-1001-008-SXXX-171229","principal":"12321442152","startPoint":"232","endPoint":"142","electric":"0","state":0,"config":"0","lat":"113.262402","lng":"23.131304","cameraNum":"C26409947","data":"{\n    \"serialNum\":\"1000-1001-008-SXXX-171229\",\n    \"lineNum\":\"1001\",\n    \"initialPoint\":\"12\",\n    \"endPoint\":\"45\",\n    \"totalTower\":30,\n    \"towerData\":[\n        {\n            \"tower\":\"12\",\n            \"charge\":0,\n            \"gps\":[121.11, 121.11,31],\n            \"point\":[\n                {\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                },{\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                }\n            ]\n        }\n    ]\n  }","lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"47fcd636036a413f896e5c3e15d0ef78","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备006","deviceNum":"1000-1001-008-SXXX-171230","principal":"2","startPoint":"232","endPoint":"142","electric":"40","state":null,"config":"0","lat":"113.270642","lng":"23.131645","cameraNum":"123","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"789072a51d604f1aaecc014b503ad1f1","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备003","deviceNum":"3","principal":"4","startPoint":"232","endPoint":"142","electric":"50","state":null,"config":"0","lat":"113.302223","lng":"23.130338","cameraNum":"dafd","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"a6ec0261e3df4236ae7354df42c29a6b","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备11","deviceNum":"123bdg","principal":"dadas","startPoint":"232","endPoint":"142","electric":"60","state":null,"config":"0","lat":"113.314681","lng":"23.133238","cameraNum":"zxcz","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"ae8dd9c1dc304163ac1b56b78acbb446","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备10","deviceNum":"12312","principal":"135412","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.328028","lng":"23.136921","cameraNum":"asda","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"b74b463d5d4341738850ce697b1a618d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备008","deviceNum":"123213","principal":"12321321","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.334941","lng":"23.140748","cameraNum":"1231","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null}],"kv":"110kv"}]}
     */

    private boolean flag;
    private String msg;
    private int code;
    private DataBean data;

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

    public int getCode() {
        return code;
    }

    public void setCode(int code) {
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
         * createBy : null
         * createDate : 1513787646000
         * updateBy : acfc0e9232f54732a5d9ffe9071bf572
         * updateDate : 1555559393000
         * id : acfc0e9232f54732a5d9ffe9071bf572
         * username : admin
         * password : a66abb5684c45962d887564f08346e8d
         * age : 24
         * email :
         * photo : 2d4c37c3-c106-4288-9c0d-e7fe1b8adc72.jpeg
         * realName : 管理员
         * delFlag : 0
         * roleList : null
         * role : null
         * lineList : [{"createBy":null,"createDate":1513787646000,"updateBy":"acfc0e9232f54732a5d9ffe9071bf572","updateDate":1555559393000,"id":"b2c06f6d70654b8bb09c3115f950fe90","lineName":"12312","lineNum":"123","startPoint":"232","endPoint":"142","deviceCount":null,"pipeCount":null,"towerList":[{"charge":0,"gps":[113.248587,23.124097],"tower":"长寿路"},{"charge":0,"gps":[113.252777,23.131612],"tower":"陈家祠"},{"charge":0,"gps":[113.262402,23.131304],"tower":"西门口"},{"charge":0,"gps":[113.270642,23.131645],"tower":"公园前"},{"charge":0,"gps":[113.281777,23.132934],"tower":"农讲所"},{"charge":0,"gps":[113.292185,23.132935],"tower":"烈士陵园"},{"charge":0,"gps":[113.302223,23.130338],"tower":"东山口"},{"charge":0,"gps":[113.314681,23.133238],"tower":"杨箕"},{"charge":0,"gps":[113.328028,23.136921],"tower":"体育西路"},{"charge":0,"gps":[113.334941,23.140748],"tower":"体育中心"},{"charge":0,"gps":[113.331062,23.155823],"tower":"广州东站"},{"charge":0,"gps":[113.276624,22.995217],"tower":"广州南站"}],"deviceList":[{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"26fee0c65a5e40edaf5b67f23f3bcee0","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备005","deviceNum":"cspid-ES-01-007","principal":"2","startPoint":"232","endPoint":"142","electric":"75","state":null,"config":"0,1,2","lat":"113.248587","lng":"23.132935","cameraNum":"678","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"39432b24d2d34508b7806409d6571b8d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备009","deviceNum":"434","principal":"2313","startPoint":"232","endPoint":"142","electric":"90","state":null,"config":"0","lat":"113.252777","lng":"23.131612","cameraNum":"456","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"432aa3ec309c49198a2bafe1a3c4f474","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备名称","deviceNum":"1000-1001-008-SXXX-171229","principal":"12321442152","startPoint":"232","endPoint":"142","electric":"0","state":0,"config":"0","lat":"113.262402","lng":"23.131304","cameraNum":"C26409947","data":"{\n    \"serialNum\":\"1000-1001-008-SXXX-171229\",\n    \"lineNum\":\"1001\",\n    \"initialPoint\":\"12\",\n    \"endPoint\":\"45\",\n    \"totalTower\":30,\n    \"towerData\":[\n        {\n            \"tower\":\"12\",\n            \"charge\":0,\n            \"gps\":[121.11, 121.11,31],\n            \"point\":[\n                {\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                },{\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                }\n            ]\n        }\n    ]\n  }","lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"47fcd636036a413f896e5c3e15d0ef78","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备006","deviceNum":"1000-1001-008-SXXX-171230","principal":"2","startPoint":"232","endPoint":"142","electric":"40","state":null,"config":"0","lat":"113.270642","lng":"23.131645","cameraNum":"123","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"789072a51d604f1aaecc014b503ad1f1","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备003","deviceNum":"3","principal":"4","startPoint":"232","endPoint":"142","electric":"50","state":null,"config":"0","lat":"113.302223","lng":"23.130338","cameraNum":"dafd","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"a6ec0261e3df4236ae7354df42c29a6b","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备11","deviceNum":"123bdg","principal":"dadas","startPoint":"232","endPoint":"142","electric":"60","state":null,"config":"0","lat":"113.314681","lng":"23.133238","cameraNum":"zxcz","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"ae8dd9c1dc304163ac1b56b78acbb446","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备10","deviceNum":"12312","principal":"135412","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.328028","lng":"23.136921","cameraNum":"asda","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"b74b463d5d4341738850ce697b1a618d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备008","deviceNum":"123213","principal":"12321321","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.334941","lng":"23.140748","cameraNum":"1231","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null}],"kv":"110kv"}]
         */


        private Object createBy;
        private long createDate;
        private String updateBy;
        private long updateDate;
        private String id;
        private String username;
        private String password;
        private int age;
        private String email;
        private String photo;
        private String realName;
        private int delFlag;
        private Object roleList;
        private Object role;
        private List<LineListBean> lineList;


        public Object getCreateBy() {
            return createBy;
        }

        public void setCreateBy(Object createBy) {
            this.createBy = createBy;
        }

        public long getCreateDate() {
            return createDate;
        }

        public void setCreateDate(long createDate) {
            this.createDate = createDate;
        }

        public String getUpdateBy() {
            return updateBy;
        }

        public void setUpdateBy(String updateBy) {
            this.updateBy = updateBy;
        }

        public long getUpdateDate() {
            return updateDate;
        }

        public void setUpdateDate(long updateDate) {
            this.updateDate = updateDate;
        }

        public String getId() {
            return id;
        }

        public void setId(String id) {
            this.id = id;
        }

        public String getUsername() {
            return username;
        }

        public void setUsername(String username) {
            this.username = username;
        }

        public String getPassword() {
            return password;
        }

        public void setPassword(String password) {
            this.password = password;
        }

        public int getAge() {
            return age;
        }

        public void setAge(int age) {
            this.age = age;
        }

        public String getEmail() {
            return email;
        }

        public void setEmail(String email) {
            this.email = email;
        }

        public String getPhoto() {
            return photo;
        }

        public void setPhoto(String photo) {
            this.photo = photo;
        }

        public String getRealName() {
            return realName;
        }

        public void setRealName(String realName) {
            this.realName = realName;
        }

        public int getDelFlag() {
            return delFlag;
        }

        public void setDelFlag(int delFlag) {
            this.delFlag = delFlag;
        }

        public Object getRoleList() {
            return roleList;
        }

        public void setRoleList(Object roleList) {
            this.roleList = roleList;
        }

        public Object getRole() {
            return role;
        }

        public void setRole(Object role) {
            this.role = role;
        }

        public List<LineListBean> getLineList() {
            return lineList;
        }

        public void setLineList(List<LineListBean> lineList) {
            this.lineList = lineList;
        }

        public static class LineListBean {
            /**
             * createBy : null
             * createDate : 1513787646000
             * updateBy : acfc0e9232f54732a5d9ffe9071bf572
             * updateDate : 1555559393000
             * id : b2c06f6d70654b8bb09c3115f950fe90
             * lineName : 12312
             * lineNum : 123
             * startPoint : 232
             * endPoint : 142
             * deviceCount : null
             * pipeCount : null
             * towerList : [{"charge":0,"gps":[113.248587,23.124097],"tower":"长寿路"},{"charge":0,"gps":[113.252777,23.131612],"tower":"陈家祠"},{"charge":0,"gps":[113.262402,23.131304],"tower":"西门口"},{"charge":0,"gps":[113.270642,23.131645],"tower":"公园前"},{"charge":0,"gps":[113.281777,23.132934],"tower":"农讲所"},{"charge":0,"gps":[113.292185,23.132935],"tower":"烈士陵园"},{"charge":0,"gps":[113.302223,23.130338],"tower":"东山口"},{"charge":0,"gps":[113.314681,23.133238],"tower":"杨箕"},{"charge":0,"gps":[113.328028,23.136921],"tower":"体育西路"},{"charge":0,"gps":[113.334941,23.140748],"tower":"体育中心"},{"charge":0,"gps":[113.331062,23.155823],"tower":"广州东站"},{"charge":0,"gps":[113.276624,22.995217],"tower":"广州南站"}]
             * deviceList : [{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"26fee0c65a5e40edaf5b67f23f3bcee0","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备005","deviceNum":"cspid-ES-01-007","principal":"2","startPoint":"232","endPoint":"142","electric":"75","state":null,"config":"0,1,2","lat":"113.248587","lng":"23.132935","cameraNum":"678","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"39432b24d2d34508b7806409d6571b8d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备009","deviceNum":"434","principal":"2313","startPoint":"232","endPoint":"142","electric":"90","state":null,"config":"0","lat":"113.252777","lng":"23.131612","cameraNum":"456","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"432aa3ec309c49198a2bafe1a3c4f474","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备名称","deviceNum":"1000-1001-008-SXXX-171229","principal":"12321442152","startPoint":"232","endPoint":"142","electric":"0","state":0,"config":"0","lat":"113.262402","lng":"23.131304","cameraNum":"C26409947","data":"{\n    \"serialNum\":\"1000-1001-008-SXXX-171229\",\n    \"lineNum\":\"1001\",\n    \"initialPoint\":\"12\",\n    \"endPoint\":\"45\",\n    \"totalTower\":30,\n    \"towerData\":[\n        {\n            \"tower\":\"12\",\n            \"charge\":0,\n            \"gps\":[121.11, 121.11,31],\n            \"point\":[\n                {\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                },{\n                    \"direction\":1,\n                    \"move\":3,\n                    \"pdz\":3,\n                    \"gps\":[121.11, 121.11, 0],\n                    \"snapShoot\":[\n                        {\n                            \"number\":1,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        },\n                        {\n                            \"number\":2,\n                            \"peripheral\":1,\n                            \"object\":1,\n                            \"ptz\":[21,32,5]\n                        }\n                    ]\n                }\n            ]\n        }\n    ]\n  }","lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"47fcd636036a413f896e5c3e15d0ef78","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备006","deviceNum":"1000-1001-008-SXXX-171230","principal":"2","startPoint":"232","endPoint":"142","electric":"40","state":null,"config":"0","lat":"113.270642","lng":"23.131645","cameraNum":"123","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"789072a51d604f1aaecc014b503ad1f1","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备003","deviceNum":"3","principal":"4","startPoint":"232","endPoint":"142","electric":"50","state":null,"config":"0","lat":"113.302223","lng":"23.130338","cameraNum":"dafd","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"a6ec0261e3df4236ae7354df42c29a6b","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备11","deviceNum":"123bdg","principal":"dadas","startPoint":"232","endPoint":"142","electric":"60","state":null,"config":"0","lat":"113.314681","lng":"23.133238","cameraNum":"zxcz","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"ae8dd9c1dc304163ac1b56b78acbb446","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备10","deviceNum":"12312","principal":"135412","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.328028","lng":"23.136921","cameraNum":"asda","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null},{"createBy":null,"createDate":1513787646000,"updateBy":null,"updateDate":1555559393000,"id":"b74b463d5d4341738850ce697b1a618d","lineId":"b2c06f6d70654b8bb09c3115f950fe90","deviceName":"设备008","deviceNum":"123213","principal":"12321321","startPoint":"232","endPoint":"142","electric":"0","state":null,"config":"0","lat":"113.334941","lng":"23.140748","cameraNum":"1231","data":null,"lineName":"12312","pipeCount":null,"humidity":null,"temperature":null,"balance":null}]
             * kv : 110kv
             */
            private int isSelected;
            private Object createBy;
            private long createDate;
            private String updateBy;
            private long updateDate;
            private String id;
            private String lineName;
            private String lineNum;
            private String startPoint;
            private String endPoint;
            private Object deviceCount;
            private Object pipeCount;
            private String kv;
            private List<TowerListBean> towerList;
            private List<DeviceListBean> deviceList;

            public int getIsSelected() {
                return isSelected;
            }

            public void setIsSelected(int isSelected) {
                this.isSelected = isSelected;
            }

            public Object getCreateBy() {
                return createBy;
            }

            public void setCreateBy(Object createBy) {
                this.createBy = createBy;
            }

            public long getCreateDate() {
                return createDate;
            }

            public void setCreateDate(long createDate) {
                this.createDate = createDate;
            }

            public String getUpdateBy() {
                return updateBy;
            }

            public void setUpdateBy(String updateBy) {
                this.updateBy = updateBy;
            }

            public long getUpdateDate() {
                return updateDate;
            }

            public void setUpdateDate(long updateDate) {
                this.updateDate = updateDate;
            }

            public String getId() {
                return id;
            }

            public void setId(String id) {
                this.id = id;
            }

            public String getLineName() {
                return lineName;
            }

            public void setLineName(String lineName) {
                this.lineName = lineName;
            }

            public String getLineNum() {
                return lineNum;
            }

            public void setLineNum(String lineNum) {
                this.lineNum = lineNum;
            }

            public String getStartPoint() {
                return startPoint;
            }

            public void setStartPoint(String startPoint) {
                this.startPoint = startPoint;
            }

            public String getEndPoint() {
                return endPoint;
            }

            public void setEndPoint(String endPoint) {
                this.endPoint = endPoint;
            }

            public Object getDeviceCount() {
                return deviceCount;
            }

            public void setDeviceCount(Object deviceCount) {
                this.deviceCount = deviceCount;
            }

            public Object getPipeCount() {
                return pipeCount;
            }

            public void setPipeCount(Object pipeCount) {
                this.pipeCount = pipeCount;
            }

            public String getKv() {
                return kv;
            }

            public void setKv(String kv) {
                this.kv = kv;
            }

            public List<TowerListBean> getTowerList() {
                return towerList;
            }

            public void setTowerList(List<TowerListBean> towerList) {
                this.towerList = towerList;
            }

            public List<DeviceListBean> getDeviceList() {
                return deviceList;
            }

            public void setDeviceList(List<DeviceListBean> deviceList) {
                this.deviceList = deviceList;
            }

            public static class TowerListBean {
                /**
                 * charge : 0
                 * gps : [113.248587,23.124097]
                 * tower : 长寿路
                 */

                private int charge;
                private String tower;
                private List<Double> gps;

                public int getCharge() {
                    return charge;
                }

                public void setCharge(int charge) {
                    this.charge = charge;
                }

                public String getTower() {
                    return tower;
                }

                public void setTower(String tower) {
                    this.tower = tower;
                }

                public List<Double> getGps() {
                    return gps;
                }

                public void setGps(List<Double> gps) {
                    this.gps = gps;
                }
            }

            public static class DeviceListBean {
                /**
                 * createBy : null
                 * createDate : 1513787646000
                 * updateBy : null
                 * updateDate : 1555559393000
                 * id : 26fee0c65a5e40edaf5b67f23f3bcee0
                 * lineId : b2c06f6d70654b8bb09c3115f950fe90
                 * deviceName : 设备005
                 * deviceNum : cspid-ES-01-007
                 * principal : 2
                 * startPoint : 232
                 * endPoint : 142
                 * electric : 75
                 * state : null
                 * config : 0,1,2
                 * lat : 113.248587
                 * lng : 23.132935
                 * cameraNum : 678
                 * data : null
                 * lineName : 12312
                 * pipeCount : null
                 * humidity : null
                 * temperature : null
                 * balance : null
                 */

                private Object createBy;
                private long createDate;
                private Object updateBy;
                private long updateDate;
                private String id;
                private String lineId;
                private String deviceName;
                private String deviceNum;
                private String principal;
                private String startPoint;
                private String endPoint;
                private String electric;
                private Object state;
                private String config;
                private String lat;
                private String lng;
                private String cameraNum;
                private Object data;
                private String lineName;
                private Object pipeCount;
                private Object humidity;
                private Object temperature;
                private Object balance;

                public Object getCreateBy() {
                    return createBy;
                }

                public void setCreateBy(Object createBy) {
                    this.createBy = createBy;
                }

                public long getCreateDate() {
                    return createDate;
                }

                public void setCreateDate(long createDate) {
                    this.createDate = createDate;
                }

                public Object getUpdateBy() {
                    return updateBy;
                }

                public void setUpdateBy(Object updateBy) {
                    this.updateBy = updateBy;
                }

                public long getUpdateDate() {
                    return updateDate;
                }

                public void setUpdateDate(long updateDate) {
                    this.updateDate = updateDate;
                }

                public String getId() {
                    return id;
                }

                public void setId(String id) {
                    this.id = id;
                }

                public String getLineId() {
                    return lineId;
                }

                public void setLineId(String lineId) {
                    this.lineId = lineId;
                }

                public String getDeviceName() {
                    return deviceName;
                }

                public void setDeviceName(String deviceName) {
                    this.deviceName = deviceName;
                }

                public String getDeviceNum() {
                    return deviceNum;
                }

                public void setDeviceNum(String deviceNum) {
                    this.deviceNum = deviceNum;
                }

                public String getPrincipal() {
                    return principal;
                }

                public void setPrincipal(String principal) {
                    this.principal = principal;
                }

                public String getStartPoint() {
                    return startPoint;
                }

                public void setStartPoint(String startPoint) {
                    this.startPoint = startPoint;
                }

                public String getEndPoint() {
                    return endPoint;
                }

                public void setEndPoint(String endPoint) {
                    this.endPoint = endPoint;
                }

                public String getElectric() {
                    return electric;
                }

                public void setElectric(String electric) {
                    this.electric = electric;
                }

                public Object getState() {
                    return state;
                }

                public void setState(Object state) {
                    this.state = state;
                }

                public String getConfig() {
                    return config;
                }

                public void setConfig(String config) {
                    this.config = config;
                }

                public String getLat() {
                    return lat;
                }

                public void setLat(String lat) {
                    this.lat = lat;
                }

                public String getLng() {
                    return lng;
                }

                public void setLng(String lng) {
                    this.lng = lng;
                }

                public String getCameraNum() {
                    return cameraNum;
                }

                public void setCameraNum(String cameraNum) {
                    this.cameraNum = cameraNum;
                }

                public Object getData() {
                    return data;
                }

                public void setData(Object data) {
                    this.data = data;
                }

                public String getLineName() {
                    return lineName;
                }

                public void setLineName(String lineName) {
                    this.lineName = lineName;
                }

                public Object getPipeCount() {
                    return pipeCount;
                }

                public void setPipeCount(Object pipeCount) {
                    this.pipeCount = pipeCount;
                }

                public Object getHumidity() {
                    return humidity;
                }

                public void setHumidity(Object humidity) {
                    this.humidity = humidity;
                }

                public Object getTemperature() {
                    return temperature;
                }

                public void setTemperature(Object temperature) {
                    this.temperature = temperature;
                }

                public Object getBalance() {
                    return balance;
                }

                public void setBalance(Object balance) {
                    this.balance = balance;
                }
            }
        }
    }

}
