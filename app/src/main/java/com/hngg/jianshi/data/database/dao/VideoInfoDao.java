package com.hngg.jianshi.data.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hngg.jianshi.data.database.bean.VideoInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_INFO".
*/
public class VideoInfoDao extends AbstractDao<VideoInfo, Long> {

    public static final String TABLENAME = "VIDEO_INFO";

    /**
     * Properties of entity VideoInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property VideoId = new Property(1, Long.class, "videoId", false, "VIDEO_ID");
        public final static Property Title = new Property(2, String.class, "title", false, "TITLE");
        public final static Property Category = new Property(3, String.class, "category", false, "CATEGORY");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property Duration = new Property(5, int.class, "duration", false, "DURATION");
        public final static Property Collected = new Property(6, boolean.class, "collected", false, "COLLECTED");
        public final static Property PlayUrl = new Property(7, String.class, "playUrl", false, "PLAY_URL");
        public final static Property FileSize = new Property(8, Long.class, "fileSize", false, "FILE_SIZE");
        public final static Property AuthorId = new Property(9, Long.class, "authorId", false, "AUTHOR_ID");
        public final static Property AuthorName = new Property(10, String.class, "authorName", false, "AUTHOR_NAME");
        public final static Property AuthorIcon = new Property(11, String.class, "authorIcon", false, "AUTHOR_ICON");
        public final static Property AuthorDesc = new Property(12, String.class, "authorDesc", false, "AUTHOR_DESC");
    }

    private DaoSession daoSession;


    public VideoInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VideoInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
        this.daoSession = daoSession;
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"VIDEO_ID\" INTEGER," + // 1: videoId
                "\"TITLE\" TEXT," + // 2: title
                "\"CATEGORY\" TEXT," + // 3: category
                "\"DESCRIPTION\" TEXT," + // 4: description
                "\"DURATION\" INTEGER NOT NULL ," + // 5: duration
                "\"COLLECTED\" INTEGER NOT NULL ," + // 6: collected
                "\"PLAY_URL\" TEXT NOT NULL ," + // 7: playUrl
                "\"FILE_SIZE\" INTEGER," + // 8: fileSize
                "\"AUTHOR_ID\" INTEGER," + // 9: authorId
                "\"AUTHOR_NAME\" TEXT," + // 10: authorName
                "\"AUTHOR_ICON\" TEXT," + // 11: authorIcon
                "\"AUTHOR_DESC\" TEXT);"); // 12: authorDesc
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(2, videoId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getDuration());
        stmt.bindLong(7, entity.getCollected() ? 1L: 0L);
        stmt.bindString(8, entity.getPlayUrl());
 
        Long fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(9, fileSize);
        }
 
        Long authorId = entity.getAuthorId();
        if (authorId != null) {
            stmt.bindLong(10, authorId);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(11, authorName);
        }
 
        String authorIcon = entity.getAuthorIcon();
        if (authorIcon != null) {
            stmt.bindString(12, authorIcon);
        }
 
        String authorDesc = entity.getAuthorDesc();
        if (authorDesc != null) {
            stmt.bindString(13, authorDesc);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(2, videoId);
        }
 
        String title = entity.getTitle();
        if (title != null) {
            stmt.bindString(3, title);
        }
 
        String category = entity.getCategory();
        if (category != null) {
            stmt.bindString(4, category);
        }
 
        String description = entity.getDescription();
        if (description != null) {
            stmt.bindString(5, description);
        }
        stmt.bindLong(6, entity.getDuration());
        stmt.bindLong(7, entity.getCollected() ? 1L: 0L);
        stmt.bindString(8, entity.getPlayUrl());
 
        Long fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(9, fileSize);
        }
 
        Long authorId = entity.getAuthorId();
        if (authorId != null) {
            stmt.bindLong(10, authorId);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(11, authorName);
        }
 
        String authorIcon = entity.getAuthorIcon();
        if (authorIcon != null) {
            stmt.bindString(12, authorIcon);
        }
 
        String authorDesc = entity.getAuthorDesc();
        if (authorDesc != null) {
            stmt.bindString(13, authorDesc);
        }
    }

    @Override
    protected final void attachEntity(VideoInfo entity) {
        super.attachEntity(entity);
        entity.__setDaoSession(daoSession);
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoInfo readEntity(Cursor cursor, int offset) {
        VideoInfo entity = new VideoInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // videoId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // title
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // category
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.getInt(offset + 5), // duration
            cursor.getShort(offset + 6) != 0, // collected
            cursor.getString(offset + 7), // playUrl
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // fileSize
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // authorId
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // authorName
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // authorIcon
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12) // authorDesc
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setVideoId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setTitle(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCategory(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDuration(cursor.getInt(offset + 5));
        entity.setCollected(cursor.getShort(offset + 6) != 0);
        entity.setPlayUrl(cursor.getString(offset + 7));
        entity.setFileSize(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setAuthorId(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setAuthorName(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setAuthorIcon(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAuthorDesc(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}