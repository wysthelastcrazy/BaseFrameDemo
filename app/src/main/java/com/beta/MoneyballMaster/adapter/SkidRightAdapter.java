package com.beta.MoneyballMaster.adapter;

import android.content.Context;
import android.content.Intent;
import android.support.v4.app.ActivityOptionsCompat;
import android.support.v4.util.Pair;
import android.view.View;
import android.widget.BaseAdapter;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.adapter.base.BaseViewHolder;
import com.beta.MoneyballMaster.common.Global;
import com.beta.MoneyballMaster.entity.PSkidEntity;
import com.bumptech.glide.Glide;
import com.bumptech.glide.load.engine.DiskCacheStrategy;

import java.util.ArrayList;

/**
 * Created by yas on 2018/5/31.
 */

public class SkidRightAdapter extends BaseRecyclerAdapter<SkidRightAdapter.MyViewHolder,PSkidEntity> {

    public SkidRightAdapter(Context mContext, ArrayList<PSkidEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_skid_right;
    }

    @Override
    protected MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    class MyViewHolder extends BaseViewHolder<PSkidEntity>{
        ImageView imgBg;
        TextView tvTitle;
        TextView tvBottom;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            imgBg = itemView.findViewById(R.id.img_bg);
            tvTitle = itemView.findViewById(R.id.tv_title);
            tvBottom = itemView.findViewById(R.id.tv_bottom);
        }

        @Override
        public void setValues(PSkidEntity pSkidEntity) {
            Glide.with(Global.getContext()).load(pSkidEntity.bgRes).asGif().diskCacheStrategy(DiskCacheStrategy.SOURCE).into(imgBg);
            tvTitle.setText(pSkidEntity.title);
        }
    }
}
