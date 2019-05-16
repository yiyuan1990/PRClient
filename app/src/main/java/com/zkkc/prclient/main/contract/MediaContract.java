package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

/**
 * Created by ShiJunRan on 2019/5/16
 * 实时视频契约类
 */
public interface MediaContract {
    interface View extends BaseView {
        void queryLineDeviceListSuccess(LineDeviceListBean b);

        void queryLineDeviceListFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<MediaContract.View> {
        public abstract void queryLineDeviceList();
    }

}
