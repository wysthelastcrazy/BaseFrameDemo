package com.beta.MoneyballMaster.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.View;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.VideoView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.adapter.base.BaseViewHolder;
import com.beta.MoneyballMaster.entity.PViewPagerEntity;
import com.beta.MoneyballMaster.utils.PkgUtil;

import java.util.ArrayList;


/**
 * Created by yas on 2018/6/6.
 */

public class ViewPagerAdapter extends BaseRecyclerAdapter<ViewPagerAdapter.MyViewHolder,PViewPagerEntity>{
    public ViewPagerAdapter(Context mContext, ArrayList<PViewPagerEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_view_pager;
    }

    @Override
    protected MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    class MyViewHolder extends BaseViewHolder<PViewPagerEntity>{
        ImageView img_thumb;
        VideoView videoView;
        ImageView img_play;
        RelativeLayout rootView;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            img_thumb = itemView.findViewById(R.id.img_thumb);
            videoView = itemView.findViewById(R.id.video_view);
            img_play = itemView.findViewById(R.id.img_play);
            rootView = itemView.findViewById(R.id.root_view);
        }

        @Override
        public void setValues(PViewPagerEntity pViewPagerEntity) {
            img_thumb.setImageResource(pViewPagerEntity.imgRes);
            videoView.setVideoURI(Uri.parse("android.resource://"+ PkgUtil.INSTANCE.getPackageName()+"/"+ pViewPagerEntity.videoRes));
        }
    }
}
