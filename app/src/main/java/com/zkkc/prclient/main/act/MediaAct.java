package com.zkkc.prclient.main.act;

import android.os.Build;
import android.os.Bundle;
import android.support.annotation.RequiresApi;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.ezvizuikit.open.EZUIError;
import com.ezvizuikit.open.EZUIKit;
import com.ezvizuikit.open.EZUIPlayer;
import com.github.ybq.android.spinkit.SpinKitView;
import com.github.ybq.android.spinkit.sprite.Sprite;
import com.github.ybq.android.spinkit.style.DoubleBounce;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.main.adapter.AdSelect;
import com.zkkc.prclient.main.adapter.AdSelectTwo;
import com.zkkc.prclient.main.contract.MediaContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.entiy.MyAccessTokenResult;
import com.zkkc.prclient.main.p.MediaPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.Calendar;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;


/**
 * Created by ShiJunRan on 2019/5/16
 * 实时视频
 */
public class MediaAct extends BaseActivity<MediaContract.View, MediaContract.Presenter> implements MediaContract.View {

    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvContent)
    TextView tvContent;
    @BindView(R.id.btnMoreLine)
    Button btnMoreLine;
    @BindView(R.id.rvSelect)
    RecyclerView rvSelect;
    @BindView(R.id.ivPopupClose)
    ImageView ivPopupClose;
    @BindView(R.id.rlPopup)
    LinearLayout rlPopup;
    @BindView(R.id.eZUIPlayer)
    EZUIPlayer mPlayer;
    @BindView(R.id.tvTip)
    TextView tvTip;

    private List<LineDeviceListBean.DataBean.LineListBean> lineList;
    AdSelect adSelect;
    AdSelectTwo adSelectTwo;
    View header;
    TextView tvDeviceNa;
    String deviceNum;
    String cameraNum;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(LineDeviceListBean.DataBean.LineListBean.DeviceListBean dLB) {
        deviceNum = dLB.getDeviceNum();
        cameraNum = dLB.getCameraNum();
        getPresenter().getMyAccessToken();//获取萤石云token
    }

    @Override
    protected void onStop() {
        super.onStop();
        //停止播放
        mPlayer.stopPlay();
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        //释放资源
        mPlayer.releasePlayer();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.media_act;
    }

    @Override
    public MediaContract.Presenter createPresenter() {
        return new MediaPresenter(this);
    }

    @Override
    public MediaContract.View createView() {
        return this;
    }


    @RequiresApi(api = Build.VERSION_CODES.P)
    @Override
    public void init() {
        //创建loadingview
        View mLoadView = LayoutInflater.from(getApplicationContext()).inflate(R.layout.loading_spinkit, null);
        RelativeLayout.LayoutParams lp = new RelativeLayout.LayoutParams(
                RelativeLayout.LayoutParams.WRAP_CONTENT, RelativeLayout.LayoutParams.WRAP_CONTENT);
        lp.addRule(RelativeLayout.CENTER_IN_PARENT);
        mLoadView.setLayoutParams(lp);
        //设置loadingview
        mPlayer.setLoadingView(mLoadView);

    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @OnClick({R.id.ivBack, R.id.btnMoreLine, R.id.ivPopupClose})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;
            case R.id.btnMoreLine:
                if (rlPopup.getVisibility() == View.GONE) {
                    rlPopup.setVisibility(View.VISIBLE);
                    getPresenter().queryLineDeviceList();
                }
                break;
            case R.id.ivPopupClose:
                if (rlPopup.getVisibility() == View.VISIBLE) {
                    rlPopup.setVisibility(View.GONE);
                }
                break;
        }
    }

    @Override
    public void queryLineDeviceListSuccess(LineDeviceListBean b) {
        lineList = b.getData().getLineList();
        adSelect = new AdSelect(R.layout.item_select_line, lineList);
        rvSelect.setLayoutManager(new GridLayoutManager(MediaAct.this, 8));
        rvSelect.setAdapter(adSelect);
        adSelect.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                final LineDeviceListBean.DataBean.LineListBean llb = (LineDeviceListBean.DataBean.LineListBean) adapter.getData().get(position);

                adSelectTwo = new AdSelectTwo(R.layout.item_select_line, llb.getDeviceList());
                rvSelect.setAdapter(adSelectTwo);
                adSelectTwo.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
                    @Override
                    public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                        rlPopup.setVisibility(View.GONE);

                        tvTip.setVisibility(View.GONE);
                        getPresenter().getMyAccessToken();
                        cameraNum = llb.getDeviceList().get(position).getCameraNum();
                        ToastUtils.showShort(llb.getDeviceList().get(position).getDeviceName());
                    }
                });
                header = LayoutInflater.from(getApplicationContext()).inflate(R.layout.header_select_recycler, null);
                tvDeviceNa = header.findViewById(R.id.tvDeviceNa);
                tvDeviceNa.setText(llb.getLineName());
                adSelectTwo.addHeaderView(header);
                tvDeviceNa.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        rvSelect.setAdapter(adSelect);
                    }
                });


            }
        });

    }

    @Override
    public void queryLineDeviceListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }

    @Override
    public void accessTokenSuccess(MyAccessTokenResult ob) {
        LogUtils.i("MyAccessTokenResult", ob.getData().getAppKey() + "/n" + ob.getData().getAccessToken() + "/n" + ob.getData().getExpireTime());

        //初始化EZUIKit
        EZUIKit.initWithAppKey(getApplication(), ob.getData().getAppKey());
        //设置授权token
        EZUIKit.setAccessToken(ob.getData().getAccessToken());
        //设置播放回调callback
        mPlayer.setCallBack(callBack);
        String url = "ezopen://open.ys7.com/" + cameraNum + "/1.hd.live";
        LogUtils.i("MySJR_url:" + url);
        //设置播放参数
        mPlayer.setUrl(url);

    }

    @Override
    public void accessTokenFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }

    EZUIPlayer.EZUIPlayerCallBack callBack = new EZUIPlayer.EZUIPlayerCallBack() {
        @Override
        public void onPlaySuccess() {
            LogUtils.i("MySJR:" + "onPlaySuccess");
            tvTip.setVisibility(View.GONE);

        }

        @Override
        public void onPlayFail(EZUIError ezuiError) {
            LogUtils.i("MySJR:" + "onPlayFail");
            tvTip.setVisibility(View.VISIBLE);
            tvTip.setText(doErrorString(ezuiError.getErrorString()));
        }

        @Override
        public void onVideoSizeChange(int i, int i1) {
            LogUtils.i("MySJR:" + "onVideoSizeChange");
        }

        @Override
        public void onPrepared() {
            LogUtils.i("MySJR:" + "onPrepared");
            //开始播放
            mPlayer.startPlay();
        }

        @Override
        public void onPlayTime(Calendar calendar) {
            LogUtils.i("MySJR:" + "onPlayTime");
        }

        @Override
        public void onPlayFinish() {
            LogUtils.i("MySJR:" + "onPlayFinish");
        }
    };
    private static final String UE102 = "UE102";
    private static final String UE103 = "UE103";
    private static final String UE105 = "UE105";
    private static final String UE106 = "UE106";

    private String doErrorString(String ezuiErrorStr) {
        String errStr = "";
        switch (ezuiErrorStr) {
            case UE102:
                errStr = "设备不在线，确认设备上线之后重试";
                break;
            case UE103:
                errStr = "播放失败，请求连接设备超时";
                break;
            case UE105:
                errStr = "视频播放失败";
                break;
            case UE106:
                errStr = "当前账号开启了终端绑定，只允许指定设备登录操作";
                break;
            default:
                errStr = "视频播放失败";
        }

        return errStr;
    }


}
