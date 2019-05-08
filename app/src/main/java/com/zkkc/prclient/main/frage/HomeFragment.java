package com.zkkc.prclient.main.frage;

import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.location.BDLocation;
import com.baidu.location.LocationClient;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatus;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.MyLocationData;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.entity.MultiItemEntity;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.adapter.HomePopupAdapter;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.RvDevice;
import com.zkkc.prclient.main.entiy.RvLine;
import com.zkkc.prclient.main.p.HomePresenter;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;

/**
 * Created by ShiJunRan on 2019/5/6
 * 首页Fragment
 */
public class HomeFragment extends BaseFragment<HomeContract.View, HomeContract.Presenter> implements HomeContract.View {

    @BindView(R.id.ivMark)
    ImageView ivMark;
    @BindView(R.id.rlPopup)
    RelativeLayout rlPopup;
    @BindView(R.id.ivPopup)
    ImageView ivPopup;
    @BindView(R.id.llTop)
    LinearLayout llTop;
    @BindView(R.id.recyclerView)
    RecyclerView recyclerView;
    Unbinder unbinder;
    @BindView(R.id.llMap)
    LinearLayout llMap;

    private ArrayList<MultiItemEntity> mList;
    HomePopupAdapter homePopupAdapter;

    BaiduMap mBaiduMap;
    MapView mMapView;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        super.onDestroyView();
        if (mMapView != null) {
            mMapView.onDestroy();
        }
        unbinder.unbind();
    }

    @Override
    public void onResume() {
        super.onResume();
        if (mMapView != null) {
            mMapView.onResume();
        }
    }

    @Override
    public void onPause() {
        super.onPause();
        if (mMapView != null) {
            mMapView.onPause();
        }
    }

    @Override
    public int getLayoutId() {
        return R.layout.home_fragment;
    }

    @Override
    public HomeContract.Presenter createPresenter() {
        return new HomePresenter(mContext);
    }

    @Override
    public HomeContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        initBaiDuMap();
        homePopupAdapter = new HomePopupAdapter(mList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(homePopupAdapter);

    }

    /**
     * 百度地图配置
     */
    private void initBaiDuMap() {
        MapStatus.Builder builder = new MapStatus.Builder();
        builder.zoom(15.0f);
        final BaiduMapOptions options = new BaiduMapOptions();
        options.logoPosition(LogoPosition.logoPostionRightTop)
                .scaleControlEnabled(false)
                .zoomControlsEnabled(false)
                .compassEnabled(false)
                .mapStatus(builder.build());
        mMapView = new MapView(mContext, options);
        llMap.addView(mMapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);

    }


    @OnClick({R.id.rlPopup, R.id.ivPopup, R.id.llTop})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.rlPopup://左Popup 大
                if (rlPopup.getVisibility() == View.VISIBLE) {
                    rlPopup.setVisibility(View.GONE);
                }
                break;
            case R.id.ivPopup://左Popup 小
                if (rlPopup.getVisibility() == View.GONE) {
                    rlPopup.setVisibility(View.VISIBLE);
                }
                break;
            case R.id.llTop://右Popup
                if (recyclerView.getVisibility() == View.GONE) {
                    getPresenter().queryLineDeviceList();
                } else {
                    recyclerView.setVisibility(View.GONE);
                }

                break;
        }
    }

    @Override
    public void queryLineDeviceListSuccess(LineDeviceListBean b) {
        recyclerView.setVisibility(View.VISIBLE);
        mList = new ArrayList<>();
        LineDeviceListBean.DataBean data = b.getData();
        List<LineDeviceListBean.DataBean.LineListBean> lineList = data.getLineList();
        for (int i = 0; i < lineList.size(); i++) {
            LineDeviceListBean.DataBean.LineListBean lineListBean = lineList.get(i);
            RvLine line = new RvLine(lineListBean.getLineName(), "");
            List<LineDeviceListBean.DataBean.LineListBean.DeviceListBean> deviceList = lineListBean.getDeviceList();
            for (int j = 0; j < deviceList.size(); j++) {
                LineDeviceListBean.DataBean.LineListBean.DeviceListBean deviceListBean = deviceList.get(j);
                RvDevice device = new RvDevice(deviceListBean.getDeviceName());
                line.addSubItem(device);
            }
            mList.add(line);
        }
        homePopupAdapter.setNewData(mList);
    }

    @Override
    public void queryLineDeviceListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }
}
