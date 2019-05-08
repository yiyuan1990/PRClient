package com.zkkc.prclient.login.m;

import com.blankj.utilcode.util.GsonUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.base.BaseModel;
import com.zkkc.prclient.base.BaseResult;
import com.zkkc.prclient.login.callback.ILogin;
import com.zkkc.prclient.login.entiy.LoginBean;
import com.zkkc.prclient.login.entiy.LoginRequest;

import static com.zkkc.prclient.PRCConstant.LOGIN;

/**
 * Created by ShiJunRan on 2019/5/5
 * 登录Model
 */
public class LoginModel extends BaseModel {
    public void login(LoginRequest request, final ILogin iLogin) {
        ViseHttp.POST(LOGIN)
                .setJson(GsonUtils.toJson(request))
                .request(new ACallback<BaseResult<LoginBean>>() {
                    @Override
                    public void onSuccess(BaseResult<LoginBean> data) {
                        if (data != null) {
                            if (data.getCode() == 200) {
                                iLogin.onSuccess(data.getData());
                            } else {
                                iLogin.onFailure("请求失败" + data.getMsg());
                            }
                        } else {
                            iLogin.onFailure("请求失败");
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        iLogin.onFailure(errMsg);
                    }
                });
    }
}
