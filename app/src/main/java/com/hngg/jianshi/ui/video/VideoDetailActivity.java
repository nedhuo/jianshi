package com.hngg.jianshi.ui.video;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.arialyy.aria.core.Aria;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.data.bean.home.RelationVideoBean;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.utils.CollectionInfoUtil;
import com.hngg.jianshi.data.database.utils.HistoryInfoUtil;
import com.hngg.jianshi.ui.adapter.RelationVideoAdapter;
import com.hngg.jianshi.ui.adapter.VideoReplyAdapter;
import com.hngg.jianshi.ui.user.UserInfoActivity;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.GlideUtil;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.hngg.network.Observer.BaseObserver;
import com.shuyu.gsyvideoplayer.GSYBaseActivityDetail;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;

import java.util.List;

import butterknife.BindView;
import butterknife.ButterKnife;
import io.reactivex.android.schedulers.AndroidSchedulers;
import timber.log.Timber;
import tv.danmaku.ijk.media.player.IjkMediaPlayer;

/**
 * Date: 2021/2/15
 * Timer: 8:33
 * Author: nedhuo
 * Description: Activity无法继承BaseActivity,因此Presenter无法注入
 * 因为其实现了VideoDetailContract.View,因此Presenter持有他的引用
 * <p>
 * 视频详情页面  视频播放(页面传递)+相关信息(页面传递)+相关评论(网络获取)+相关视频(网络获取)
 */
public class VideoDetailActivity extends GSYBaseActivityDetail<StandardGSYVideoPlayer>
        implements VideoDetailContract.View, View.OnClickListener {


    @BindView(R.id.videoPlayer)
    StandardGSYVideoPlayer videoPlayer;
    @BindView(R.id.tv_author)
    TextView tvTitle;
    @BindView(R.id.tv_tag)
    TextView tvTag;
    @BindView(R.id.tv_desc)
    TextView tvDesc;
    //    @BindView(R.id.tv_collectionCount)
//    TextView tvCollectionCount;
//    @BindView(R.id.tv_replyCount)
//    TextView tvReplyCount;
//    @BindView(R.id.tv_shareCount)
//    TextView tvShareCount;
    @BindView(R.id.iv_collection)
    ImageView ivCollection;
    @BindView(R.id.iv_download)
    ImageView ivDownload;
    @BindView(R.id.iv_share)
    ImageView ivShare;
    @BindView(R.id.rv_relationVideo)
    RecyclerView rvRelationVideo;
    @BindView(R.id.rv_videoReply)
    RecyclerView rvVideoReply;
    @BindView(R.id.iv_headImage)
    ImageView ivHeadImage;
    @BindView(R.id.tv_name)
    TextView tvName;
    @BindView(R.id.tv_personDesc)
    TextView tvPersonDesc;
    @BindView(R.id.ib_back)
    ImageButton ibBack;
    @BindView(R.id.item_author)
    RelativeLayout itemAuthor;

    private Data mVideoData;
    private VideoDetailPresenter mPresenter;
    private final String TAG = "VideoDetailActivity";
    private CollectionInfoUtil mCollectionDao;
    private HistoryInfoUtil mHistoryDao;
    private boolean mIsCollection = false;
    private Activity mCtx = this;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_videodetail);
        ButterKnife.bind(this);
        Aria.download(this).register();
        Aria.get(this).getDownloadConfig().setConvertSpeed(true);
        Aria.get(this).getDownloadConfig().setMaxTaskNum(3);
        Aria.get(this).getDownloadConfig().setMaxSpeed(1024);
    }

    @Override
    protected void onStart() {
        super.onStart();
        initData();
        initView();
    }

    /**
     * 加载从
     */
    private void initData() {
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
        //获取页面传递数据
        Intent intent = getIntent();
        Bundle bundle = intent.getBundleExtra(Constant.VIDEO_BUNDLE);
        if (bundle != null) {
            mVideoData = (Data) bundle.get(Constant.VIDEO_BEAN);
            if (mVideoData == null) {
                LogUtil.e(TAG, "接收数据为null");
                return;
            }
            mCollectionDao = DbManager.getInstance(this).getCollectionInfoDao();
            mHistoryDao = DbManager.getInstance(this).getHistoryInfoDao();

            mIsCollection = mCollectionDao.queryIsExist(mVideoData.getId());
            if (mIsCollection) {
                ivCollection.setImageDrawable(getDrawable(R.drawable.ic_collection_fill));
            } else {
                ivCollection.setImageDrawable(getDrawable(R.drawable.ic_collection));
            }
            /*加入历史纪录*/
            mHistoryDao.add(mVideoData);
            LogUtil.i(TAG, mVideoData.toString());
        } else {
            LogUtil.e(TAG, "接收数据为null");
        }


    }

    private void initView() {
        IjkMediaPlayer.loadLibrariesOnce(null);
        IjkMediaPlayer.native_profileBegin("libijkplayer.so");

        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);

        initVideoBuilderMode();

        //video
        tvTitle.setText(mVideoData.getTitle());
        String tag = mVideoData.getAuthor().getName() + " / " + mVideoData.getCategory();
        tvTag.setText(tag);
        tvDesc.setText(mVideoData.getDescription());


        //Author
        GlideUtil.loadCircleImage(this, mVideoData.getAuthor().getIcon(), ivHeadImage);
        tvName.setText(mVideoData.getAuthor().getName());
        tvPersonDesc.setText(mVideoData.getAuthor().getDescription());

        //相关视频RecyclerView
        mPresenter = new VideoDetailPresenter(new VideoDetailModel(), this);
        mPresenter.initRecyclerView();

