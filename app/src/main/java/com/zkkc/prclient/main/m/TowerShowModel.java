package com.zkkc.prclient.main.m;

import com.blankj.utilcode.util.SPUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.main.callback.IQueryTower;
import com.zkkc.prclient.main.entiy.TowerListBean;

import static com.zkkc.prclient.PRCConstant.TOWER_LIST;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class TowerShowModel {

    public void queryTowerList(String mediaId, String towerNum, int page, int limit, final IQueryTower callback) {
        ViseHttp.GET(TOWER_LIST)
                .addHeader("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN))
                .addParam("mediaId", mediaId)
                .addParam("towerNum", towerNum)
                .addParam("page", "")
                .addParam("limit", "")
                .request(new ACallback<TowerListBean>() {
                    @Override
                    public void onSuccess(TowerListBean data) {
                        if (data != null) {
                            if (data.getCode() == 200) {
                                callback.onSuccess(data);
                            } else {
                                callback.onFailure(data.getMsg());
                            }
                        } else {
                            callback.onFailure("请求失败");
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        callback.onFailure(errMsg);
                    }
                });

    }
}
