package com.zkkc.prclient.main.frage;

import android.content.Intent;
import android.graphics.Point;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;
import android.widget.Toast;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BaiduMapOptions;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.LogoPosition;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.ColorUtils;
import com.blankj.utilcode.util.GsonUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.xuhao.didi.socket.client.sdk.OkSocket;
import com.xuhao.didi.socket.client.sdk.client.ConnectionInfo;
import com.xuhao.didi.socket.client.sdk.client.action.SocketActionAdapter;
import com.xuhao.didi.socket.client.sdk.client.connection.IConnectionManager;
import com.zkkc.prclient.PRClientApp;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.adapter.AdHomePopup;
import com.zkkc.prclient.main.adapter.AdHomePopupDevice;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.entiy.AllShowMarkerBean;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.WebDeviceBean;
import com.zkkc.prclient.main.p.HomePresenter;
import com.zkkc.prclient.main.utils.LocationService;
import com.zkkc.prclient.service.UpdateMarkerService;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.InputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.Timer;
import java.util.TimerTask;
import java.util.concurrent.TimeUnit;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;
import butterknife.Unbinder;
import okhttp3.WebSocket;

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
    //    @BindView(R.id.bMapView)
    MapView mMapView;

    BaiduMap mBaiduMap;
    private LocationService locationService;

    private List<Integer> lineColors = new ArrayList<>();
    private List<LineDeviceListBean.DataBean.LineListBean> lineList;
    AdHomePopup adHomePopup;
    AdHomePopupDevice adHomePopupDevice;

    WebSocket mWebSocket;

    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container, Bundle savedInstanceState) {
        View rootView = super.onCreateView(inflater, container, savedInstanceState);
        unbinder = ButterKnife.bind(this, rootView);
        return rootView;
    }

    @Override
    public void onDestroyView() {
        if (mWebSocket != null) {
            mWebSocket.close(1000, null);
        }
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
    public void onStop() {
        locationService.unregisterListener(mListener); //注销掉监听
        locationService.stop(); //停止定位服务
        super.onStop();
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
        //百度地图初始设置
        initBaiDuMap();
        lineColors.add(R.mipmap.line_a);
        lineColors.add(R.mipmap.line_b);
        lineColors.add(R.mipmap.line_c);
        lineColors.add(R.mipmap.line_d);
        lineColors.add(R.mipmap.line_e);
        adHomePopup = new AdHomePopup(R.layout.item_home_popup, lineList);
        recyclerView.setLayoutManager(new LinearLayoutManager(mContext));
        recyclerView.setAdapter(adHomePopup);
        adHomePopup.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<LineDeviceListBean.DataBean.LineListBean> list = adapter.getData();
                LineDeviceListBean.DataBean.LineListBean lineListBean = list.get(position);
                RecyclerView rvDevice = (RecyclerView) adHomePopup.getViewByPosition(recyclerView, position, R.id.rvDevice);
                ImageView ivLine = (ImageView) adHomePopup.getViewByPosition(recyclerView, position, R.id.ivLine);
                ImageView ivMark = (ImageView) adHomePopup.getViewByPosition(recyclerView, position, R.id.ivMark);
                if (rvDevice.getAdapter() == null) {//折叠状态时点击逻辑
                    //线路绘制颜色和角标显示
                    ivMark.setImageResource(R.mipmap.icon_up);
                    if (lineColors.size() > 0) {
                        ivLine.setVisibility(View.VISIBLE);
                        ivLine.setImageResource(lineColors.get(0));
                        list.get(position).setIsSelected(lineColors.get(0));
                        lineColors.remove(0);
                        //设备列表
                        adHomePopupDevice = new AdHomePopupDevice(R.layout.item_home_popup_a, lineListBean.getDeviceList());
                        rvDevice.setLayoutManager(new LinearLayoutManager(mContext));
                        rvDevice.setAdapter(adHomePopupDevice);
                        adHomePopupDevice.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ToastUtils.showShort("设备-" + position);
                                List<LineDeviceListBean.DataBean.LineListBean.DeviceListBean> listBeans = adapter.getData();
                                LineDeviceListBean.DataBean.LineListBean.DeviceListBean deviceListBean = listBeans.get(position);
                                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(Double.valueOf(deviceListBean.getLng()), Double.valueOf(deviceListBean.getLat()))));
                            }
                        });
                        recyclerView.scrollToPosition(position);
                        //绘制线路和设备
                        showMyClickedLineAndDevice(lineListBean, position);
                        String lat = lineListBean.getDeviceList().get(0).getLat();
                        String lng = lineListBean.getDeviceList().get(0).getLng();
                        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(Double.valueOf(lng), Double.valueOf(lat))));
                    } else {
                        ToastUtils.showShort("最多同时显示5条线路");
                    }
                } else {//展开状态时点击逻辑
                    //线路绘制颜色和角标显示
                    ivMark.setImageResource(R.mipmap.icon_down);
                    ivLine.setVisibility(View.GONE);
                    lineColors.add(lineListBean.getIsSelected());
                    lineListBean.setIsSelected(0);
                    //设备列表
                    rvDevice.setAdapter(null);
                    //移除绘制
                    int my = -1;
                    for (int i = 0; i < markerBeanList.size(); i++) {
                        AllShowMarkerBean bean = markerBeanList.get(i);
                        if (bean.getmPos() == position) {
                            my = i;
                            for (Overlay ol : bean.getOverlays()) {
                                ol.remove();
                            }
                        }
                    }
                    if (my != -1) {
                        markerBeanList.remove(my);
                    }

                }
            }
        });


    }

    List<AllShowMarkerBean> markerBeanList = new ArrayList<>();//线路绘制的集合
    List<Overlay> overlays;//（线路 塔 设备）

    private void showMyClickedLineAndDevice(LineDeviceListBean.DataBean.LineListBean lineListBean, int pos) {
        overlays = new ArrayList<>();
        List<LineDeviceListBean.DataBean.LineListBean.TowerListBean> towerList = lineListBean.getTowerList();
        List<LatLng> points = new ArrayList<LatLng>();
        //创建OverlayOptions的集合
        List<OverlayOptions> towerOptions = new ArrayList<OverlayOptions>();
        for (LineDeviceListBean.DataBean.LineListBean.TowerListBean tlb : towerList) {
            List<Double> gps = tlb.getGps();
            LatLng latLng = new LatLng(gps.get(1), gps.get(0));
            points.add(latLng);

            //构建Marker图标
            BitmapDescriptor bitmap = BitmapDescriptorFactory
                    .fromResource(R.mipmap.marker_tower);
            //创建OverlayOptions属性
            Bundle bundle = new Bundle();
            bundle.putString("deviceNum", "tower");
            OverlayOptions option = new MarkerOptions()
                    .position(latLng)
                    .icon(bitmap)
                    .yOffset(25)
                    .perspective(true)
                    .draggable(false)
                    .extraInfo(bundle)
                    .flat(false);
            towerOptions.add(option);
        }
        Overlay overlayLine = addDeviceLine(points, getLineColor(lineListBean.getIsSelected()));
        //在地图上批量添加
        List<Overlay> overlayMarker = mBaiduMap.addOverlays(towerOptions);
        //存入集合
        overlays.add(overlayLine);
        for (Overlay ol : overlayMarker) {
            overlays.add(ol);
        }
        //-----------------------------------
        List<LineDeviceListBean.DataBean.LineListBean.DeviceListBean> deviceList = lineListBean.getDeviceList();
        //创建OverlayOptions的集合
        List<OverlayOptions> deviceOptions = new ArrayList<OverlayOptions>();
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_a);
        for (LineDeviceListBean.DataBean.LineListBean.DeviceListBean dlb : deviceList) {
            LatLng latLng = new LatLng(Double.valueOf(dlb.getLng()), Double.valueOf(dlb.getLat()));
            Bundle bundle = new Bundle();
            bundle.putString("deviceNum", dlb.getDeviceNum());
            //创建OverlayOptions属性
            OverlayOptions option = new MarkerOptions()
                    .position(latLng)
                    .icon(bitmap)
                    .yOffset(25)
                    .perspective(true)
                    .draggable(false)
                    .flat(false)
                    .extraInfo(bundle);
            deviceOptions.add(option);
        }
        //在地图上批量添加
        List<Overlay> overlaysDevice = mBaiduMap.addOverlays(deviceOptions);
        //存入集合
        for (Overlay ol : overlaysDevice) {
            overlays.add(ol);
        }
        AllShowMarkerBean bean = new AllShowMarkerBean();
        bean.setmPos(pos);
        bean.setOverlays(overlays);
        markerBeanList.add(bean);
    }

    private int getLineColor(int draw) {
        int color = 0;
        switch (draw) {
            case R.mipmap.line_a:
                color = R.color.line_a;
                break;
            case R.mipmap.line_b:
                color = R.color.line_b;
                break;
            case R.mipmap.line_c:
                color = R.color.line_c;
                break;
            case R.mipmap.line_d:
                color = R.color.line_d;
                break;
            case R.mipmap.line_e:
                color = R.color.line_e;
                break;
        }
        return color;
    }

    /**
     * 百度地图配置
     */
    private void initBaiDuMap() {
        BaiduMapOptions options = new BaiduMapOptions();
        options.overlookingGesturesEnabled(false);
        options.zoomControlsEnabled(false);
        options.logoPosition(LogoPosition.logoPostionRightTop);
        mMapView = new MapView(getActivity(), options);
        llMap.addView(mMapView);
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(15));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(23.112018, 113.329817)));

        //LocationService
        locationService = PRClientApp.locationService;
        LocationClientOption mOption = locationService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        locationService.setLocationOption(mOption);
        locationService.registerListener(mListener);
        locationService.start();

        mBaiduMap.setOnMarkerClickListener(new BaiduMap.OnMarkerClickListener() {
            //marker被点击时回调的方法
            //若响应点击事件，返回true，否则返回false
            //默认返回false
            @Override
            public boolean onMarkerClick(Marker marker) {
                ToastUtils.showShort(marker.getTitle());
                return false;
            }
        });

    }

    /**
     * 实时改变Maker状态
     */
    private void doDeviceMaker() {


    }

    /**
     * 添加Line
     *
     * @param points
     */
    private Overlay addDeviceLine(List<LatLng> points, int color) {
        //设置折线的属性
        Bundle bundle = new Bundle();
        bundle.putString("deviceNum", "line");
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(14)
                .extraInfo(bundle)
                .color(ColorUtils.getColor(color))
                .points(points);
        //在地图上绘制折线
        //mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
        return mPolyline;
    }

    /***
     * 定位结果回调，在此方法中处理定位结果
     */
    BDAbstractLocationListener mListener = new BDAbstractLocationListener() {

        @Override
        public void onReceiveLocation(BDLocation location) {
            if (null != location && location.getLocType() != BDLocation.TypeServerError) {
                StringBuffer sb = new StringBuffer(256);
                sb.append("time : ");
                /**
                 * 时间也可以使用systemClock.elapsedRealtime()方法 获取的是自从开机以来，每次回调的时间；
                 * location.getTime() 是指服务端出本次结果的时间，如果位置不发生变化，则时间不变
                 */
                sb.append(location.getTime());
                sb.append("\nlocType : ");// 定位类型
                sb.append(location.getLocType());
                sb.append("\nlocType description : ");// *****对应的定位类型说明*****
                sb.append(location.getLocTypeDescription());
                sb.append("\nlatitude : ");// 纬度
                sb.append(location.getLatitude());
                sb.append("\nlontitude : ");// 经度
                sb.append(location.getLongitude());
                sb.append("\nradius : ");// 半径
                sb.append(location.getRadius());
                sb.append("\nCountryCode : ");// 国家码
                sb.append(location.getCountryCode());
                sb.append("\nCountry : ");// 国家名称
                sb.append(location.getCountry());
                sb.append("\ncitycode : ");// 城市编码
                sb.append(location.getCityCode());
                sb.append("\ncity : ");// 城市
                sb.append(location.getCity());
                sb.append("\nDistrict : ");// 区
                sb.append(location.getDistrict());
                sb.append("\nStreet : ");// 街道
                sb.append(location.getStreet());
                sb.append("\naddr : ");// 地址信息
                sb.append(location.getAddrStr());
                sb.append("\nUserIndoorState: ");// *****返回用户室内外判断结果*****
                sb.append(location.getUserIndoorState());
                sb.append("\nDirection(not all devices have value): ");
                sb.append(location.getDirection());// 方向
                sb.append("\nlocationdescribe: ");
                sb.append(location.getLocationDescribe());// 位置语义化信息
                sb.append("\nPoi: ");// POI信息
                if (location.getPoiList() != null && !location.getPoiList().isEmpty()) {
                    for (int i = 0; i < location.getPoiList().size(); i++) {
                        Poi poi = (Poi) location.getPoiList().get(i);
                        sb.append(poi.getName() + ";");
                    }
                }
                if (location.getLocType() == BDLocation.TypeGpsLocation) {// GPS定位结果
                    sb.append("\nspeed : ");
                    sb.append(location.getSpeed());// 速度 单位：km/h
                    sb.append("\nsatellite : ");
                    sb.append(location.getSatelliteNumber());// 卫星数目
                    sb.append("\nheight : ");
                    sb.append(location.getAltitude());// 海拔高度 单位：米
                    sb.append("\ngps status : ");
                    sb.append(location.getGpsAccuracyStatus());// *****gps质量判断*****
                    sb.append("\ndescribe : ");
                    sb.append("gps定位成功");
                } else if (location.getLocType() == BDLocation.TypeNetWorkLocation) {// 网络定位结果
                    // 运营商信息
                    if (location.hasAltitude()) {// *****如果有海拔高度*****
                        sb.append("\nheight : ");
                        sb.append(location.getAltitude());// 单位：米
                    }
                    sb.append("\noperationers : ");// 运营商信息
                    sb.append(location.getOperators());
                    sb.append("\ndescribe : ");
                    sb.append("网络定位成功");
                } else if (location.getLocType() == BDLocation.TypeOffLineLocation) {// 离线定位结果
                    sb.append("\ndescribe : ");
                    sb.append("离线定位成功，离线定位结果也是有效的");
                } else if (location.getLocType() == BDLocation.TypeServerError) {
                    sb.append("\ndescribe : ");
                    sb.append("服务端网络定位失败，可以反馈IMEI号和大体定位时间到loc-bugs@baidu.com，会有人追查原因");
                } else if (location.getLocType() == BDLocation.TypeNetWorkException) {
                    sb.append("\ndescribe : ");
                    sb.append("网络不同导致定位失败，请检查网络是否通畅");
                } else if (location.getLocType() == BDLocation.TypeCriteriaException) {
                    sb.append("\ndescribe : ");
                    sb.append("无法获取有效定位依据导致定位失败，一般是由于手机的原因，处于飞行模式下一般会造成这种结果，可以试着重启手机");
                }
                LogUtils.i("BDABSTRACTLOCATIONLISTENER", sb.toString());
            }
        }

    };

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
        lineList = b.getData().getLineList();
        adHomePopup.setNewData(lineList);
        List<String> strings = new ArrayList<>();
        for (LineDeviceListBean.DataBean.LineListBean llb : lineList) {
            List<LineDeviceListBean.DataBean.LineListBean.DeviceListBean> deviceList = llb.getDeviceList();
            for (LineDeviceListBean.DataBean.LineListBean.DeviceListBean dlb : deviceList) {
                strings.add(dlb.getDeviceNum());
            }
        }
        getPresenter().deviceWebSocket(strings);
    }

    @Override
    public void queryLineDeviceListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }

    @Override
    public void onWebSocketSuccess(WebSocket webSocket) {
        ToastUtils.showShort("WebSocket连接成功");
        LogUtils.d("UpdateMarkerService", "Thread id is " + Thread.currentThread().getId());
        mWebSocket = webSocket;
        Timer timer = new Timer();
        timer.schedule(new TimerTask() {
            @Override
            public void run() {
                if (markerBeanList.size() > 0 && webDeviceBeans.size() > 0) {
                    for (WebDeviceBean wdb : webDeviceBeans) {
                        WebDeviceBean.DataBean data = wdb.getData();
                        String serialNum = data.getSerialNum();//序列号
                        int state = data.getState();//状态
                        WebDeviceBean.DataBean.GpsBean gps = data.getGps();//GPS
                        int balance = data.getBalance();//平衡状态
                        int humidity = data.getHumidity();//温度 -25<正常>80
                        int temperature = data.getTemperature();//湿度 正常<65

                        for (AllShowMarkerBean asmb : markerBeanList) {
                            List<Overlay> overlays = asmb.getOverlays();
                            if (overlays.size() > 0) {
                                for (Overlay ol : overlays) {
                                    String deviceNum = ol.getExtraInfo().getString("deviceNum");
                                    if (serialNum.equals(deviceNum)) {
//                                   机器状态 0手动 1自动 2配置 3过障 4充电 5低电 6信采集 7待机 8数据上传 9.设备离线
                                        Marker marker = (Marker) ol;
                                        BitmapDescriptor bitmap;
                                        switch (state) {
                                            case 9://设备离线
                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_e);
                                                marker.setIcon(bitmap);
                                                break;
                                            case 0://巡检
                                            case 1:
                                            case 2:
                                            case 3:
                                            case 6:
                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_c);
                                                marker.setIcon(bitmap);
                                                break;
                                            case 4://充电
                                            case 8:
                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_a);
                                                marker.setIcon(bitmap);
                                                break;
                                            case 7://待机
                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_b);
                                                marker.setIcon(bitmap);
                                                break;
                                            case 5://低电（报警）
                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_d);
                                                marker.setIcon(bitmap);
                                                break;
                                        }
//                                        int balance = data.getBalance();//平衡状态
//                                        int humidity = data.getHumidity();//温度 -25<正常>80
//                                        int temperature = data.getTemperature();//湿度 正常<65
                                        if (balance > 0 || humidity < -25 || humidity > 80 || temperature > 65) {
                                            bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_d);
                                            marker.setIcon(bitmap);
                                        }
                                    }

                                }
                            }
                        }

                    }
                }


            }
        }, 1000, 1000);

