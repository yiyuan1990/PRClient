package com.zkkc.prclient.main.adapter;

import android.graphics.drawable.Drawable;
import android.support.annotation.Nullable;
import android.view.View;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.DataSource;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.load.engine.GlideException;
import com.bumptech.glide.request.RequestListener;
import com.bumptech.glide.request.RequestOptions;
import com.bumptech.glide.request.target.Target;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.PicShowBean;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class AdVideo extends BaseQuickAdapter<PicShowBean, BaseViewHolder> {

    public AdVideo(int layoutResId, @Nullable List<PicShowBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, final PicShowBean item) {
//        http://172.16.1.152:180//cspid-ES-01-007_20190320/12/photo/camera_1000-1001-008-SXXX-171229_12_0_20190320_12.jpg
        helper.setText(R.id.tvPicName, item.getPicName());
        final ImageView ivLoad = helper.getView(R.id.ivLoad);
        ImageView ivPic = helper.getView(R.id.ivPic);
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.pic_loading);
        requestOptions.error(R.mipmap.video_play_err);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);

        Glide.with(mContext)
                .load(item.getPicUrl())
                .apply(requestOptions).thumbnail(1)
                .listener(new RequestListener<Drawable>() {
                    @Override
                    public boolean onLoadFailed(@Nullable GlideException e, Object model, Target<Drawable> target, boolean isFirstResource) {
                        ivLoad.setVisibility(View.GONE);
                        item.setErr(true);
                        return false;
                    }

                    @Override
                    public boolean onResourceReady(Drawable resource, Object model, Target<Drawable> target, DataSource dataSource, boolean isFirstResource) {
                        ivLoad.setVisibility(View.VISIBLE);
                        item.setErr(false);
                        return false;
                    }
                })
                .into(ivPic);
    }
}
