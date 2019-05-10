package com.zkkc.prclient.main.act;

import android.content.Intent;
import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.TimeUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.vise.xsnow.http.ViseHttp;
import com.vise.xsnow.http.callback.ACallback;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseResult;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.adapter.AdPic;
import com.zkkc.prclient.main.adapter.AdVideo;
import com.zkkc.prclient.main.entiy.PicBean;
import com.zkkc.prclient.main.entiy.PicShowBean;
import com.zkkc.prclient.main.entiy.TowerAdBean;
import com.zkkc.prclient.main.entiy.VideoBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.Locale;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

import static com.zkkc.prclient.PRCConstant.TOWER_VIDEODATA;
import static com.zkkc.prclient.login.act.LoginAct.ACCESSTOKEN;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class TowerVideoAct extends BaseActivity {


    @BindView(R.id.ivBack)
    ImageView ivBack;
    @BindView(R.id.tvTabName)
    TextView tvTabName;
    @BindView(R.id.rvTower)
    RecyclerView rvTower;

    TowerAdBean tab;
    String mUrl;
    List<PicShowBean> mList;
    AdVideo adVideo;
    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(final TowerAdBean tab) {
        this.tab = tab;
        tvTabName.setText(tab.getLineName() + "      " + tab.getDeviceName() + "      塔号" + tab.getTowerNum()
                + "      时间：" + TimeUtils.millis2String(Long.parseLong(tab.getMdate()) * 1000, new SimpleDateFormat("yyyy-MM-dd HH:mm", Locale.ENGLISH)));
        mUrl = tab.getPlayUrl() + tab.getBaseUrl();
        ViseHttp.GET(TOWER_VIDEODATA)
                .addHeader("accessToken", SPUtils.getInstance().getString(ACCESSTOKEN))
                .addParam("tid", tab.getTid())
                .request(new ACallback<BaseResult<VideoBean>>() {
                    @Override
                    public void onSuccess(BaseResult<VideoBean> data) {
                        //
                        //http://172.16.1.152:180//cspid-ES-01-007_20190320/12/photo/camera_1000-1001-008-SXXX-171229_12_0_20190320_12.jpg
                        if (data != null) {
                            if (data.getCode() == 200) {
                                mList = new ArrayList<>();
                                VideoBean.VideoDataBean videoData = data.getData().getVideoData();
                                List<String> fileName = videoData.getFileName();
                                for (String s : fileName) {
                                    PicShowBean psb = new PicShowBean();
                                    psb.setPicName(s);
                                    psb.setPicUrl(mUrl + videoData.getPath() + "/" + s);
                                    mList.add(psb);
                                }
                                adVideo.setNewData(mList);
                            } else {
                                ToastUtils.showShort(data.getMsg());
                            }
                        } else {
                            ToastUtils.showShort("请求失败");
                        }
                    }

                    @Override
                    public void onFail(int errCode, String errMsg) {
                        ToastUtils.showShort(errMsg);
                    }
                });

    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.tower_pic_show_act;
    }

    @Override
    public BasePresenter createPresenter() {
        return null;
    }

    @Override
    public BaseView createView() {
        return null;
    }

    @Override
    public void init() {
        adVideo = new AdVideo(R.layout.item_tower_pic_video, mList);
        rvTower.setLayoutManager(new GridLayoutManager(this, 4));
        rvTower.setAdapter(adVideo);
        adVideo.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {
                List<PicShowBean> psbList =  adapter.getData();
                if (!psbList.get(position).isErr()){
                    EventBus.getDefault().postSticky(mList.get(position));
                    startActivity(new Intent(TowerVideoAct.this, VideoShowAct.class));
                }else {
                    ToastUtils.showShort("视频资源不存在");
                }

            }
        });
    }


    @OnClick({R.id.ivBack})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivBack:
                finish();
                break;

        }
    }
}
