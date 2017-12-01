package com.wys.baseframedemo.entity;


import java.io.Serializable;

/**
 * 用户实体类
 * Created by Administrator on 2016/7/22.
 */
public class UserInfo implements Serializable{
    public String email;
    public int firstLogin;          //是否为第一次登录 1否  2是
    public String lastLoginTime;    //最后一次登录时间
    public String mobile;
    public String realName;
    public String shopId;
    public String shopName;
    public String staffName;
    public String staffId;
    public int status;              //状态 1禁用  2启用
    public String token;
}
