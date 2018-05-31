package com.beta.MoneyballMaster.activity.fragment;

import android.support.v7.widget.RecyclerView;
import android.view.View;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.adapter.SkidRightAdapter;
import com.beta.MoneyballMaster.adapter.base.OnItemClickListener;
import com.beta.MoneyballMaster.entity.PSkidEntity;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.skidright.SkidRightLayoutManager;

import java.util.ArrayList;

/**
 * Created by yas on 2018/5/31.
 */

public class SkidRightFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SkidRightLayoutManager mSkidRightLayoutManager;
    private SkidRightAdapter mAdapter;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_skid_right;
    }

    @Override
    public void initViews(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recycler_view);

        mSkidRightLayoutManager = new SkidRightLayoutManager(1.5f, 0.85f);
        mRecyclerView.setLayoutManager(mSkidRightLayoutManager);

        mAdapter=new SkidRightAdapter(getActivity(),getData());
        mAdapter.setOnItemClickListener(new OnItemClickListener<PSkidEntity>() {
            @Override
            public void onItemClick(View itemView, PSkidEntity bean, int position) {

            }
        });
        mRecyclerView.setAdapter(mAdapter);
    }

    private ArrayList<PSkidEntity> getData(){
        ArrayList<PSkidEntity> mList=new ArrayList<>();
         int[] imgs = {
                R.mipmap.skid_right_1,
                R.mipmap.skid_right_2,
                R.mipmap.skid_right_3,
                R.mipmap.skid_right_4,
                R.mipmap.skid_right_5,
                R.mipmap.skid_right_6,
                R.mipmap.skid_right_7,

        };
        String[] titles = {"Acknowl", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence","Bridge"};

        for (int i=0;i<imgs.length;i++){
            PSkidEntity entity=new PSkidEntity(titles[i],imgs[i]);
            mList.add(entity);
        }
        return mList;
    }
}
