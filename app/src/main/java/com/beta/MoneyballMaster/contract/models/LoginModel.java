package com.beta.MoneyballMaster.contract.models;

import com.beta.MoneyballMaster.contract.LoginContract;
import com.beta.MoneyballMaster.contract.base.BaseModel;
import com.beta.MoneyballMaster.bean.UserInfo;
import com.beta.MoneyballMaster.http.callback.ApiCallBack;


/**
 * Created by yas on 2017/12/14.
 */

public class LoginModel extends BaseModel implements LoginContract.ILoginModel{

    @Override
    public void login(String userName, String pwd, ApiCallBack<UserInfo> callBack) {
        addSubscription(apiStores.userLogin(userName,pwd),callBack);

    }

    @Override
    public void saveUserInfo(UserInfo userInfo) {

    }
}
