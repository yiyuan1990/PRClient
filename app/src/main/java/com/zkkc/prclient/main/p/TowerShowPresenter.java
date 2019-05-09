package com.zkkc.prclient.main.p;

import android.content.Context;

import com.zkkc.prclient.main.callback.IQueryTower;
import com.zkkc.prclient.main.contract.TowerShowContract;
import com.zkkc.prclient.main.entiy.TowerListBean;
import com.zkkc.prclient.main.m.TowerShowModel;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class TowerShowPresenter extends TowerShowContract.Presenter {
    private Context mContext;
    private TowerShowModel model;

    public TowerShowPresenter(Context mContext) {
        this.mContext = mContext;
        model = new TowerShowModel();
    }

    @Override
    public void queryTowerList(String mediaId, String towerNum, int page, int limit) {
        model.queryTowerList(mediaId, towerNum, page, limit, new IQueryTower() {
            @Override
            public void onSuccess(TowerListBean b) {
                getView().queryTowerListSuccess(b);
            }

            @Override
            public void onFailure(String strErr) {
                getView().queryTowerListFailure(strErr);
            }
        });

    }
}
