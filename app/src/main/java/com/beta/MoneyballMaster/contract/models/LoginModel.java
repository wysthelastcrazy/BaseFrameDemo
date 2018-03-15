package com.beta.MoneyballMaster.contract.models;

import com.beta.MoneyballMaster.contract.LoginContract;
import com.beta.MoneyballMaster.contract.base.BaseModel;
import com.beta.MoneyballMaster.bean.UserInfo;
import com.beta.MoneyballMaster.http.base.ApiCallBack;
import com.beta.MoneyballMaster.http.base.SubscriberCallBack;

import java.util.HashMap;
import java.util.Map;

/**
 * Created by yas on 2017/12/14.
 */

public class LoginModel extends BaseModel implements LoginContract.ILoginModel{
    @Override
    public void login(String userName, String pwd,ApiCallBack<UserInfo> callBack) {
        Map<String,Object> params=new HashMap<>();
        params.put("userName",userName);
        params.put("password",pwd);
        addSubscription(apiStores.userLogin(creatRequestBody(params)),new SubscriberCallBack<UserInfo>(callBack));
    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {

    }
}
