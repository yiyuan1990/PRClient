package com.zkkc.prclient.main.p;

import android.content.Context;

import com.zkkc.prclient.main.callback.IAccessToken;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.contract.MediaContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.MyAccessTokenResult;
import com.zkkc.prclient.main.m.HomeModel;
import com.zkkc.prclient.main.m.MediaModel;

/**
 * Created by ShiJunRan on 2019/5/16
 */
public class MediaPresenter extends MediaContract.Presenter {
    private Context mContext;
    private HomeModel homeModel;
    private MediaModel mediaModel;

    public MediaPresenter(Context mContext) {
        this.mContext = mContext;
        homeModel = new HomeModel();
        mediaModel = new MediaModel();
    }

    @Override
    public void queryLineDeviceList() {
        homeModel.queryLineDeviceList(new IQueryLineDeviceList() {
            @Override
            public void onSuccess(LineDeviceListBean b) {
                getView().queryLineDeviceListSuccess(b);
            }

            @Override
            public void onFailure(String strErr) {
                getView().queryLineDeviceListFailure(strErr);
            }
        });
    }

    @Override
    public void getMyAccessToken() {
        mediaModel.getMyAccessToken(new IAccessToken() {
            @Override
            public void onSuccess(MyAccessTokenResult b) {
                getView().accessTokenSuccess(b);
            }

            @Override
            public void onFailure(String strErr) {
            getView().accessTokenFailure(strErr);
            }
        });
    }
}
