package com.zkkc.prclient.login.act;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;

import com.blankj.utilcode.util.EncryptUtils;
import com.blankj.utilcode.util.LogUtils;
import com.blankj.utilcode.util.ObjectUtils;
import com.blankj.utilcode.util.SPUtils;
import com.blankj.utilcode.util.ToastUtils;
import com.zkkc.prclient.R;
import com.zkkc.prclient.base.BaseActivity;
import com.zkkc.prclient.login.contract.LoginContract;
import com.zkkc.prclient.login.entiy.LoginBean;
import com.zkkc.prclient.login.entiy.LoginRequest;
import com.zkkc.prclient.login.p.LoginPresenter;
import com.zkkc.prclient.main.act.MainActivity;
import com.zkkc.prclient.weight.MCheckBox;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

/**
 * Created by ShiJunRan on 2019/4/28
 */
public class LoginAct extends BaseActivity<LoginContract.View, LoginContract.Presenter> implements LoginContract.View {
    @BindView(R.id.etName)
    EditText etName;
    @BindView(R.id.etPW)
    EditText etPW;
    @BindView(R.id.btnLogin)
    Button btnLogin;
    @BindView(R.id.mcb)
    MCheckBox mcb;
    @BindView(R.id.ivClose)
    ImageView ivClose;
    public static final String USER_NAME = "USER_NAME";
    public static final String USER_PW = "USER_PW";
    public static final String USER_MCB = "USER_MCB";
    public static final String ACCESSTOKEN = "ACCESSTOKEN";
    public static final String EXPIRETIME = "EXPIRETIME";

    @Override
    public int getLayoutId() {
        return R.layout.login_act;
    }

    @Override
    public LoginContract.Presenter createPresenter() {
        return new LoginPresenter(this);
    }

    @Override
    public LoginContract.View createView() {
        return this;
    }


    @Override
    public void init() {
        //动态权限申请
        permissionsSet();
        etName.setText(SPUtils.getInstance().getString(USER_NAME));
        etPW.setText(SPUtils.getInstance().getString(USER_PW));
        if (SPUtils.getInstance().getBoolean(USER_MCB)) {
            mcb.setChecked(true);
        } else {
            mcb.setChecked(false);
        }
    }

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        ButterKnife.bind(this);
    }

    String pw = "";

    @OnClick({R.id.ivClose, R.id.btnLogin})
    public void onViewClicked(View view) {
        switch (view.getId()) {
            case R.id.ivClose:
                showCloseDialog();
                break;
            case R.id.btnLogin:
                String nameStr = etName.getText().toString().trim();
                String pwStr = etPW.getText().toString().trim();
                if (ObjectUtils.isEmpty(nameStr) || ObjectUtils.isEmpty(pwStr)) {
                    ToastUtils.showShort("用户名或密码不能为空");
                } else {
                    if (mcb.isChecked()) {
                        SPUtils.getInstance().put(USER_NAME, nameStr);
                        SPUtils.getInstance().put(USER_PW, pwStr);
                        SPUtils.getInstance().put(USER_MCB, true);
                    } else {
                        SPUtils.getInstance().put(USER_NAME, "");
                        SPUtils.getInstance().put(USER_PW, "");
                        SPUtils.getInstance().put(USER_MCB, false);
                    }
                    pw = nameStr+pwStr ;
                    pw = EncryptUtils.encryptMD5ToString(pw).toLowerCase();
                    LogUtils.i(pw);
                    LoginRequest b = new LoginRequest();
                    b.setUsername(nameStr);
                    b.setPassword(pw);
                    getPresenter().login(b);
                }

                break;
        }
    }

    @Override
    public void onSuccess(LoginBean bean) {
        LogUtils.i(TAG, bean.toString());
        SPUtils.getInstance().put(ACCESSTOKEN, bean.getAccessToken());
        SPUtils.getInstance().put(EXPIRETIME, bean.getExpireTime());
        startActivity(new Intent(LoginAct.this, MainActivity.class));
        finish();
    }

    @Override
    public void onFailure(String strErr) {
        LogUtils.i(TAG, strErr);
        ToastUtils.showShort("登录失败，请检查用户名或密码是否正确");
    }
}
