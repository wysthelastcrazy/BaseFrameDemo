package com.beta.MoneyballMaster.http;


import com.beta.MoneyballMaster.bean.UserInfo;
import com.beta.MoneyballMaster.http.callback.HttpBaseResult;
import com.beta.MoneyballMaster.http.utils.FileException;
import com.beta.MoneyballMaster.http.utils.HttpUtils;
import com.beta.MoneyballMaster.http.utils.RetrofitUtils;

import java.util.HashMap;
import java.util.Map;

import rx.Observable;


/**
 * Created by yas on 2018/4/27.
 * 统一的网络请求管理类，负责参数格式的组织
 */

public class ProtocolManager {
    private final String TAG = "ProtocolManager";

    private volatile static ProtocolManager instance;
    public APIService apiStores;
    private ProtocolManager() {
        apiStores = RetrofitUtils.newInstance().create(APIService.class);
    }

    public  static final ProtocolManager getInstance() {
        if (instance == null) {
            synchronized(ProtocolManager.class){
                if (instance==null)
                    instance = new ProtocolManager();
            }

        }
        return instance;
    }

    /**
    /**
     * 登录（普通post请求）
     * @param phone
     * @param smsCode
     * @return
     */
    public Observable<HttpBaseResult<UserInfo>> userLogin(String phone, String smsCode){
        return apiStores.userLogin(phone,smsCode);
    }

    /**
     * 文件上传（带参数的多文件上传）
     * @param param01
     * @param params02
     * @param files
     * @return
     * @throws FileException
     */
    public Observable<HttpBaseResult> uploadFile(String param01, int params02, Map<String,String> files) throws FileException {
        Map<String,Object> mMap=new HashMap<>();
        mMap.put("param01",param01);
        mMap.put("params02",params02);
        return apiStores.uploadFile(HttpUtils.createMultipartBody(mMap,files));
    }

}
