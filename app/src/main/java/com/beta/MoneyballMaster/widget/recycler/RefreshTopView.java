package com.beta.MoneyballMaster.widget.recycler;

import android.content.Context;
import android.support.annotation.NonNull;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.scwang.smartrefresh.layout.api.RefreshHeader;
import com.scwang.smartrefresh.layout.api.RefreshKernel;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.constant.RefreshState;
import com.scwang.smartrefresh.layout.constant.SpinnerStyle;

/**
 * Created by yas on 2018/4/27.
 * 自定义下拉刷新头部View
 */

public class RefreshTopView extends RelativeLayout implements RefreshHeader {
    private ImageView refreshIndicatorView;     //指示器view
    private ProgressBar progressBar;            //刷新bar
    private TextView tvTips;                    //文本显示
    public RefreshTopView(Context context) {
        super(context);
        init();
    }

    public RefreshTopView(Context context, AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public RefreshTopView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }
    private void init(){
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.refresh_top_view,this,true);
        refreshIndicatorView=this.findViewById(R.id.refresh_img_indicator);
        progressBar=this.findViewById(R.id.progress);
        tvTips=this.findViewById(R.id.tv_tips);
    }
    @NonNull
    @Override
    public View getView() {
        return this;
    }

    @Override
    public SpinnerStyle getSpinnerStyle() {
        return SpinnerStyle.Translate;//指定为平移，不能null;
    }

    @Override
    public void onStartAnimator(RefreshLayout layout, int height, int extendHeight) {
        refreshIndicatorView.setVisibility(View.GONE);
        progressBar.setVisibility(View.VISIBLE);
    }

    @Override
    public int onFinish(RefreshLayout layout, boolean success) {
        progressBar.setVisibility(View.GONE);
        refreshIndicatorView.setVisibility(View.VISIBLE);
        if (success){
            tvTips.setText("刷新完成");
        } else {
            tvTips.setText("刷新失败");
        }
        return 500;
    }
    @Override
    public void onStateChanged(RefreshLayout refreshLayout, RefreshState oldState, RefreshState newState) {
        switch (newState){
            case None:
            case PullDownToRefresh:
                refreshIndicatorView.setImageResource(R.mipmap.refresh_arrow_down);
                refreshIndicatorView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tvTips.setText("下拉开始刷新");
                break;
            case Refreshing:
                refreshIndicatorView.setVisibility(View.GONE);
                progressBar.setVisibility(View.VISIBLE);
                tvTips.setText("正在刷新");
                break;
            case ReleaseToRefresh:
                refreshIndicatorView.setImageResource(R.mipmap.refresh_arrow_up);
                refreshIndicatorView.setVisibility(View.VISIBLE);
                progressBar.setVisibility(View.GONE);
                tvTips.setText("释放立即刷新");
                break;
        }
    }
    @Override
    public void onPullingDown(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onReleasing(float percent, int offset, int headerHeight, int extendHeight) {

    }

    @Override
    public void onRefreshReleased(RefreshLayout layout, int headerHeight, int extendHeight) {

    }



    @Override
    public void setPrimaryColors(int... colors) {

    }

    @Override
    public void onInitialized(RefreshKernel kernel, int height, int extendHeight) {

    }

    @Override
    public void onHorizontalDrag(float percentX, int offsetX, int offsetMax) {

    }



    @Override
    public boolean isSupportHorizontalDrag() {
        return false;
    }

}
