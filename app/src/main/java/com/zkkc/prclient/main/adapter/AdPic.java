package com.zkkc.prclient.main.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.RequestBuilder;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.vise.xsnow.loader.ILoader;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.PicShowBean;
import com.zkkc.prclient.main.entiy.TowerAdBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class AdPic extends BaseQuickAdapter<PicShowBean, BaseViewHolder> {

    public AdPic(int layoutResId, @Nullable List<PicShowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, PicShowBean item) {
//        http://172.16.1.152:180//cspid-ES-01-007_20190320/12/photo/camera_1000-1001-008-SXXX-171229_12_0_20190320_12.jpg
        helper.setText(R.id.tvPicName, item.getPicName());
        ImageView ivPic = helper.getView(R.id.ivPic);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.pic_loading);
        requestOptions.error(R.mipmap.pic_load_err);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(item.getPicUrl())
                .apply(requestOptions).thumbnail(1)
                .into(ivPic);
    }
}
