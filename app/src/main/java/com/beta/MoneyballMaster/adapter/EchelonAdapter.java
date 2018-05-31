package com.beta.MoneyballMaster.adapter;

import android.content.Context;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.adapter.base.BaseViewHolder;
import com.beta.MoneyballMaster.entity.PEchelonEntity;

import java.util.ArrayList;

/**
 * Created by yas on 2018/5/31.
 */

public class EchelonAdapter extends BaseRecyclerAdapter<EchelonAdapter.MyViewHolder,PEchelonEntity>{

    public EchelonAdapter(Context mContext, ArrayList<PEchelonEntity> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.item_echelon;
    }

    @Override
    protected MyViewHolder getViewHolder(View itemView) {
        return new MyViewHolder(itemView);
    }

    class MyViewHolder extends BaseViewHolder<PEchelonEntity>{
        private ImageView icon;
        private ImageView bg;
        private TextView nickName;
        private TextView desc;
        public MyViewHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            icon = itemView.findViewById(R.id.img_icon);
            bg = itemView.findViewById(R.id.img_bg);
            nickName = itemView.findViewById(R.id.tv_nickname);
            desc = itemView.findViewById(R.id.tv_desc);
        }

        @Override
        public void setValues(PEchelonEntity pEchelonEntity) {
            icon.setImageResource(pEchelonEntity.iconRes);
            nickName.setText(pEchelonEntity.nickName);
            desc.setText(pEchelonEntity.desc);
            bg.setImageResource(pEchelonEntity.bgRes);
        }
    }
}
