package com.hngg.jianshi.data.database.dao;

import android.database.Cursor;
import android.database.sqlite.SQLiteStatement;

import org.greenrobot.greendao.AbstractDao;
import org.greenrobot.greendao.Property;
import org.greenrobot.greendao.internal.DaoConfig;
import org.greenrobot.greendao.database.Database;
import org.greenrobot.greendao.database.DatabaseStatement;

import com.hngg.jianshi.data.database.bean.VideoTaskInfo;

// THIS CODE IS GENERATED BY greenDAO, DO NOT EDIT.
/** 
 * DAO for table "VIDEO_TASK_INFO".
*/
public class VideoTaskInfoDao extends AbstractDao<VideoTaskInfo, Long> {

    public static final String TABLENAME = "VIDEO_TASK_INFO";

    /**
     * Properties of entity VideoTaskInfo.<br/>
     * Can be used for QueryBuilder and for referencing column names.
     */
    public static class Properties {
        public final static Property Id = new Property(0, Long.class, "id", true, "_id");
        public final static Property TaskId = new Property(1, Long.class, "taskId", false, "TASK_ID");
        public final static Property DownId = new Property(2, int.class, "downId", false, "DOWN_ID");
        public final static Property VideoId = new Property(3, Long.class, "videoId", false, "VIDEO_ID");
        public final static Property VideoName = new Property(4, String.class, "videoName", false, "VIDEO_NAME");
        public final static Property Poster = new Property(5, String.class, "poster", false, "POSTER");
        public final static Property FilePath = new Property(6, String.class, "filePath", false, "FILE_PATH");
        public final static Property FileSize = new Property(7, Long.class, "fileSize", false, "FILE_SIZE");
        public final static Property Percent = new Property(8, int.class, "percent", false, "PERCENT");
        public final static Property DownloadSize = new Property(9, Long.class, "downloadSize", false, "DOWNLOAD_SIZE");
        public final static Property Url = new Property(10, String.class, "url", false, "URL");
        public final static Property TaskState = new Property(11, int.class, "taskState", false, "TASK_STATE");
        public final static Property CreateTime = new Property(12, Long.class, "createTime", false, "CREATE_TIME");
    }


    public VideoTaskInfoDao(DaoConfig config) {
        super(config);
    }
    
    public VideoTaskInfoDao(DaoConfig config, DaoSession daoSession) {
        super(config, daoSession);
    }

    /** Creates the underlying database table. */
    public static void createTable(Database db, boolean ifNotExists) {
        String constraint = ifNotExists? "IF NOT EXISTS ": "";
        db.execSQL("CREATE TABLE " + constraint + "\"VIDEO_TASK_INFO\" (" + //
                "\"_id\" INTEGER PRIMARY KEY AUTOINCREMENT ," + // 0: id
                "\"TASK_ID\" INTEGER," + // 1: taskId
                "\"DOWN_ID\" INTEGER NOT NULL ," + // 2: downId
                "\"VIDEO_ID\" INTEGER," + // 3: videoId
                "\"VIDEO_NAME\" TEXT," + // 4: videoName
                "\"POSTER\" TEXT," + // 5: poster
                "\"FILE_PATH\" TEXT," + // 6: filePath
                "\"FILE_SIZE\" INTEGER," + // 7: fileSize
                "\"PERCENT\" INTEGER NOT NULL ," + // 8: percent
                "\"DOWNLOAD_SIZE\" INTEGER," + // 9: downloadSize
                "\"URL\" TEXT," + // 10: url
                "\"TASK_STATE\" INTEGER NOT NULL ," + // 11: taskState
                "\"CREATE_TIME\" INTEGER);"); // 12: createTime
        // Add Indexes
        db.execSQL("CREATE UNIQUE INDEX " + constraint + "IDX_VIDEO_TASK_INFO_VIDEO_ID ON \"VIDEO_TASK_INFO\"" +
                " (\"VIDEO_ID\" ASC);");
    }

    /** Drops the underlying database table. */
    public static void dropTable(Database db, boolean ifExists) {
        String sql = "DROP TABLE " + (ifExists ? "IF EXISTS " : "") + "\"VIDEO_TASK_INFO\"";
        db.execSQL(sql);
    }

