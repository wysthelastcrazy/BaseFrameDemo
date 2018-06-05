package com.beta.MoneyballMaster.widget.recycler;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v7.widget.RecyclerView;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnLoadmoreListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshListener;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

/**
 * Created by yas on 2018/4/26.
 */

public class ExtendRecyclerView extends FrameLayout {
    private RefreshLayout mRefreshLayout;
    private XRecyclerView mRecyclerView;
    private LinearLayout layoutEmpty;
    private TextView tvEmptyInfo;
    public ExtendRecyclerView(Context context) {
        super(context);
        init();
    }

    public ExtendRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
        init();
    }

    public ExtendRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        init();
    }

    private void init() {
        LayoutInflater li= (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
        li.inflate(R.layout.extent_recycler_view,this,true);

        layoutEmpty=this.findViewById(R.id.layoutEmpty);
        tvEmptyInfo=this.findViewById(R.id.text_empty);
        mRecyclerView=this.findViewById(R.id.inner_recyclerView);
        mRecyclerView.setEmptyListener(new RecycleDataChangeListener() {
            @Override
            public void showEmpty() {
                mRecyclerView.setVisibility(View.GONE);
                layoutEmpty.setVisibility(View.VISIBLE);
            }

            @Override
            public void hideEmpty() {
                mRecyclerView.setVisibility(View.VISIBLE);
                layoutEmpty.setVisibility(View.GONE);
            }
        });

        mRefreshLayout=this.findViewById(R.id.mRefreshLayout);
    }

    /**
     * 添加HeaderView
     * @param headerView
     */
    public void addHeaderView(View headerView){
        mRecyclerView.addHeaderView(headerView);
    }

    /**
     * 添加FooterView
     * @param footerView
     */
    public void addFooterView(View footerView){
        mRecyclerView.addFootView(footerView);
    }
    /**
     * 设置是否监听列表在滚动到底部时触发加载事件
     */
    public void setEnableAutoLoadmore(boolean enable){
        mRefreshLayout.setEnableAutoLoadmore(enable);
    }
    /**
     * 是否启用下拉刷新（默认启用）
     */
    public void setEnableRefresh(boolean enable){
        mRefreshLayout.setEnableRefresh(enable);
    }
    /**
     * 设置上拉加载和下拉监听
     * @param listener
     */
    public void setOnRefreshLoadmoreListener(OnRefreshLoadmoreListener listener){
        mRefreshLayout.setOnRefreshLoadmoreListener(listener);
    }

    /**
     * 单独设置上拉加载更多监听
     * @param listener
     */
    public void setOnLoadmoreListener(OnLoadmoreListener listener){
        mRefreshLayout.setOnLoadmoreListener(listener);
    }

    /**
     * 单独设置刷新监听
     * @param listener
     */
    public void setOnRefreshListener(OnRefreshListener listener){
        mRefreshLayout.setOnRefreshListener(listener);
    }
    /**
     * 完成加载
     */
    public void finishLoadmore(){
        finishLoadmore(true);
    }
    /**
     * 完成刷新
     */
    public void finishRefresh(){
        finishRefresh(true);
    }
    public void finishRefresh(boolean isSucc){
        mRefreshLayout.finishRefresh(isSucc);
    }
    public void finishLoadmore(boolean isSucc){
        mRefreshLayout.finishLoadmore(isSucc);
    }
    /**
     * 设置布局管理器
     * @param layoutManager
     */
    public void setLayoutManager(RecyclerView.LayoutManager layoutManager){
        mRecyclerView.setLayoutManager(layoutManager);
    }
    public void setAdapter(BaseRecyclerAdapter adapter){
        mRecyclerView.setAdapter(adapter);
    }

    /**
     * 设置emptyView类型，根据类型不同，显示不同的UI
     * @param type
     */
    public void setEmptyType(int type){
        //TODO 根据不同类型，设置显示的文本
    }
    public RecyclerView getRecyclerView(){
        return mRecyclerView;
    }
}
