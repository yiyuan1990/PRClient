package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/16
 */
public class AdSelect extends BaseQuickAdapter<LineDeviceListBean.DataBean.LineListBean, BaseViewHolder> {

    public AdSelect(int layoutResId, @Nullable List<LineDeviceListBean.DataBean.LineListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LineDeviceListBean.DataBean.LineListBean item) {
        helper.setText(R.id.tvCon, item.getLineName());


    }
}
