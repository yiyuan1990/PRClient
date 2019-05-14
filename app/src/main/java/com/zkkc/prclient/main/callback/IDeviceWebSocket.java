package com.zkkc.prclient.main.callback;


import okhttp3.WebSocket;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public interface IDeviceWebSocket {
    void onWebSocketSuccess(WebSocket webSocket);

    void onWebSocketMessage(String text,int size);

    void onWebSocketClosed();

    void onWebSocketFailure(String strErr);
}
