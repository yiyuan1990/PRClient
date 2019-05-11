package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.PicShowBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class AdHomePopup extends BaseQuickAdapter<LineDeviceListBean.DataBean.LineListBean, BaseViewHolder> {

    public AdHomePopup(int layoutResId, @Nullable List<LineDeviceListBean.DataBean.LineListBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final LineDeviceListBean.DataBean.LineListBean item) {
        helper.setText(R.id.tvLineName, item.getLineName());
        ImageView ivLine = helper.getView(R.id.ivLine);
        ImageView ivMark = helper.getView(R.id.ivMark);

    }
}
