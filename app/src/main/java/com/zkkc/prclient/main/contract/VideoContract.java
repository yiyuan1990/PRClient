package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.MediaListBean;

/**
 * Created by ShiJunRan on 2019/5/6
 * 视频契约类
 */
public interface VideoContract {
    interface View extends BaseView {
        void queryMediaListSuccess(MediaListBean b);

        void queryMediaListFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<VideoContract.View> {
        public abstract void queryMediaList(String deviceName,String lineName);
    }
}
