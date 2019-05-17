package com.zkkc.prclient.main.m;

import com.blankj.utilcode.util.SPUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.base.BaseModel;
import com.zkkc.prclient.main.callback.IAccessToken;
import com.zkkc.prclient.main.entiy.MyAccessTokenResult;

import static com.zkkc.prclient.PRCConstant.ACCESS_TOKEN_URL;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;

/**
 * Created by ShiJunRan on 2019/5/16
 */
public class MediaModel extends BaseModel {

    public void getMyAccessToken(final IAccessToken callback) {
        ViseHttp.GET(ACCESS_TOKEN_URL)
                .addHeader("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN))
                .request(new ACallback<MyAccessTokenResult>() {
                    @Override
                    public void onSuccess(MyAccessTokenResult data) {
                        callback.onSuccess(data);
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        callback.onFailure(errMsg);
                    }
                });
    }

}
