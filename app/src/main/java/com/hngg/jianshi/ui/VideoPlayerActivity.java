package com.hngg.jianshi.ui;

import android.app.Activity;
import android.content.Intent;
import android.content.res.Configuration;
import android.database.Cursor;
import android.net.Uri;
import android.os.Bundle;
import android.provider.DocumentsContract;
import android.provider.MediaStore;
import android.view.View;

import androidx.annotation.Nullable;

import com.hngg.jianshi.R;
import com.hngg.jianshi.base.BaseActivity;
import com.hngg.jianshi.data.database.DbManager;
import com.hngg.jianshi.data.database.bean.PlayInfo;
import com.hngg.jianshi.data.database.bean.VideoTaskInfo;
import com.hngg.jianshi.data.database.utils.PlayerInfoUtil;
import com.hngg.jianshi.data.database.utils.VideoTaskInfoUtil;
import com.hngg.jianshi.utils.Constant;
import com.hngg.jianshi.utils.LogUtil;
import com.hngg.jianshi.utils.StatusBarUtil;
import com.shuyu.gsyvideoplayer.GSYVideoManager;
import com.shuyu.gsyvideoplayer.builder.GSYVideoOptionBuilder;
import com.shuyu.gsyvideoplayer.listener.GSYSampleCallBack;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.utils.OrientationUtils;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoPlayer;

import butterknife.BindView;
import butterknife.ButterKnife;

