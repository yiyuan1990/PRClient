package com.zkkc.prclient.login.callback;

import com.zkkc.prclient.login.entiy.LoginBean;

/**
 * Created by ShiJunRan on 2019/5/5
 */
public interface ILogin {
    void onSuccess(LoginBean bean);

    void onFailure(String strErr);
}
