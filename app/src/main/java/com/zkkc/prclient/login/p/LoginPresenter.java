package com.zkkc.prclient.login.p;

import android.content.Context;
import android.graphics.Color;

import com.cazaea.sweetalert.SweetAlertDialog;
import com.luoxudong.app.threadpool.ThreadPoolHelp;
import com.zkkc.prclient.login.callback.ILogin;
import com.zkkc.prclient.login.contract.LoginContract;
import com.zkkc.prclient.login.entiy.LoginBean;
import com.zkkc.prclient.login.entiy.LoginRequest;
import com.zkkc.prclient.login.m.LoginModel;

import java.util.concurrent.ExecutorService;

/**
 * Created by ShiJunRan on 2019/5/5
 */
public class LoginPresenter extends LoginContract.Presenter {
    private Context mContext;
    private LoginModel model;
    private ExecutorService threadPool;

    public LoginPresenter(Context mContext) {
        this.mContext = mContext;
        this.model = new LoginModel();
        threadPool = ThreadPoolHelp.Builder
                .cached()
                .builder();
    }


    @Override
    public void login(LoginRequest b) {
        showBaseProDialog(mContext, "登陆中...");
        model.login(b, new ILogin() {
            @Override
            public void onSuccess(LoginBean bean) {
                dismissBaseProDialog();
                getView().onSuccess(bean);
            }

            @Override
            public void onFailure(String strErr) {
                dismissBaseProDialog();
                getView().onFailure(strErr);
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
        if (proDialog!=null){
            proDialog.dismiss();
            proDialog = null;
        }
    }
}
