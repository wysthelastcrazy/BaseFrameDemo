package com.beta.MoneyballMaster.widget.customLayoutManager;

import android.graphics.Rect;
import android.support.v7.widget.RecyclerView;
import android.util.SparseArray;
import android.util.SparseBooleanArray;
import android.view.View;

import com.beta.MoneyballMaster.utils.DeviceUtil;
import com.beta.MoneyballMaster.utils.MyLog;

/**
 * Created by yas on 2018/6/1.
 * 自定义LayoutManager主要要求我们完成三件事情：
 * 1.计算每个ItemView的位置；
 * 2.处理滑动事件；
 * 3.缓存并重用ItemView
 */

public class CustomLayoutManager extends RecyclerView.LayoutManager{
    private int totalHeight;
    private int verticalScrollOffset;

    /**用于保存item的位置信息**/
    private SparseArray<Rect> allItemRects=new SparseArray<>();
    /**用于保存item是否处于可见状态的信息**/
    private SparseBooleanArray itemStates=new SparseBooleanArray();
    @Override
    public RecyclerView.LayoutParams generateDefaultLayoutParams() {
        return new RecyclerView.LayoutParams(RecyclerView.LayoutParams.WRAP_CONTENT,
                RecyclerView.LayoutParams.WRAP_CONTENT);
    }


