package com.wys.baseframedemo.contract.presenters;

import com.wys.baseframedemo.entity.UserInfo;
import com.wys.baseframedemo.contract.LoginContract;
import com.wys.baseframedemo.contract.base.BasePresenter;
import com.wys.baseframedemo.http.base.ApiCallBack;
import com.wys.baseframedemo.http.base.SubscriberCallBack;
import java.util.HashMap;
import java.util.Map;

/**
 * Created by Administrator on 2016/7/22.
 */
public class LoginPresenter extends BasePresenter<LoginContract.ILoginView> implements LoginContract.ILoginPresenter {


    public LoginPresenter(LoginContract.ILoginView mView) {
       attach(mView);
    }
    /**
     * 登录
     */
    @Override
    public void login(String userName,String pwd) {
        Map<String,Object> params=new HashMap<>();
        params.put("userName",userName);
        params.put("password",pwd);
        mView.showProgress();
        addSubscription(apiStores.userLogin(creatRequestBody(params)),new SubscriberCallBack<UserInfo>(new ApiCallBack<UserInfo>() {
            @Override
            public void onSuccessful(UserInfo tokenResult) {
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
        }));
    }
}
