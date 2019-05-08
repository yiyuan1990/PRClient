package com.zkkc.prclient.login.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.login.entiy.LoginBean;
import com.zkkc.prclient.login.entiy.LoginRequest;

/**
 * Created by ShiJunRan on 2019/5/5
 * Login契约类
 */
public interface LoginContract {
    interface View extends BaseView {
        void onSuccess(LoginBean bean);

        void onFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<View> {
        public abstract void login(LoginRequest b);
    }
}
