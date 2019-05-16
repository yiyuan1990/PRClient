package com.zkkc.prclient.main.act;

import android.os.Bundle;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.blankj.utilcode.util.ToastUtils;
import com.chad.library.adapter.base.BaseQuickAdapter;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.main.adapter.AdSelect;
import com.zkkc.prclient.main.adapter.AdSelectTwo;
import com.zkkc.prclient.main.contract.MediaContract;
import com.zkkc.prclient.main.entiy.LineDeviceListBean;
import com.zkkc.prclient.main.p.MediaPresenter;

import org.greenrobot.eventbus.Subscribe;
import org.greenrobot.eventbus.ThreadMode;

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
    @BindView(R.id.btnHistoryVideo)
    Button btnHistoryVideo;
    @BindView(R.id.rvVideo)
    RecyclerView rvVideo;
    @BindView(R.id.rvSelect)
    RecyclerView rvSelect;
    @BindView(R.id.ivPopupClose)
    ImageView ivPopupClose;
    @BindView(R.id.rlPopup)
    LinearLayout rlPopup;

    private List<LineDeviceListBean.DataBean.LineListBean> lineList;
    AdSelect adSelect;
    AdSelectTwo adSelectTwo;
    View header;
    TextView tvDeviceNa;

    @Subscribe(threadMode = ThreadMode.MAIN, sticky = true)
    public void mEvent(LineDeviceListBean.DataBean.LineListBean.DeviceListBean dLB) {


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


    @Override
    public void init() {


    }


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @OnClick({R.id.ivBack, R.id.btnMoreLine, R.id.btnHistoryVideo, R.id.ivPopupClose})
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
            case R.id.btnHistoryVideo:
                ToastUtils.showShort("历史视频");
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

    }
}