public class VideoPlayerActivity extends BaseActivity {
    @BindView(R.id.viewPlayer)
    StandardGSYVideoPlayer videoPlayer;
    private PlayerInfoUtil mPlayerInfoDao;
    private long mSeekTime = 0;
    private long mVideoId = -1;
    private PlayInfo mPlayerInfo;
    private OrientationUtils mOrientationUtils;
    private boolean isPlay;
    private boolean isPause;
    private VideoPlayerActivity mCtx = this;
    private VideoTaskInfoUtil mVideoTaskDao;


    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        StatusBarUtil.setFontColor(getWindow(), getColor(R.color.color_statusBar_font));
        setContentView(R.layout.activity_videoplayer);
        ButterKnife.bind(this);
        mPlayerInfoDao = DbManager.getInstance(this).getPlayerInfoDao();
        mVideoTaskDao = DbManager.getInstance(this).getVideoTaskDao();
    }

    @Override
    protected void initData() {
        Intent intent = getIntent();
        if (intent == null) {
            LogUtil.e(TAG, "VideoPlayerActivity intent为null");
            return;
        }
        Bundle bundleExtra = intent.getBundleExtra(Constant.PLAYER_BUNDLE);
        if (bundleExtra == null) {
            LogUtil.e(TAG, "VideoPlayerActivity bundleExtra为null");
            return;
        }
        mVideoId = bundleExtra.getLong(Constant.PLAYER_VIDEO_ID);
        if (mVideoId == -1) {
            LogUtil.e(TAG, "VideoPlayerActivity获取数据为null");
            return;
        }

        mPlayerInfo = mPlayerInfoDao.queryByVideoId(mVideoId);
        if (mPlayerInfo != null) {
            /*从数据库获取数据，数据库无数据则返回*/
            mSeekTime = mPlayerInfo.getSeekTime();
        } else {
            /*查询数据库是否已存在该数据数据，无数据则进行保存*/
            mPlayerInfo = getPlayInfo(mVideoTaskDao.queryByVideoId(mVideoId));
        }


    }


    @Override
    protected void initView() {
        if (mPlayerInfo == null) {
            LogUtil.e(TAG, "mPlayerInfo获取数据库为null");
            return;
        }
        //外部辅助的旋转，帮助全屏
        mOrientationUtils = new OrientationUtils(this, videoPlayer);
        //初始化不打开外部的旋转
        mOrientationUtils.setEnable(false);


        GSYVideoOptionBuilder gsyVideoOption = new GSYVideoOptionBuilder();
        gsyVideoOption
                //  .setThumbImageView(imageView)
                .setIsTouchWiget(true)
                .setRotateViewAuto(false)
                .setLockLand(false)
                .setAutoFullWithSize(true)
                .setShowFullAnimation(false)
                .setNeedLockFull(true)
                .setSeekOnStart(mSeekTime)
                .setUrl(getUrl())
                .setCacheWithPlay(false)
                .setVideoTitle(mPlayerInfo.getVideoName())
                .setVideoAllCallBack(new GSYSampleCallBack() {
                    @Override
                    public void onPrepared(String url, Object... objects) {
                        super.onPrepared(url, objects);
                        //开始播放了才能旋转和全屏
                        mOrientationUtils.setEnable(true);
                        isPlay = true;
                    }

                    @Override
                    public void onQuitFullscreen(String url, Object... objects) {
                        super.onQuitFullscreen(url, objects);
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[0]);//title
                        Debuger.printfError("***** onQuitFullscreen **** " + objects[1]);//当前非全屏player
                        if (mOrientationUtils != null) {
                            mOrientationUtils.backToProtVideo();
                        }
                    }

                })
                .setLockClickListener((view, lock) -> {
                    if (mOrientationUtils != null) {
                        //配合下方的onConfigurationChanged
                        mOrientationUtils.setEnable(!lock);
                    }
                })
                .build(videoPlayer);


        videoPlayer.getFullscreenButton().setOnClickListener(v -> {
            //直接横屏
            mOrientationUtils.resolveByClick();

            videoPlayer.getBackButton().setOnClickListener(v1 -> onBackPressed());

            //第一个true是否需要隐藏actionbar，第二个true是否需要隐藏statusbar
            videoPlayer.startWindowFullscreen(mCtx, true, true);
        });
    }


    @Override
    public void onBackPressed() {
        if (videoPlayer != null) {
            // mSeekTime = videoPlayer.getCurrentPlayer().getPlayPosition();
            mSeekTime = GSYVideoManager.instance().getCurrentPosition();
            mPlayerInfoDao.updateSeekTime(mPlayerInfo, mSeekTime);
            LogUtil.i(TAG, "PLAYpOSITION" + mSeekTime);
        }
        if (mOrientationUtils != null) {
            mOrientationUtils.backToProtVideo();
        }
        if (GSYVideoManager.backFromWindowFull(this)) {
            return;
        }
        super.onBackPressed();
    }


    @Override
    protected void onPause() {
        videoPlayer.getCurrentPlayer().onVideoPause();
        super.onPause();
        isPause = true;
    }

    @Override
    protected void onResume() {
        videoPlayer.getCurrentPlayer().onVideoResume(false);
        super.onResume();
        isPause = false;
    }

    @Override
    protected void onDestroy() {

        if (isPlay) {
            videoPlayer.getCurrentPlayer().release();
        }
        if (mOrientationUtils != null) {
            mOrientationUtils.releaseListener();
        }
        super.onDestroy();
    }


    @Override
    public void onConfigurationChanged(Configuration newConfig) {
        super.onConfigurationChanged(newConfig);
        //如果旋转了就全屏
        if (isPlay && !isPause) {
            videoPlayer.onConfigurationChanged(this, newConfig, mOrientationUtils, true, true);
        }
    }


    private void resolveNormalVideoUI() {
        //增加title
        videoPlayer.getTitleTextView().setVisibility(View.GONE);
        videoPlayer.getBackButton().setVisibility(View.GONE);
    }

    private GSYVideoPlayer getCurPlay() {
        if (videoPlayer.getFullWindowPlayer() != null) {
            return videoPlayer.getFullWindowPlayer();
        }
        return videoPlayer;
    }


    private String getUrl() {
//        String url = "file://"+ Environment.getExternalStorageDirectory().getPath() + "Download/132451525666042.mp4";
        String url = "file:/" + mPlayerInfo.getFilePath() + "/" + mPlayerInfo.getVideoName() + ".mp4";
        LogUtil.i(TAG, "URL" + url);
        //String url = "android.resource://" + getPackageName() + "/" + R.raw.test;
        //注意，用ijk模式播放raw视频，这个必须打开
        // GSYVideoManager.instance().enableRawPlay(getApplicationContext());

        ///exo 播放 raw
        //String url = "rawresource://" + getPackageName() + "/" + R.raw.test;

        ///exo raw 支持
        //String url =  RawResourceDataSource.buildRawResourceUri(R.raw.test).toString();

        //断网自动重新链接，url前接上ijkhttphook:
        //String url = "ijkhttphook:https://res.exexm.com/cw_145225549855002";

        //String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        //String url = "http://9890.vod.myqcloud.com/9890_4e292f9a3dd011e6b4078980237cc3d3.f20.mp4";
        //String url = "http://7xjmzj.com1.z0.glb.clouddn.com/20171026175005_JObCxCE2.mp4";
        //String url = "http://hjq-1257036536.cos.ap-shanghai.myqcloud.com/m3u8/m1/out2.m3u8";
        //String url = "http://223.110.243.138/PLTV/2510088/224/3221227177/index.m3u8";
        //String url = "http://qiniu.carmmi.com/image/132451525666042.mp4";
        //String url = "http://ucp.wn.sunmath.cn/file-upload/gYQJHxK9iNQKJeWyS/V80418-103803.mp4?rc_uid=7sCFCGoaF2iTc9vH9&rc_token=prJK-xGutKmy2LDQO-OZASjob0o1u_s3e5SgMHmgjtn";
        //String url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
        //String url = "file://"+ Environment.getExternalStorageDirectory().getPath() + "Download/132451525666042.mp4";

        //String url =  "http://ipsimg-huabei2.speiyou.cn/010/video/other/20180427/40288b156241ec6301624243bdf7021e/40288b156290270d0162a3e7eb2e0726/1524814477/movie.mp4";
        //String url =  "http://ipsimg-huabei2.speiyou.cn/010/video/other/20180424/40288b156290270d0162a3db8cdd033e/40288b156290270d0162a3e8207f074f/e787a64c-f2d0-48fe-896d-246af05f111a.mp4";

        //String url =  "http://video.7k.cn/app_video/20171202/6c8cf3ea/v.m3u8.mp4";
        //String url =  "http://devimages.apple.com.edgekey.net/streaming/examples/bipbop_4x3/bipbop_4x3_variant.m3u8";
        //String url = "rtmp://ctc-zhenjiang04.rt1.gensee.com/5324e855b28b453db7b0ec226598b76c_171391_0_8801038305_1591077225_205d01b8/video";
        //String url = "http://video1.dgtle.com/backend%2F2020%2F3%2F0%2F%E6%88%91%E6%B2%A1%E6%9C%89%E7%BB%99%E4%B8%80%E5%8A%A08Pro%E5%81%9A%E8%AF%84%E6%B5%8B_%E5%8D%B4%E5%B8%A6%E7%9D%80%E5%AE%83%E6%BC%82%E6%B5%81.mp4_1080.mp4";
        //String url = "http://yongtaizx.xyz/20191230/t2Axgh3k/index.m3u8";
        //String url = "http://123.56.109.212:8035/users/bfe52074fba74247853caa764b522731/films/orig/aa4c3451-0468-452a-a189-bd064a1963e5-鹿鼎记下.mp4";
        //String url = "http://static.hnyequ.cn/yequ_iOS/4940735da1227890e6a261937223e0d2_828x1472.mp4";
        //String url = "http://39.104.119.42/elevator-1.0/api/downFile?path=demo.ogv";
        //String url = "http://pointshow.oss-cn-hangzhou.aliyuncs.com/transcode/ORIGINAL/Mnbc61586842828593.mp4";
        //ssl error
        //String url =  "http://qlqfj2ujf.hn-bkt.clouddn.com/aijianji-fuwupeixunshipin_index.m3u8";
        //String url =  "http://122.228.250.223/al.flv.huya.com/src/1394565191-1394565191-5989611887484993536-2789253838-10057-A-0-1-imgplus.flv?ali_dispatch_cold_stream=on&ali_redirect_ex_hot=0";
        //String url =  "http://1258557277.vod2.myqcloud.com/204551f3vodcq1258557277/8cc724f05285890813366287037/playlist_eof.m3u8";
        //String url =  "http://video.85tstss.com/record/live-nianhui-all_x264.mp4 ";
        //String url =  "https://ops-aiops.oss-cn-hongkong.aliyuncs.com/vod/6103_42349_nvrendesuipian2020H265_play.ts";
        //String url =  "https://us-4.wl-cdn.com/hls/20200225/fde4f8ef394731f38d68fe6d601cfd56/index.m3u8";
        //String url =  "https://cdn61.ytbbs.tv/cn/tv/55550/55550-1/play.m3u8?md5=v4sI4lWlo4XojzeAjgBGaQ&expires=1521204012&token=55550";
        //String url =  "http://1253492636.vod2.myqcloud.com/2e5fc148vodgzp1253492636/d08af82d4564972819086152830/plHZZoSkje0A.mp4";

        //String url = "rtsp://ajj:12345678@218.21.217.122:65523/h264/ch40/sub/av_stream";
        //String url = "rtsp://ajj:ajj12345678@218.21.217.122:65522/h264/ch15/sub/av_stream";
        //String url = "rtsp://wowzaec2demo.streamlock.net/vod/mp4:BigBuckBunny_115k.mov";
        //String url = "http://s.swao.cn/o_1c4gm8o1nniu1had13bk1t0l1rq64m.mov";
        //String url = "http://api.ciguang.tv/avideo/play?num=02-041-0491&type=flv&v=1&client=android";
        //String url = "http://video.7k.cn/app_video/20171213/276d8195/v.m3u8.mp4";
        //String url = "http://103.233.191.21/riak/riak-bucket/6469ac502e813a4c1df7c99f364e70c1.mp4";
        //String url = "http://7xjmzj.com1.z0.glb.cl ouddn.com/20171026175005_JObCxCE2.mp4";
        //String url = "https://media6.smartstudy.com/ae/07/3997/2/dest.m3u8";
        //String url = "http://cdn.tiaobatiaoba.com/Upload/square/2017-11-02/1509585140_1279.mp4";

        //String url = "http://hcjs2ra2rytd8v8np1q.exp.bcevod.com/mda-hegtjx8n5e8jt9zv/mda-hegtjx8n5e8jt9zv.m3u8";
        //String url = "http://7xse1z.com1.z0.glb.clouddn.com/1491813192";
        //String url = "http://ocgk7i2aj.bkt.clouddn.com/17651ac2-693c-47e9-b2d2-b731571bad37";
        //String url = "http://111.198.24.133:83/yyy_login_server/pic/YB059284/97778276040859/1.mp4";
        //String url = "http://vr.tudou.com/v2proxy/v?sid=95001&id=496378919&st=3&pw=";
        //String url = "http://pl-ali.youku.com/playlist/m3u8?type=mp4&ts=1490185963&keyframe=0&vid=XMjYxOTQ1Mzg2MA==&ep=ciadGkiFU8cF4SvajD8bYyuwJiYHXJZ3rHbN%2FrYDAcZuH%2BrC6DPcqJ21TPs%3D&sid=04901859548541247bba8&token=0524&ctype=12&ev=1&oip=976319194";
        //String url = "http://hls.ciguang.tv/hdtv/video.m3u8";
        //String url = "https://res.exexm.com/cw_145225549855002";
        //String url = "http://storage.gzstv.net/uploads/media/huangmeiyan/jr05-09.mp4";//mepg
        //String url = "https://zh-files.oss-cn-qingdao.aliyuncs.com/20170808223928mJ1P3n57.mp4";//90度
        return url;
    }

    private static final int READ_REQUEST_CODE = 42;

    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if (data == null || resultCode != Activity.RESULT_OK) return;
        if (requestCode == READ_REQUEST_CODE) {
            getPathForSearch(data.getData());
        }
    }


    private void getPathForSearch(Uri uri) {
        String[] selectionArgs = new String[]{DocumentsContract.getDocumentId(uri).split(":")[1]};
        Cursor cursor = getContentResolver().query(MediaStore.Video.Media.EXTERNAL_CONTENT_URI,
                null, MediaStore.Images.Media._ID + "=?",
                selectionArgs, null);
        if (null != cursor) {
            if (cursor.moveToFirst()) {
                int index = cursor.getColumnIndex(MediaStore.Video.Media.DATA);
                if (index > -1) {
                    videoPlayer.setUp(uri.toString(), false, "File");
                    videoPlayer.startPlayLogic();
                }
            }
            cursor.close();
        }
    }

    private PlayInfo getPlayInfo(VideoTaskInfo taskInfo) {
        PlayInfo playInfo = new PlayInfo();
        playInfo.setSeekTime(0L);
        playInfo.setAuthorDesc(taskInfo.getAuthorDesc());
        playInfo.setAuthorIcon(taskInfo.getAuthorIcon());
        playInfo.setAuthorId(taskInfo.getAuthorId());
        playInfo.setCategory(taskInfo.getCategory());
        playInfo.setAuthorName(taskInfo.getAuthorName());
        playInfo.setDescription(taskInfo.getDescription());
        playInfo.setDuration(taskInfo.getDuration());
        playInfo.setFilePath(taskInfo.getFilePath());
        playInfo.setPlayUrl(taskInfo.getUrl());
        playInfo.setPoster(taskInfo.getPoster());
        playInfo.setVideoId(taskInfo.getVideoId());
        playInfo.setVideoName(taskInfo.getVideoName());
        playInfo.setVideoSize(taskInfo.getFileSize());
        return playInfo;
    }

    protected void fileSearch() {
        Intent intent = new Intent(Intent.ACTION_OPEN_DOCUMENT);
        intent.addCategory(Intent.CATEGORY_OPENABLE);
        intent.setType("video/*");
        startActivityForResult(intent, READ_REQUEST_CODE);
    }
}
