package com.zkkc.prclient.main.act;

import android.os.Bundle;
import android.support.v4.app.Fragment;
import android.view.View;

import com.next.easynavigation.constant.Anim;
import com.next.easynavigation.view.EasyNavigationBar;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.base.BasePresenter;
import com.zkkc.prclient.base.BaseView;
import com.zkkc.prclient.main.frage.HomeFragment;
import com.zkkc.prclient.main.frage.PicFragment;
import com.zkkc.prclient.main.frage.VideoFragment;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

public class MainActivity extends BaseActivity {

    @BindView(R.id.navigationBar)
    EasyNavigationBar navigationBar;




    private String[] tabText = {"首页", "视频", "图片", "退出"};
    //未选中icon
    private int[] normalIcon = {R.mipmap.tab_home, R.mipmap.tab_video, R.mipmap.tab_pic, R.mipmap.tab_out};
    //选中时icon
    private int[] selectIcon = {R.mipmap.tab_home_a, R.mipmap.tab_video_a, R.mipmap.tab_pic_a, R.mipmap.tab_out_a};

    private List<Fragment> fragments = new ArrayList<>();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    @Override
    public int getLayoutId() {
        return R.layout.main_act;
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
        fragments.add(new HomeFragment());
        fragments.add(new VideoFragment());
        fragments.add(new PicFragment());
        navigationBar.titleItems(tabText)
                .normalIconItems(normalIcon)
                .selectIconItems(selectIcon)
                .fragmentList(fragments)
                .normalTextColor(getResources().getColor(R.color.gray))
                .selectTextColor(getResources().getColor(R.color.orange))
                .iconSize(22)     //Tab图标大小
                .tabTextSize(10)   //Tab文字大小
                .tabTextTop(2)     //Tab文字距Tab图标的距离
                .navigationHeight(50)//导航栏高度
                .navigationBackground(getResources().getColor(R.color.light_white))   //导航栏背景色
                .onTabClickListener(new EasyNavigationBar.OnTabClickListener() {   //Tab点击事件  return true 页面不会切换
                    @Override
                    public boolean onTabClickEvent(View view, int position) {
                        if (position == 3) {
                            showCloseDialog();
                            return true;
                        } else {
                            return false;
                        }
                    }
                })
                .anim(Anim.Pulse) //点击Tab时的动画
                .smoothScroll(false)  //点击Tab  Viewpager切换是否有动画
                .canScroll(false)    //Viewpager能否左右滑动
                .fragmentManager(getSupportFragmentManager())
                .build();
    }


}
