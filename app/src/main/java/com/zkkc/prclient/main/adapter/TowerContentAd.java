package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.blankj.utilcode.util.TimeUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.entiy.TowerAdBean;

import java.text.SimpleDateFormat;
import java.util.List;
import java.util.Locale;

/**
 * Created by ShiJunRan on 2019/5/9
 * 图片模块adapter
 */
public class TowerContentAd extends BaseQuickAdapter<TowerAdBean, BaseViewHolder> {

    public TowerContentAd(int layoutResId, @Nullable List<TowerAdBean> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, TowerAdBean item) {
        helper.setText(R.id.tvTowerNo, "塔号"+item.getTowerNum())
                .setText(R.id.tv, item.getLineName())
                .setText(R.id.tv2, item.getDeviceName())
                .setText(R.id.tv3, TimeUtils.millis2String(Long.parseLong(item.getMdate()),
                        new SimpleDateFormat("yyyy-MM-dd HH:mm:ss", Locale.ENGLISH)) );
    }
}