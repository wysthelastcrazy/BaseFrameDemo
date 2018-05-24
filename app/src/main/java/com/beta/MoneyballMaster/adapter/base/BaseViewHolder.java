package com.beta.MoneyballMaster.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.View;

/**
 * Created by yas on 2017/8/31.
 */

public abstract class BaseViewHolder<T> extends RecyclerView.ViewHolder{
    protected final String TAG = getClass().getSimpleName();
    protected int pos;
    protected int size;
    protected Context mContext;
    public BaseViewHolder(View itemView){
        super(itemView);
        this.mContext=mContext;
    }
    public abstract void initView();
    public abstract void setValues(T t);

    public void setPos(int pos){
        this.pos = pos;
    }

    public void setSize(int size){
        this.size = size;
    }
}