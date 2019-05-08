package com.zkkc.prclient.main.p;

import android.content.Context;
import android.graphics.Color;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.m.HomeModel;


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
