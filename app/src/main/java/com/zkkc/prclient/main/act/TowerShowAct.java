package com.zkkc.prclient.main.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.text.Editable;
import android.text.TextWatcher;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.main.adapter.TowerContentAd;
import com.zkkc.prclient.main.contract.TowerShowContract;
import com.zkkc.prclient.main.entiy.MediaListBean;
import com.zkkc.prclient.main.entiy.TowerAdBean;
import com.zkkc.prclient.main.entiy.TowerListBean;
import com.zkkc.prclient.main.p.TowerShowPresenter;

import org.greenrobot.eventbus.EventBus;
import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.OnClick;

/**
 * Created by ShiJunRan on 2019/5/9
 * 塔文件夹展示
 */
public class TowerShowAct extends BaseActivity<TowerShowContract.View, TowerShowContract.Presenter> implements TowerShowContract.View {


    @BindView(R.id.etTower)
    EditText etTower;
    @BindView(R.id.rvTower)
    RecyclerView rvTower;

    MediaListBean.ListBean bean;
    TowerContentAd towerContentAd;
    List<TowerAdBean> mList;
    @BindView(R.id.ivBack)
    ImageView ivBack;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(MediaListBean.ListBean bean) {
        this.bean = bean;
        getPresenter().queryTowerList(bean.getId(), "", 1, 1000);
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EventBus.getDefault().register(this);
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        EventBus.getDefault().unregister(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.tower_show_act;
    }

    @Override
    public TowerShowContract.Presenter createPresenter() {
        return new TowerShowPresenter(this);
    }

    @Override
    public TowerShowContract.View createView() {
        return this;
    }

    @Override
    public void init() {
        towerContentAd = new TowerContentAd(R.layout.item_tower, mList);
        rvTower.setLayoutManager(new GridLayoutManager(this, 5));
        rvTower.setAdapter(towerContentAd);
        towerContentAd.setOnItemClickListener(new BaseQuickAdapter.OnItemClickListener() {
            @Override
            public void onItemClick(BaseQuickAdapter adapter, View view, int position) {

            }
        });

        etTower.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
            }

            @Override
            public void afterTextChanged(Editable s) {
                String towerStr = etTower.getText().toString().trim();
                getPresenter().queryTowerList(bean.getId(), towerStr, 1, 1000);
            }
        });
    }


    @Override
    public void queryTowerListSuccess(TowerListBean b) {
        List<TowerListBean.DataBean> data = b.getData();
        mList = new ArrayList<>();
        for (int i = 0; i < data.size(); i++) {
            TowerAdBean tab = new TowerAdBean();
            tab.setTowerNum(data.get(i).getTowerNum());
            tab.setDeviceName(bean.getDeviceName());
            tab.setLineName(bean.getLineName());
            tab.setMdate(bean.getDate());
            mList.add(tab);
        }
        towerContentAd.setNewData(mList);
    }

    @Override
    public void queryTowerListFailure(String strErr) {
        ToastUtils.showShort(strErr);
    }

    @OnClick(R.id.ivBack)
    public void onViewClicked() {
        finish();
    }
}
