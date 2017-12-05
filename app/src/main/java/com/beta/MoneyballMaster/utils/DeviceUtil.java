package com.beta.MoneyballMaster.utils;

import android.app.Activity;
import android.content.Context;
import android.os.Build;
import android.provider.Settings;
import android.telephony.TelephonyManager;
import android.text.TextUtils;
import android.view.Window;
import android.view.WindowManager;


import com.beta.MoneyballMaster.common.Global;

import java.io.File;
import java.io.IOException;
import java.io.InputStreamReader;
import java.io.LineNumberReader;
import java.net.Inet4Address;
import java.net.InetAddress;
import java.net.NetworkInterface;
import java.net.SocketException;
import java.util.Collections;
import java.util.Enumeration;
import java.util.List;

public class DeviceUtil {
    private static final String TAG = "DeviceUtil";

    public static int mWidth;
    public static int mHeight;

    static {
        WindowManager wm = (WindowManager) Global.mContext.getSystemService(Context.WINDOW_SERVICE);
        mWidth = wm.getDefaultDisplay().getWidth();
        mHeight = wm.getDefaultDisplay().getHeight();
    }

//    public static Location getGPS(){
//        Location mLocation=null;
//
//        LocationManager locationManager = (LocationManager)Global.getContext().getSystemService(Context.LOCATION_SERVICE);
//        if(locationManager.isProviderEnabled(LocationManager.GPS_PROVIDER)){
//            Location location = locationManager.getLastKnownLocation(LocationManager.GPS_PROVIDER);
//            if(location != null){
//                mLocation=location;
//            }
//        }else{
//            LocationListener locationListener = new LocationListener() {
//
//                // Provider的状态在可用、暂时不可用和无服务三个状态直接切换时触发此函数
//                @Override
//                public void onStatusChanged(String provider, int status, Bundle extras) {
//
//                }
//
//                // Provider被enable时触发此函数，比如GPS被打开
//                @Override
//                public void onProviderEnabled(String provider) {
//
//                }
//
//                // Provider被disable时触发此函数，比如GPS被关闭
//                @Override
//                public void onProviderDisabled(String provider) {
//
//                }
//
//                //当坐标改变时触发此函数，如果Provider传进相同的坐标，它就不会被触发
//                @Override
//                public void onLocationChanged(Location location) {
//                    if (location != null) {
//                        Log.e("Map", "Location changed : Lat: "
//                                + location.getLatitude() + " Lng: "
//                                + location.getLongitude());
//                    }
//                }
//            };
//            locationManager.requestLocationUpdates(LocationManager.NETWORK_PROVIDER,1000, 0,locationListener);
//            Location location = locationManager.getLastKnownLocation(LocationManager.NETWORK_PROVIDER);
//            if(location != null){
//                mLocation=location;
//            }
//        }
//        return mLocation;
//    }

    /**
     * 获取ip地址
     *
     * @return
     */
    public static String getHostIP() {
        try {
            for (Enumeration<NetworkInterface> en = NetworkInterface.getNetworkInterfaces(); en.hasMoreElements(); ) {
                NetworkInterface intf = en.nextElement();
                for (Enumeration<InetAddress> enumIpAddr = intf.getInetAddresses(); enumIpAddr.hasMoreElements(); ) {
                    InetAddress inetAddress = enumIpAddr.nextElement();
                    if (!inetAddress.isLoopbackAddress()&& !inetAddress.isLinkLocalAddress()&& inetAddress instanceof Inet4Address) {
                        return inetAddress.getHostAddress().toString();
                    }
                }
            }
        } catch (SocketException e) {
            e.printStackTrace();
        }
        return null;

    }

    /***
     * 获得IMEI(设备号，即设备唯一标识)
     *
     * @return
     */
    public static final String getIMEI() {
        String str = "";
        Context mContext = Global.getContext();
        TelephonyManager telManager = (TelephonyManager) Global.mContext.getSystemService(Context.TELEPHONY_SERVICE);
        if (telManager != null) {
            str = telManager.getDeviceId();
            if (TextUtils.isEmpty(str)) {
                str = Settings.Secure.getString(mContext.getContentResolver(), Settings.Secure.ANDROID_ID);
            }
        }
        return str;
    }

    /****
     * android os序列号
     *
     * @return
     */
    public static final String getAndroidSer() {
        String ser = "";
        ser = Build.SERIAL;
        return ser;
    }

    public static final String getAndroidVer() {
        String ver = "";
        ver = Build.VERSION.SDK;
        return ver;
    }

    public static final String getAndroidTime() {
        String time = "";
        time = Build.TIME + "";
        return time;
    }

    /***
     * 获取mac地址
     *
     * @return
     */
    public static final String getMacAdd() {
        // 获取mac地址：
        String command = "cat /sys/class/net/wlan0/address ";
        return getStringInfo(command);
    }

    /***
     * 获取CPU信息
     *
     * @return
     */
    public static final String getCpuInfo() {
        String command = "cat /proc/cpuinfo";
        return getStringInfo(command);
    }

