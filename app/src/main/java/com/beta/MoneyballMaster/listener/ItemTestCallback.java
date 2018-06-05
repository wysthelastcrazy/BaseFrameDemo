package com.beta.MoneyballMaster.listener;

import android.graphics.Canvas;
import android.support.v7.widget.RecyclerView;
import android.support.v7.widget.helper.ItemTouchHelper;

import com.beta.MoneyballMaster.adapter.AdapterTest;


/**
 * Created by yas on 2018/6/5.
 */

public class ItemTestCallback extends ItemTouchHelper.Callback{
    private final AdapterTest mAdapter;
    public ItemTestCallback(AdapterTest adapter){
        this.mAdapter=adapter;
    }
    /**
     * 设置滑动方向
     * @param recyclerView
     * @param viewHolder
     * @return
     */
    @Override
    public int getMovementFlags(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        int dragFlags=ItemTouchHelper.UP|ItemTouchHelper.DOWN;  //允许上下滑动
        int swipeFlags=ItemTouchHelper.LEFT;    //只允许从右向左侧滑
        return makeMovementFlags(dragFlags,swipeFlags);
    }

    /**
     * 当用户拖动一个Item进行上下移动从旧的位置到新的位置的时候会调用该方法，
     * 在该方法内，我们可以调用Adapter的notifyItemMoved方法来交换两个ViewHolder的位置，
     * 最后放回true，表示被拖动的ViewHolder已经移动到了目的的位置。
     * @param recyclerView
     * @param viewHolder
     * @param target
     * @return
     */
    @Override
    public boolean onMove(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, RecyclerView.ViewHolder target) {
        mAdapter.onItemMove(viewHolder.getAdapterPosition(),target.getAdapterPosition());
        return true;
    }

    /**
     * 当用户左右滑动item达到删除条件时，会调用该方法，一般手指触摸滑动的距离达到RecyclerView宽
     * 度的一半时，再松开手指，此时改item会继续向原先滑动方向划过去并且调用onSwiped方法进行删除，
     * 否则会反向滑回原来的位置；
     * 如果在onSwiped方法内我们没有进行任何操作，即不删除已经滑过去的item，那么就会留下空白，因为
     * 实际上该itemView还占据这该位置，只是移除了我们的可视范围
     * @param viewHolder
     * @param direction
     */
    @Override
    public void onSwiped(RecyclerView.ViewHolder viewHolder, int direction) {
        mAdapter.onItemDel(viewHolder.getAdapterPosition());
    }

    /**
     * 是否支持长按拖动，默认返回true
     * @return
     */
    @Override
    public boolean isLongPressDragEnabled() {
        return super.isLongPressDragEnabled();
    }

    /**
     * 该方法返回true时，表示如果用户触摸并右滑动了View，那么可以执行滑动删除操作，
     * 即可调用onSwiped方法。默认返回true
     * @return
     */
    @Override
    public boolean isItemViewSwipeEnabled() {
        return super.isItemViewSwipeEnabled();
    }

    /**
     * 从静止状态变为拖拽或者滑动的时候会回调该方法，参数actionState表示当前的状态。
     * @param viewHolder
     * @param actionState
     */
    @Override
    public void onSelectedChanged(RecyclerView.ViewHolder viewHolder, int actionState) {
        super.onSelectedChanged(viewHolder, actionState);
    }

    /**
     * 当用户操作完某个item并且其动画也结束后会调用该方法，一般我们在该方法内恢复ItemView的初始状态，
     * 防止由于复用而产生的显示错乱问题
     * @param recyclerView
     * @param viewHolder
     */
    @Override
    public void clearView(RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder) {
        super.clearView(recyclerView, viewHolder);
    }

    /**
     * 我们可以在这个方法内实现我们自定义的交互规则或者自定义的动画效果
     * @param c
     * @param recyclerView
     * @param viewHolder
     * @param dX
     * @param dY
     * @param actionState
     * @param isCurrentlyActive
     */
    @Override
    public void onChildDraw(Canvas c, RecyclerView recyclerView, RecyclerView.ViewHolder viewHolder, float dX, float dY, int actionState, boolean isCurrentlyActive) {
        super.onChildDraw(c, recyclerView, viewHolder, dX, dY, actionState, isCurrentlyActive);
    }
}
