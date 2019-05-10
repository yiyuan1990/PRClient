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
import com.zkkc.prclient.main.act.VideoTowerShowAct;
import com.zkkc.prclient.main.adapter.AdVideoContent;
import com.zkkc.prclient.main.contract.VideoContract;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.p.VideoPresenter;

import org.greenrobot.eventbus.EventBus;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ShiJunRan on 2019/5/6
 * 视频Fragment
 */
public class VideoFragment extends BaseFragment<VideoContract.View, VideoContract.Presenter> implements VideoContract.View {

    @BindView(R.id.etDevice)
    EditText etDevice;
    @BindView(R.id.etLine)
    EditText etLine;
    @BindView(R.id.btnQuery)
    Button btnQuery;
    @BindView(R.id.rvVideo)
    RecyclerView rvVideo;
    @BindView(R.id.tvNoData)
    TextView tvNoData;

    Unbinder unbinder;
    AdVideoContent adVideoContent;
    List<MediaListBean.ListBean> mList;

    @Override
    public int getLayoutId() {
        return R.layout.video_fragment;
    }

    @Override
    public VideoContract.Presenter createPresenter() {
        return new VideoPresenter(getActivity());
    }

    @Override
    public VideoContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        adVideoContent = new AdVideoContent(R.layout.item_video, mList);
        rvVideo.setLayoutManager(new GridLayoutManager(getActivity(), 5));
        rvVideo.setAdapter(adVideoContent);
        adVideoContent.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                EventBus.getDefault().postSticky(mList.get(position));
                startActivity(new Intent(getActivity(), VideoTowerShowAct.class));
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

    @OnClick(R.id.btnQuery)
    public void onViewClicked() {
        String deviceName = etDevice.getText().toString().trim();
        String lineName = etLine.getText().toString().trim();
        mList = null;
        adVideoContent.setNewData(mList);
        getPresenter().queryMediaList(deviceName, lineName);
    }

    @Override
    public void queryMediaListSuccess(MediaListBean b) {
        tvNoData.setVisibility(View.GONE);
        mList = b.getList();
        for (MediaListBean.ListBean lb : mList) {
            lb.setPlayUrl(b.getPlayUrl());
        }
        adVideoContent.setNewData(mList);
    }

    @Override
    public void queryMediaListFailure(String strErr) {
        tvNoData.setVisibility(View.VISIBLE);
        ToastUtils.showShort(strErr);
    }
}
