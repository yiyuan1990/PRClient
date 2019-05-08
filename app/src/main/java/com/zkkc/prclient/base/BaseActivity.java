package com.zkkc.prclient.base;

import android.Manifest;
import android.content.Context;
import android.os.Build;
import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.view.View;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;

import com.cy.dialog.BaseDialog;
import com.vise.xsnow.permission.OnPermissionCallback;
import com.vise.xsnow.permission.PermissionManager;
import com.zkkc.prclient.R;

import org.greenrobot.eventbus.EventBus;

import butterknife.ButterKnife;


/**
 * 父类->基类->动态指定类型->泛型设计（通过泛型指定动态类型->由子类指定，父类只需要规定范围即可）
 *
 * @param <V>
 * @param <P>
 */
public abstract class BaseActivity<V extends BaseView, P extends BasePresenter<V>> extends AppCompatActivity {
    public String TAG = getClass().getSimpleName();
    public Context mContext;
    //引用V层和P层
    private P presenter;
    private V view;

    public P getPresenter() {
        return presenter;
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        supportRequestWindowFeature(Window.FEATURE_NO_TITLE);
        setContentView(getLayoutId());
        ButterKnife.bind(this);
        if (isRegisterBus) {
            EventBus.getDefault().register(this);
        }
        mContext = this;
        if (presenter == null) {
            presenter = createPresenter();
        }
        if (view == null) {
            view = createView();
        }
        if (presenter != null && view != null) {
            presenter.attachView(view);
        }
        fullscreen(true);
        init();

    }

    private boolean isRegisterBus = false;

    public boolean isDoEventBus(boolean is) {
        this.isRegisterBus = is;
        return isRegisterBus;
    }

    @Override
    protected void onDestroy() {
        if (outDialog != null) {
            outDialog.dismiss();
            outDialog = null;
        }
        super.onDestroy();
        if (isRegisterBus) {
            EventBus.getDefault().unregister(this);
        }
    }

    //由子类指定具体类型
    public abstract int getLayoutId();

    public abstract P createPresenter();

    public abstract V createView();

    public abstract void init();


    public void fullscreen(boolean enable) {

        if (enable) { //显示状态栏

            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.flags |= WindowManager.LayoutParams.FLAG_FULLSCREEN;

            getWindow().setAttributes(lp);

            getWindow().addFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        } else { //隐藏状态栏

            WindowManager.LayoutParams lp = getWindow().getAttributes();

            lp.flags = (WindowManager.LayoutParams.FLAG_FULLSCREEN);

            getWindow().setAttributes(lp);
            getWindow().clearFlags(WindowManager.LayoutParams.FLAG_LAYOUT_NO_LIMITS);

        }
        hideBottomUIMenu();
    }

    /**
     * 退出Dialog
     */
    private BaseDialog outDialog;

    public void showCloseDialog() {
        outDialog = new BaseDialog(this);
        outDialog.contentView(R.layout.dialog_out)
                .canceledOnTouchOutside(false).show();
        Button btnCancel = outDialog.findViewById(R.id.btnCancel);
        Button btnOk = outDialog.findViewById(R.id.btnOk);
        btnCancel.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDialog.dismiss();
            }
        });
        btnOk.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                outDialog.dismiss();
                finish();
            }
        });


    }

    /**
     * 动态权限申请
     */
    public void permissionsSet() {
        PermissionManager.instance().request(this, new OnPermissionCallback() {
                    @Override
                    public void onRequestAllow(String permissionName) {
                    }

                    @Override
                    public void onRequestRefuse(String permissionName) {
                    }

                    @Override
                    public void onRequestNoAsk(String permissionName) {
                    }
                }, Manifest.permission.CAMERA,
                Manifest.permission.READ_PHONE_STATE,
                Manifest.permission.READ_EXTERNAL_STORAGE,
                Manifest.permission.WRITE_EXTERNAL_STORAGE,
                Manifest.permission.ACCESS_FINE_LOCATION,
                Manifest.permission.ACCESS_COARSE_LOCATION,
                Manifest.permission.RECORD_AUDIO);
    }

    private void hideBottomUIMenu() {
        //隐藏虚拟按键，并且全屏
        if (Build.VERSION.SDK_INT > 11 && Build.VERSION.SDK_INT < 19) { // lower api
            View v = this.getWindow().getDecorView();
            v.setSystemUiVisibility(View.GONE);
        } else if (Build.VERSION.SDK_INT >= 19) {

            Window _window = getWindow();
            WindowManager.LayoutParams params = _window.getAttributes();
            params.systemUiVisibility = View.SYSTEM_UI_FLAG_HIDE_NAVIGATION | View.SYSTEM_UI_FLAG_IMMERSIVE;
            _window.setAttributes(params);
        }
    }


}
