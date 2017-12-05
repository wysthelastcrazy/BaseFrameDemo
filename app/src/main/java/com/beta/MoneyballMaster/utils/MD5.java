package com.beta.MoneyballMaster.utils;

import android.text.TextUtils;

import java.io.UnsupportedEncodingException;

public class MD5 {
	
	public static String getMd5(byte[] source) {
        String s = null;
        char hexDigits[] = { '0', '1', '2', '3', '4', '5', '6', '7', '8', '9', 'a', 'b', 'c', 'd', 'e', 'f' };  
        try {  
            java.security.MessageDigest md = java.security.MessageDigest.getInstance("MD5");  
            md.update(source);  
            byte tmp[] = md.digest();   
            char str[] = new char[16 * 2];   
            int k = 0;   
            for (int i = 0; i < 16; i++) {   
                byte byte0 = tmp[i];   
                str[k++] = hexDigits[byte0 >>> 4 & 0xf];   
                str[k++] = hexDigits[byte0 & 0xf];   
            }  
            s = new String(str);
        } catch (Exception e) {
            e.printStackTrace();  
        }  
        return s;  
    }  

	/***
	 * 密码加密后进行登录
	 * @param str
	 * @return
	 */
	public static final String getMd5(String str){
		if(!TextUtils.isEmpty(str)){
			byte[] array = str.getBytes();
			return getMd5(array);
		}
		return null;
	}


    public static final String getMd5(String str, String encode){
        String code = encode;

        if(!TextUtils.isEmpty(str)){

            if(TextUtils.isEmpty(code) || !code.equalsIgnoreCase("gbk")) {
                code = "utf-8";
            }
            byte[] array = new byte[0];
            try {
                array = str.getBytes(code);
            } catch (UnsupportedEncodingException e) {
                e.printStackTrace();
            }
            return getMd5(array);
        }
        return null;
    }
}
