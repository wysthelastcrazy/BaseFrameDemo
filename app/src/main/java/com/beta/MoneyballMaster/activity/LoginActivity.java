package com.beta.MoneyballMaster.activity;

import android.content.Context;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ProgressBar;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseActivity;
import com.beta.MoneyballMaster.contract.LoginContract;
import com.beta.MoneyballMaster.contract.presenters.LoginPresenter;
import com.beta.MoneyballMaster.utils.MyLog;
import com.beta.MoneyballMaster.utils.ToastUtils;
import com.beta.MoneyballMaster.widget.RandomCodeView;

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
    @BindView(R.id.codeView)
    RandomCodeView codeView;
    private LoginPresenter mLoginPresenter;

    @Override
    protected int getLayoutRes() {
        return R.layout.activity_login;
    }

    @Override
    protected void initLayout() {
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
                MyLog.debug(TAG,"[onClick]  code:"+codeView.getRandomCode());

                mLoginPresenter.login(etLoginUserName.getText().toString(), etLoginPwd.getText().toString());
                break;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        mLoginPresenter.deAttach();
    }
}