    @Override
    protected final void bindValues(DatabaseStatement stmt, VideoTaskInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindLong(2, taskId);
        }
        stmt.bindLong(3, entity.getDownId());
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(4, videoId);
        }
 
        String videoName = entity.getVideoName();
        if (videoName != null) {
            stmt.bindString(5, videoName);
        }
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(6, poster);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(7, filePath);
        }
 
        Long fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(8, fileSize);
        }
        stmt.bindLong(9, entity.getPercent());
 
        Long downloadSize = entity.getDownloadSize();
        if (downloadSize != null) {
            stmt.bindLong(10, downloadSize);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(11, url);
        }
        stmt.bindLong(12, entity.getTaskState());
 
        Long createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(13, createTime);
        }
    }

    @Override
    protected final void bindValues(SQLiteStatement stmt, VideoTaskInfo entity) {
        stmt.clearBindings();
 
        Long id = entity.getId();
        if (id != null) {
            stmt.bindLong(1, id);
        }
 
        Long taskId = entity.getTaskId();
        if (taskId != null) {
            stmt.bindLong(2, taskId);
        }
        stmt.bindLong(3, entity.getDownId());
 
        Long videoId = entity.getVideoId();
        if (videoId != null) {
            stmt.bindLong(4, videoId);
        }
 
        String videoName = entity.getVideoName();
        if (videoName != null) {
            stmt.bindString(5, videoName);
        }
 
        String poster = entity.getPoster();
        if (poster != null) {
            stmt.bindString(6, poster);
        }
 
        String filePath = entity.getFilePath();
        if (filePath != null) {
            stmt.bindString(7, filePath);
        }
 
        Long fileSize = entity.getFileSize();
        if (fileSize != null) {
            stmt.bindLong(8, fileSize);
        }
        stmt.bindLong(9, entity.getPercent());
 
        Long downloadSize = entity.getDownloadSize();
        if (downloadSize != null) {
            stmt.bindLong(10, downloadSize);
        }
 
        String url = entity.getUrl();
        if (url != null) {
            stmt.bindString(11, url);
        }
        stmt.bindLong(12, entity.getTaskState());
 
        Long createTime = entity.getCreateTime();
        if (createTime != null) {
            stmt.bindLong(13, createTime);
        }
    }

    @Override
    public Long readKey(Cursor cursor, int offset) {
        return cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0);
    }    

    @Override
    public VideoTaskInfo readEntity(Cursor cursor, int offset) {
        VideoTaskInfo entity = new VideoTaskInfo( //
            cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0), // id
            cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1), // taskId
            cursor.getInt(offset + 2), // downId
            cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3), // videoId
            cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4), // videoName
            cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5), // poster
            cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6), // filePath
            cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7), // fileSize
            cursor.getInt(offset + 8), // percent
            cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9), // downloadSize
            cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10), // url
            cursor.getInt(offset + 11), // taskState
            cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12) // createTime
        );
        return entity;
    }
     
    @Override
    public void readEntity(Cursor cursor, VideoTaskInfo entity, int offset) {
        entity.setId(cursor.isNull(offset + 0) ? null : cursor.getLong(offset + 0));
        entity.setTaskId(cursor.isNull(offset + 1) ? null : cursor.getLong(offset + 1));
        entity.setDownId(cursor.getInt(offset + 2));
        entity.setVideoId(cursor.isNull(offset + 3) ? null : cursor.getLong(offset + 3));
        entity.setVideoName(cursor.isNull(offset + 4) ? null : cursor.getString(offset + 4));
        entity.setPoster(cursor.isNull(offset + 5) ? null : cursor.getString(offset + 5));
        entity.setFilePath(cursor.isNull(offset + 6) ? null : cursor.getString(offset + 6));
        entity.setFileSize(cursor.isNull(offset + 7) ? null : cursor.getLong(offset + 7));
        entity.setPercent(cursor.getInt(offset + 8));
        entity.setDownloadSize(cursor.isNull(offset + 9) ? null : cursor.getLong(offset + 9));
        entity.setUrl(cursor.isNull(offset + 10) ? null : cursor.getString(offset + 10));
        entity.setTaskState(cursor.getInt(offset + 11));
        entity.setCreateTime(cursor.isNull(offset + 12) ? null : cursor.getLong(offset + 12));
     }
    
    @Override
    protected final Long updateKeyAfterInsert(VideoTaskInfo entity, long rowId) {
        entity.setId(rowId);
        return rowId;
    }
    
    @Override
    public Long getKey(VideoTaskInfo entity) {
        if(entity != null) {
            return entity.getId();
        } else {
            return null;
        }
    }

    @Override
    public boolean hasKey(VideoTaskInfo entity) {
        return entity.getId() != null;
    }

    @Override
    protected final boolean isEntityUpdateable() {
        return true;
    }
    
}