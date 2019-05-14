package com.zkkc.prclient.main.p;

import android.content.Context;
import android.graphics.Color;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.zkkc.prclient.main.callback.IDeviceWebSocket;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.m.HomeModel;

import java.util.List;

import okhttp3.WebSocket;


/**
 * Created by ShiJunRan on 2019/5/6
 * 首页Presenter
 */
public class HomePresenter extends HomeContract.Presenter {
    private Context mContext;
    private HomeModel model;

    public HomePresenter(Context mContext) {
        this.mContext = mContext;
        model = new HomeModel();
    }


    @Override
    public void queryLineDeviceList() {
        showBaseProDialog(mContext, "查询中...");
        model.queryLineDeviceList(new IQueryLineDeviceList() {
            @Override
            public void onSuccess(LineDeviceListBean b) {
                dismissBaseProDialog();
                getView().queryLineDeviceListSuccess(b);
            }

            @Override
            public void onFailure(String strErr) {
                dismissBaseProDialog();
                getView().queryLineDeviceListFailure(strErr);
            }
        });
    }

    @Override
    public void deviceWebSocket(List<String> deviceNumList) {
        model.deviceWebSocket(deviceNumList, new IDeviceWebSocket() {
            @Override
            public void onWebSocketSuccess(WebSocket webSocket) {
                getView().onWebSocketSuccess(webSocket);
            }

            @Override
            public void onWebSocketMessage(String text,int size) {
                getView().onWebSocketMessage(text,size);
            }

            @Override
            public void onWebSocketClosed() {
                getView().onWebSocketClosed();
            }

            @Override
            public void onWebSocketFailure(String strErr) {
                getView().onWebSocketFailure(strErr);
            }
        });
    }


    /**
     * 加载Dialog
     */
    SweetAlertDialog proDialog;

    private void showBaseProDialog(Context mContext, String msg) {
        proDialog = new SweetAlertDialog(mContext, SweetAlertDialog.PROGRESS_TYPE);
        proDialog.getProgressHelper().setBarColor(Color.parseColor("#A5DC86"));
        proDialog.setTitleText(msg);
        proDialog.setCancelable(true);
        proDialog.show();
    }

    private void dismissBaseProDialog() {
        if (proDialog != null) {
            proDialog.dismiss();
            proDialog = null;
        }
    }
}
