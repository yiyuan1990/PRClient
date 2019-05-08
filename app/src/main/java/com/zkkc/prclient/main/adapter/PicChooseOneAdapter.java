package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;
import android.view.View;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 */
public class PicChooseOneAdapter extends BaseQuickAdapter<LineDeviceListBean.DataBean.LineListBean, BaseViewHolder> {

    public PicChooseOneAdapter(int layoutResId, @Nullable List<LineDeviceListBean.DataBean.LineListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, LineDeviceListBean.DataBean.LineListBean item) {
        helper.setText(R.id.tvChoose, item.getLineName());
        final TextView textView = helper.getView(R.id.tvChoose);
        if (item.getIsSelected() == 1) {
            textView.setTextColor(mContext.getResources().getColor(R.color.orange));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }
}
