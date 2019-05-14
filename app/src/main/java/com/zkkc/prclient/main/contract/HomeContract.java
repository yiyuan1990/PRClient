package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

import java.util.List;

import okhttp3.WebSocket;

/**
 * Created by ShiJunRan on 2019/5/6
 * 首页契约类
 */
public interface HomeContract {
    interface View extends BaseView {
        void queryLineDeviceListSuccess(LineDeviceListBean b);

        void queryLineDeviceListFailure(String strErr);

        void onWebSocketSuccess(WebSocket webSocket);

        void onWebSocketMessage(String text,int size);

        void onWebSocketClosed();

        void onWebSocketFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<HomeContract.View> {
        public abstract void queryLineDeviceList();
        public abstract void deviceWebSocket(List<String> deviceNumList);
    }
}
