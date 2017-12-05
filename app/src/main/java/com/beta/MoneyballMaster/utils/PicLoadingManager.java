package com.beta.MoneyballMaster.utils;

import android.content.Context;
import android.graphics.drawable.Drawable;
import android.widget.ImageView;

import com.bumptech.glide.DrawableRequestBuilder;
import com.bumptech.glide.DrawableTypeRequest;
import com.bumptech.glide.Glide;

import java.io.File;

public class PicLoadingManager {
	private final String TAG = "PicListViewManager";
	private static PicLoadingManager instance = null;

	private PicLoadingManager() {
	}


	public static PicLoadingManager getInstance(){
		if(instance == null){
			instance = new PicLoadingManager();
		}
		return instance;
	}
	
	public void requestGlideImg(Context mContext, ImageView imgView, String picUrl){
		DrawableRequestBuilder builder = Glide.with(mContext).load(picUrl);
		builder.centerCrop();
		builder.into(imgView);
	}

	public void requestGlideImgV2(Context mContext, ImageView imgView, String picUrl, Drawable drawable){
		DrawableRequestBuilder builder = Glide.with(mContext).load(picUrl);
		builder.centerCrop();
		if(drawable != null){
			builder.placeholder(drawable).crossFade().centerCrop();
		}
		builder.into(imgView);
	}

	/**
	 * glide加载本地图片
	 * @param mContext
	 * @param imageView
	 * @param picPath
	 */
	public void requestLocImg(Context mContext, ImageView imageView, String picPath){
		File file=new File(picPath);
		DrawableTypeRequest builder= Glide.with(mContext).load(file);
		builder.centerCrop();
		builder.into(imageView);
	}
}
