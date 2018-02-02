package com.beta.MoneyballMaster.adapter;

import android.content.Context;
import android.view.ViewGroup;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.msglist.item.TestItemHolder;

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
        return new TestItemHolder(mContext,parent, R.layout.layout_look_detail);
    }
}
