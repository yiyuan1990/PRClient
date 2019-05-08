package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;

/**
 * Created by ShiJunRan on 2019/5/6
 * 视频契约类
 */
public interface VideoContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<VideoContract.View> {

    }
}
