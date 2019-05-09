package com.zkkc.prclient.main.m;

import com.blankj.utilcode.util.SPUtils;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.base.BaseModel;
import com.zkkc.prclient.base.BaseResult;
import com.zkkc.prclient.main.callback.IQueryLineDeviceList;
import com.zkkc.prclient.main.callback.IQueryMediaList;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.MediaListBean;

import static com.zkkc.prclient.PRCConstant.MEDIA_LIST;
import static com.zkkc.prclient.PRCConstant.USER_LINE_DEVICE_LIST;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片Model
 */
public class PicModel extends BaseModel {
    /**
     * 查询媒体列表
     */
    public void queryMediaList(String deviceName, String lineName, final IQueryMediaList callback) {
        ViseHttp.GET(MEDIA_LIST)
                .addHeader("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN))
                .addParam("deviceName", deviceName)
                .addParam("lineName", lineName)
                .request(new ACallback<BaseResult<MediaListBean>>() {
                    @Override
                    public void onSuccess(BaseResult<MediaListBean> data) {
                        if (data != null) {
                            if (data.getCode() == 200) {
                                callback.onSuccess(data.getData());
                            }else {
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
