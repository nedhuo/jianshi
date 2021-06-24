package com.hngg.jianshi.data.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hngg.jianshi.data.database.bean.PlayInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "PLAY_INFO".
*/
public class PlayInfoDao extends AbstractDao<PlayInfo, Long> {

    public static final String TABLENAME = "PLAY_INFO";

    /**
     * Properties of entity PlayInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property VideoId = new Property(1, Long.class, "videoId", false, "VIDEO_ID");
        public final static Property VideoName = new Property(2, String.class, "videoName", false, "VIDEO_NAME");
        public final static Property Category = new Property(3, String.class, "category", false, "CATEGORY");
        public final static Property Description = new Property(4, String.class, "description", false, "DESCRIPTION");
        public final static Property Duration = new Property(5, int.class, "duration", false, "DURATION");
        public final static Property Poster = new Property(6, String.class, "poster", false, "POSTER");
        public final static Property PlayUrl = new Property(7, String.class, "playUrl", false, "PLAY_URL");
        public final static Property VideoSize = new Property(8, Long.class, "videoSize", false, "VIDEO_SIZE");
        public final static Property FilePath = new Property(9, String.class, "filePath", false, "FILE_PATH");
        public final static Property AuthorId = new Property(10, Long.class, "authorId", false, "AUTHOR_ID");
        public final static Property AuthorName = new Property(11, String.class, "authorName", false, "AUTHOR_NAME");
        public final static Property AuthorIcon = new Property(12, String.class, "authorIcon", false, "AUTHOR_ICON");
        public final static Property AuthorDesc = new Property(13, String.class, "authorDesc", false, "AUTHOR_DESC");
        public final static Property SeekTime = new Property(14, Long.class, "seekTime", false, "SEEK_TIME");
    }


    public PlayInfoDao(DaoConfig config) {
        super(config);
    }
    
    public PlayInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"PLAY_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"VIDEO_ID\" INTEGER," + // 1: videoId
                "\"VIDEO_NAME\" TEXT," + // 2: videoName
                "\"CATEGORY\" TEXT," + // 3: category
                "\"DESCRIPTION\" TEXT," + // 4: description
                "\"DURATION\" INTEGER NOT NULL ," + // 5: duration
                "\"POSTER\" TEXT," + // 6: poster
                "\"PLAY_URL\" TEXT," + // 7: playUrl
                "\"VIDEO_SIZE\" INTEGER," + // 8: videoSize
                "\"FILE_PATH\" TEXT," + // 9: filePath
                "\"AUTHOR_ID\" INTEGER," + // 10: authorId
                "\"AUTHOR_NAME\" TEXT," + // 11: authorName
                "\"AUTHOR_ICON\" TEXT," + // 12: authorIcon
                "\"AUTHOR_DESC\" TEXT," + // 13: authorDesc
                "\"SEEK_TIME\" INTEGER);"); // 14: seekTime
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"PLAY_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, PlayInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(2, videoId);
        }
 
        String videoName = entity.getVideoName();
        if (videoName != null) {
            stmt.bindString(3, videoName);
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
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(7, poster);
        }
 
        String playUrl = entity.getPlayUrl();
        if (playUrl != null) {
            stmt.bindString(8, playUrl);
        }
 
        Long videoSize = entity.getVideoSize();
        if (videoSize != null) {
            stmt.bindLong(9, videoSize);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(10, filePath);
        }
 
        Long authorId = entity.getAuthorId();
        if (authorId != null) {
            stmt.bindLong(11, authorId);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(12, authorName);
        }
 
        String authorIcon = entity.getAuthorIcon();
        if (authorIcon != null) {
            stmt.bindString(13, authorIcon);
        }
 
        String authorDesc = entity.getAuthorDesc();
        if (authorDesc != null) {
            stmt.bindString(14, authorDesc);
        }
 
        Long seekTime = entity.getSeekTime();
        if (seekTime != null) {
            stmt.bindLong(15, seekTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, PlayInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(2, videoId);
        }
 
        String videoName = entity.getVideoName();
        if (videoName != null) {
            stmt.bindString(3, videoName);
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
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(7, poster);
        }
 
        String playUrl = entity.getPlayUrl();
        if (playUrl != null) {
            stmt.bindString(8, playUrl);
        }
 
        Long videoSize = entity.getVideoSize();
        if (videoSize != null) {
            stmt.bindLong(9, videoSize);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(10, filePath);
        }
 
        Long authorId = entity.getAuthorId();
        if (authorId != null) {
            stmt.bindLong(11, authorId);
        }
 
        String authorName = entity.getAuthorName();
        if (authorName != null) {
            stmt.bindString(12, authorName);
        }
 
        String authorIcon = entity.getAuthorIcon();
        if (authorIcon != null) {
            stmt.bindString(13, authorIcon);
        }
 
        String authorDesc = entity.getAuthorDesc();
        if (authorDesc != null) {
            stmt.bindString(14, authorDesc);
        }
 
        Long seekTime = entity.getSeekTime();
        if (seekTime != null) {
            stmt.bindLong(15, seekTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public PlayInfo readEntity(Cursor cursor, int offset) {
        PlayInfo entity = new PlayInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // videoId
            cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2), // videoName
            cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3), // category
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // description
            cursor.getInt(offset + 5), // duration
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // poster
            cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7), // playUrl
            cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8), // videoSize
            cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9), // filePath
            cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10), // authorId
            cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11), // authorName
            cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12), // authorIcon
            cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13), // authorDesc
            cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14) // seekTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, PlayInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setVideoId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setVideoName(cursor.isNull(offset + 2) ? null : cursor.getString(offset + 2));
        entity.setCategory(cursor.isNull(offset + 3) ? null : cursor.getString(offset + 3));
        entity.setDescription(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setDuration(cursor.getInt(offset + 5));
        entity.setPoster(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setPlayUrl(cursor.isNull(offset + 7) ? null : cursor.getString(offset + 7));
        entity.setVideoSize(cursor.isNull(offset + 8) ? null : cursor.getLong(offset + 8));
        entity.setFilePath(cursor.isNull(offset + 9) ? null : cursor.getString(offset + 9));
        entity.setAuthorId(cursor.isNull(offset + 10) ? null : cursor.getLong(offset + 10));
        entity.setAuthorName(cursor.isNull(offset + 11) ? null : cursor.getString(offset + 11));
        entity.setAuthorIcon(cursor.isNull(offset + 12) ? null : cursor.getString(offset + 12));
        entity.setAuthorDesc(cursor.isNull(offset + 13) ? null : cursor.getString(offset + 13));
        entity.setSeekTime(cursor.isNull(offset + 14) ? null : cursor.getLong(offset + 14));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(PlayInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(PlayInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(PlayInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}
