package com.wys.baseframedemo.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.wys.baseframedemo.R;
import com.wys.baseframedemo.activity.base.BaseActivity;
import com.wys.baseframedemo.contract.LoginContract;
import com.wys.baseframedemo.contract.presenters.LoginPresenter;
import com.wys.baseframedemo.utils.ToastUtils;

import butterknife.BindView;
import butterknife.ButterKnife;
import butterknife.OnClick;

public class LoginActivity extends BaseActivity implements LoginContract.ILoginView {


    @BindView(R.id.et_login_userName)
    EditText etLoginUserName;
    @BindView(R.id.et_login_pwd)
    EditText etLoginPwd;
    @BindView(R.id.btn_login)
    Button btnLogin;
    @BindView(R.id.pb_progress)
    ProgressBar pbProgress;
    @BindView(R.id.tv_title)
    TextView tvTitle;
    private LoginPresenter mLoginPresenter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);
        ButterKnife.bind(this);
        tvTitle.setText("用户登录");
        mLoginPresenter = new LoginPresenter(this);
    }


    @Override
    public Context getCurContext() {
        return this;
    }

    @Override
    public void showProgress() {
        pbProgress.setVisibility(View.VISIBLE);
    }

    @Override
    public void hideProgress() {
        pbProgress.setVisibility(View.GONE);
    }

    @Override
    public void showInfo(String info) {
        ToastUtils.showToast(this, info);
    }

    @Override
    public void showErrorMsg(String msg) {
        ToastUtils.showToast(this, msg);
    }

    @Override
    public void toMain() {
        ToastUtils.showToast(this, "toMainActivity");
    }

    @Override
    public void toRegister() {
        ToastUtils.showToast(this, "toRegisterActivity");
    }

    public void onRegister(View view) {
        toRegister();
    }

    @OnClick({R.id.iv_title_back, R.id.btn_login})
    public void onClick(View view) {
        switch (view.getId()) {
            case R.id.iv_title_back:
                finish();
                break;
            case R.id.btn_login:
                mLoginPresenter.login(etLoginUserName.getText().toString(),etLoginPwd.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.deAttach();
    }
}
