package com.hngg.jianshi.ui.community;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.utils.Constant;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Date: 2021/2/21
 * Timer: 16:45
 * Author: nedhuo
 * Description:
 */
public class CommunityVideoActivity extends GSYBaseActivityDetail {
    @BindView(R.id.videoPlayer)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.tv_description)
    TextView tvDescription;
    @BindView(R.id.iv_headImage)
    ImageView ivHeadImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_personDesc)
    TextView tvPersonDesc;
    private Data mVideoData;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_community_video);
        ButterKnife.bind(this);
        initData();
        initView();
    }

    @Override
    public GSYBaseVideoPlayer getGSYVideoPlayer() {
        return videoPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        Glide.with(this).load(mVideoData.getCover().getFeed()).centerCrop().into(imageView);
        //  loadCover(imageView,);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(mVideoData.getPlayUrl())
                .setCacheWithPlay(true)
                .setVideoTitle(" ")
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekRatio(1);
    }

    @Override
    public void clickForFullScreen() {

    }

    @Override
    public boolean getDetailOrientationRotateAuto() {
        return false;
    }

    private void initData() {
        //获取页面传递数据
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.VIDEO_BUNDLE);
        mVideoData = (Data) bundle.get(Constant.VIDEO_BEAN);
    }

    private void initView() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);

        initVideoBuilderMode();

        Glide.with(this)
                .load(mVideoData.getOwner().getAvatar())
                .centerCrop()
                .circleCrop()
                .into(ivHeadImage);
        tvName.setText(mVideoData.getOwner().getNickname());
        tvDescription.setText(mVideoData.getDescription());
        tvPersonDesc.setText(mVideoData.getOwner().getDescription());
    }
}
