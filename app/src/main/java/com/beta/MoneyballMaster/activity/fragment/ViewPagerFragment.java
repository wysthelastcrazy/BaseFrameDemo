package com.beta.MoneyballMaster.activity.fragment;

import android.media.MediaPlayer;
import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.adapter.ViewPagerAdapter;
import com.beta.MoneyballMaster.entity.PViewPagerEntity;
import com.beta.MoneyballMaster.utils.MyLog;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.viewPager.OnViewPagerListener;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.viewPager.ViewPagerLayoutManager;

import java.util.ArrayList;

/**
 * Created by yas on 2018/6/6.
 */

public class ViewPagerFragment extends BaseFragment{
    private RecyclerView mRecyclerView;
    private ViewPagerLayoutManager mLayoutManager;
    private ViewPagerAdapter mAdapter;
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_view_pager;
    }

    @Override
    public void initViews(View rootView) {
        mRecyclerView = rootView.findViewById(R.id.recycler);

        mLayoutManager = new ViewPagerLayoutManager(getActivity());
        mLayoutManager.setOnViewPagerListener(new OnViewPagerListener() {
            @Override
            public void onPageRelease(boolean isNext, int position) {
                MyLog.debug(TAG,"释放位置:"+position +" 下一页:"+isNext);
                int index=0;
                if (isNext){
                    index=0;
                }else{
                    index=1;
                }
                releaseVideo(index);
            }

            @Override
            public void onPageSelected(int position, boolean isBottom) {
                MyLog.debug(TAG,"选中位置:"+position+"  是否是滑动到底部:"+isBottom);
                playVideo(0);
            }

            @Override
            public void onLayoutComplete() {
                MyLog.debug(TAG,"[onLayoutComplete]=====");
            }
        });
        mRecyclerView.setLayoutManager(mLayoutManager);
        mAdapter=new ViewPagerAdapter(getActivity(),getData());
        mRecyclerView.setAdapter(mAdapter);
    }



    private void playVideo(int position) {
        View itemView = mRecyclerView.getChildAt(0);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final RelativeLayout rootView = itemView.findViewById(R.id.root_view);
        final MediaPlayer[] mediaPlayer = new MediaPlayer[1];
        videoView.start();
        videoView.setOnInfoListener(new MediaPlayer.OnInfoListener() {
            @Override
            public boolean onInfo(MediaPlayer mp, int what, int extra) {
                mediaPlayer[0] = mp;
                Log.e(TAG,"onInfo");
                mp.setLooping(true);
                imgThumb.animate().alpha(0).setDuration(200).start();
                return false;
            }
        });
        videoView.setOnPreparedListener(new MediaPlayer.OnPreparedListener() {
            @Override
            public void onPrepared(MediaPlayer mp) {
                Log.e(TAG,"onPrepared");

            }
        });


        imgPlay.setOnClickListener(new View.OnClickListener() {
            boolean isPlaying = true;
            @Override
            public void onClick(View v) {
                if (videoView.isPlaying()){
                    Log.e(TAG,"isPlaying:"+videoView.isPlaying());
                    imgPlay.animate().alpha(1f).start();
                    videoView.pause();
                    isPlaying = false;
                }else {
                    Log.e(TAG,"isPlaying:"+videoView.isPlaying());
                    imgPlay.animate().alpha(0f).start();
                    videoView.start();
                    isPlaying = true;
                }
            }
        });
    }

    private void releaseVideo(int index){
        View itemView = mRecyclerView.getChildAt(index);
        final VideoView videoView = itemView.findViewById(R.id.video_view);
        final ImageView imgThumb = itemView.findViewById(R.id.img_thumb);
        final ImageView imgPlay = itemView.findViewById(R.id.img_play);
        videoView.stopPlayback();
        imgThumb.animate().alpha(1).start();
        imgPlay.animate().alpha(0f).start();
    }
    private ArrayList<PViewPagerEntity> getData(){
        ArrayList<PViewPagerEntity> mList=new ArrayList<>();
        int[] imgs = {R.mipmap.img_video_1,R.mipmap.img_video_2,R.mipmap.img_video_1,R.mipmap.img_video_2
        ,R.mipmap.img_video_1,R.mipmap.img_video_2,R.mipmap.img_video_1,R.mipmap.img_video_2,
                R.mipmap.img_video_1,R.mipmap.img_video_2};
        int[] videos = {R.raw.video_1,R.raw.video_2,R.raw.video_1,R.raw.video_2,R.raw.video_1,R.raw.video_2
        ,R.raw.video_1,R.raw.video_2,R.raw.video_1,R.raw.video_2,
                R.raw.video_1,R.raw.video_2};
        for (int i=0;i<imgs.length;i++){
            mList.add(new PViewPagerEntity(imgs[i],videos[i]));
        }
        return mList;
    }
}