    /***
     * 获取android ID
     *
     * @return
     */
    public static final String getAndroidID() {
        String androidID = "";
        androidID = Build.ID;
        return androidID;
    }

    /***
     * 获取屏幕宽度
     *
     * @return
     */
    public static final int getDeviceWidth() {
        return mWidth;
    }

    /***
     * 获取屏幕高度
     *
     * @return
     */
    public static final int getDeviceHeight() {
        return mHeight;
    }


    private static final String getStringInfo(String command) {
        String str = "";
        try {
            Process pp = Runtime.getRuntime().exec(command);
            InputStreamReader ir = new InputStreamReader(pp.getInputStream());
            LineNumberReader input = new LineNumberReader(ir);
            for (; null != str; ) {
                str = input.readLine();
                if (str != null) {
                    str = str.trim();// 去空格
                    break;
                }
            }
        } catch (IOException ex) {
            // 赋予默认值
            ex.printStackTrace();
            str = "00000000";
        }
        return str;
    }

    public static int getAndroidSDKVersion() {
        int ver = 0;
        String version = Build.VERSION.SDK;
        if (!TextUtils.isEmpty(version)) {
            try {
                ver = Integer.valueOf(version);
            } catch (Exception ee) {
                MyLog.error(TAG, ee);
            }
        }
        return ver;
    }

    /***
     * 获取手机型号
     *
     * @return
     */
    public static final String getDeviceModel() {
        String model = Build.MODEL;
        return model;
    }

    /**
     * 获取手机品牌
     * @return
     */
    public static final String getDeviceBrand(){
        String brand = Build.BRAND;
        return brand;
    }

    /***
     * 获取手机制作厂商
     *
     * @return
     */
    public static final String getDeviceProduct() {
        return Build.MANUFACTURER;
    }



    /***
     * 获取realse信息
     *
     * @return
     */
    public static final String getDeviceRelease() {
        return Build.VERSION.RELEASE;
    }

    public static String getIPAddress(boolean useIPv4) {
        try {
            List<NetworkInterface> interfaces = Collections.list(NetworkInterface.getNetworkInterfaces());
            for (NetworkInterface intf : interfaces) {
                List<InetAddress> addrs = Collections.list(intf.getInetAddresses());
                for (InetAddress addr : addrs) {
                    if (!addr.isLoopbackAddress()) {
                        String sAddr = addr.getHostAddress();
                        boolean isIPv4 = sAddr.indexOf(':') < 0;

                        if (useIPv4) {
                            if (isIPv4)
                                return sAddr;
                        } else {
                            if (!isIPv4) {
                                int delim = sAddr.indexOf('%'); // drop ip6 zone suffix
                                return delim < 0 ? sAddr.toUpperCase() : sAddr.substring(0, delim).toUpperCase();
                            }
                        }
                    }
                }
            }
        } catch (Exception ex) {
        } // for now eat exceptions
        return "";
    }

    /**
     * 获取状态栏高度
     *
     * @return
     */
    public static int getStatusHeight() {
        int statusBarHeight = -1;
        //获取status_bar_height资源的ID
        int resourceId = Global.getContext().getResources().getIdentifier("status_bar_height", "dimen", "android");
        if (resourceId > 0) {
            //根据资源ID获取响应的尺寸值
            statusBarHeight = Global.getContext().getResources().getDimensionPixelSize(resourceId);
        }
        return statusBarHeight;
    }

    /**
     * 设置状态栏颜色
     *
     * @param activity
     * @param colorResId
     */
    public static void setWindowStatusBarColor(Activity activity, int colorResId) {
        try {
            if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.LOLLIPOP) {
                Window window = activity.getWindow();
                window.addFlags(WindowManager.LayoutParams.FLAG_DRAWS_SYSTEM_BAR_BACKGROUNDS);
                window.setStatusBarColor(activity.getResources().getColor(colorResId));
            }
        } catch (Exception e) {
            e.printStackTrace();
        }
    }

    /**
     * 获取设备号
     * @return
     */
    public static String getDeviceSn(){
        TelephonyManager tm = (TelephonyManager)Global.getContext().getSystemService(Context.TELEPHONY_SERVICE);
        String sn =  tm.getSimSerialNumber();
        return sn;
    }

    /**
     * 判断手机是否ROOT
     */
    public static boolean isRoot() {

        boolean root = false;

        try {
            if ((!new File("/system/bin/su").exists())
                    && (!new File("/system/xbin/su").exists())) {
                root = false;
            } else {
                root = true;
            }

        } catch (Exception e) {
        }

        return root;
    }

    /**
     * 根据手机的分辨率从 dp 的单位 转成为 px(像素)
     */
    public static int dip2px(Context context, float dpValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (dpValue * scale + 0.5f);
    }

    /**
     * 根据手机的分辨率从 px(像素) 的单位 转成为 dp
     */
    public static int px2dip(Context context, float pxValue) {
        final float scale = context.getResources().getDisplayMetrics().density;
        return (int) (pxValue / scale + 0.5f);
    }

}
