package com.droidraksha.mobile.data.local.dao;

import android.database.Cursor;
import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.room.CoroutinesRoom;
import androidx.room.EntityInsertionAdapter;
import androidx.room.RoomDatabase;
import androidx.room.RoomSQLiteQuery;
import androidx.room.SharedSQLiteStatement;
import androidx.room.util.CursorUtil;
import androidx.room.util.DBUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.droidraksha.mobile.data.local.entity.ScanHistoryEntity;
import java.lang.Class;
import java.lang.Exception;
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
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class ScanHistoryDao_Impl implements ScanHistoryDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<ScanHistoryEntity> __insertionAdapterOfScanHistoryEntity;

  private final SharedSQLiteStatement __preparedStmtOfPruneOldSessions;

  public ScanHistoryDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfScanHistoryEntity = new EntityInsertionAdapter<ScanHistoryEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `scan_history` (`id`,`scanStartedAt`,`scanCompletedAt`,`totalAppsScanned`,`criticalCount`,`highCount`,`mediumCount`,`lowCount`,`safeCount`,`newlyFlaggedPackages`,`deviceOverallScore`,`triggeredBy`) VALUES (nullif(?, 0),?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final ScanHistoryEntity entity) {
        statement.bindLong(1, entity.getId());
        statement.bindLong(2, entity.getScanStartedAt());
        statement.bindLong(3, entity.getScanCompletedAt());
        statement.bindLong(4, entity.getTotalAppsScanned());
        statement.bindLong(5, entity.getCriticalCount());
        statement.bindLong(6, entity.getHighCount());
        statement.bindLong(7, entity.getMediumCount());
        statement.bindLong(8, entity.getLowCount());
        statement.bindLong(9, entity.getSafeCount());
        statement.bindString(10, entity.getNewlyFlaggedPackages());
        statement.bindLong(11, entity.getDeviceOverallScore());
        statement.bindString(12, entity.getTriggeredBy());
      }
    };
    this.__preparedStmtOfPruneOldSessions = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "\n"
                + "        DELETE FROM scan_history \n"
                + "        WHERE id NOT IN (\n"
                + "            SELECT id FROM scan_history ORDER BY scanCompletedAt DESC LIMIT ?\n"
                + "        )\n"
                + "    ";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final ScanHistoryEntity entry,
      final Continuation<? super Long> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Long>() {
      @Override
      @NonNull
      public Long call() throws Exception {
        __db.beginTransaction();
        try {
          final Long _result = __insertionAdapterOfScanHistoryEntity.insertAndReturnId(entry);
          __db.setTransactionSuccessful();
          return _result;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object pruneOldSessions(final int keepCount,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfPruneOldSessions.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, keepCount);
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
          __preparedStmtOfPruneOldSessions.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<ScanHistoryEntity>> getAllSessions() {
    final String _sql = "SELECT * FROM scan_history ORDER BY scanCompletedAt DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"scan_history"}, new Callable<List<ScanHistoryEntity>>() {
      @Override
      @NonNull
      public List<ScanHistoryEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfScanStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanStartedAt");
          final int _cursorIndexOfScanCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanCompletedAt");
          final int _cursorIndexOfTotalAppsScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAppsScanned");
          final int _cursorIndexOfCriticalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "criticalCount");
          final int _cursorIndexOfHighCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highCount");
          final int _cursorIndexOfMediumCount = CursorUtil.getColumnIndexOrThrow(_cursor, "mediumCount");
          final int _cursorIndexOfLowCount = CursorUtil.getColumnIndexOrThrow(_cursor, "lowCount");
          final int _cursorIndexOfSafeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "safeCount");
          final int _cursorIndexOfNewlyFlaggedPackages = CursorUtil.getColumnIndexOrThrow(_cursor, "newlyFlaggedPackages");
          final int _cursorIndexOfDeviceOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceOverallScore");
          final int _cursorIndexOfTriggeredBy = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredBy");
          final List<ScanHistoryEntity> _result = new ArrayList<ScanHistoryEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final ScanHistoryEntity _item;
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpScanStartedAt;
            _tmpScanStartedAt = _cursor.getLong(_cursorIndexOfScanStartedAt);
            final long _tmpScanCompletedAt;
            _tmpScanCompletedAt = _cursor.getLong(_cursorIndexOfScanCompletedAt);
            final int _tmpTotalAppsScanned;
            _tmpTotalAppsScanned = _cursor.getInt(_cursorIndexOfTotalAppsScanned);
            final int _tmpCriticalCount;
            _tmpCriticalCount = _cursor.getInt(_cursorIndexOfCriticalCount);
            final int _tmpHighCount;
            _tmpHighCount = _cursor.getInt(_cursorIndexOfHighCount);
            final int _tmpMediumCount;
            _tmpMediumCount = _cursor.getInt(_cursorIndexOfMediumCount);
            final int _tmpLowCount;
            _tmpLowCount = _cursor.getInt(_cursorIndexOfLowCount);
            final int _tmpSafeCount;
            _tmpSafeCount = _cursor.getInt(_cursorIndexOfSafeCount);
            final String _tmpNewlyFlaggedPackages;
            _tmpNewlyFlaggedPackages = _cursor.getString(_cursorIndexOfNewlyFlaggedPackages);
            final int _tmpDeviceOverallScore;
            _tmpDeviceOverallScore = _cursor.getInt(_cursorIndexOfDeviceOverallScore);
            final String _tmpTriggeredBy;
            _tmpTriggeredBy = _cursor.getString(_cursorIndexOfTriggeredBy);
            _item = new ScanHistoryEntity(_tmpId,_tmpScanStartedAt,_tmpScanCompletedAt,_tmpTotalAppsScanned,_tmpCriticalCount,_tmpHighCount,_tmpMediumCount,_tmpLowCount,_tmpSafeCount,_tmpNewlyFlaggedPackages,_tmpDeviceOverallScore,_tmpTriggeredBy);
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
  public Flow<ScanHistoryEntity> getLatestSession() {
    final String _sql = "SELECT * FROM scan_history ORDER BY scanCompletedAt DESC LIMIT 1";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"scan_history"}, new Callable<ScanHistoryEntity>() {
      @Override
      @Nullable
      public ScanHistoryEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfId = CursorUtil.getColumnIndexOrThrow(_cursor, "id");
          final int _cursorIndexOfScanStartedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanStartedAt");
          final int _cursorIndexOfScanCompletedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "scanCompletedAt");
          final int _cursorIndexOfTotalAppsScanned = CursorUtil.getColumnIndexOrThrow(_cursor, "totalAppsScanned");
          final int _cursorIndexOfCriticalCount = CursorUtil.getColumnIndexOrThrow(_cursor, "criticalCount");
          final int _cursorIndexOfHighCount = CursorUtil.getColumnIndexOrThrow(_cursor, "highCount");
          final int _cursorIndexOfMediumCount = CursorUtil.getColumnIndexOrThrow(_cursor, "mediumCount");
          final int _cursorIndexOfLowCount = CursorUtil.getColumnIndexOrThrow(_cursor, "lowCount");
          final int _cursorIndexOfSafeCount = CursorUtil.getColumnIndexOrThrow(_cursor, "safeCount");
          final int _cursorIndexOfNewlyFlaggedPackages = CursorUtil.getColumnIndexOrThrow(_cursor, "newlyFlaggedPackages");
          final int _cursorIndexOfDeviceOverallScore = CursorUtil.getColumnIndexOrThrow(_cursor, "deviceOverallScore");
          final int _cursorIndexOfTriggeredBy = CursorUtil.getColumnIndexOrThrow(_cursor, "triggeredBy");
          final ScanHistoryEntity _result;
          if (_cursor.moveToFirst()) {
            final long _tmpId;
            _tmpId = _cursor.getLong(_cursorIndexOfId);
            final long _tmpScanStartedAt;
            _tmpScanStartedAt = _cursor.getLong(_cursorIndexOfScanStartedAt);
            final long _tmpScanCompletedAt;
            _tmpScanCompletedAt = _cursor.getLong(_cursorIndexOfScanCompletedAt);
            final int _tmpTotalAppsScanned;
            _tmpTotalAppsScanned = _cursor.getInt(_cursorIndexOfTotalAppsScanned);
            final int _tmpCriticalCount;
            _tmpCriticalCount = _cursor.getInt(_cursorIndexOfCriticalCount);
            final int _tmpHighCount;
            _tmpHighCount = _cursor.getInt(_cursorIndexOfHighCount);
            final int _tmpMediumCount;
            _tmpMediumCount = _cursor.getInt(_cursorIndexOfMediumCount);
            final int _tmpLowCount;
            _tmpLowCount = _cursor.getInt(_cursorIndexOfLowCount);
            final int _tmpSafeCount;
            _tmpSafeCount = _cursor.getInt(_cursorIndexOfSafeCount);
            final String _tmpNewlyFlaggedPackages;
            _tmpNewlyFlaggedPackages = _cursor.getString(_cursorIndexOfNewlyFlaggedPackages);
            final int _tmpDeviceOverallScore;
            _tmpDeviceOverallScore = _cursor.getInt(_cursorIndexOfDeviceOverallScore);
            final String _tmpTriggeredBy;
            _tmpTriggeredBy = _cursor.getString(_cursorIndexOfTriggeredBy);
            _result = new ScanHistoryEntity(_tmpId,_tmpScanStartedAt,_tmpScanCompletedAt,_tmpTotalAppsScanned,_tmpCriticalCount,_tmpHighCount,_tmpMediumCount,_tmpLowCount,_tmpSafeCount,_tmpNewlyFlaggedPackages,_tmpDeviceOverallScore,_tmpTriggeredBy);
          } else {
            _result = null;
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

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
