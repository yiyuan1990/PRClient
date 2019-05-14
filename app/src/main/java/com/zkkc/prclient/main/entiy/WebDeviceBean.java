package com.zkkc.prclient.main.entiy;

/**
 * Created by ShiJunRan on 2019/5/14
 */
public class WebDeviceBean {

    /**
     * code : 150
     * data : {"advance":{"direction":0,"tower":14},"arm":{"ocDevice":0,"left":0,"right":0},"balance":0,"electric":50,"gps":{"date":"2018 - 05 - 31","latitude":"121.12345 ","longitude":"121.12345","time":null},"humidity":46,"peripheral":{"camera":0,"ir":0,"radar":0},"serialNum":"1000-1001-008-SXXX-171229","temperature":24,"state":1}
     */

    private int code;
    private DataBean data;

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
         * advance : {"direction":0,"tower":14}
         * arm : {"ocDevice":0,"left":0,"right":0}
         * balance : 0
         * electric : 50
         * gps : {"date":"2018 - 05 - 31","latitude":"121.12345 ","longitude":"121.12345","time":null}
         * humidity : 46
         * peripheral : {"camera":0,"ir":0,"radar":0}
         * serialNum : 1000-1001-008-SXXX-171229
         * temperature : 24
         * state : 1
         */

        private AdvanceBean advance;
        private ArmBean arm;
        private int balance;
        private int electric;
        private GpsBean gps;
        private int humidity;
        private PeripheralBean peripheral;
        private String serialNum;
        private int temperature;
        private int state;

        public AdvanceBean getAdvance() {
            return advance;
        }

        public void setAdvance(AdvanceBean advance) {
            this.advance = advance;
        }

        public ArmBean getArm() {
            return arm;
        }

        public void setArm(ArmBean arm) {
            this.arm = arm;
        }

        public int getBalance() {
            return balance;
        }

        public void setBalance(int balance) {
            this.balance = balance;
        }

        public int getElectric() {
            return electric;
        }

        public void setElectric(int electric) {
            this.electric = electric;
        }

        public GpsBean getGps() {
            return gps;
        }

        public void setGps(GpsBean gps) {
            this.gps = gps;
        }

        public int getHumidity() {
            return humidity;
        }

        public void setHumidity(int humidity) {
            this.humidity = humidity;
        }

        public PeripheralBean getPeripheral() {
            return peripheral;
        }

        public void setPeripheral(PeripheralBean peripheral) {
            this.peripheral = peripheral;
        }

        public String getSerialNum() {
            return serialNum;
        }

        public void setSerialNum(String serialNum) {
            this.serialNum = serialNum;
        }

        public int getTemperature() {
            return temperature;
        }

        public void setTemperature(int temperature) {
            this.temperature = temperature;
        }

        public int getState() {
            return state;
        }

        public void setState(int state) {
            this.state = state;
        }

        public static class AdvanceBean {
            /**
             * direction : 0
             * tower : 14
             */

            private int direction;
            private int tower;

            public int getDirection() {
                return direction;
            }

            public void setDirection(int direction) {
                this.direction = direction;
            }

            public int getTower() {
                return tower;
            }

            public void setTower(int tower) {
                this.tower = tower;
            }
        }

        public static class ArmBean {
            /**
             * ocDevice : 0
             * left : 0
             * right : 0
             */

            private int ocDevice;
            private int left;
            private int right;

            public int getOcDevice() {
                return ocDevice;
            }

            public void setOcDevice(int ocDevice) {
                this.ocDevice = ocDevice;
            }

            public int getLeft() {
                return left;
            }

            public void setLeft(int left) {
                this.left = left;
            }

            public int getRight() {
                return right;
            }

            public void setRight(int right) {
                this.right = right;
            }
        }

        public static class GpsBean {
            /**
             * date : 2018 - 05 - 31
             * latitude : 121.12345
             * longitude : 121.12345
             * time : null
             */

            private String date;
            private String latitude;
            private String longitude;
            private Object time;

            public String getDate() {
                return date;
            }

            public void setDate(String date) {
                this.date = date;
            }

            public String getLatitude() {
                return latitude;
            }

            public void setLatitude(String latitude) {
                this.latitude = latitude;
            }

            public String getLongitude() {
                return longitude;
            }

            public void setLongitude(String longitude) {
                this.longitude = longitude;
            }

            public Object getTime() {
                return time;
            }

            public void setTime(Object time) {
                this.time = time;
            }
        }

        public static class PeripheralBean {
            /**
             * camera : 0
             * ir : 0
             * radar : 0
             */

            private int camera;
            private int ir;
            private int radar;

            public int getCamera() {
                return camera;
            }

            public void setCamera(int camera) {
                this.camera = camera;
            }

            public int getIr() {
                return ir;
            }

            public void setIr(int ir) {
                this.ir = ir;
            }

            public int getRadar() {
                return radar;
            }

            public void setRadar(int radar) {
                this.radar = radar;
            }
        }
    }
}
