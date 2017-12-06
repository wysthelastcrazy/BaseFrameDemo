package com.beta.MoneyballMaster.activity.base;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

/**
 * Created by yas on 2017/11/6.
 */

public abstract class BaseFragment extends Fragment {
    private View rootView;
    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, Bundle savedInstanceState) {
        rootView=inflater.inflate(getLayoutRes(),null);
        initViews(rootView);
        return rootView;
    }

    /**
     * 获取布局文件id
     * @return
     */
    public abstract int getLayoutRes();

    /***
     * 初始化控件
     * @param rootView
     */
    public abstract void initViews(View rootView);


    /**
     * loading
     * @param str
     */
    protected void showLoading(String str){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).showLoading(str);
        }
    }

    /**
     * 隐藏loading
     */
    protected void hideLoading(){
        if (getActivity() instanceof BaseActivity){
            ((BaseActivity) getActivity()).hideLoadingDialog();
        }
    }
}
