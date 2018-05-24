package com.beta.MoneyballMaster.adapter.base;

import android.content.Context;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import java.util.ArrayList;

/**
 * Created by yas on 2017/7/19.
 */

public abstract class BaseRecyclerAdapter<VH extends BaseViewHolder,T>extends RecyclerView.Adapter<VH> implements View.OnClickListener {
    protected Context mContext;
    private ArrayList<T> mList;
    private OnItemClickListener<T> itemClickListener;
    public BaseRecyclerAdapter(Context mContext, ArrayList<T> mList) {
        this.mContext=mContext;
        this.mList = mList;
    }
    protected abstract int getItemLayout();
    @Override
    public VH onCreateViewHolder(ViewGroup parent, int viewType) {
        View itemView = LayoutInflater.from(mContext).inflate(getItemLayout(), parent, false);
        return getViewHolder(itemView);
    }
    @Override
    public void onBindViewHolder(VH holder, int position) {
        if (holder!=null){
            holder.setPos(position);
            holder.setSize(getItemCount());
            holder.initView();
            holder.setValues(mList.get(position));
            holder.itemView.setTag(position);
            holder.itemView.setOnClickListener(this);
        }
    }
    @Override
    public int getItemCount() {
        if (mList!=null){
            return mList.size();
        }
        return 0;
    }

    /**
     * 设置item点击回调
     * @param itemClickListener
     */
    public void setOnItemClickListener(OnItemClickListener itemClickListener){
         this.itemClickListener=itemClickListener;
    }

    public void appendList(ArrayList<T> mList){
        if(this.mList == null){
            this.mList = mList;
        }else{
            this.mList.addAll(mList);
        }
        notifyDataSetChanged();
    }

    public void reSetList(ArrayList<T> mList){
        this.mList = mList;
        notifyDataSetChanged();
    }
    public T getItemEntity(int pos){
        if (mList!=null&&mList.size()>pos){
            return mList.get(pos);
        }
        return null;
    }
    public ArrayList<T> getList(){
        return mList;
    }
    protected abstract VH getViewHolder(View itemView);

    @Override
    public void onClick(View v) {
        if (itemClickListener!=null){
            int pos= (int) v.getTag();
            itemClickListener.onItemClick(v,mList.get(pos),pos);
        }
    }
}
