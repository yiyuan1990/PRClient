package com.zkkc.prclient.main.callback;


import com.zkkc.prclient.main.entiy.LineDeviceListBean;

/**
 * Created by ShiJunRan on 2019/5/6
 */
public interface IQueryLineDeviceList {
    void onSuccess(LineDeviceListBean b);

    void onFailure(String strErr);
}
