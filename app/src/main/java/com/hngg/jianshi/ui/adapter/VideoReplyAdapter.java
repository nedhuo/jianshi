package com.hngg.jianshi.ui.adapter;

import android.app.Activity;
import android.util.Log;
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
import com.hngg.jianshi.data.bean.reply.User;
import com.hngg.jianshi.ui.viewholder.ReplyHeaderViewHolder;
import com.hngg.jianshi.ui.viewholder.VideoReplyViewHolder;
import com.hngg.jianshi.utils.CommonUtil;
import com.hngg.jianshi.utils.GlideUtil;

import java.util.ArrayList;
import java.util.List;

import timber.log.Timber;

/**
 * Date: 2021/2/17
 * Timer: 16:26
 * Author: nedhuo
 * Description:
 */
public class VideoReplyAdapter extends RecyclerView.Adapter<RecyclerView.ViewHolder> {

    private static final String TAG = "VideoReplyAdapter";
    private final Activity mCtx;

    private List<ItemList> mItemList;

    public VideoReplyAdapter(Activity ctx) {
        mItemList = new ArrayList<>();
        mCtx = ctx;
    }

    public void setData(List<ItemList> itemList) {
        mItemList = itemList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == DataType.LEFT_ALIGN_TEXT_HEADER_ID) {
            View view = LayoutInflater.from(mCtx)
                    .inflate(R.layout.item_reply_header, parent, false);
            return new ReplyHeaderViewHolder(view) {
            };
        } else if (viewType == DataType.REPLY_ID) {
            View view = LayoutInflater.from(mCtx)
                    .inflate(R.layout.item_video_reply, parent, false);
            return new VideoReplyViewHolder(view);
        }
        return null;
    }

    /**
     * 数据类型1：
     * "dataType":"LeftAlignTextHeader",
     * "text":"热门评论",
     * "font":"normal",
     * "actionUrl":"eyepetizer://replies/hot?videoId=186856&amp;type=video",
     * "adTrack":null
     * 数据类型2：
     * "dataType":"ReplyBeanForClient",
     * "id":1230296904789655552,
     * "videoId":186856,
     * "videoTitle":"枪响之后没有赢家，带你身临其境体验战争",
     * "parentReplyId":0,
     * "rootReplyId":1230296904789655552,
     * "sequence":1,
     * "message":"董卿在主持人大赛说过这句话：枪响之后没有赢家",
     * "replyStatus":"PUBLISHED",
     * "createTime":1582160615000,
     * "user":{
     * "uid":303533138,
     * "nickname":"pursuitx",
     * "avatar":"http://img.kaiyanapp.com/01ab88a31f6cc865173965e2fffc4cf0.jpeg?imageMogr2/quality/60/format/jpg",
     * "userType":"NORMAL",
     * "ifPgc":false,
     * "description":"Marvels’s girl.
     * 人民有信仰，国家有力量.
     * （ding～🌼✨这里是漫威女孩的个人分享主页，我会给大家推荐美剧，app，书籍，电影，壁纸等等.）",
     * "area":null,
     * "gender":"female",
     * "registDate":1579167182000,
     * "releaseDate":1595312755000,
     * "cover":"http://img.kaiyanapp.com/88b410c35a9cedaa946bcb29ae151d0c.jpeg?imageMogr2/quality/60/format/jpg",
     * "actionUrl":"eyepetizer://pgc/detail/303533138/?title=pursuitx&amp;userType=NORMAL&amp;tabIndex=0",
     * "followed":false,
     * "limitVideoOpen":false,
     * "library":"BLOCK",
     * "birthday":1057075200000,
     * "country":"",
     * "city":"",
     * "university":"",
     * "job":"",
     * "expert":false
     * },
     * "likeCount":144,
     * "liked":false,
     * "hot":false,
     * "userType":null,
     * "type":"video",
     * "actionUrl":null,
     * "imageUrl":"",
     * "ugcVideoId":null,
     * "parentReply":null,
     * "showParentReply":true,
     * "showConversationButton":false,
     * "ugcVideoUrl":null,
     * "cover":null,
     * "userBlocked":false,
     * "sid":"1230296904789655552"
     */
    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        ItemList itemList = mItemList.get(position);
        Data data = itemList.getData();
        if (holder instanceof VideoReplyViewHolder) {
            VideoReplyViewHolder viewHolder = (VideoReplyViewHolder) holder;
            User user = data.getUser();

            GlideUtil.loadCircleImage(mCtx,user.getAvatar(),viewHolder.ivHeadImage);
            viewHolder.tvName.setText(user.getNickname());
            viewHolder.tvReply.setText(data.getMessage());
            viewHolder.tvPublishTime.setText(CommonUtil.longToDate(data.getCreateTime()));
        } else if (holder instanceof ReplyHeaderViewHolder) {
            ((ReplyHeaderViewHolder) holder).tvTitle.setText(data.getText());
        }
    }

    @Override
    public int getItemCount() {
        Timber.i("videoReply.size" + mItemList.size());
        return mItemList.size();
    }

    @Override
    public int getItemViewType(int position) {
        ItemList itemList = mItemList.get(position);

        switch (itemList.getType()) {
            case DataType.LEFT_ALIGN_TEXT_HEADER:
                return DataType.LEFT_ALIGN_TEXT_HEADER_ID;
            case DataType.REPLY:
                return DataType.REPLY_ID;
            default:
                break;
        }
        return super.getItemViewType(position);
    }

    class DataType {
        final static String LEFT_ALIGN_TEXT_HEADER = "leftAlignTextHeader";
        final static String REPLY = "reply";


        final static int LEFT_ALIGN_TEXT_HEADER_ID = 1;
        final static int REPLY_ID = 2;
    }
}
