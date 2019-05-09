package com.zkkc.prclient.main.callback;

import com.zkkc.prclient.main.entiy.TowerListBean;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public interface IQueryTower {
    void onSuccess(TowerListBean b);

    void onFailure(String strErr);
}