    /***=======================测量Children=================================================***/
    /**
     * 该方法是LayoutManager的入口。它会在如下情况下被调用：
     * 1.在RecyclerVIew初始化时，会被调用两次；
     * 2.在调用adapter.notifyDataSetChanged()时，会被调用
     * 3.在调用setAdapter替换Adapter时，会被调用
     * 4.在RecyclerView执行动画时，它也会被调用
     * 即RecyclerView初始化、数据源改变时都会被调用。
     *
     * 我们需要在这里面layout当前屏幕可见的所有子View，
     * 千万不要layout出所有的子View
     * @param recycler
     * @param state
     */
    @Override
    public void onLayoutChildren(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount()<=0||state.isPreLayout()){
            return;
        }
        super.onLayoutChildren(recycler,state);
        //先把所有的View从RecyclerView中detach掉，然后标记为‘Scrap’状态，表示这些View出于可被重用状态（非显示中）
        //实际就是把View放到了Recycler中的一个集合中。
        detachAndScrapAttachedViews(recycler);
        calculateChildrenSite(recycler);
//        recycleAndFillView(recycler,state);
    }

    /**
     * 实现简单的LayoutManager，不能滑动，也没有缓存机制
     * @param recycler
     */
    private void calculateChildrenSite(RecyclerView.Recycler recycler) {
        totalHeight=0;
        for (int i=0;i<getItemCount();i++){
            //遍历Recycker中保存的View取出来
            View view=recycler.getViewForPosition(i);
            addView(view);  //因为刚刚进行了detach操作，所以现在可以重新添加
            measureChildWithMargins(view,0,0);

            //计算view的实际大小，包括了ItemDecorator中设置的偏移量
            int width=getDecoratedMeasuredWidth(view);
            int height=getDecoratedMeasuredHeight(view);

            Rect mTmpRect=new Rect();
            //调用这个方法能够调整ItemView的大小，以除去ItemDecorator
            calculateItemDecorationsForChild(view,mTmpRect);

            //调用这句我们制定了该View的显示区域，并将View显示上去，此时所有区域都用于显示View
            //包括ItemDecorator设置的距离
            layoutDecorated(view,0,totalHeight,width,totalHeight+height);
            totalHeight+=height;




        }
    }


    /**
     * 双列式的LayoutManager，不能滑动，也没有缓存机制
     * @param recycler
     */
    private void calculateChildrenSiteV2(RecyclerView.Recycler recycler) {
        totalHeight=0;
        for (int i=0;i<getItemCount();i++){
            //遍历Recycker中保存的View取出来
            View view=recycler.getViewForPosition(i);
            addView(view);  //因为刚刚进行了detach操作，所以现在可以重新添加

            //我们自己制定ItemView的尺寸
            measureChildWithMargins(view, DeviceUtil.getDeviceWidth()/2,0);

            //计算view的实际大小，包括了ItemDecorator中设置的偏移量
            int width=getDecoratedMeasuredWidth(view);
            int height=getDecoratedMeasuredHeight(view);

            Rect mTmpRect=allItemRects.get(i);
            if (mTmpRect==null){
                mTmpRect=new Rect();
            }
            //调用这个方法能够调整ItemView的大小，以除去ItemDecorator
            calculateItemDecorationsForChild(view,mTmpRect);

            if (i%2==0){
                //当i能被2整除时，是左，否则是右
                layoutDecoratedWithMargins(view,0,totalHeight,DeviceUtil.getDeviceWidth()/2,
                        totalHeight+height);
            }else{
                layoutDecoratedWithMargins(view,DeviceUtil.getDeviceWidth()/2,totalHeight,
                        DeviceUtil.getDeviceWidth(),totalHeight+height);
                totalHeight+=height;
            }

            //保存ItemView的位置信息
            allItemRects.put(i,mTmpRect);
            //由于之前调用过detachAndScrapAttachedViews(recycler),所以此时item都是不可见的
            itemStates.put(i,false);
        }
    }
    /***========================测量Children  End================================================***/


    /***=========================处理滑动===============================================***/
    @Override
    public boolean canScrollVertically() {
        //返回true表示可以纵向滑动
        return true;
    }

    @Override
    public int scrollVerticallyBy(int dy, RecyclerView.Recycler recycler, RecyclerView.State state) {
        //每次滑动时先释放掉所有的View，因为后面调用recycleAndFillView()时会重新addView()
//        detachAndScrapAttachedViews(recycler);

        //列表向下滚动dy为正，列表向上滚动dy为负，这点与Android坐标系保持一致
        //实际要滑动的距离
        int travel = dy;
        MyLog.INSTANCE.debug("dddd", "[scrollVerticallyBy] dy=" + dy);
        //如果滑动到最顶部
        if (verticalScrollOffset + dy < 0) {
            travel = -verticalScrollOffset;
        } else if (verticalScrollOffset + dy > totalHeight - getVerticalSpace()){
            //如果滑动到最底部
            travel = totalHeight - getVerticalSpace() - verticalScrollOffset;
        }

        //将竖直方向的偏移量+travel
        verticalScrollOffset+=travel;


        //调用该方法通知view在y方向上移动指定距离
        offsetChildrenVertical(-travel);
        //回收并显示View
//        recycleAndFillView(recycler,state);

        return travel;
    }

    public int getVerticalSpace() {
        //计算RecyclerView的可用高度，除去上下Padding值
        return getHeight() - getPaddingBottom() - getPaddingTop();
    }
    public int getHorizontalSpace(){
        return getWidth()-getPaddingRight()-getPaddingLeft();
    }
    @Override
    public boolean canScrollHorizontally() {
        return super.canScrollHorizontally();
    }

    @Override
    public int scrollHorizontallyBy(int dx, RecyclerView.Recycler recycler, RecyclerView.State state) {
        return super.scrollHorizontallyBy(dx, recycler, state);
    }
    /***=========================处理滑动 End===============================================***/


    /***=========================缓存并重用ItemView===============================================***/

    /**
     * 实现缓存最主要的就是先把每个ItemView的位置信息保存起来，然后在滑动过程中通过判断每个ItemView的位置
     * 是否和当前RecyclerView应该显示的区域重合，若有就显示它，若没有就移除并回收
     * @param recycler
     * @param state
     */
    private void recycleAndFillView(RecyclerView.Recycler recycler, RecyclerView.State state) {
        if (getItemCount()<=0||state.isPreLayout()){
            return;
        }
        //当前scroll offset状态下的显示区域
        Rect displayRect=new Rect(0,verticalScrollOffset,getHorizontalSpace(),
                verticalScrollOffset+getVerticalSpace());

        //将滑出屏幕的items回收到Recycle缓存中
        Rect childRect=new Rect();
        for (int i=0;i<getChildCount();i++){
            //这个方法获取的是RecyclerView中的View，注意区别Recycler中的View
            //这获取的是实际的View
            View child=getChildAt(i);
            //下面几个方法能够获取每个View占用空间的位置信息，包括ItemDecorator
            childRect.left=getDecoratedLeft(child);
            childRect.top=getDecoratedTop(child);
            childRect.right=getDecoratedRight(child);
            childRect.bottom=getDecoratedBottom(child);

            //如果item没有在显示区域，就说明需要回收
            if (!Rect.intersects(displayRect,childRect)){
                //移除并回收掉滑出屏幕的View
                removeAndRecycleView(child,recycler);
                //更新该view的状态为未依附
                itemStates.put(i,false);
            }
        }

        //重新显示需要出现在屏幕上的子View
        for (int i=0;i<getItemCount();i++){
            //判断itemView的位置和当前显示区是否重合
            if (Rect.intersects(displayRect,allItemRects.get(i))){
                //获得Recycler中缓存的View
                View itemView=recycler.getViewForPosition(i);
                measureChildWithMargins(itemView,DeviceUtil.getDeviceWidth()/2,0);
                //添加View到RecyclerView上
                addView(itemView);
                //取出先前存好的itemView的位置矩形
                Rect rect=allItemRects.get(i);
                //将这个item布局出来
                layoutDecoratedWithMargins(itemView,
                        rect.left,rect.top-verticalScrollOffset,
                        rect.right,rect.bottom-verticalScrollOffset);
                //更新该View的状态为依附
                itemStates.put(i,true);
            }
        }
        MyLog.INSTANCE.debug("ddddd","[recycleAndFillView]  itemCount:"+getItemCount()+"     childCount:"+getChildCount());
    }
    /***=========================缓存并重用ItemView End===============================================***/


























}
