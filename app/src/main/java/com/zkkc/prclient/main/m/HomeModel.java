package com.zkkc.prclient.main.m;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.SPUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.base.BaseModel;
import com.zkkc.prclient.main.callback.IDeviceWebSocket;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.QueryBean;

import java.io.IOException;
import java.util.List;
import java.util.concurrent.TimeUnit;

import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.Response;
import okhttp3.WebSocket;
import okhttp3.WebSocketListener;
import okio.ByteString;

import static com.zkkc.prclient.PRCConstant.USER_LINE_DEVICE_LIST;
import static com.zkkc.prclient.PRCConstant.WEBSOCKET_BASE_URL;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;

/**
 * Created by ShiJunRan on 2019/5/6
 * 首页Model
 */
public class HomeModel extends BaseModel {
    /**
     * 查询线路和设备列表
     */
    public void queryLineDeviceList(final IQueryLineDeviceList callback) {
        String user_name = SPUtils.getInstance().getString("USER_NAME");
        QueryBean queryBean = new QueryBean();
        queryBean.setUsername(user_name);
        ViseHttp.GET(USER_LINE_DEVICE_LIST)
                .addHeader("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN))
                .request(new ACallback<LineDeviceListBean>() {
                    @Override
                    public void onSuccess(LineDeviceListBean data) {
                        if (data != null) {
                            if (data.getCode() == 200) {
                                callback.onSuccess(data);
                            } else {
                                callback.onFailure(data.getMsg());
                            }
                        } else {
                            callback.onFailure("请求失败");
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        callback.onFailure(errMsg);
                    }
                });
    }

    public void deviceWebSocket(final List<String> deviceNumList, final IDeviceWebSocket callback) {
        String str = "";
        final OkHttpClient client = new OkHttpClient.Builder().readTimeout(0, TimeUnit.MILLISECONDS).build();
        for (String s : deviceNumList) {
            str += s + ",";
        }
        Request request = new Request.Builder().url(WEBSOCKET_BASE_URL + str).build();
        client.newWebSocket(request, new WebSocketListener() {
            @Override
            public void onOpen(WebSocket webSocket, Response response) {
                super.onOpen(webSocket, response);
                callback.onWebSocketSuccess(webSocket);
            }

            @Override
            public void onMessage(WebSocket webSocket, String text) {
                super.onMessage(webSocket, text);
                callback.onWebSocketMessage(text,deviceNumList.size());
            }

            @Override
            public void onClosed(WebSocket webSocket, int code, String reason) {
                super.onClosed(webSocket, code, reason);
                callback.onWebSocketClosed();
            }

            @Override
            public void onFailure(WebSocket webSocket, Throwable t, Response response) {
                super.onFailure(webSocket, t, response);
            }
        });
        client.dispatcher().executorService().shutdown();

    }

}
