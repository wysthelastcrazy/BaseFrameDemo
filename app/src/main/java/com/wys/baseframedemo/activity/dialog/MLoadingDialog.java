package com.wys.baseframedemo.activity.dialog;

import android.app.Dialog;
import android.content.Context;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup.LayoutParams;
import android.view.animation.Animation;
import android.view.animation.AnimationUtils;
import android.widget.ImageView;
import android.widget.TextView;

import com.wys.baseframedemo.R;


/***
 * 转菊花 对话框
 * @date 2017/07/09
 * @author yas
 */
public class MLoadingDialog extends Dialog {
	private LayoutInflater li = null;
	private TextView txtView;
	
	public MLoadingDialog(Context context) {
		super(context);
		init();
	}
	
	public MLoadingDialog(Context context, int theme) {
		super(context, theme);
		init();
	}

	private void init(){
		li = (LayoutInflater) getContext().getSystemService(Context.LAYOUT_INFLATER_SERVICE);
		this.setCancelable(true);
		this.setCanceledOnTouchOutside(true);
	}
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		View mView = li.inflate(R.layout.dialog_loading, null);
		ImageView imgView =  mView.findViewById(R.id.loading_img);
		txtView = mView.findViewById(R.id.load_tips);
		Animation ani = AnimationUtils.loadAnimation(getContext(),R.anim.loading_anim_rotate);
		ani.setRepeatCount(Animation.INFINITE);
		imgView.startAnimation(ani);
		LayoutParams llp = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.MATCH_PARENT);
		this.setContentView(mView,llp);
	}
	
	public void setMyTips(String str){
		if(txtView != null){
			txtView.setVisibility(View.VISIBLE);
			txtView.setText(str);
		}
	}
}
