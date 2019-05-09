package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;
import com.zkkc.prclient.R;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.entiy.TowerAdBean;

import java.util.List;

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
        helper.setText(R.id.tvTowerNo, item.getTowerNum())
                .setText(R.id.tv, item.getLineName())
                .setText(R.id.tv2, item.getDeviceName())
                .setText(R.id.tv3, item.getMdate());
    }
}
