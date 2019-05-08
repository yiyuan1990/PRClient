package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;
import android.widget.TextView;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.MediaListBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 */
public class PicChooseTwoAdapter extends BaseQuickAdapter<MediaListBean.ListBean, BaseViewHolder> {

    public PicChooseTwoAdapter(int layoutResId, @Nullable List<MediaListBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MediaListBean.ListBean item) {
        helper.setText(R.id.tvChoose, item.getDeviceName());
        final TextView textView = helper.getView(R.id.tvChoose);
        if (item.getIsSelected() == 1) {
            textView.setTextColor(mContext.getResources().getColor(R.color.orange));
        } else {
            textView.setTextColor(mContext.getResources().getColor(R.color.black));
        }
    }
}
