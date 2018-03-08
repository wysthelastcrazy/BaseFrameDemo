package com.beta.MoneyballMaster.contract.base;

import android.text.TextUtils;

import com.beta.MoneyballMaster.common.MConfiger;
import com.beta.MoneyballMaster.http.APIService;
import com.beta.MoneyballMaster.http.base.RetrofitUtils;
import com.beta.MoneyballMaster.utils.DeviceUtil;
import com.beta.MoneyballMaster.utils.MD5;
import com.google.gson.Gson;
import com.google.gson.reflect.TypeToken;

import java.io.File;
import java.lang.reflect.Type;
import java.net.FileNameMap;
import java.net.URLConnection;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;
import rx.Observable;
import rx.Subscriber;
import rx.android.schedulers.AndroidSchedulers;
import rx.schedulers.Schedulers;
import rx.subscriptions.CompositeSubscription;

/**
 * Created by yas on 2017/12/14.
 */

public class BaseModel {
    public APIService apiStores = RetrofitUtils.newInstence().create(APIService.class);
    private CompositeSubscription mCompositeSubscription;
    //RXjava取消注册，以避免内存泄露
    public void onUnsubscribe() {
        if (mCompositeSubscription != null && mCompositeSubscription.hasSubscriptions()) {
            mCompositeSubscription.unsubscribe();
        }
    }


    public void addSubscription(Observable observable, Subscriber subscriber) {
        if (mCompositeSubscription == null) {
            mCompositeSubscription = new CompositeSubscription();
        }
        mCompositeSubscription.add(observable
                .subscribeOn(Schedulers.io())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(subscriber));
    }
    /**
     * 单张图片上传
     * @param key
     * @param filePath
     * @return
     */
    protected final MultipartBody.Part createSingleFileBody(String key, String filePath){
        File file = new File(filePath);
        RequestBody imageBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
        MultipartBody.Part imageBodyPart = MultipartBody.Part.createFormData(key, file.getName(), imageBody);
        return imageBodyPart;
    }
    /**
     * 多图片上传
     * @param params 文本参数
     * @param files 文件参数，map的key为参数名  val为文件路径
     * @return
     */
    protected final List<MultipartBody.Part> createMultipartBody(Map<String, Object> params, Map<String,String> files){
        params=addCommonParams(params);
        MultipartBody.Builder builder = new MultipartBody.Builder()
                .setType(MultipartBody.FORM);//表单类型
        if (params!=null&&params.size()>0){
            for (Map.Entry<String, Object> entry : params.entrySet()) {
                String key = entry.getKey();
                Object val = entry.getValue();
                if(val == null){
                    builder.addFormDataPart(key ,"");
                } else {
                    builder.addFormDataPart(key ,val+"");
                }
            }
        }
        if (files!=null&&files.size()>0){
            for (Map.Entry<String, String> entry : files.entrySet()) {
                String key = entry.getKey();
                String val = entry.getValue();
                if(!TextUtils.isEmpty(val)) {
                    File file = new File(val);
                    RequestBody imageBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
                    builder.addFormDataPart(key, file.getName(), imageBody);//imgfile 后台接收图片流的参数名
                }
            }
        }
        List<MultipartBody.Part> parts = builder.build().parts();
        return parts;
    }
    protected final RequestBody creatRequestBody(Map<String, Object> map){
        String strJson=getPostJavaParams(map);
        RequestBody body=RequestBody.create(MediaType.parse("application/json; charset=utf-8"),strJson);
        return body;
    }
    /**
     * post请求时json参数处理
     * @param map
     * @return
     */
    protected final String getPostJavaParams(Map<String, Object> map) {
        map=addCommonParams(map);
        if (map != null) {
            //添加时间戳
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            String str = gson.toJson(map, type);
            return str;
        } else {
            return "";
        }
    }

    /**
     * 公共参数添加
     * @param map
     * @return
     */
    private Map<String, Object> addCommonParams(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String sign= getSignParam(map);
        if (!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }
        //添加fromType
        map.put("fromType", 13);
        //添加时间戳
        map.put("timestamp", System.currentTimeMillis() + "");
        //添加渠道号
        map.put("channel", MConfiger.CHANNEL_ID);
        //添加设备号
        String strIMEI = DeviceUtil.getIMEI();
        if (!TextUtils.isEmpty(strIMEI)) {
            map.put("deviceSn", strIMEI);
        }
        //添加请求ip
        String deviceIp = DeviceUtil.getHostIP();
        if (!TextUtils.isEmpty(deviceIp)) {
            map.put("deviceIp", deviceIp);
        }
//
//        String deviceGps = LBSController.getInstance().getLocInfo();
//        map.put("deviceGps", deviceGps);
        return map;
    }
    /**
     * 获取sign签名验证
     * @param map
     * @return
     */
    protected final String getSignParam(Map<String, Object> map) {
        StringBuilder builder = new StringBuilder();
        String sign = "";

        if (map != null && map.size() > 0) {
            TreeMap<String, Object> treeMap = new TreeMap<>(new Comparator<String>() {
                @Override
                public int compare(String s, String t1) {
                    int compare = 0;
                    if (!TextUtils.isEmpty(s) && !TextUtils.isEmpty(t1)) {
                        compare = s.compareTo(t1);
                    }

                    if (compare > 0) {
                        return 1;
                    } else if (compare < 0) {
                        return -1;
                    } else {
                        return 0;
                    }
                }
            });

            treeMap.putAll(map);
            if (treeMap.size() > 0) {
                for (Map.Entry<String, Object> entry : treeMap.entrySet()) {
                    String key = entry.getKey();
                    Object val = entry.getValue();
                    if(val == null){
                        builder.append(key + "=0&");
                    } else {
                        if (val instanceof String){
                            String str= (String) val;
                            if (TextUtils.isEmpty(str)){
                                builder.append(key + "=0&");
                            }else{
                                builder.append(key + "=" + val + "&");
                            }
                        }else if(val instanceof ArrayList){
                            //list暂时不参与签名
                        }else {
                            builder.append(key + "=" + val + "&");
                        }
                    }
                }
//                if(builder.length()>0) {
//                    builder.append(NetCommon.JAVA_KEY_CODE);
//                }
            }
            sign = MD5.getMd5(builder.toString(), "utf-8");
            if (!TextUtils.isEmpty(sign)) {
                sign=sign.toLowerCase();
            }
        }
        return sign;
    }
    /**
     * 获取文件类型
     * @param name 文件名称
     * @return
     */
    public static String guessMimeType(String name)
    {
        FileNameMap fileNameMap = URLConnection.getFileNameMap();
        String contentTypeFor = fileNameMap.getContentTypeFor(name);
        if (contentTypeFor == null)
        {
            contentTypeFor = "application/octet-stream";
        }
        return contentTypeFor;
    }
}
