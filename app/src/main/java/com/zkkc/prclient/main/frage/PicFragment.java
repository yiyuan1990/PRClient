package com.zkkc.prclient.main.frage;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.act.TowerShowAct;
import com.zkkc.prclient.main.adapter.PicContentAd;
import com.zkkc.prclient.main.contract.PicContract;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.p.PicPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ShiJunRan on 2019/5/6
 * 图片Fragment
 */
public class PicFragment extends BaseFragment<PicContract.View, PicContract.Presenter> implements PicContract.View {

    Unbinder unbinder;
    @BindView(R.id.etDevice)
    EditText etDevice;
    @BindView(R.id.etLine)
    EditText etLine;
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.rvPic)
    RecyclerView rvPic;
    @BindView(R.id.tvNoData)
    TextView tvNoData;


    List<MediaListBean.ListBean> mList;
    PicContentAd picContentAd;

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
        picContentAd = new PicContentAd(R.layout.item_pic, mList);
        rvPic.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        rvPic.setAdapter(picContentAd);
        picContentAd.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().postSticky(mList.get(position));
                startActivity(new Intent(getActivity(), TowerShowAct.class));
            }
        });
        getPresenter().queryMediaList("", "");
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

    @OnClick({R.id.btnQuery})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.btnQuery:
                String deviceName = etDevice.getText().toString().trim();
                String lineName = etLine.getText().toString().trim();
                mList = null;
                picContentAd.setNewData(mList);
                getPresenter().queryMediaList(deviceName, lineName);
                break;
        }
    }

    @Override
    public void queryMediaListSuccess(MediaListBean b) {
        tvNoData.setVisibility(View.GONE);
        mList = b.getList();
        picContentAd.setNewData(mList);
    }

    @Override
    public void queryMediaListFailure(String strErr) {
        tvNoData.setVisibility(View.VISIBLE);
        ToastUtils.showShort(strErr);
    }


}
