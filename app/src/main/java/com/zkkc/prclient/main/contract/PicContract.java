package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.MediaListBean;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片契约类
 */
public interface PicContract {
    interface View extends BaseView {

        void queryMediaListSuccess(MediaListBean b);

        void queryMediaListFailure(String strErr);

    }

    abstract class Presenter extends BasePresenter<PicContract.View> {

        public abstract void queryMediaList(String deviceName,String lineName);
    }
}
