package com.wys.baseframedemo.activity;

import android.animation.Animator;
import android.animation.ValueAnimator;
import android.support.v4.view.ViewCompat;
import android.os.Bundle;
import android.support.v7.widget.LinearLayoutManager;
import android.support.v7.widget.RecyclerView;
import android.view.View;
import android.view.ViewTreeObserver;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.wys.baseframedemo.R;
import com.wys.baseframedemo.activity.base.BaseActivity;
import com.wys.baseframedemo.adapter.AdapterTest;

import java.util.ArrayList;

public class MainActivity extends BaseActivity {
    private final String TAG="MainActivity";
    private int mLayoutHeight = 0;  //动画执行的padding高度
    private boolean isOpen = false; //是否开启状态
    private TextView lookDetail;
    private LinearLayout layer2;
    private ImageView icon1;
    private TextView tvDetail;

    private RecyclerView mRecyclerView;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        initView();

        //展开/收起
//        initShowHide();
        ArrayList<String> mList=new ArrayList<>();
        for (int i=1;i<11;i++){
            mList.add("详情详情详情详情详情详情详情详情详情详情"+i);
        }
        AdapterTest mAdapter=new AdapterTest(this,mList);
        mRecyclerView.setAdapter(mAdapter);
    }

    private void initView() {
//        lookDetail = (TextView) findViewById(R.id.look_detail);
//        layer2 = (LinearLayout) findViewById(R.id.layer2);
//        icon1 = (ImageView) findViewById(R.id.icon1);
//        tvDetail= (TextView) findViewById(R.id.tv_detail);
//        tvDetail.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View view) {
//                Toast.makeText(MainActivity.this,"详情",Toast.LENGTH_LONG).show();;
//            }
//        });
        mRecyclerView= (RecyclerView) this.findViewById(R.id.mRecyclerView);
        LinearLayoutManager manager=new LinearLayoutManager(this);
        mRecyclerView.setLayoutManager(manager);
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
                layer2.setPadding(0,-mLayoutHeight,0,0);
            }
        });

        //点击,开始执行动画
        lookDetail.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                ValueAnimator valueAnimator = new ValueAnimator();
                if (isOpen){
                    valueAnimator.setIntValues(0, -mLayoutHeight);
                    lookDetail.setText("查看详情");
                }else {
                    valueAnimator.setIntValues(-mLayoutHeight, 0);
                    lookDetail.setText("收起");
                }
                //设置监听的值
                valueAnimator.addUpdateListener(new ValueAnimator.AnimatorUpdateListener() {
                    @Override
                    public void onAnimationUpdate(ValueAnimator animator) {
                        int value = (int) animator.getAnimatedValue();
                        layer2.setPadding(0,value,0,0);
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
