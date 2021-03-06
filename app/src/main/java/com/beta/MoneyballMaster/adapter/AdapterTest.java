package com.beta.MoneyballMaster.adapter;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.content.Context;
import android.support.v4.view.ViewCompat;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.adapter.base.BaseRecyclerAdapter;
import com.beta.MoneyballMaster.adapter.base.BaseViewHolder;
import com.beta.MoneyballMaster.listener.ItemTouchHelperAdaper;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;

import butterknife.BindView;

/**
 * Created by yas on 2017/12/1.
 */

public class AdapterTest extends BaseRecyclerAdapter<AdapterTest.TestItemHolder,String> implements ItemTouchHelperAdaper{

    public AdapterTest(Context mContext, ArrayList<String> mList) {
        super(mContext, mList);
    }

    @Override
    protected int getItemLayout() {
        return R.layout.layout_look_detail;
    }

    @Override
    protected TestItemHolder getViewHolder(View itemView) {
        return new TestItemHolder(itemView);
    }

    @Override
    public void onItemMove(int fromPosition, int toPosition) {
        Collections.swap(getList(),fromPosition,toPosition);
        notifyItemMoved(fromPosition,toPosition);
    }

    @Override
    public void onItemDel(int position) {
        getList().remove(position);
        notifyItemRemoved(position);
    }

    public class TestItemHolder extends BaseViewHolder<String> {
        @BindView(R.id.look_detail)
        TextView lookDetail;
        @BindView(R.id.icon1)
        ImageView icon1;
        @BindView(R.id.layer1)
        LinearLayout layer1;
        @BindView(R.id.tv_detail)
        TextView tvDetail;
        @BindView(R.id.layer2)
        LinearLayout layer2;
        private int mLayoutHeight = 0;  //动画执行的padding高度
        private boolean isOpen = false; //是否开启状态

        public TestItemHolder(View itemView) {
            super(itemView);
        }

        @Override
        public void initView() {
            lookDetail = itemView.findViewById(R.id.look_detail);
            layer2 = itemView.findViewById(R.id.layer2);
            icon1 = itemView.findViewById(R.id.icon1);
            tvDetail = itemView.findViewById(R.id.tv_detail);
            initShowHide();
        }

        @Override
        public void setValues(String s) {
            tvDetail.setText(s);
            lookDetail.setText(s);
        }

        /**
         * 展开收起 执行动画
         */
        private void initShowHide() {
//        //布局完成
            layer2.getViewTreeObserver().addOnGlobalLayoutListener(new ViewTreeObserver.OnGlobalLayoutListener() {
                @Override
                public void onGlobalLayout() {
                    //移除所有监听
                    layer2.getViewTreeObserver().removeGlobalOnLayoutListener(this);
                    mLayoutHeight = layer2.getHeight();
                    System.out.println("得到的高度：" + mLayoutHeight);
                    //隐藏当前控件
                    layer2.setPadding(0, -mLayoutHeight, 0, 0);
                }
            });

            //点击,开始执行动画
            lookDetail.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View view) {
                    ValueAnimator valueAnimator = new ValueAnimator();
                    if (isOpen) {
                        valueAnimator.setIntValues(0, -mLayoutHeight);
                        lookDetail.setText("查看详情");
                    } else {
                        valueAnimator.setIntValues(-mLayoutHeight, 0);
                        lookDetail.setText("收起");
                    }
                    //设置监听的值
                    valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                        @Override
                        public void onAnimationUpdate(ValueAnimator animator) {
                            int value = (int) animator.getAnimatedValue();
                            layer2.setPadding(0, value, 0, 0);
                        }
                    });
                    //动画执行中监听
                    valueAnimator.addListener(new Animator.AnimatorListener() {
                        @Override
                        public void onAnimationStart(Animator animator) {
                            //动画开始，不能点击
                            lookDetail.setClickable(false);
                        }

                        @Override
                        public void onAnimationEnd(Animator animator) {
                            lookDetail.setClickable(true);
                        }

                        @Override
                        public void onAnimationCancel(Animator animator) {

                        }

                        @Override
                        public void onAnimationRepeat(Animator animator) {

                        }
                    });

                    valueAnimator.setDuration(500);
                    valueAnimator.start();
                    //状态更改
                    isOpen = !isOpen;

                    //进行旋转
                    ViewCompat.animate(icon1).rotationBy(180f).setDuration(500).start();
                }
            });
        }
    }

}