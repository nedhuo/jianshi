package com.hngg.jianshi.widget;

import android.content.Context;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewParent;
import android.widget.ImageView;
import android.widget.LinearLayout;

import com.hngg.jianshi.R;
import com.hngg.jianshi.utils.LogUtil;
import com.shuyu.gsyvideoplayer.player.IjkPlayerManager;
import com.shuyu.gsyvideoplayer.player.PlayerFactory;
import com.shuyu.gsyvideoplayer.utils.Debuger;
import com.shuyu.gsyvideoplayer.video.StandardGSYVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYBaseVideoPlayer;
import com.shuyu.gsyvideoplayer.video.base.GSYVideoViewBridge;

import moe.codeest.enviews.ENDownloadView;
import moe.codeest.enviews.ENPlayView;

public class CustomVideoPlayer extends StandardGSYVideoPlayer {

    // private Context context;
    private ImageView ivShare;
    private ImageView mIvForward;//快进
    private ImageView mIvRewind;//快退
    private static final String TAG = "CustomVideoPlayer";
    private ENPlayView mIvStart;
    private LinearLayout mRlPlayButton;
    private boolean isSeek = false; //seekTo操作标志位
   // private boolean isLoadUi = false; //处理ExoPlayer闪烁问题


    public CustomVideoPlayer(Context context, Boolean fullFlag) {
        super(context, fullFlag);
    }

    public CustomVideoPlayer(Context context) {
        super(context);
    }

    public CustomVideoPlayer(Context context, AttributeSet attrs) {
        super(context, attrs);
    }


    @Override
    protected void init(final Context context) {
       // PlayerFactory.setPlayManager(Exo2PlayerManager.class);
        PlayerFactory.setPlayManager(IjkPlayerManager.class);
        super.init(context);
        //
        ivShare = findViewById(R.id.iv_share);
        mIvRewind = findViewById(R.id.player_rewind);
        mIvForward = findViewById(R.id.player_fastForward);
        mIvStart = findViewById(R.id.player_startBottom);
        mRlPlayButton = findViewById(R.id.rl_playButton);

        initView();
    }

    @Override
    public int getLayoutId() {
        return R.layout.detail_video_player;
    }


    /**
     * 重载该方法实现大小屏同步
     */
    @Override
    protected void cloneParams(GSYBaseVideoPlayer from, GSYBaseVideoPlayer to) {
        super.cloneParams(from, to);
        LogUtil.i(TAG, "cloneParams");
        CustomVideoPlayer sf = (CustomVideoPlayer) from;
        CustomVideoPlayer st = (CustomVideoPlayer) to;
        st.mShowFullAnimation = sf.mShowFullAnimation;
    }


