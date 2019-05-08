package com.zkkc.prclient.main.frage;

import android.os.Bundle;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.contract.PicContract;
import com.zkkc.prclient.main.p.PicPresenter;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.Unbinder;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片Fragment
 */
public class PicFragment extends BaseFragment<PicContract.View, PicContract.Presenter> implements PicContract.View {

    @BindView(R.id.rvChoose)
    RecyclerView rvChoose;
    @BindView(R.id.rvChoose2)
    RecyclerView rvChoose2;
    @BindView(R.id.rvChoose3)
    RecyclerView rvChoose3;
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
}
