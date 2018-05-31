package com.beta.MoneyballMaster.activity.fragment;

import android.os.Bundle;
import android.support.annotation.Nullable;
import android.support.v4.app.Fragment;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.adapter.EchelonAdapter;
import com.beta.MoneyballMaster.entity.PEchelonEntity;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.echelon.EchelonLayoutManager;

import java.util.ArrayList;


/**
 * Created by yas on 2018/5/31.
 * 梯形布局
 */
public class EchelonFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private EchelonLayoutManager mLayoutManager;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_echelon;
    }

    @Override
    public void initViews(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recycler_view);
    }

    @Override
    public void onActivityCreated(@Nullable Bundle savedInstanceState) {
        super.onActivityCreated(savedInstanceState);
        initData();
    }

    private void initData() {
        mLayoutManager = new EchelonLayoutManager(getContext());
        mRecyclerView.setLayoutManager(mLayoutManager);
        mRecyclerView.setAdapter(new EchelonAdapter(getActivity(),getData()));

    }
    private ArrayList<PEchelonEntity> getData(){
        ArrayList<PEchelonEntity> mList=new ArrayList<>();
         int[] icons = {R.mipmap.header_icon_1,R.mipmap.header_icon_2,R.mipmap.header_icon_3,R.mipmap.header_icon_4};
         int[] bgs = {R.mipmap.bg_1,R.mipmap.bg_2,R.mipmap.bg_3,R.mipmap.bg_4};
         String[] nickNames = {"左耳近心","凉雨初夏","稚久九栀","半窗疏影"};
         String[] descs = {
                "回不去的地方叫故乡 没有根的迁徙叫流浪...",
                "人生就像迷宫，我们用上半生找寻入口，用下半生找寻出口",
                "原来地久天长，只是误会一场",
                "不是故事的结局不够好，而是我们对故事的要求过多",
                "只想优雅转身，不料华丽撞墙"
        };
         for (int i=0;i<icons.length;i++){
             PEchelonEntity entity=new PEchelonEntity();
             entity.bgRes=bgs[i];
             entity.iconRes=icons[i];
             entity.nickName=nickNames[i];
             entity.desc=descs[i];
             mList.add(entity);
         }
         return mList;
    }
}