//
//        PRClientApp.timingThreadPool.scheduleWithFixedDelay(new Runnable() {
//            @Override
//            public void run() {
//                LogUtils.i("timingThreadPool");
//                if (markerBeanList.size() > 0 && webDeviceBeans.size() > 0) {
//                    for (WebDeviceBean wdb : webDeviceBeans) {
//                        WebDeviceBean.DataBean data = wdb.getData();
//                        String serialNum = data.getSerialNum();//序列号
//                        int state = data.getState();//状态
//                        WebDeviceBean.DataBean.GpsBean gps = data.getGps();//GPS
//                        int balance = data.getBalance();//平衡状态
//                        int humidity = data.getHumidity();//温度 -25<正常>80
//                        int temperature = data.getTemperature();//湿度 正常<65
//
//                        for (AllShowMarkerBean asmb : markerBeanList) {
//                            List<Overlay> overlays = asmb.getOverlays();
//                            if (overlays.size() > 0) {
//                                for (Overlay ol : overlays) {
//                                    String deviceNum = ol.getExtraInfo().getString("deviceNum");
//                                    if (serialNum.equals(deviceNum)) {
////                                   机器状态 0手动 1自动 2配置 3过障 4充电 5低电 6信采集 7待机 8数据上传 9.设备离线
//                                        Marker marker = (Marker) ol;
//                                        BitmapDescriptor bitmap;
//                                        switch (state) {
//                                            case 9://设备离线
//                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_e);
//                                                marker.setIcon(bitmap);
//                                                break;
//                                            case 0://巡检
//                                            case 1:
//                                            case 2:
//                                            case 3:
//                                            case 6:
//                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_c);
//                                                marker.setIcon(bitmap);
//                                                break;
//                                            case 4://充电
//                                            case 8:
//                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_a);
//                                                marker.setIcon(bitmap);
//                                                break;
//                                            case 7://待机
//                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_b);
//                                                marker.setIcon(bitmap);
//                                                break;
//                                            case 5://低电（报警）
//                                                bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_d);
//                                                marker.setIcon(bitmap);
//                                                break;
//                                        }
////                                        int balance = data.getBalance();//平衡状态
////                                        int humidity = data.getHumidity();//温度 -25<正常>80
////                                        int temperature = data.getTemperature();//湿度 正常<65
//                                        if (balance > 0 || humidity < -25 || humidity > 80 || temperature > 65) {
//                                            bitmap = BitmapDescriptorFactory.fromResource(R.mipmap.device_maker_d);
//                                            marker.setIcon(bitmap);
//                                        }
//                                    }
//
//                                }
//                            }
//                        }
//
//                    }
//                }
//
//            }
//        }, 1000, 1000, TimeUnit.MILLISECONDS);
    }

    private List<WebDeviceBean> webDeviceBeans = new ArrayList<>();

    @Override
    public void onWebSocketMessage(String text, int size) {
        ToastUtils.showShort(text);
        if (webDeviceBeans.size() == size) {
            webDeviceBeans.clear();
        }
        WebDeviceBean webDeviceBean = GsonUtils.fromJson(text, WebDeviceBean.class);
        webDeviceBeans.add(webDeviceBean);
        LogUtils.i("onWebSocketMessage:" + webDeviceBeans.size() + "--" + markerBeanList.size() + "::" + text);
        WebDeviceBean.DataBean data = webDeviceBean.getData();
        String serialNum = data.getSerialNum();
        int state = data.getState();


    }

    @Override
    public void onWebSocketClosed() {
        ToastUtils.showShort("WebSocketClosed");
    }

    @Override
    public void onWebSocketFailure(String strErr) {
        ToastUtils.showShort("WebSocketFailure：" + strErr);
        mWebSocket = null;
    }
}
