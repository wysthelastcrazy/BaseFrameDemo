package com.beta.MoneyballMaster.activity.fragment;

import android.support.v7.widget.OrientationHelper;
import android.support.v7.widget.RecyclerView;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import com.beta.MoneyballMaster.R;
import com.beta.MoneyballMaster.activity.base.BaseFragment;
import com.beta.MoneyballMaster.common.Global;
import com.beta.MoneyballMaster.widget.recycler.customLayoutManager.banner.BannerLayoutManager;

import java.util.ArrayList;
import java.util.List;

/**
 * Created by yas on 2018/6/1.
 */

public class BannerFragment extends BaseFragment {
    private ImageView mImg1,mImg2,mImg3,mImg4;
    private List<ImageView> mImgList=new ArrayList<>();
    private int mLastSelectPosition=0;
    private int mCurrSelect=0;
    private RecyclerView mRecycler_1;       //广告轮播图
    private RecyclerView mRecycler_2;       //消息轮播
    @Override
    public int getLayoutRes() {
        return R.layout.fragment_banner;
    }

    @Override
    public void initViews(View rootView) {
        mImg1 = rootView.findViewById(R.id.img_1);
        mImg2 = rootView.findViewById(R.id.img_2);
        mImg3 = rootView.findViewById(R.id.img_3);
        mImg4 = rootView.findViewById(R.id.img_4);
        mImgList.add(mImg1);
        mImgList.add(mImg2);
        mImgList.add(mImg3);
        mImgList.add(mImg4);

      /*广告轮播图*/
        mRecycler_1 = rootView.findViewById(R.id.recycler1);
        MyAdapter myAdapter = new MyAdapter();
        BannerLayoutManager bannerLayoutManager = new BannerLayoutManager(getActivity(),mRecycler_1,4, OrientationHelper.HORIZONTAL);
        mRecycler_1.setLayoutManager(bannerLayoutManager);
        mRecycler_1.setAdapter(myAdapter);
        bannerLayoutManager.setOnSelectedViewListener(new BannerLayoutManager.OnSelectedViewListener() {
            @Override
            public void onSelectedView(View view, int position) {
                changeUI(position);
            }
        });
        changeUI(0);

        /*消息轮播*/
        mRecycler_2 = rootView.findViewById(R.id.recycler2);
        MyNewsAdapter myNewsAdapter = new MyNewsAdapter();
        BannerLayoutManager bannerNewsLayoutManager = new BannerLayoutManager(getActivity(),mRecycler_2,4,OrientationHelper.VERTICAL);
        bannerNewsLayoutManager.setTimeSmooth(400f);
        mRecycler_2.setLayoutManager(bannerNewsLayoutManager);
        mRecycler_2.setAdapter(myNewsAdapter);
    }

    private void changeUI(int position){
        if (position != mLastSelectPosition) {
            mImgList.get(position).setImageDrawable(getResources().getDrawable(R.drawable.circle_red));
            mImgList.get(mLastSelectPosition).setImageDrawable(getResources().getDrawable(R.drawable.circle_gray));
            mLastSelectPosition = position;
        }

    }

    /**
     * 图片轮播适配器
     */
    class MyAdapter extends RecyclerView.Adapter<MyAdapter.ViewHolder> {
        private int[] imgs = {
                R.mipmap.banner_1,
                R.mipmap.banner_2,
                R.mipmap.banner_3,
                R.mipmap.banner_4,

        };

        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Global.getContext()).inflate(R.layout.item_banner, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.img.setImageResource(imgs[position % 4]);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            ImageView img;
            public ViewHolder(View itemView) {
                super(itemView);
                img = itemView.findViewById(R.id.img);
            }
        }
    }

    /**
     * 新闻轮播适配器
     */
    class MyNewsAdapter extends RecyclerView.Adapter<MyNewsAdapter.ViewHolder> {
        private String[] mTitles = {
                "小米8官方宣布有双路GPS,小米8、小米8SE发售时间曝光",
                "这样的锤子你玩懂了吗?坚果R1带来不一样的体验",
                "三星真的很爱酸苹果!新广告讽刺苹果手机电池降速事件",
                "双摄全面屏 游戏长续航 魅族科技发布魅蓝6T售799元起",
        };
        @Override
        public ViewHolder onCreateViewHolder(ViewGroup parent, int viewType) {
            View view = LayoutInflater.from(Global.getContext()).inflate(R.layout.item_banner_news, parent, false);
            return new ViewHolder(view);
        }

        @Override
        public void onBindViewHolder(final ViewHolder holder, final int position) {
            holder.tv_news.setText(mTitles[position%4]);
        }

        @Override
        public int getItemCount() {
            return Integer.MAX_VALUE;
        }

        public class ViewHolder extends RecyclerView.ViewHolder {
            TextView tv_news;
            public ViewHolder(View itemView) {
                super(itemView);
                tv_news = itemView.findViewById(R.id.tv_news);
            }
        }
    }
}