    /**
     * TODO 自定义控件的显示与隐藏
     * 点击触摸显示和隐藏逻辑
     */
    @Override
    protected void onClickUiToggle(MotionEvent e) {
        LogUtil.i(TAG, "onClickUiToggle");
//        if (isSeek) {
//            changeSeekBehaviorShow();
//            return;
//        }

        /*是否全屏 是否锁定 是否需要锁定*/
        //mIfCurrentIsFullscreen &&mLockCurScreen && mNeedLockFull
        if (mLockCurScreen && mNeedLockFull) {
            setViewShowState(mLockScreen, VISIBLE);
            return;
        }
        if (mCurrentState == CURRENT_STATE_PREPAREING) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPrepareingClear();
                } else {
                    changeUiToPreparingShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PLAYING) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPlayingClear();
                } else {
                    changeUiToPlayingShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PAUSE) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPauseClear();
                } else {
                    changeUiToPauseShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_AUTO_COMPLETE) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToCompleteClear();
                } else {
                    changeUiToCompleteShow();
                }
            }
        } else if (mCurrentState == CURRENT_STATE_PLAYING_BUFFERING_START) {
            if (mBottomContainer != null) {
                if (mBottomContainer.getVisibility() == View.VISIBLE) {
                    changeUiToPlayingBufferingClear();
                } else {
                    changeUiToPlayingBufferingShow();
                }
            }
        }
    }



    /**
     * 处理锁屏屏幕触摸逻辑
     * 大概三秒钟回调一次
     */
    @Override
    protected void hideAllWidget() {
        LogUtil.i(TAG, "hideAllWidget");
        super.hideAllWidget();
        //自定义
        setViewShowState(mRlPlayButton, INVISIBLE);
    }

    @Override
    protected void changeUiToNormal() {
        LogUtil.i(TAG, "changeUiToNormal");
        super.changeUiToNormal();

        //自定义layout显示 随着mStartButton设置
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
        //  setViewShowState(mLockScreen, mNeedLockFull ? VISIBLE : GONE);
    }

    /**
     * 为seekTo方法写的控件改变
     */
    private void changeSeekBehaviorShow() {
        LogUtil.i(TAG, "changeSeekBehaviorShow");
        isSeek = false;
        if (mLoadingProgressBar instanceof ENDownloadView) {
            //((ENDownloadView) mLoadingProgressBar).reset();
            ((ENDownloadView) mLoadingProgressBar).reset();
        }
    }

    @Override
    protected void changeUiToPreparingShow() {
        LogUtil.i(TAG, "changeUiToPreparingShow");
        super.changeUiToPreparingShow();
        setViewShowState(mRlPlayButton, INVISIBLE);
        setViewShowState(mLockScreen, GONE);
    }

    @Override
    protected void changeUiToPlayingShow() {
        if (isSeek) {
            changeSeekBehaviorShow();
            return;
        }
//        if (!isLoadUi) {
//            //解决ExoPlayer闪烁问题
//            isLoadUi = true;
//            return;
//        }

        LogUtil.i(TAG, "changeUiToPlayingShow");
        super.changeUiToPlayingShow();
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
    }

    @Override
    protected void changeUiToPauseShow() {
        if (isSeek) {
            changeSeekBehaviorShow();
            return;
        }
        LogUtil.i(TAG, "changeUiToPauseShow");
        super.changeUiToPauseShow();
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
        //  setViewShowState(mLockScreen, mNeedLockFull ? VISIBLE : GONE);
    }

    @Override
    protected void changeUiToPlayingBufferingShow() {
        if (isSeek) {
            changeSeekBehaviorShow();
            return;
        }

        LogUtil.i(TAG, "changeUiToPlayingBufferingShow");
        super.changeUiToPlayingBufferingShow();
        // setViewShowState(mStartButton, INVISIBLE);
        setViewShowState(mRlPlayButton, INVISIBLE);
        setViewShowState(mLockScreen, GONE);

    }

    @Override
    protected void changeUiToCompleteShow() {
        LogUtil.i(TAG, "changeUiToCompleteShow");
        super.changeUiToCompleteShow();
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
        // setViewShowState(mLockScreen, mNeedLockFull ? VISIBLE : GONE);
    }


    @Override
    protected void changeUiToError() {
        LogUtil.i(TAG, "changeUiToError");
        super.changeUiToError();
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
        //setViewShowState(mLockScreen, mNeedLockFull ? VISIBLE : GONE);
    }


    protected void changeUiToPrepareingClear() {
        super.changeUiToPrepareingClear();
        setViewShowState(mRlPlayButton, INVISIBLE);
        setViewShowState(mLockScreen, GONE);
    }


    protected void changeUiToPlayingBufferingClear() {
        LogUtil.i(TAG, "changeUiToPlayingBufferingClear");
        super.changeUiToPlayingBufferingClear();
        setViewShowState(mRlPlayButton, INVISIBLE);
        setViewShowState(mLockScreen, GONE);
    }

    protected void changeUiToClear() {
        LogUtil.i(TAG, "changeUiToClear");
        super.changeUiToClear();
        setViewShowState(mRlPlayButton, INVISIBLE);
        setViewShowState(mLockScreen, GONE);
    }

    protected void changeUiToCompleteClear() {
        LogUtil.i(TAG, "changeUiToCompleteClear");
        super.changeUiToCompleteClear();
        setViewShowState(mRlPlayButton, VISIBLE);
        setViewShowState(mLockScreen, VISIBLE);
        // setViewShowState(mLockScreen, mNeedLockFull ? VISIBLE : GONE);
    }

    @Override
    protected void updateStartImage() {
        isSeek = false;
        LogUtil.i(TAG, "updateStartImage");
        super.updateStartImage();
        //自定义按钮事件
        ENPlayView enPlayView = mIvStart;
        enPlayView.setDuration(500);
        if (mCurrentState == CURRENT_STATE_PLAYING) {
            enPlayView.play();
        } else if (mCurrentState == CURRENT_STATE_ERROR) {
            enPlayView.pause();
        } else {
            enPlayView.pause();
        }
    }

    @Override
    protected boolean isShowNetConfirm() {
        return super.isShowNetConfirm();
    }


    public ImageView getIvShare() {
        return ivShare;
    }

    private void initView() {
        GSYVideoViewBridge videoManager = getGSYVideoManager();
        mIvForward.setOnClickListener(v ->
                seekTo(videoManager.getCurrentPosition() + 5000));

        mIvRewind.setOnClickListener(v -> {
            if (videoManager.getCurrentPosition() <= 5000) {
                seekTo(1);
            } else {
                seekTo(videoManager.getCurrentPosition() - 5000);
            }
        });

        mIvStart.setOnClickListener(v -> clickStartIcon());
    }

    /**
     * 重写了seekTo方法，添加标志位，解决控件闪烁
     */
    @Override
    public void seekTo(long time) {
        isSeek = true;
        super.seekTo(time);
    }

    /**
     * 重写父类两方法，设置旋转图标
     */
    @Override
    public int getShrinkImageRes() {
        LogUtil.i(TAG, "getShrinkImageRes");
        if (mShrinkImageRes == -1) {
            return R.drawable.ic_player_enlarge;
        }
        return mShrinkImageRes;
    }

    @Override
    public int getEnlargeImageRes() {
        if (mShrinkImageRes == -1) {
            LogUtil.i(TAG, "GET ENLARGE IMAGE");
            return R.drawable.ic_player_enlarge;
        }
        return mShrinkImageRes;
    }

    /**
     * 重写了父类方法的锁屏逻辑
     * 解决了解锁后控件不立即显示问题
     */
    @Override
    protected void lockTouchLogic() {
        if (mLockCurScreen) {
            mLockScreen.setImageResource(R.drawable.ic_player_unlock_white);
            mLockCurScreen = false;
            changeUiToPauseShow();
            if (mOrientationUtils != null)
                mOrientationUtils.setEnable(false);
        } else {
            //TODO 图片未出，待替换
            mLockScreen.setImageResource(R.drawable.ic_player_lock_white);
            mLockCurScreen = true;
            hideAllWidget();
            if (mOrientationUtils != null)
                mOrientationUtils.setEnable(isRotateViewAuto());
        }
    }

    /**
     * 亮度、进度、音频
     * 重写GSYVideoControlView中onTouch方法
     */
    @Override
    public boolean onTouch(View v, MotionEvent event) {
        LogUtil.i(TAG, "onTouch");
        int id = v.getId();
        float x = event.getX();
        float y = event.getY();
        //if (mIfCurrentIsFullscreen && mLockCurScreen && mNeedLockFull) {
        //解决竖屏状态下锁屏状态无效问题
        if (mLockCurScreen && mNeedLockFull) {
            onClickUiToggle(event);
            startDismissControlViewTimer();
            return true;
        }

        if (id == R.id.fullscreen) {
            return false;
        }

        if (id == R.id.surface_container) {
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    touchSurfaceDown(x, y);
                    break;
                case MotionEvent.ACTION_MOVE:
                    float deltaX = x - mDownX;
                    float deltaY = y - mDownY;
                    float absDeltaX = Math.abs(deltaX);
                    float absDeltaY = Math.abs(deltaY);

                    if ((mIfCurrentIsFullscreen && mIsTouchWigetFull)
                            || (mIsTouchWiget && !mIfCurrentIsFullscreen)) {
                        if (!mChangePosition && !mChangeVolume && !mBrightness) {
                            touchSurfaceMoveFullLogic(absDeltaX, absDeltaY);
                        }
                    }
                    touchSurfaceMove(deltaX, deltaY, y);

                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    touchSurfaceUp();
                    Debuger.printfLog(hashCode() +
                            "------------------------------ surface_container ACTION_UP");
                    startProgressTimer();

                    //不要和隐藏虚拟按键后，滑出虚拟按键冲突
                    if (mHideKey && mShowVKey) {
                        return true;
                    }
                    break;
            }
            gestureDetector.onTouchEvent(event);
        } else if (id == R.id.progress) {
            //自定义 解决点击seekBar底部闪烁问题
            isSeek = true;
            switch (event.getAction()) {
                case MotionEvent.ACTION_DOWN:
                    cancelDismissControlViewTimer();
                case MotionEvent.ACTION_MOVE:
                    cancelProgressTimer();
                    ViewParent vpdown = getParent();
                    while (vpdown != null) {
                        vpdown.requestDisallowInterceptTouchEvent(true);
                        vpdown = vpdown.getParent();
                    }
                    break;
                case MotionEvent.ACTION_UP:
                    startDismissControlViewTimer();
                    Debuger.printfLog(hashCode() + "------------------------------ progress ACTION_UP");
                    startProgressTimer();
                    ViewParent vpup = getParent();
                    while (vpup != null) {
                        vpup.requestDisallowInterceptTouchEvent(false);
                        vpup = vpup.getParent();
                    }
                    mBrightnessData = -1f;
                    break;
            }
        }
        return false;
    }

}