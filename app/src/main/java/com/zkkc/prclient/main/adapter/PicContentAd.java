package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.MediaListBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class PicContentAd extends BaseQuickAdapter<MediaListBean.ListBean, BaseViewHolder> {

    public PicContentAd(int layoutResId, @Nullable List<MediaListBean.ListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, MediaListBean.ListBean item) {
        helper.setText(R.id.tv, item.getLineName())
                .setText(R.id.tv2, item.getDeviceName())
                .setText(R.id.tv3, item.getDate());
    }
}
