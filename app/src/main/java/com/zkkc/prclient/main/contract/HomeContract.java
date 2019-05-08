package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

/**
 * Created by ShiJunRan on 2019/5/6
 * 首页契约类
 */
public interface HomeContract {
    interface View extends BaseView {
        void queryLineDeviceListSuccess(LineDeviceListBean b);

        void queryLineDeviceListFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<HomeContract.View> {
        public abstract void queryLineDeviceList();
    }
}
