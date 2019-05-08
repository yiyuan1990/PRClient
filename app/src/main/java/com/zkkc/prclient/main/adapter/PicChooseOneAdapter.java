package com.zkkc.prclient.main.adapter;

import android.support.annotation.Nullable;

import com.chad.library.adapter.base.BaseQuickAdapter;
import com.chad.library.adapter.base.BaseViewHolder;

import java.util.List;

/**
 * Created by ShiJunRan on 2019/5/8
 */
public class PicChooseOneAdapter extends BaseQuickAdapter<String, BaseViewHolder> {

    public PicChooseOneAdapter(int layoutResId, @Nullable List<String> data) {
        super(layoutResId, data);
    }

    @Override
    protected void convert(BaseViewHolder helper, String item) {

    }
}
