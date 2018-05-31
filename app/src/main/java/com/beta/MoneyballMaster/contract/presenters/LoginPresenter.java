package com.beta.MoneyballMaster.contract.presenters;

import com.beta.MoneyballMaster.contract.models.LoginModel;
import com.beta.MoneyballMaster.entity.UserInfo;
import com.beta.MoneyballMaster.contract.LoginContract;
import com.beta.MoneyballMaster.contract.base.BasePresenter;
import com.beta.MoneyballMaster.http.callback.ApiCallBack;

/**
 * Created by Administrator on 2016/7/22.
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {
    private LoginModel loginModel;

    public LoginPresenter(LoginContract.ILoginView mView) {
       attach(mView);
        loginModel=new LoginModel();
    }
    /**
     * 登录
     */
    @Override
    public void login(String userName,String pwd) {
        mView.showProgress();
        loginModel.login(userName, pwd, new ApiCallBack<UserInfo>() {
            @Override
            public void onSuccessful(UserInfo userInfo) {
                mView.toMain();
            }

            @Override
            public void onFailed(int errorCode, String errorMsg) {
                mView.showErrorMsg(errorMsg);
            }

            @Override
            public void onCompleted() {
                mView.hideProgress();
            }
        });
    }

    @Override
    public void onUnsubscribe() {
        loginModel.onUnsubscribe();
    }
}