//        tvCollectionCount.setText(String.valueOf(mVideoData.getConsumption().getCollectionCount()));
//        tvReplyCount.setText(String.valueOf(mVideoData.getConsumption().getReplyCount()));
//        tvShareCount.setText(String.valueOf(mVideoData.getConsumption().getShareCount()));
        ivCollection.setOnClickListener(this);
        ivDownload.setOnClickListener(this);
        ivShare.setOnClickListener(this);

        ibBack.setOnClickListener(v -> onBackPressed());

        itemAuthor.setOnClickListener(v -> {
            Bundle bundle = new Bundle();
            bundle.putLong(Constant.USERINFO_BEAN_ID, mVideoData.getAuthor().getId());
         //   LogUtil.i(TAG, " mVideoData.getUserType()" + mVideoData.getUserType());
            bundle.putString(Constant.USERINFO_BEAN_TYPE, "PGC");
            Intent intent = new Intent(this, UserInfoActivity.class);
            intent.putExtra(Constant.USERINFO_BUNDLE, bundle);
            startActivity(intent);
        });
    }


    @Override
    public StandardGSYVideoPlayer getGSYVideoPlayer() {
        return videoPlayer;
    }

    @Override
    public GSYVideoOptionBuilder getGSYVideoOptionBuilder() {
        //内置封面可参考SampleCoverVideo
        ImageView imageView = new ImageView(this);
        GlideUtil.loadImage(this, mVideoData.getCover().getFeed(), imageView);
        //  loadCover(imageView,);
        return new GSYVideoOptionBuilder()
                .setThumbImageView(imageView)
                .setUrl(mVideoData.getPlayUrl())
                .setCacheWithPlay(true)
                .setVideoTitle(mVideoData.getVideoTitle())
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


    @Override
    public void showMessage(@NonNull String message) {

    }


    /**
     * 相关视频
     */
    public void initRelationVideoRV(LinearLayoutManager layoutManager, RelationVideoAdapter adapter) {
        rvRelationVideo.setLayoutManager(layoutManager);
        rvRelationVideo.setAdapter(adapter);
        //TODO 沟通Presenter联系Model获取数据
        mPresenter.obtainRelationVideo(mVideoData.getId())
                .observeOn(AndroidSchedulers.mainThread())
                .subscribe(new BaseObserver<RelationVideoBean>() {
                    @Override
                    protected void onSuccess(RelationVideoBean videoBean) {
                        List<ItemList> itemList = videoBean.getItemList();
                        adapter.setData(itemList);
                        adapter.notifyDataSetChanged();
                        Timber.i("videoRelation数据已返回");
                    }

                    @Override
                    public void onFail(Throwable e) {
                        Timber.e(e.toString());
                    }
                });
    }

    /**
     * 视频回复
     * <p>
     * 此版块先撤销，有需要再完善
     * <p>
     * 需要解决两个RecyclerView的问题
     */
    public void initVideoReplyRV(LinearLayoutManager layoutManager, VideoReplyAdapter adapter) {
//        rvVideoReply.setLayoutManager(layoutManager);
//        rvVideoReply.setAdapter(adapter);
//
//        mPresenter.obtainVideoReply(mVideoData.getId())
//                .observeOn(AndroidSchedulers.mainThread())
//                .subscribe(new BaseObserver<JsonRootBean>() {
//                    @Override
//                    protected void onSuccess(JsonRootBean replyBean) {
//                        adapter.setData(replyBean.getItemList());
//                        adapter.notifyDataSetChanged();
//                        Timber.i("videoReply数据已返回");
//                    }
//
//                    @Override
//                    public void onFail(Throwable e) {
//                        Timber.e(e.toString());
//                    }
//                });
    }


    @Override
    protected void onDestroy() {
        super.onDestroy();
        if (mPresenter != null) mPresenter.onDestroy();//释放资源
        this.mPresenter = null;
        Aria.download(this).unRegister();
    }

    @Override
    public void onClick(View v) {
        switch (v.getId()) {
            case R.id.iv_collection:
                checkAndCollection(mVideoData);
                break;
            case R.id.iv_download:
                mPresenter.onDownloadVideo(mVideoData);
                break;
            case R.id.iv_share:
                mPresenter.onShare(mVideoData.getWebUrl().getRaw());
                break;
            default:
        }
    }

    /**
     * @return true  表示未收藏，已经将其收藏
     * false 表示已收藏，已取消收藏，或者收藏失败
     */
    void checkAndCollection(Data videoData) {
        if (mIsCollection) {
            /*从数据库移除*/
            boolean isSuccess = mCollectionDao.cancelCollection(videoData);
            if (isSuccess) {
                mIsCollection = false;
                Toast.makeText(mCtx, "取消收藏", Toast.LENGTH_SHORT).show();
                ivCollection.setImageDrawable(getDrawable(R.drawable.ic_collection));
            }
        } else {
            /*添加到数据库*/
            boolean isSuccess = mCollectionDao.onCollection(videoData);
            if (isSuccess) {
                Toast.makeText(mCtx, "收藏成功", Toast.LENGTH_SHORT).show();
                mIsCollection = true;
                ivCollection.setImageDrawable(getDrawable(R.drawable.ic_collection_fill));
            }

        }
    }

}
