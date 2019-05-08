package com.zkkc.prclient.main.frage;

import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.contract.PicContract;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.p.PicPresenter;

import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片Fragment
 */
public class PicFragment extends BaseFragment<PicContract.View, PicContract.Presenter> implements PicContract.View {

    Unbinder unbinder;
    @Override
    public int getLayoutId() {
        return R.layout.pic_fragment;
    }

    @Override
    public PicContract.Presenter createPresenter() {
        return new PicPresenter(mContext);
    }

    @Override
    public PicContract.View createView() {
        return this;
    }

    @Override
    public void init() {

    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        unbinder.unbind();
    }

    @OnClick({R.id.lla, R.id.btnOk})
    public void onViewClicked(View view) {
        switch (view.getId()) {

        }
    }


    @Override
    public void queryMediaListSuccess(MediaListBean b) {
        ToastUtils.showShort("媒体查询成功");
        LogUtils.i(b.toString());

    }

    @Override
    public void queryMediaListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }
}
