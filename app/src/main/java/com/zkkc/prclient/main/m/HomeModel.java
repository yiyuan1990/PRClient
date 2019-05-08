package com.zkkc.prclient.main.m;

import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.SPUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.base.BaseModel;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.QueryBean;

import static com.zkkc.prclient.PRCConstant.LOGIN;
import static com.zkkc.prclient.PRCConstant.USER_LINE_DEVICE_LIST;

/**
 * Created by ShiJunRan on 2019/5/6
 * 首页Model
 */
public class HomeModel extends BaseModel {
    /**
     * 查询线路和设备列表
     */
    public void queryLineDeviceList(final IQueryLineDeviceList callback) {
        String user_name = SPUtils.getInstance().getString("USER_NAME");
        QueryBean queryBean = new QueryBean();
        queryBean.setUsername(user_name);
        ViseHttp.GET(USER_LINE_DEVICE_LIST)
                .request(new ACallback<LineDeviceListBean>() {
                    @Override
                    public void onSuccess(LineDeviceListBean data) {
                        if (data != null) {
                            if (data.getCode() == 200) {
                                callback.onSuccess(data);
                            } else {
                                callback.onFailure("请求失败" + data.getMsg());
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
