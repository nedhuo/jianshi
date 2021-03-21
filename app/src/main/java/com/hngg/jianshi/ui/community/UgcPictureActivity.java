package com.hngg.jianshi.ui.community;

import android.app.Activity;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import androidx.viewpager.widget.PagerAdapter;
import androidx.viewpager.widget.ViewPager;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.utils.Constant;

import java.util.ArrayList;
import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;

/**
 * Date: 2021/2/21
 * Timer: 16:44
 * Author: nedhuo
 * Description:
 */
public class UgcPictureActivity extends AppCompatActivity {
    //    @BindView(R.id.rv_ugcPicture)
//    RecyclerView rvUgcPicture;
    @BindView(R.id.vp_ugcPicture)
    ViewPager viewPager;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.iv_headImage)
    ImageView ivHeadImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_personDesc)
    TextView tvPersonDesc;
    private Data mData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_ugcpicture);
        ButterKnife.bind(this);

        initData();

        initView();
    }

    private void initView() {
        LinearLayoutManager layoutManager = new LinearLayoutManager(this);
        layoutManager.setOrientation(RecyclerView.HORIZONTAL);
//        rvUgcPicture.setLayoutManager(layoutManager);
//        rvUgcPicture.setAdapter(new PictureAdapter(mData.getUrls(), this));

        viewPager.setAdapter(new UgcPagerAdapter(initViewPagerData(), this));
        viewPager.setCurrentItem(0);
        viewPager.setOffscreenPageLimit(mData.getUrls().size());

        Glide.with(this)
                .load(mData.getOwner().getAvatar())
                .centerCrop()
                .circleCrop()
                .into(ivHeadImage);
        tvName.setText(mData.getOwner().getNickname());
        tvDescription.setText(mData.getDescription());
        tvPersonDesc.setText(mData.getOwner().getDescription());
    }

    private void initData() {
        Bundle bundleExtra = getIntent().getBundleExtra(Constant.PICTURE_BUNDLE);
        mData = (Data) bundleExtra.get(Constant.PICTURE_BEAN);
    }

    private List<View> initViewPagerData() {
        List<View> views = new ArrayList<>();
        List<String> urls = mData.getUrls();
        int i = 0;
        while (i < urls.size()) {
            View view = LayoutInflater.from(this).inflate(R.layout.item_ugcpicture, null, false);
            ImageView imageView = view.findViewById(R.id.iv_content);
            Glide.with(imageView)
                    .load(urls.get(i))
                    .centerCrop()
                    .into(imageView);
            views.add(view);
            i++;
        }
        return views;
    }


    private class UgcPagerAdapter extends PagerAdapter {
        private final List<View> mDatas;

        UgcPagerAdapter(List<View> list, Activity ctx) {
            mDatas = list;
        }

        @Override
        public int getCount() {
            return mDatas.size();
        }

        @NonNull
        @Override
        public Object instantiateItem(@NonNull ViewGroup container, int position) {
            container.addView(mDatas.get(position));
            return mDatas.get(position);
        }

        @Override
        public boolean isViewFromObject(@NonNull View view, @NonNull Object object) {
            return view == object;
        }
    }

    private class PictureAdapter extends RecyclerView.Adapter {

        private final List<String> mDatas;
        private final Activity mCtx;

        PictureAdapter(List<String> data, Activity ctx) {
            mDatas = data;
            mCtx = ctx;
        }

        @NonNull
        @Override
        public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {

            View view = LayoutInflater.from(mCtx).inflate(R.layout.item_ugcpicture, parent, false);
            return new RecyclerView.ViewHolder(view) {
            };
        }

        @Override
        public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
            ImageView itemView = (ImageView) holder.itemView.findViewById(R.id.iv_content);
            Glide.with(itemView)
                    .load(mDatas.get(position))
                    .centerCrop()
                    .into(itemView);
        }

        @Override
        public int getItemCount() {
            return mDatas.size();
        }


    }
}
