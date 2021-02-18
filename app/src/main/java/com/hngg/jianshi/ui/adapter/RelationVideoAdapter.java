package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.bumptech.glide.Glide;
import com.hngg.jianshi.R;
import com.hngg.jianshi.data.bean.home.Data;
import com.hngg.jianshi.data.bean.home.ItemList;
import com.hngg.jianshi.ui.video.VideoDetailActivity;
import com.hngg.jianshi.ui.viewholder.TextHeaderViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoSmallCardViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.Constant;

import java.util.ArrayList;
import java.util.List;

/**
 * Date: 2021/2/17
 * Timer: 11:19
 * Author: nedhuo
 * Description:
 */
public class RelationVideoAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private final String TAG = "RelationVideoAdapter";
    private final Activity mCtx;
    private List<ItemList> mItemList;

    public RelationVideoAdapter(Activity ctx) {
        mItemList=new ArrayList<>();
        mCtx = ctx;
    }

    public void setData(List<ItemList> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.TEXT_CARD_ID) {
            View view = LayoutInflater.from(mCtx)
                    .inflate(R.layout.item_header, parent, false);
            return new TextHeaderViewHolder(view);
        } else if (viewType == DataType.VIDEO_SMALL_CARD_ID) {
            View view = LayoutInflater.from(mCtx)
                    .inflate(R.layout.item_video_smallcard, parent, false);
            return new VideoSmallCardViewHolder(view);
        }
        //
        TextView textView = new TextView(mCtx);
        return new RecyclerView.ViewHolder(textView) {
        };
    }

    /**
     * TextCard
     * "dataType":"TextCard",
     * "id":0,
     * "type":"header4",
     * "text":"动画类最新视频",
     * "subTitle":null,
     * "actionUrl":"eyepetizer://common/?title=%E5%8A%A8%E7%94%BB%E7%B1%BB%E6%9C%80%E6%96%B0%E8%A7%86%E9%A2%91&amp;url=http%3A%2F%2Fbaobab.kaiyanapp.com%2Fapi%2Fv4%2Fvideo%2Fcategory%3Fid%3D10",
     * "adTrack":null,
     * "follow":null
     * <p>
     * videoSmallCard
     * <p>
     * "dataType":"VideoBeanForClient",
     * "id":224491,
     * "title":"创意剪辑，动画世界的摇滚 live",
     * "description":"短片「Stephen Ong 」通过剪辑将一系列动画进行组接，配备架子鼓的强律动古典，上演了一场摇滚 live。From Make it Move",
     * "library":"DAILY",
     * "tags":[
     * {
     * "id":14,
     * "name":"动画梦工厂",
     * "actionUrl":"eyepetizer://tag/14/?title=%E5%8A%A8%E7%94%BB%E6%A2%A6%E5%B7%A5%E5%8E%82",
     * "adTrack":null,
     * "desc":"有趣的人永远不缺童心",
     * "bgPicture":"http://img.kaiyanapp.com/afb9e7d7f061d10ade5ebcb524dc8679.jpeg?imageMogr2/quality/60/format/jpg",
     * "headerImage":"http://img.kaiyanapp.com/f9eae3e0321fa1e99a7b38641b5536a2.jpeg?imageMogr2/quality/60/format/jpg",
     * "tagRecType":"IMPORTANT",
     * "childTagList":null,
     * "childTagIdList":null,
     * "haveReward":false,
     * "ifNewest":false,
     * "newestEndTime":null,
     * "communityIndex":0
     * },
     * {
     * "id":1023,
     * "name":"动画",
     * "actionUrl":"eyepetizer://tag/1023/?title=%E5%8A%A8%E7%94%BB",
     * "adTrack":null,
     * "desc":"有趣的人永远不缺童心",
     * "bgPicture":"http://img.kaiyanapp.com/349cbd33cdf71fc74d5e9c7a00b444fd.jpeg?imageMogr2/quality/60/format/jpg",
     * "headerImage":"http://img.kaiyanapp.com/208aa67386c045497389f015ae28dd29.jpeg?imageMogr2/quality/60/format/jpg",
     * "tagRecType":"NORMAL",
     * "childTagList":null,
     * "childTagIdList":null,
     * "haveReward":false,
     * "ifNewest":false,
     * "newestEndTime":null,
     * "communityIndex":0
     * }
     * ],
     * "consumption":{
     * "collectionCount":70,
     * "shareCount":55,
     * "replyCount":0,
     * "realCollectionCount":80
     * },
     * "resourceType":"video",
     * "slogan":null,
     * "provider":{
     * "name":"Vimeo",
     * "alias":"vimeo",
     * "icon":"http://img.kaiyanapp.com/c3ad630be461cbb081649c9e21d6cbe3.png"
     * },
     * "category":"动画",
     * "author":{
     * "id":2170,
     * "icon":"http://img.kaiyanapp.com/482c741c06644f5566c7218096dbaf26.jpeg",
     * "name":"开眼动画精选",
     * "description":"有趣的人永远不缺童心",
     * "link":"",
     * "latestReleaseTime":1613437207000,
     * "videoNum":1028,
     * "adTrack":null,
     * "follow":{
     * "itemType":"author",
     * "itemId":2170,
     * "followed":false
     * },
     * "shield":{
     * "itemType":"author",
     * "itemId":2170,
     * "shielded":false
     * },
     * "approvedNotReadyVideoCount":0,
     * "ifPgc":true,
     * "recSort":0,
     * "expert":false
     * },
     * "cover":{
     * "feed":"http://img.kaiyanapp.com/bcff247771a14baf19620a300a469c5f.png?imageMogr2/quality/60/format/jpg",
     * "detail":"http://img.kaiyanapp.com/bcff247771a14baf19620a300a469c5f.png?imageMogr2/quality/60/format/jpg",
     * "blurred":"http://img.kaiyanapp.com/4dfb3f2f2bb33405b058a0745a7e1dee.png?imageMogr2/quality/60/format/jpg",
     * "sharing":null,
     * "homepage":"http://img.kaiyanapp.com/bcff247771a14baf19620a300a469c5f.png?imageView2/1/w/720/h/560/format/jpg/q/75|watermark/1/image/aHR0cDovL2ltZy5rYWl5YW5hcHAuY29tL2JsYWNrXzMwLnBuZw==/dissolve/100/gravity/Center/dx/0/dy/0|imageslim"
     * },
     * "playUrl":"http://baobab.kaiyanapp.com/api/v1/playUrl?vid=224491&amp;resourceType=video&amp;editionType=default&amp;source=aliyun&amp;playUrlType=url_oss&amp;udid=",
     * "thumbPlayUrl":null,
     * "duration":62,
     * "webUrl":{
     * "raw":"http://www.eyepetizer.net/detail.html?vid=224491",
     * "forWeibo":"http://www.eyepetizer.net/detail.html?vid=224491&amp;resourceType=video&amp;utm_campaign=routine&amp;utm_medium=share&amp;utm_source=weibo&amp;uid=0"
     * },
     * "releaseTime":1613145664000,
     * "playInfo":[
     * <p>
     * ],
     * "campaign":null,
     * "waterMarks":null,
     * "ad":false,
     * "adTrack":[
     * <p>
     * ],
     * "type":"NORMAL",
     * "titlePgc":null,
     * "descriptionPgc":null,
     * "remark":null,
     * "ifLimitVideo":false,
     * "searchWeight":0,
     * "brandWebsiteInfo":null,
     * "videoPosterBean":null,
     * "idx":0,
     * "shareAdTrack":null,
     * "favoriteAdTrack":null,
     * "webAdTrack":null,
     * "date":1613145664000,
     * "promotion":null,
     * "label":null,
     * "labelList":[
     * <p>
     * ],
     * "descriptionEditor":"短片「Stephen Ong 」通过剪辑将一系列动画进行组接，配备架子鼓的强律动古典，上演了一场摇滚 live。From Make it Move",
     * "collected":false,
     * "reallyCollected":false,
     * "played":false,
     * "subtitles":[
     * <p>
     * ],
     * "lastViewTime":null,
     * "playlists":null,
     * "src":null,
     * "recallSource":null,
     * "recall_source":null
     *
     * TODO 此处的smallcard是可以无限制多开的，没打开一个就会new VideoDetailActivity，需要进行处理
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Data data = mItemList.get(position).getData();
        if (holder instanceof TextHeaderViewHolder) {
            ((TextHeaderViewHolder) holder).mTvHeaderTime.setText(data.getText());
        } else if (holder instanceof VideoSmallCardViewHolder) {
            VideoSmallCardViewHolder videoHolder = (VideoSmallCardViewHolder) holder;
            Glide.with(mCtx)
                    .load(data.getCover().getFeed())
                    .centerCrop()
                    .into(videoHolder.iv_videoImage);
            videoHolder.tv_videoTitle.setText(data.getTitle());
            videoHolder.tv_videoCategory.setText("#" + data.getCategory());
            videoHolder.tv_VideoDuration.setText(CommonUtil.intToTime(data.getDuration()));

            videoHolder.ll_smallCard.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent = new Intent(mCtx, VideoDetailActivity.class);
                    Bundle bundle = new Bundle();
                    bundle.putSerializable(Constant.VIDEO_BEAN, data);
                    intent.putExtra(Constant.VIDEO_BUNDLE, bundle);
                    mCtx.startActivity(intent);
                }
            });
        } else {
        }
    }

    @Override
    public int getItemCount() {
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = mItemList.get(position);

        switch (itemList.getType()) {
            case DataType.TEXT_CARD:
                return DataType.TEXT_CARD_ID;
            case DataType.VIDEO_SMALL_CARD:
                return DataType.VIDEO_SMALL_CARD_ID;
            default:
                break;
        }
        return super.getItemViewType(position);
    }

    class DataType {
        final static String TEXT_CARD = "textCard";
        final static String VIDEO_SMALL_CARD = "videoSmallCard";


        final static int TEXT_CARD_ID = 1;
        final static int VIDEO_SMALL_CARD_ID = 2;
    }
}
