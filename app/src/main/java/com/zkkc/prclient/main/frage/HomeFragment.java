package com.zkkc.prclient.main.frage;

import android.os.Bundle;
import android.os.Message;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.RelativeLayout;

import com.baidu.location.BDAbstractLocationListener;
import com.baidu.location.BDLocation;
import com.baidu.location.LocationClientOption;
import com.baidu.location.Poi;
import com.baidu.mapapi.map.BaiduMap;
import com.baidu.mapapi.map.BitmapDescriptor;
import com.baidu.mapapi.map.BitmapDescriptorFactory;
import com.baidu.mapapi.map.MapStatusUpdateFactory;
import com.baidu.mapapi.map.MapView;
import com.baidu.mapapi.map.Marker;
import com.baidu.mapapi.map.MarkerOptions;
import com.baidu.mapapi.map.Overlay;
import com.baidu.mapapi.map.OverlayOptions;
import com.baidu.mapapi.map.PolylineOptions;
import com.baidu.mapapi.model.LatLng;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkkc.prclient.PRClientApp;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseFragment;
import com.zkkc.prclient.main.adapter.AdHomePopup;
import com.zkkc.prclient.main.adapter.AdHomePopupDevice;
import com.zkkc.prclient.main.contract.HomeContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.p.HomePresenter;
import com.zkkc.prclient.main.utils.LocationService;

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
    @BindView(R.id.bMapView)
    MapView mMapView;

    BaiduMap mBaiduMap;
    private LocationService locationService;

    private List<Integer> lineColors = new ArrayList<>();
    private List<LineDeviceListBean.DataBean.LineListBean> lineList;
    AdHomePopup adHomePopup;
    AdHomePopupDevice adHomePopupDevice;

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
                ToastUtils.showShort("点击" + position);
                List<LineDeviceListBean.DataBean.LineListBean> list = adapter.getData();
                RecyclerView rvDevice = (RecyclerView) adHomePopup.getViewByPosition(recyclerView, position, R.id.rvDevice);
                ImageView ivLine = (ImageView) adHomePopup.getViewByPosition(recyclerView, position, R.id.ivLine);
                ImageView ivMark = (ImageView) adHomePopup.getViewByPosition(recyclerView, position, R.id.ivMark);
                if (rvDevice.getAdapter() == null) {
                    //线路绘制颜色和角标显示
                    ivMark.setImageResource(R.mipmap.icon_up);
                    if (lineColors.size() > 0) {
                        ivLine.setVisibility(View.VISIBLE);
                        ivLine.setImageResource(lineColors.get(0));
                        list.get(position).setIsSelected(lineColors.get(0));
                        lineColors.remove(0);
                        //设备列表
                        adHomePopupDevice = new AdHomePopupDevice(R.layout.item_home_popup_a, list.get(position).getDeviceList());
                        rvDevice.setLayoutManager(new LinearLayoutManager(mContext));
                        rvDevice.setAdapter(adHomePopupDevice);
                        adHomePopupDevice.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                            @Override
                            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                                ToastUtils.showShort("设备-" + position);

                            }
                        });
                        recyclerView.scrollToPosition(position);
                    } else {
                        ToastUtils.showShort("最多同时显示5条线路");
                    }


                } else {
                    //线路绘制颜色和角标显示
                    ivMark.setImageResource(R.mipmap.icon_down);
                    ivLine.setVisibility(View.GONE);
                    lineColors.add(list.get(position).getIsSelected());
                    //设备列表
                    rvDevice.setAdapter(null);
                }
                //当前线路的Line绘制
                List<LineDeviceListBean.DataBean.LineListBean.TowerListBean> towerList = list.get(position).getTowerList();
                List<LatLng> points = new ArrayList<LatLng>();
                for (int i = 0; i < towerList.size(); i++) {
                    List<Double> gps = towerList.get(i).getGps();
                    Double aDouble = gps.get(1);
                    Double aDouble2 = gps.get(0);
                    LatLng latLng = new LatLng(aDouble2, aDouble);
                    points.add(latLng);
                }
                addDeviceLine(points);

                String lat = list.get(position).getDeviceList().get(0).getLat();
                String lng = list.get(position).getDeviceList().get(0).getLng();
                mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(Double.valueOf(lat), Double.valueOf(lng))));

            }
        });


    }

    /**
     * 百度地图配置
     */
    private void initBaiDuMap() {
        mBaiduMap = mMapView.getMap();
        mBaiduMap.setMapType(BaiduMap.MAP_TYPE_NORMAL);
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.zoomTo(14));
        mBaiduMap.setMapStatus(MapStatusUpdateFactory.newLatLng(new LatLng(23.112018, 113.329817)));
        //LocationService
        locationService = PRClientApp.locationService;
        LocationClientOption mOption = locationService.getDefaultLocationClientOption();
        mOption.setLocationMode(LocationClientOption.LocationMode.Battery_Saving);
        mOption.setCoorType("bd09ll");
        locationService.setLocationOption(mOption);
        locationService.registerListener(mListener);
        locationService.start();
        //添加Line
        //构建折线点坐标     113.329817,23.112018
        LatLng p1 = new LatLng(22.999313, 113.336756);
        LatLng p2 = new LatLng(23.023517, 113.328086);
        LatLng p3 = new LatLng(23.112018, 113.329817);
        LatLng p4 = new LatLng(23.13692, 113.328015);
        List<LatLng> points = new ArrayList<LatLng>();
        points.add(p1);
        points.add(p2);
        points.add(p3);
        points.add(p4);
        addDeviceLine(points);

        //添加设备Maker
        addDeviceMaker(22.999313, 113.336756, "AAA");
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
     * 添加Maker
     *
     * @param lat
     * @param lon
     * @param title
     */
    private void addDeviceMaker(double lat, double lon, String title) {
        //定义Maker坐标点
//        LatLng point = new LatLng(23.166997, 113.481745);
        LatLng point = new LatLng(lat, lon);
        //构建Marker图标
        BitmapDescriptor bitmap = BitmapDescriptorFactory
                .fromResource(R.mipmap.maker_tower);
        //构建MarkerOption，用于在地图上添加Marker
        OverlayOptions option = new MarkerOptions()
                .icon(bitmap)
                .position(point)
                .animateType(MarkerOptions.MarkerAnimateType.jump)
                .perspective(true)
                .draggable(false)
                .title(title)
                .flat(false);
//在地图上添加Marker，并显示
        mBaiduMap.addOverlay(option);
    }

    /**
     * 添加Line
     *
     * @param points
     */
    private void addDeviceLine(List<LatLng> points) {
        //设置折线的属性
        OverlayOptions mOverlayOptions = new PolylineOptions()
                .width(10)
                .color(0xFF8659ed)
                .points(points);
        //在地图上绘制折线
        //mPloyline 折线对象
        Overlay mPolyline = mBaiduMap.addOverlay(mOverlayOptions);
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
    }

    @Override
    public void queryLineDeviceListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }
}
