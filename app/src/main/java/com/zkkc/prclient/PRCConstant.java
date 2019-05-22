package com.zkkc.prclient;

/**
 * Created by ShiJunRan on 2019/4/28
 */
public class PRCConstant {
    //本地环境
//    public static final String BASE_URL = "http://172.16.1.228:8765/";
//    public static final String WEBSOCKET_BASE_URL = "ws://172.16.1.228:8762/websocket/";
    //测试环境
//    public static final String BASE_URL = "http://172.16.1.152:8765/";
//    public static final String WEBSOCKET_BASE_URL = "ws://172.16.1.152:8762/websocket/";
    //生产环境
    public static final String BASE_URL = "http://120.79.132.29:8765/";
    public static final String WEB = "api/";
    public static final String IRSVC = "ir-svc/";
    public static final String WEBSOCKET_BASE_URL = "ws://120.79.132.29:8762/websocket/";

    /**
     * 萤石AK  测试AccessToken
     */
    public static final String ACCESS_TOKEN_URL = WEB + "device/token";

    /**
     * 登录
     */
    public static final String LOGIN = WEB + "login";
    /**
     * 获取线路设备列表
     */
    public static final String USER_LINE_DEVICE_LIST = WEB + "user/lineDeviceList";

    /**
     * 媒体列表
     */
    public static final String MEDIA_LIST = WEB + "media/list";
    /**
     * 获取塔数据
     */
    public static final String TOWER_LIST = WEB + "tower/list";
    /**
     * 获取图片列表
     */
    public static final String TOWER_PICDATA = WEB + "tower/picData";
    /**
     * 获取视频列表
     */
    public static final String TOWER_VIDEODATA = WEB + "tower/videoData";


}
