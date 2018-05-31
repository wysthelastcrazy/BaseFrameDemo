package com.beta.MoneyballMaster.activity;

import android.support.v4.app.Fragment;
import android.support.v4.app.FragmentManager;
import android.support.v4.app.FragmentTransaction;
import android.widget.FrameLayout;

import com.ashokvarma.bottomnavigation.BottomNavigationBar;
import com.ashokvarma.bottomnavigation.BottomNavigationItem;
import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseActivity;
import com.beta.MoneyballMaster.activity.fragment.EchelonFragment;
import com.beta.MoneyballMaster.activity.fragment.FirstFragment;

import butterknife.BindView;


public class MainActivity extends BaseActivity {

    @BindView(R.id.content)
    FrameLayout content;
    @BindView(R.id.mBottomView)
    BottomNavigationBar mBottomView;
    private Fragment firstFragment;
    private FragmentManager manager;
    private Fragment mContent;
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
        firstFragment = new EchelonFragment();
        manager = getSupportFragmentManager();
        FragmentTransaction fragmentTransaction = manager.beginTransaction();
        fragmentTransaction.add(R.id.content, firstFragment, "flag1").commitAllowingStateLoss();
        mContent = firstFragment;
    }
    private BottomNavigationBar.OnTabSelectedListener mTabChangeListener=new BottomNavigationBar.OnTabSelectedListener() {
        @Override
        public void onTabSelected(int position) {

        }

        @Override
        public void onTabUnselected(int position) {

        }

        @Override
        public void onTabReselected(int position) {

        }
    };
    public void switchContent(Fragment from, Fragment to) {

    }
}
