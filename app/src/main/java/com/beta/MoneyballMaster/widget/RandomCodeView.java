package com.beta.MoneyballMaster.widget;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.support.annotation.Nullable;
import android.text.TextUtils;
import android.util.AttributeSet;
import android.view.View;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.utils.MyLog;

import java.util.Random;

/**
 * Created by yas on 2018/6/11.
 * 随机验证码
 */

public class RandomCodeView extends View{
    private static final String TAG="RandomCodeView";
    private int mHeight;
    private int mWidth;
    private int mTextSize;
    private Random mRandom;
    private int mBgColor;
    //随机码
    private char[] mCodes=new char[4];
    //随机码颜色
    private int[] mColors=new int[4];
    //字体的y位置
    private float[] mYs=new float[4];
    //是否已经初始化数据的flag
    private boolean flag=false;
    private boolean mIsOnclickRefresh=false;
    private Paint mPaint;
    public RandomCodeView(Context context) {
        this(context,null);
    }

    public RandomCodeView(Context context, AttributeSet attrs) {
        this(context, attrs,0);
    }

    public RandomCodeView(Context context, AttributeSet attrs, int defStyleAttr) {
        super(context, attrs, defStyleAttr);
        mPaint=new Paint();
        if (attrs!=null){
            TypedArray array=context.obtainStyledAttributes(attrs, R.styleable.RandomCodeView);
            mIsOnclickRefresh=array.getBoolean(R.styleable.RandomCodeView_refresh,true);
        }
    }

    @Override
    protected void onMeasure(int widthMeasureSpec, int heightMeasureSpec) {
        super.onMeasure(widthMeasureSpec, heightMeasureSpec);
        //处理wrap_content问题
        int widthSpecMode=MeasureSpec.getMode(widthMeasureSpec);
        int widthSpecSize=MeasureSpec.getSize(widthMeasureSpec);

        int heightSpecMode=MeasureSpec.getMode(heightMeasureSpec);
        int heightSpecSize=MeasureSpec.getSize(heightMeasureSpec);

        if (widthSpecMode==MeasureSpec.AT_MOST&&heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(200,100);
        }else if (widthSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(heightSpecSize*2,heightSpecSize);
        }else if (heightSpecMode==MeasureSpec.AT_MOST){
            setMeasuredDimension(widthSpecSize,widthSpecSize/2);
        }
        mHeight=getMeasuredHeight();
        mWidth=getMeasuredWidth();

        mTextSize= (int) (mWidth/4.5);
        //如果高度小于字体大小，则字体高度为0.5倍的view高度，防止字体超出view大小
        if (mHeight<mTextSize){
            mTextSize= (int) (0.5*mHeight);
        }
    }

    @Override
    protected void onDraw(Canvas canvas) {
        super.onDraw(canvas);
        //先初始化字体大小，下一步初始化随机位置
        //去锯齿
        mPaint.setAntiAlias(true);
        //设置字体大小，单位px
        mPaint.setTextSize(mTextSize);
        //设置笔触宽度
        mPaint.setStrokeWidth(3);
        //设置阴影为null
        mPaint.setShader(null);

        Paint.FontMetrics fontMetrics=mPaint.getFontMetrics();

        //获取字体高度
        float textHeight=Math.abs(fontMetrics.ascent)+fontMetrics.descent;
        if (!flag){
            //初始化各种随机参数，定义flag，防止频繁调用onDraw刷新数据
            init(fontMetrics,mHeight);
        }

        //设置背景色
        setBackgroundColor(mBgColor);

        //起始x位置
        String drawText="A B C D";
        float startX=(getWidth()-mPaint.measureText(drawText))/2;
        //画四个随机字母，每个字母随机颜色
        for (int i=0;i<4;i++){
            mPaint.setColor(mColors[i]);
            float x=startX+i*mPaint.measureText("A ");
            if (i==3){
                canvas.drawText(String.valueOf(mCodes[i]),x,mYs[i],mPaint);
            }else{
                canvas.drawText(mCodes[i]+" ",x,mYs[i],mPaint);
            }
        }

        //画三条干扰线，颜色和位置也可以提前初始化
        for (int i=0;i<3;i++){
            mPaint.setColor(getTextRandomColor());
            canvas.drawLine(0,mRandom.nextInt(mHeight),mWidth,mRandom.nextInt(mHeight),mPaint);
        }

        mPaint.setStrokeWidth(8);
        //画20个干扰点
        for (int i=0;i<20;i++){
            mPaint.setColor(getTextRandomColor());
            canvas.drawPoint(mRandom.nextInt(mWidth),mRandom.nextInt(mHeight),mPaint);
        }
    }

    private int getTextRandomColor() {
        int r = mRandom.nextInt(90) + 40;
        int g = mRandom.nextInt(90) + 40;
        int b = mRandom.nextInt(90) + 40;
        return Color.rgb(r, g, b);
    }

    private void init(Paint.FontMetrics fontMetrics, int mHeight) {
        mRandom=new Random(System.currentTimeMillis());
        mBgColor=getBgRandomColor();
        //获取随机码Y位置
        for (int i=0;i<4;i++){
            mYs[i]=getRandomY(fontMetrics,mHeight);
            mCodes[i]=getRandomText();
            mColors[i]=getTextRandomColor();
        }
        //点击事件
        if (mIsOnclickRefresh){
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //清空数据
                    flag=false;
                    //重绘
                    invalidate();
                }
            });
        }
        flag=true;
    }

    private char getRandomText() {
        int i=mRandom.nextInt(42)+48;
        while (i>57&&i<65){
            i=mRandom.nextInt(42)+48;
        }
        char tmp= (char) i;
        MyLog.debug(TAG,"[getRandomText]  i:"+i+"   tmp:"+tmp);
        return tmp;
    }

    private float getRandomY(Paint.FontMetrics fontMetrics, int mHeight) {
        int min= (int) (mHeight-Math.abs(fontMetrics.ascent)-fontMetrics.descent);
        return mRandom.nextInt(min)+Math.abs(fontMetrics.ascent);
    }

    private int getBgRandomColor() {
        int r = mRandom.nextInt(140) + 115;
        int g = mRandom.nextInt(140) + 115;
        int b = mRandom.nextInt(140) + 115;
        return Color.rgb(r, g, b);
    }

    /**
     * 获取随机验证码
     * @return
     */
    public String getRandomCode(){
        return String.valueOf(mCodes);
    }

    /**
     * 检查输入结果
     * @param code
     * @return
     */
    public boolean checkCode(String code){
        if (TextUtils.isEmpty(code)||
                TextUtils.isEmpty(String.valueOf(mCodes))){
            return false;
        }
        if (String.valueOf(mCodes).toLowerCase().equals(code.trim().toLowerCase())){
            return true;
        }
        return false;
    }

    /**
     * 刷新code
     */
    public void refresh(){
        flag=false;
        invalidate();
    }
    /**
     * setOnClickRefresh 设置是否点击刷新
     *
     * @param refresh 是否点击刷新
     * @return void
     * @api 6
     * @since 3.1.0
     */
    public void setOnClickRefresh(boolean refresh) {

        if (refresh) {
            this.setOnClickListener(new OnClickListener() {
                @Override
                public void onClick(View v) {
                    //清空数据
                    flag = false;
                    //重绘
                    invalidate();
                }
            });
        }
    }
}
