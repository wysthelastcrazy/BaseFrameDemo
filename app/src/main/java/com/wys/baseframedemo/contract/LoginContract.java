package com.wys.baseframedemo.contract;

import android.content.Context;

import com.wys.baseframedemo.contract.base.IBaseView;
/**
 * 登录的关联类
 * Created by HDL on 2016/7/22.
 */
public class LoginContract {
    /**
     * V视图层
     */
    public interface ILoginView extends IBaseView {
        Context getCurContext();//获取上下文对象,用于保存数据等

        void showInfo(String info);//提示用户,提升友好交互

        void showErrorMsg(String msg);//发生错误就显示错误信息

        void toMain();//跳转到主页面

        void toRegister();//跳转到注册页面
    }

    /**
     * P视图与逻辑处理的连接层
     */
    public interface ILoginPresenter{
        void login(String userName,String pwd);//唯一的桥梁就是登录了
    }
}
