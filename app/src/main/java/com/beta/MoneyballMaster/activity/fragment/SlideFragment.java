package com.beta.MoneyballMaster.activity.fragment;

import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;
import android.util.Log;
import android.view.View;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.adapter.SlideAdapter;
import com.beta.MoneyballMaster.entity.PSlideEntity;
import com.beta.MoneyballMaster.widget.SmileView;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.slide.ItemConfig;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.slide.ItemTouchHelperCallback;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.slide.OnSlideListener;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.slide.SlideLayoutManager;

import java.util.ArrayList;
import java.util.List;

import static android.content.ContentValues.TAG;

/**
 * Created by yas on 2018/5/31.
 */

public class SlideFragment extends BaseFragment {
    private RecyclerView mRecyclerView;
    private SlideAdapter mAdapter;
    private SlideLayoutManager mSlideLayoutManager;
    private ItemTouchHelper mItemTouchHelper;
    private ItemTouchHelperCallback mItemTouchHelperCallback;
    private ArrayList<PSlideEntity> mList = new ArrayList<>();

    private int mLikeCount = 50;
    private int mDislikeCount = 50;
    private SmileView mSmileView;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_slide;
    }

    @Override
    public void initViews(View rootView) {

        mSmileView = rootView.findViewById(R.id.smile_view);

        mSmileView.setLike(mLikeCount);
        mSmileView.setDisLike(mDislikeCount);

        mRecyclerView=rootView.findViewById(R.id.recycler_view);

        mAdapter=new SlideAdapter(getActivity(),mList);
        mRecyclerView.setAdapter(mAdapter);
        addData();

        mItemTouchHelperCallback = new ItemTouchHelperCallback(mRecyclerView.getAdapter(), mList);

        mItemTouchHelper=new ItemTouchHelper(mItemTouchHelperCallback);

        mSlideLayoutManager=new SlideLayoutManager(mRecyclerView,mItemTouchHelper);
        mItemTouchHelper.attachToRecyclerView(mRecyclerView);
        mRecyclerView.setLayoutManager(mSlideLayoutManager);

        initListener();
    }

    private void initListener() {
        mItemTouchHelperCallback.setOnSlideListener(new OnSlideListener() {
            @Override
            public void onSliding(RecyclerView.ViewHolder viewHolder, float ratio, int direction) {
                if (direction == ItemConfig.SLIDING_LEFT) {
                } else if (direction == ItemConfig.SLIDING_RIGHT) {
                }
            }

            @Override
            public void onSlided(RecyclerView.ViewHolder viewHolder, Object o, int direction) {
                if (direction == ItemConfig.SLIDED_LEFT) {
                    mDislikeCount++;
                    mSmileView.setDisLike(mDislikeCount);
                    mSmileView.disLikeAnimation();
                } else if (direction == ItemConfig.SLIDED_RIGHT) {
                    mLikeCount++;
                    mSmileView.setLike(mLikeCount);
                    mSmileView.likeAnimation();
                }
                int position = viewHolder.getAdapterPosition();
                Log.e(TAG, "onSlided--position:" + position);
            }

            @Override
            public void onClear() {
                addData();
            }
        });
    }

    /**
     * 向集合中添加数据
     */
    private void addData(){
        int[] icons = {R.mipmap.header_icon_1, R.mipmap.header_icon_2, R.mipmap.header_icon_3,
                R.mipmap.header_icon_4, R.mipmap.header_icon_1, R.mipmap.header_icon_2};
        String[] titles = {"Acknowledging", "Belief", "Confidence", "Dreaming", "Happiness", "Confidence"};
        String[] says = {
                "Do one thing at a time, and do well.",
                "Keep on going never give up.",
                "Whatever is worth doing is worth doing well.",
                "I can because i think i can.",
                "Jack of all trades and master of none.",
                "Keep on going never give up.",
                "Whatever is worth doing is worth doing well.",
        };
        int[] bgs = {
                R.mipmap.img_slide_1,
                R.mipmap.img_slide_2,
                R.mipmap.img_slide_3,
                R.mipmap.img_slide_4,
                R.mipmap.img_slide_5,
                R.mipmap.img_slide_6
        };

        for (int i = 0; i < 6; i++) {
            mList.add(new PSlideEntity(bgs[i],titles[i],icons[i],says[i]));
        }
    }
}
