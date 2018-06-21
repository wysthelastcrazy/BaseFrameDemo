package com.beta.MoneyballMaster.note;

/**
 * Created by yas on 2018/6/19.
 */

public class Note_View {
    /**
     *
     * ViewRoot和DecorView的概念：
     *
     *    ViewRoot对应于ViewRootImpl类，它是链接WindowManager和DecorView的纽带，View的三大流程均是通过ViewRoot
     * 来完成的。在ActivityThread中，当Activity对象被创建完毕后，会将DecorView添加到Window中，同时会创建
     * ViewRootImpl对象，并将ViewRootImpl对象和DecorView建立关联，这个过程看如下源码：
     *      root=new ViewRootImpl(view.getContext(),display);
     *      root.setView(view,wparams,panelParentView);
     *
     * View的绘制流程是从ViewRoot的performTraversals方法开始的，它经过measure、layout和draw三个过程才能最终
     * 将一个View绘制出来，其中measure用来测量View的宽高，layout用来确定View在父容器中的放置位置，而draw则
     * 负责将View绘制在屏幕上。
     *
     *                    针对performTraversals的大致流程如下：
     *
     *               performTraversals
     *
     *
     * View的三大流程：measure、layout、draw
     *
     */
}
