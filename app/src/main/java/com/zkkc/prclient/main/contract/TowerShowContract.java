package com.zkkc.prclient.main.contract;

import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.TowerListBean;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public interface TowerShowContract {
    interface View extends BaseView {
        void queryTowerListSuccess(TowerListBean b);

        void queryTowerListFailure(String strErr);
    }

    abstract class Presenter extends BasePresenter<TowerShowContract.View> {
        public abstract void queryTowerList(String mediaId, String towerNum, int page, int limit);
    }
}
