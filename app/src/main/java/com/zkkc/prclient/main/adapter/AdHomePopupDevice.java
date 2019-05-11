package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class AdHomePopupDevice extends BaseQuickAdapter<LineDeviceListBean.DataBean.LineListBean.DeviceListBean, BaseViewHolder> {

    public AdHomePopupDevice(int layoutResId, @Nullable List<LineDeviceListBean.DataBean.LineListBean.DeviceListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LineDeviceListBean.DataBean.LineListBean.DeviceListBean item) {
        helper.setText(R.id.tvDeviceName, item.getDeviceName());

    }
}
