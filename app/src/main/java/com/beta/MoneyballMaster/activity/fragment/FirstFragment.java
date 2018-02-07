package com.beta.MoneyballMaster.activity.fragment;

import android.support.v7.widget.LinearLayoutManager;
import android.view.View;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.adapter.AdapterTest;
import com.beta.MoneyballMaster.widget.XRecyclerView;
import com.scwang.smartrefresh.layout.api.RefreshLayout;
import com.scwang.smartrefresh.layout.listener.OnRefreshLoadmoreListener;

import java.util.ArrayList;

/**
 * Created by yas on 2017/12/6.
 */

public class FirstFragment extends BaseFragment{
    private XRecyclerView mRecyclerView;
    private RefreshLayout mRefreshView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_first;
    }

    @Override
    public void initViews(View rootView) {
        mRecyclerView=rootView.findViewById(R.id.mRecyclerView);
        LinearLayoutManager linearLayoutManager=new LinearLayoutManager(getActivity());
        mRecyclerView.setLayoutManager(linearLayoutManager);
        TextView tv=new TextView(getActivity());
        tv.setText("this is headerView");
        mRecyclerView.addHeaderView(tv);
        ArrayList<String> mList=new ArrayList<>();
        for (int i=1;i<11;i++){
            mList.add("item"+i);
        }
        AdapterTest adapterTest=new AdapterTest(getActivity(),mList);
        mRecyclerView.setAdapter(adapterTest);

        mRefreshView=rootView.findViewById(R.id.mRefreshLayout);
        mRefreshView.setOnRefreshLoadmoreListener(mRefreshListener);

    }
    /**
     * 下拉刷新，上了加载更多监听
     */
    private OnRefreshLoadmoreListener mRefreshListener=new OnRefreshLoadmoreListener() {
        @Override
        public void onLoadmore(RefreshLayout refreshlayout) {

        }

        @Override
        public void onRefresh(RefreshLayout refreshlayout) {
        }
    };
}
