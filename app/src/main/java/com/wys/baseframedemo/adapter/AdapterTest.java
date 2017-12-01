package com.wys.baseframedemo.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.wys.baseframedemo.adapter.base.BaseRecyclerAdapter;
import com.wys.baseframedemo.msglist.item.TestItemHolder;

import java.util.ArrayList;

/**
 * Created by yas on 2017/12/1.
 */

public class AdapterTest extends BaseRecyclerAdapter<TestItemHolder,String>{
    public AdapterTest(Context mContext, ArrayList<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected TestItemHolder getViewHolder(ViewGroup parent) {
        return new TestItemHolder(mContext,parent);
    }
}
