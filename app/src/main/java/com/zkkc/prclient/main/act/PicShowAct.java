package com.zkkc.prclient.main.act;

import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;
import com.bumptech.glide.request.RequestOptions;
import com.github.chrisbanes.photoview.PhotoView;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.entiy.PicShowBean;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ShiJunRan on 2019/5/9
 */
public class PicShowAct extends BaseActivity {

    @BindView(R.id.photoView)
    PhotoView photoView;
    @BindView(R.id.btnCancel)
    ImageView btnCancel;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(PicShowBean tab) {
        RequestOptions requestOptions = new RequestOptions();
        requestOptions.placeholder(R.mipmap.pic_loading);
        requestOptions.error(R.mipmap.pic_load_err);
        requestOptions.diskCacheStrategy(DiskCacheStrategy.ALL);
        Glide.with(mContext)
                .load(tab.getPicUrl())
                .apply(requestOptions)
                .thumbnail(1)
                .into(photoView);

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
        return R.layout.pic_show_act;
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

    }


    @OnClick(R.id.btnCancel)
    public void onViewClicked() {
        finish();
    }
}
