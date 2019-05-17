package com.zkkc.prclient.main.callback;

import com.zkkc.prclient.main.entiy.MyAccessTokenResult;

/**
 * Created by ShiJunRan on 2019/5/17
 */
public interface IAccessToken {
    void onSuccess(MyAccessTokenResult b);

    void onFailure(String strErr);
}
