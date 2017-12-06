package com.beta.MoneyballMaster.widget;

import android.content.Context;
import android.support.annotation.Nullable;
import android.support.v4.util.SparseArrayCompat;
import android.support.v7.widget.GridLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.StaggeredGridLayoutManager;
import android.util.AttributeSet;
import android.view.View;
import android.view.ViewGroup;

/***
 * Created by yas on 2017/12/1.
 * 可以添加headerView和footerView的RecyclerView
 */
public class XRecyclerView extends RecyclerView {
    private static final int BASE_ITEM_TYPE_HEADER = 100000;
    private static final int BASE_ITEM_TYPE_FOOTER = 200000;
    private SparseArrayCompat<View> mHeaderViews = new SparseArrayCompat<>();
    private SparseArrayCompat<View> mFooterViews = new SparseArrayCompat<>();
    private WrapAdapter mWrapAdapter;
    private final RecyclerView.AdapterDataObserver mDataObserver = new DataObserver();
    public XRecyclerView(Context context) {
        super(context);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs) {
        super(context, attrs);
    }

    public XRecyclerView(Context context, @Nullable AttributeSet attrs, int defStyle) {
        super(context, attrs, defStyle);
    }

    @Override
    public void setAdapter(Adapter adapter) {
        mWrapAdapter = new WrapAdapter(adapter);
        super.setAdapter(mWrapAdapter);
        adapter.registerAdapterDataObserver(mDataObserver);
        mDataObserver.onChanged();
    }

    public void addHeaderView(View view) {
        mHeaderViews.put(mHeaderViews.size() + BASE_ITEM_TYPE_HEADER, view);
        if (mWrapAdapter != null) {
            mWrapAdapter.notifyDataSetChanged();
        }
    }

    public void addFootView(View view) {
        mFooterViews.put(mFooterViews.size() + BASE_ITEM_TYPE_FOOTER, view);
        if (mWrapAdapter != null) {
            mWrapAdapter.notifyDataSetChanged();
        }
    }
    /**622848 0631676136114
     * 装饰类
     */
    private class WrapAdapter extends Adapter<ViewHolder> {

        private Adapter mInnerAdapter;

        public WrapAdapter(Adapter adapter) {
            this.mInnerAdapter = adapter;
        }

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            if (mHeaderViews.get(viewType) != null) {

                ViewHolder holder = new SimpleViewHolder(mHeaderViews.get(viewType));
                return holder;

            } else if (mFooterViews.get(viewType) != null) {
                ViewHolder holder = new SimpleViewHolder(mFooterViews.get(viewType));
                return holder;
            }
            return mInnerAdapter.onCreateViewHolder(parent, viewType);
        }

        @Override
        public int getItemViewType(int position) {
            if (isHeaderViewPos(position)) {
                return mHeaderViews.keyAt(position);
            } else if (isFooterViewPos(position)) {
                return mFooterViews.keyAt(position - getHeadersCount() - getRealItemCount());
            }
            return mInnerAdapter.getItemViewType(position - getHeadersCount());
        }

        @Override
        public void onBindViewHolder(ViewHolder holder, int position) {
            if (isHeaderViewPos(position)) {
                return;
            }
            if (isFooterViewPos(position)) {
                return;
            }
            mInnerAdapter.onBindViewHolder(holder, position - getHeadersCount());
        }

        @Override
        public int getItemCount() {
            return getHeadersCount() + getFootersCount() + getRealItemCount();
        }

        @Override
        public void onAttachedToRecyclerView(RecyclerView recyclerView) {
            mInnerAdapter.onAttachedToRecyclerView(recyclerView);
            final LayoutManager layoutManager = recyclerView.getLayoutManager();
            if (layoutManager instanceof GridLayoutManager) {
                final GridLayoutManager gridLayoutManager = (GridLayoutManager) layoutManager;
                final GridLayoutManager.SpanSizeLookup spanSizeLookup = gridLayoutManager.getSpanSizeLookup();
                gridLayoutManager.setSpanSizeLookup(new GridLayoutManager.SpanSizeLookup() {
                    @Override
                    public int getSpanSize(int position) {
                        int viewType = getItemViewType(position);
                        if (mHeaderViews.get(viewType) != null || mFooterViews.get(viewType) != null) {
                            return gridLayoutManager.getSpanCount();
                        }
                        if (spanSizeLookup != null) {
                            return spanSizeLookup.getSpanSize(position);
                        }
                        return 1;
                    }
                });
                gridLayoutManager.setSpanCount(gridLayoutManager.getSpanCount());
            }
//            super.onAttachedToRecyclerView(recyclerView);
        }

        @Override
        public void onViewAttachedToWindow(ViewHolder holder) {
//            super.onViewAttachedToWindow(holder);
            mInnerAdapter.onViewAttachedToWindow(holder);
            int position = holder.getLayoutPosition();
            if (isHeaderViewPos(position) || isFooterViewPos(position)) {
                ViewGroup.LayoutParams lp = holder.itemView.getLayoutParams();
                if (lp != null && lp instanceof StaggeredGridLayoutManager.LayoutParams) {
                    StaggeredGridLayoutManager.LayoutParams p =
                            (StaggeredGridLayoutManager.LayoutParams) lp;
                    p.setFullSpan(true);
                }
            }
        }

        /**
         * 判断是否为headerView
         *
         * @param position
         * @return
         */
        private boolean isHeaderViewPos(int position) {
            return position < getHeadersCount();
        }

        private boolean isFooterViewPos(int position) {
            return position >= getHeadersCount() + getRealItemCount();
        }

        public int getHeadersCount() {
            return mHeaderViews.size();
        }

        public int getFootersCount() {
            return mFooterViews.size();
        }

        private int getRealItemCount() {
            if (mInnerAdapter == null) {
                return 0;
            }
            return mInnerAdapter.getItemCount();
        }

        private class SimpleViewHolder extends ViewHolder {
            public SimpleViewHolder(View itemView) {
                super(itemView);
            }
        }
    }
    private class DataObserver extends RecyclerView.AdapterDataObserver {
        @Override
        public void onChanged() {
            if (mWrapAdapter != null) {
                mWrapAdapter.notifyDataSetChanged();
            }
        }

        @Override
        public void onItemRangeInserted(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeInserted(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount);
        }

        @Override
        public void onItemRangeChanged(int positionStart, int itemCount, Object payload) {
            mWrapAdapter.notifyItemRangeChanged(positionStart, itemCount, payload);
        }

        @Override
        public void onItemRangeRemoved(int positionStart, int itemCount) {
            mWrapAdapter.notifyItemRangeRemoved(positionStart, itemCount);
        }

        @Override
        public void onItemRangeMoved(int fromPosition, int toPosition, int itemCount) {
            mWrapAdapter.notifyItemMoved(fromPosition, toPosition);
        }
    };
}