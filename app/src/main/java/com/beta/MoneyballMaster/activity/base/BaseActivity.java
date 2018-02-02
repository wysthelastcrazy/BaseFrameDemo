package com.beta.MoneyballMaster.activity.base;

import android.os.Bundle;
import android.support.v7.app.AppCompatActivity;
import android.text.TextUtils;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.dialog.MLoadingDialog;
import com.zhy.autolayout.AutoLayoutActivity;

import butterknife.ButterKnife;

/**
 * Created by Administrator on 2016/7/22.
 */
public abstract class BaseActivity extends AutoLayoutActivity {
    protected final String TAG = getClass().getSimpleName();

    private MLoadingDialog mLoadingDialog;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(getLayoutRes());
        ButterKnife.bind(this);
        initExtras();
        initLayout();
    }

    /**
     * 获取布局
     * @return
     */
    protected abstract int getLayoutRes();

    /**
     * 初始化布局view，做一些控件初始设置等操作
     */
    protected abstract void initLayout();

    /**
     * 获取上一页面传值
     */
    protected void  initExtras(){}

    protected void showLoading(){
        showLoading("数据正在加载中，请稍等。。。");
    }
    protected void showLoading(String msg){
        showLoading(msg,true);
    }

    /**
     * loading弹窗
     * @param msg
     * @param isCancel
     */
    protected void showLoading(String msg, boolean isCancel){
        hideLoadingDialog();
        if(!isFinishing() && mLoadingDialog == null){
            mLoadingDialog = new MLoadingDialog(this, R.style.MyDialogBg);
            mLoadingDialog.setCanceledOnTouchOutside(isCancel);
            mLoadingDialog.setCancelable(isCancel);
            mLoadingDialog.show();

            if(TextUtils.isEmpty(msg)){
                msg = "数据正在加载中，请稍等。。。";
            }
            if(mLoadingDialog != null){
                mLoadingDialog.setMyTips(msg);
            }
        }
    }
    protected void hideLoadingDialog(){
        if(mLoadingDialog != null){
            mLoadingDialog.dismiss();
            mLoadingDialog = null;
        }
    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        hideLoadingDialog();
    }
}
