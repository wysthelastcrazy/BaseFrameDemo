package com.beta.MoneyballMaster.widget.recycler.customLayoutManager.viewPager;

/**
 * Created by yas on 2018/6/6.
 */

public interface OnViewPagerListener {
    /**
     * 释放
     * @param isNext
     * @param position
     */
    void onPageRelease(boolean isNext,int position);

    /**
     * 选中监听以及判断是否滑动到底部
     * @param position
     * @param isBottom
     */
    void onPageSelected(int position,boolean isBottom);

    /**
     * 布局完成的监听
     */
    void onLayoutComplete();
}
