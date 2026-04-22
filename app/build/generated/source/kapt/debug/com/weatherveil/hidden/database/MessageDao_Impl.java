package com.weatherveil.hidden.database;

import android.database.Cursor;
import android.os.CancellationSignal;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.lifecycle.LiveData;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityDeletionOrUpdateAdapter;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Long;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class MessageDao_Impl implements MessageDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<Message> __insertionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __deletionAdapterOfMessage;

  private final EntityDeletionOrUpdateAdapter<Message> __updateAdapterOfMessage;

  private final SharedSQLiteStatement __preparedStmtOfDeleteById;

  private final SharedSQLiteStatement __preparedStmtOfDeleteExpiredMessages;

  private final SharedSQLiteStatement __preparedStmtOfSetSaved;

  private final SharedSQLiteStatement __preparedStmtOfMarkAllRead;

  public MessageDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfMessage = new EntityInsertionAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `messages` (`id`,`senderId`,`text`,`mediaPath`,`remoteUrl`,`messageType`,`timestamp`,`isSaved`,`isRead`,`firebaseId`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Message entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSenderId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSenderId());
        }
        if (entity.getText() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getText());
        }
        if (entity.getMediaPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMediaPath());
        }
        if (entity.getRemoteUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRemoteUrl());
        }
        if (entity.getMessageType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMessageType());
        }
        statement.bindLong(7, entity.getTimestamp());
        final int _tmp = entity.isSaved() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isRead() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        if (entity.getFirebaseId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getFirebaseId());
        }
      }
    };
    this.__deletionAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "DELETE FROM `messages` WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Message entity) {
        statement.bindLong(1, entity.getId());
      }
    };
    this.__updateAdapterOfMessage = new EntityDeletionOrUpdateAdapter<Message>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "UPDATE OR ABORT `messages` SET `id` = ?,`senderId` = ?,`text` = ?,`mediaPath` = ?,`remoteUrl` = ?,`messageType` = ?,`timestamp` = ?,`isSaved` = ?,`isRead` = ?,`firebaseId` = ? WHERE `id` = ?";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final Message entity) {
        statement.bindLong(1, entity.getId());
        if (entity.getSenderId() == null) {
          statement.bindNull(2);
        } else {
          statement.bindString(2, entity.getSenderId());
        }
        if (entity.getText() == null) {
          statement.bindNull(3);
        } else {
          statement.bindString(3, entity.getText());
        }
        if (entity.getMediaPath() == null) {
          statement.bindNull(4);
        } else {
          statement.bindString(4, entity.getMediaPath());
        }
        if (entity.getRemoteUrl() == null) {
          statement.bindNull(5);
        } else {
          statement.bindString(5, entity.getRemoteUrl());
        }
        if (entity.getMessageType() == null) {
          statement.bindNull(6);
        } else {
          statement.bindString(6, entity.getMessageType());
        }
        statement.bindLong(7, entity.getTimestamp());
        final int _tmp = entity.isSaved() ? 1 : 0;
        statement.bindLong(8, _tmp);
        final int _tmp_1 = entity.isRead() ? 1 : 0;
        statement.bindLong(9, _tmp_1);
        if (entity.getFirebaseId() == null) {
          statement.bindNull(10);
        } else {
          statement.bindString(10, entity.getFirebaseId());
        }
        statement.bindLong(11, entity.getId());
      }
    };
    this.__preparedStmtOfDeleteById = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM messages WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfDeleteExpiredMessages = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "        DELETE FROM messages \n"
                + "        WHERE isSaved = 0 \n"
                + "        AND timestamp < ?\n"
                + "    ";
        return _query;
      }
    };
    this.__preparedStmtOfSetSaved = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET isSaved = ? WHERE id = ?";
        return _query;
      }
    };
    this.__preparedStmtOfMarkAllRead = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE messages SET isRead = 1 WHERE senderId != ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final Message message, final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfMessage.insertAndReturnId(message);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object delete(final Message message, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __deletionAdapterOfMessage.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object update(final Message message, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __updateAdapterOfMessage.handle(message);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteById(final long id, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteById.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteById.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteExpiredMessages(final long cutoffTimestamp,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteExpiredMessages.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffTimestamp);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfDeleteExpiredMessages.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object setSaved(final long id, final boolean saved,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfSetSaved.acquire();
        int _argIndex = 1;
        final int _tmp = saved ? 1 : 0;
        _stmt.bindLong(_argIndex, _tmp);
        _argIndex = 2;
        _stmt.bindLong(_argIndex, id);
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfSetSaved.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object markAllRead(final String currentUser,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkAllRead.acquire();
        int _argIndex = 1;
        if (currentUser == null) {
          _stmt.bindNull(_argIndex);
        } else {
          _stmt.bindString(_argIndex, currentUser);
        }
        try {
          __db.beginTransaction();
          try {
            _stmt.executeUpdateDelete();
            __db.setTransactionSuccessful();
            return Unit.INSTANCE;
          } finally {
            __db.endTransaction();
          }
        } finally {
          __preparedStmtOfMarkAllRead.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public LiveData<List<Message>> getAllMessages() {
    final String _sql = "SELECT * FROM messages ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return __db.getInvalidationTracker().createLiveData(new String[] {"messages"}, false, new Callable<List<Message>>() {
      @Override
      @Nullable
      public List<Message> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSenderId = CursorUtil.getColumnIndexOrThrow(_cursor, "senderId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfMediaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaPath");
          final int _cursorIndexOfRemoteUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "remoteUrl");
          final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsSaved = CursorUtil.getColumnIndexOrThrow(_cursor, "isSaved");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfFirebaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "firebaseId");
          final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Message _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSenderId;
            if (_cursor.isNull(_cursorIndexOfSenderId)) {
              _tmpSenderId = null;
            } else {
              _tmpSenderId = _cursor.getString(_cursorIndexOfSenderId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpMediaPath;
            if (_cursor.isNull(_cursorIndexOfMediaPath)) {
              _tmpMediaPath = null;
            } else {
              _tmpMediaPath = _cursor.getString(_cursorIndexOfMediaPath);
            }
            final String _tmpRemoteUrl;
            if (_cursor.isNull(_cursorIndexOfRemoteUrl)) {
              _tmpRemoteUrl = null;
            } else {
              _tmpRemoteUrl = _cursor.getString(_cursorIndexOfRemoteUrl);
            }
            final String _tmpMessageType;
            if (_cursor.isNull(_cursorIndexOfMessageType)) {
              _tmpMessageType = null;
            } else {
              _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsSaved;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSaved);
            _tmpIsSaved = _tmp != 0;
            final boolean _tmpIsRead;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_1 != 0;
            final String _tmpFirebaseId;
            if (_cursor.isNull(_cursorIndexOfFirebaseId)) {
              _tmpFirebaseId = null;
            } else {
              _tmpFirebaseId = _cursor.getString(_cursorIndexOfFirebaseId);
            }
            _item = new Message(_tmpId,_tmpSenderId,_tmpText,_tmpMediaPath,_tmpRemoteUrl,_tmpMessageType,_tmpTimestamp,_tmpIsSaved,_tmpIsRead,_tmpFirebaseId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
        }
      }

      @Override
      protected void finalize() {
        _statement.release();
      }
    });
  }

  @Override
  public Object getAllMessagesOnce(final Continuation<? super List<Message>> $completion) {
    final String _sql = "SELECT * FROM messages ORDER BY timestamp ASC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<List<Message>>() {
      @Override
      @NonNull
      public List<Message> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfSenderId = CursorUtil.getColumnIndexOrThrow(_cursor, "senderId");
          final int _cursorIndexOfText = CursorUtil.getColumnIndexOrThrow(_cursor, "text");
          final int _cursorIndexOfMediaPath = CursorUtil.getColumnIndexOrThrow(_cursor, "mediaPath");
          final int _cursorIndexOfRemoteUrl = CursorUtil.getColumnIndexOrThrow(_cursor, "remoteUrl");
          final int _cursorIndexOfMessageType = CursorUtil.getColumnIndexOrThrow(_cursor, "messageType");
          final int _cursorIndexOfTimestamp = CursorUtil.getColumnIndexOrThrow(_cursor, "timestamp");
          final int _cursorIndexOfIsSaved = CursorUtil.getColumnIndexOrThrow(_cursor, "isSaved");
          final int _cursorIndexOfIsRead = CursorUtil.getColumnIndexOrThrow(_cursor, "isRead");
          final int _cursorIndexOfFirebaseId = CursorUtil.getColumnIndexOrThrow(_cursor, "firebaseId");
          final List<Message> _result = new ArrayList<Message>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final Message _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final String _tmpSenderId;
            if (_cursor.isNull(_cursorIndexOfSenderId)) {
              _tmpSenderId = null;
            } else {
              _tmpSenderId = _cursor.getString(_cursorIndexOfSenderId);
            }
            final String _tmpText;
            if (_cursor.isNull(_cursorIndexOfText)) {
              _tmpText = null;
            } else {
              _tmpText = _cursor.getString(_cursorIndexOfText);
            }
            final String _tmpMediaPath;
            if (_cursor.isNull(_cursorIndexOfMediaPath)) {
              _tmpMediaPath = null;
            } else {
              _tmpMediaPath = _cursor.getString(_cursorIndexOfMediaPath);
            }
            final String _tmpRemoteUrl;
            if (_cursor.isNull(_cursorIndexOfRemoteUrl)) {
              _tmpRemoteUrl = null;
            } else {
              _tmpRemoteUrl = _cursor.getString(_cursorIndexOfRemoteUrl);
            }
            final String _tmpMessageType;
            if (_cursor.isNull(_cursorIndexOfMessageType)) {
              _tmpMessageType = null;
            } else {
              _tmpMessageType = _cursor.getString(_cursorIndexOfMessageType);
            }
            final long _tmpTimestamp;
            _tmpTimestamp = _cursor.getLong(_cursorIndexOfTimestamp);
            final boolean _tmpIsSaved;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSaved);
            _tmpIsSaved = _tmp != 0;
            final boolean _tmpIsRead;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsRead);
            _tmpIsRead = _tmp_1 != 0;
            final String _tmpFirebaseId;
            if (_cursor.isNull(_cursorIndexOfFirebaseId)) {
              _tmpFirebaseId = null;
            } else {
              _tmpFirebaseId = _cursor.getString(_cursorIndexOfFirebaseId);
            }
            _item = new Message(_tmpId,_tmpSenderId,_tmpText,_tmpMediaPath,_tmpRemoteUrl,_tmpMessageType,_tmpTimestamp,_tmpIsSaved,_tmpIsRead,_tmpFirebaseId);
            _result.add(_item);
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @Override
  public Object countUnread(final String currentUser,
      final Continuation<? super Integer> $completion) {
    final String _sql = "SELECT COUNT(*) FROM messages WHERE isRead = 0 AND senderId != ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    if (currentUser == null) {
      _statement.bindNull(_argIndex);
    } else {
      _statement.bindString(_argIndex, currentUser);
    }
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final Integer _tmp;
            if (_cursor.isNull(0)) {
              _tmp = null;
            } else {
              _tmp = _cursor.getInt(0);
            }
            _result = _tmp;
          } else {
            _result = null;
          }
          return _result;
        } finally {
          _cursor.close();
          _statement.release();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
