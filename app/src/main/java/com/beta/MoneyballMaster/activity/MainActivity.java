package com.beta.MoneyballMaster.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseActivity;
import com.beta.MoneyballMaster.activity.fragment.BannerFragment;
import com.beta.MoneyballMaster.activity.fragment.EchelonFragment;
import com.beta.MoneyballMaster.activity.fragment.FirstFragment;
import com.beta.MoneyballMaster.activity.fragment.SkidRightFragment;
import com.beta.MoneyballMaster.activity.fragment.SlideFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.mBottomView)
    BottomNavigationBar mBottomView;
    private Fragment mEchelonFragment;
    private Fragment mSlideFragment;
    private FragmentManager manager;
    private Fragment currFragment;
    private Fragment mSkidRightFragment;
    private Fragment mBannerFragment;
    @Override
    protected int getLayoutRes() {
        return R.layout.activity_main;
    }

    @Override
    protected void initLayout() {
        mBottomView.setMode(BottomNavigationBar.MODE_SHIFTING );
        mBottomView.setBackgroundStyle(BottomNavigationBar.BACKGROUND_STYLE_RIPPLE);
        mBottomView.setBarBackgroundColor(R.color.common_blue);
        mBottomView.addItem(new BottomNavigationItem(R.drawable.btn_guide_selector, getString(R.string.tab_guide)).setActiveColorResource(R.color.bg_tab))
                .addItem(new BottomNavigationItem(R.drawable.btn_score_selector, getString(R.string.tab_score)).setActiveColorResource(R.color.bg_tab))
                .addItem(new BottomNavigationItem(R.drawable.btn_data_selector, getString(R.string.tab_data)).setActiveColorResource(R.color.bg_tab))
                .addItem(new BottomNavigationItem(R.drawable.btn_mine_selector, getString(R.string.tab_mine)).setActiveColorResource(R.color.bg_tab))
                .initialise();
        mBottomView.setTabSelectedListener(mTabChangeListener);

        initFragment();
    }
    private void initFragment() {
        mEchelonFragment = new EchelonFragment();
        mSlideFragment = new SlideFragment();
        mSkidRightFragment=new SkidRightFragment();
        mBannerFragment=new BannerFragment();

        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.content, mEchelonFragment, "flag1").commitAllowingStateLoss();
        currFragment = mEchelonFragment;
    }
    private BottomNavigationBar.OnTabSelectedListener mTabChangeListener=new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {
            switch (position){
                case 0:
                    switchContent(currFragment,mEchelonFragment);
                    break;
                case 1:
                    switchContent(currFragment,mSlideFragment);
                    break;
                case 2:
                    switchContent(currFragment,mSkidRightFragment);
                    break;
                case 3:
                    switchContent(currFragment,mBannerFragment);
                    break;
            }
        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };
    public void switchContent(Fragment from, Fragment to) {
        if (currFragment != to) {
            currFragment = to;
            FragmentTransaction transaction = manager.beginTransaction();
            if (!to.isAdded()) {    // 先判断是否被add过
                String flag = "";
                if (to instanceof EchelonFragment)
                    flag = "flag1";
                if (to instanceof SlideFragment)
                    flag = "flag2";
                if (to instanceof SkidRightFragment)
                    flag="flag3";
                if (to instanceof BannerFragment){
                    flag="flag4";
                }
                transaction.hide(from).add(R.id.content, to, flag).commitAllowingStateLoss(); // 隐藏当前的fragment，add下一个到Activity中
            } else {
                transaction.hide(from).show(to).commitAllowingStateLoss(); // 隐藏当前的fragment，显示下一个
            }
        }
    }
}
