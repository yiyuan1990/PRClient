package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片契约类
 */
public interface PicContract {
    interface View extends BaseView {

    }

    abstract class Presenter extends BasePresenter<PicContract.View> {

    }
}
