package com.hngg.jianshi.data.database.bean;

import com.hngg.jianshi.data.databse.dao.DaoSession;
import com.hngg.jianshi.data.databse.dao.VideoInfoDao;
import com.hngg.jianshi.data.databse.dao.VideoResolutionInfoDao;

import org.greenrobot.greendao.DaoException;
import org.greenrobot.greendao.annotation.Entity;
import org.greenrobot.greendao.annotation.Generated;
import org.greenrobot.greendao.annotation.Id;
import org.greenrobot.greendao.annotation.NotNull;
import org.greenrobot.greendao.annotation.ToMany;

import java.util.List;
/**
 * @Description: java类作用描述
 * @Author: nedhuo
 * @Data:
 *
 * 针对一些下载  收藏  播放 视频的具体信息存储
 */
@Entity
public class VideoInfo {

    @Id(autoincrement = true)
    private Long id;
    private Long videoId;
    private String title;
    private String category;
    private String description;
    private int duration;   //时长
    private boolean collected;
    @NotNull
    private String playUrl;
    private Long fileSize;
    /*TODO 版本二实现
     * private List<> playInfo;  视频多精度
     * private String webUrl;
     */

    private Long authorId;
    private String authorName;
    private String authorIcon;
    private String authorDesc;

    /*多分辨率选择*/
    @ToMany(referencedJoinProperty = "videoInfoId")
    private List<VideoResolutionInfo> playInfos;
    /** Used to resolve relations */
    @Generated(hash = 2040040024)
    private transient DaoSession daoSession;
    /** Used for active entity operations. */
    @Generated(hash = 785593785)
    private transient VideoInfoDao myDao;

    @Generated(hash = 2065294394)
    public VideoInfo(Long id, Long videoId, String title, String category,
            String description, int duration, boolean collected,
            @NotNull String playUrl, Long fileSize, Long authorId,
            String authorName, String authorIcon, String authorDesc) {
        this.id = id;
        this.videoId = videoId;
        this.title = title;
        this.category = category;
        this.description = description;
        this.duration = duration;
        this.collected = collected;
        this.playUrl = playUrl;
        this.fileSize = fileSize;
        this.authorId = authorId;
        this.authorName = authorName;
        this.authorIcon = authorIcon;
        this.authorDesc = authorDesc;
    }

    @Generated(hash = 296402066)
    public VideoInfo() {
    }

    public Long getId() {
        return this.id;
    }

    public void setId(Long id) {
        this.id = id;
    }

    public Long getVideoId() {
        return this.videoId;
    }

    public void setVideoId(Long videoId) {
        this.videoId = videoId;
    }

    public String getTitle() {
        return this.title;
    }

    public void setTitle(String title) {
        this.title = title;
    }

    public String getCategory() {
        return this.category;
    }

    public void setCategory(String category) {
        this.category = category;
    }

    public String getDescription() {
        return this.description;
    }

    public void setDescription(String description) {
        this.description = description;
    }

    public int getDuration() {
        return this.duration;
    }

    public void setDuration(int duration) {
        this.duration = duration;
    }

    public boolean getCollected() {
        return this.collected;
    }

    public void setCollected(boolean collected) {
        this.collected = collected;
    }

    public String getPlayUrl() {
        return this.playUrl;
    }

    public void setPlayUrl(String playUrl) {
        this.playUrl = playUrl;
    }

    public Long getFileSize() {
        return this.fileSize;
    }

    public void setFileSize(Long fileSize) {
        this.fileSize = fileSize;
    }

    public Long getAuthorId() {
        return this.authorId;
    }

    public void setAuthorId(Long authorId) {
        this.authorId = authorId;
    }

    public String getAuthorName() {
        return this.authorName;
    }

    public void setAuthorName(String authorName) {
        this.authorName = authorName;
    }

    public String getAuthorIcon() {
        return this.authorIcon;
    }

    public void setAuthorIcon(String authorIcon) {
        this.authorIcon = authorIcon;
    }

    public String getAuthorDesc() {
        return this.authorDesc;
    }

    public void setAuthorDesc(String authorDesc) {
        this.authorDesc = authorDesc;
    }

    /**
     * To-many relationship, resolved on first access (and after reset).
     * Changes to to-many relations are not persisted, make changes to the target entity.
     */
    @Generated(hash = 446621971)
    public List<VideoResolutionInfo> getPlayInfos() {
        if (playInfos == null) {
            final DaoSession daoSession = this.daoSession;
            if (daoSession == null) {
                throw new DaoException("Entity is detached from DAO context");
            }
            VideoResolutionInfoDao targetDao = daoSession
                    .getVideoResolutionInfoDao();
            List<VideoResolutionInfo> playInfosNew = targetDao
                    ._queryVideoInfo_PlayInfos(id);
            synchronized (this) {
                if (playInfos == null) {
                    playInfos = playInfosNew;
                }
            }
        }
        return playInfos;
    }

    /** Resets a to-many relationship, making the next get call to query for a fresh result. */
    @Generated(hash = 981745761)
    public synchronized void resetPlayInfos() {
        playInfos = null;
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#delete(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 128553479)
    public void delete() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.delete(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#refresh(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 1942392019)
    public void refresh() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.refresh(this);
    }

    /**
     * Convenient call for {@link org.greenrobot.greendao.AbstractDao#update(Object)}.
     * Entity must attached to an entity context.
     */
    @Generated(hash = 713229351)
    public void update() {
        if (myDao == null) {
            throw new DaoException("Entity is detached from DAO context");
        }
        myDao.update(this);
    }

    /** called by internal mechanisms, do not call yourself. */
    @Generated(hash = 1598960089)
    public void __setDaoSession(DaoSession daoSession) {
        this.daoSession = daoSession;
        myDao = daoSession != null ? daoSession.getVideoInfoDao() : null;
    }
   
}
