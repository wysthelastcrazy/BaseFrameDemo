package com.beta.MoneyballMaster.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.adapter.base.BaseViewHolder;
import com.beta.MoneyballMaster.entity.PSlideEntity;

import java.util.ArrayList;

/**
 * Created by yas on 2018/5/31.
 */

public class SlideAdapter extends BaseRecyclerAdapter<SlideAdapter.MyViewHolder,PSlideEntity>{

    public SlideAdapter(Context mContext, ArrayList<PSlideEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_slide;
    }

    @Override
    protected MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    class MyViewHolder extends BaseViewHolder<PSlideEntity>{
        private ImageView imgBg;
        private ImageView userIcon;
        private TextView tvTitle;
        private TextView userSay;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            imgBg = itemView.findViewById(R.id.img_bg);
            userIcon = itemView.findViewById(R.id.img_user);
            tvTitle = itemView.findViewById(R.id.tv_title);
            userSay = itemView.findViewById(R.id.tv_user_say);
        }

        @Override
        public void setValues(PSlideEntity pSlideEntity) {
            imgBg.setImageResource(pSlideEntity.mItemBg);
            tvTitle.setText(pSlideEntity.mTitle);
            userIcon.setImageResource(pSlideEntity.mUserIcon);
            userSay.setText(pSlideEntity.mUserSay);
        }
    }
}
