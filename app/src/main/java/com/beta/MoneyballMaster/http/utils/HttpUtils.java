package com.beta.MoneyballMaster.http.utils;

import android.text.TextUtils;

import com.beta.MoneyballMaster.common.MConfiger;
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
import java.util.Map;
import java.util.TreeMap;

import okhttp3.MediaType;
import okhttp3.MultipartBody;
import okhttp3.RequestBody;

/**
 * Created by yas on 2018/4/26.
 */

public class HttpUtils {

    /**
     * 单张图片上传请求数据组织
     * @param key
     * @param filePath
     * @return
     */
    public static MultipartBody createSingleFileBody(String key, String filePath) throws FileException {
        Map<String,String> files=new HashMap<>();
        files.put(key,filePath);
        return createMultipartBody(files);
    }
    /**
     * 单张图片上传，且带文本参数，请求数据组织
     * @param key
     * @param filePath
     * @return
     */
    public static MultipartBody createSingleFileBody(Map<String, Object> params,String key, String filePath) throws FileException {
        Map<String,String> files=new HashMap<>();
        files.put(key,filePath);
        return createMultipartBody(params,files);
    }
    /**
     * 多图片上传，不带文本参数请求数据组织
     * @param files
     * @return
     */
    public static MultipartBody createMultipartBody(Map<String,String> files) throws FileException {
        return createMultipartBody(null,files);
    }
    /**
     * 多图片上传请求数据组织
     * @param params 文本参数
     * @param files 文件参数，map的key为参数名  val为文件路径
     * @return
     */
    public static MultipartBody createMultipartBody(Map<String, Object> params, Map<String,String> files) throws FileException {
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
                    if (file.exists()) {
                        RequestBody imageBody = RequestBody.create(MediaType.parse(guessMimeType(file.getName())), file);
                        builder.addFormDataPart(key, file.getName(), imageBody);//imgfile 后台接收图片流的参数名
                    }else{
                        throw new FileException("文件"+val+"不存在");
                    }
                }
            }
        }
        return builder.build();
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

    /**
     * 添加公共参数
     * @param map
     * @return
     */
    public static Map<String, Object> addCommonParams(Map<String, Object> map) {
        if (map == null) {
            map = new HashMap<>();
        }
        String sign= getSignParam(map);
        if (!TextUtils.isEmpty(sign)) {
            map.put("sign", sign);
        }
        map.putAll(getCommonParams());
        return map;
    }

    /**
     * 获取公共参数
     * @return
     */
    public static Map<String,Object> getCommonParams(){
        Map mMap = new HashMap<>();
        //添加fromType
        mMap.put("fromType", 13);
        //添加时间戳
        mMap.put("timestamp", System.currentTimeMillis() + "");
        //添加渠道号
        mMap.put("channel", MConfiger.CHANNEL_ID);
        //添加设备号
        String strIMEI = DeviceUtil.getIMEI();
        if (!TextUtils.isEmpty(strIMEI)) {
            mMap.put("deviceSn", strIMEI);
        }
        //添加请求ip
        String deviceIp = DeviceUtil.getHostIP();
        if (!TextUtils.isEmpty(deviceIp)) {
            mMap.put("deviceIp", deviceIp);
        }

//        String deviceGps = LBSController.getInstance().getLocInfo();
//        map.put("deviceGps", deviceGps);
        return mMap;
    }
    /**
     * 获取sign签名验证
     * @param map
     * @return
     */
    public static String getSignParam(Map<String, Object> map) {
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
     * post请求时json参数处理
     * @param map
     * @return
     */
    public static String mapToJsonStr(Map<String, Object> map) {
        map=addCommonParams(map);
        if (map != null) {
            Gson gson = new Gson();
            Type type = new TypeToken<Map<String, Object>>() {
            }.getType();
            String str = gson.toJson(map, type);
            return str;
        } else {
            return "";
        }
    }
}
