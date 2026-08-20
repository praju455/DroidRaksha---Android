package com.droidraksha.mobile.data.local.dao;

import android.database.Cursor;
import android.os.CancellationSignal;
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
import com.droidraksha.mobile.data.local.entity.DeepScanResultEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.Collections;
import java.util.List;
import java.util.concurrent.Callable;
import javax.annotation.processing.Generated;
import kotlin.Unit;
import kotlin.coroutines.Continuation;
import kotlinx.coroutines.flow.Flow;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class DeepScanResultDao_Impl implements DeepScanResultDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<DeepScanResultEntity> __insertionAdapterOfDeepScanResultEntity;

  private final SharedSQLiteStatement __preparedStmtOfDeleteByPackage;

  private final SharedSQLiteStatement __preparedStmtOfEvictStale;

  public DeepScanResultDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfDeepScanResultEntity = new EntityInsertionAdapter<DeepScanResultEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `deep_scan_results` (`packageName`,`fetchedAt`,`virusTotalDetections`,`virusTotalTotalEngines`,`abuseIpdbMaxConfidence`,`otxIndicatorCount`,`malBertLabel`,`malBertConfidence`,`backendC2Verdict`,`confirmedC2IpCount`,`detectedFrameworks`,`aiNarrativeSummary`,`aiRecommendedAction`,`aiActionDetail`,`backendRiskScore`,`backendRiskLevel`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final DeepScanResultEntity entity) {
        statement.bindString(1, entity.getPackageName());
        statement.bindLong(2, entity.getFetchedAt());
        statement.bindLong(3, entity.getVirusTotalDetections());
        statement.bindLong(4, entity.getVirusTotalTotalEngines());
        statement.bindLong(5, entity.getAbuseIpdbMaxConfidence());
        statement.bindLong(6, entity.getOtxIndicatorCount());
        statement.bindString(7, entity.getMalBertLabel());
        statement.bindDouble(8, entity.getMalBertConfidence());
        statement.bindString(9, entity.getBackendC2Verdict());
        statement.bindLong(10, entity.getConfirmedC2IpCount());
        statement.bindString(11, entity.getDetectedFrameworks());
        statement.bindString(12, entity.getAiNarrativeSummary());
        statement.bindString(13, entity.getAiRecommendedAction());
        statement.bindString(14, entity.getAiActionDetail());
        statement.bindLong(15, entity.getBackendRiskScore());
        statement.bindString(16, entity.getBackendRiskLevel());
      }
    };
    this.__preparedStmtOfDeleteByPackage = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM deep_scan_results WHERE packageName = ?";
        return _query;
      }
    };
    this.__preparedStmtOfEvictStale = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM deep_scan_results WHERE fetchedAt < ?";
        return _query;
      }
    };
  }

  @Override
  public Object insert(final DeepScanResultEntity result,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfDeepScanResultEntity.insert(result);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object deleteByPackage(final String packageName,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfDeleteByPackage.acquire();
        int _argIndex = 1;
        _stmt.bindString(_argIndex, packageName);
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
          __preparedStmtOfDeleteByPackage.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object evictStale(final long cutoffEpochMillis,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfEvictStale.acquire();
        int _argIndex = 1;
        _stmt.bindLong(_argIndex, cutoffEpochMillis);
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
          __preparedStmtOfEvictStale.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<DeepScanResultEntity> getResultByPackage(final String packageName) {
    final String _sql = "SELECT * FROM deep_scan_results WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"deep_scan_results"}, new Callable<DeepScanResultEntity>() {
      @Override
      @Nullable
      public DeepScanResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final int _cursorIndexOfVirusTotalDetections = CursorUtil.getColumnIndexOrThrow(_cursor, "virusTotalDetections");
          final int _cursorIndexOfVirusTotalTotalEngines = CursorUtil.getColumnIndexOrThrow(_cursor, "virusTotalTotalEngines");
          final int _cursorIndexOfAbuseIpdbMaxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "abuseIpdbMaxConfidence");
          final int _cursorIndexOfOtxIndicatorCount = CursorUtil.getColumnIndexOrThrow(_cursor, "otxIndicatorCount");
          final int _cursorIndexOfMalBertLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "malBertLabel");
          final int _cursorIndexOfMalBertConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "malBertConfidence");
          final int _cursorIndexOfBackendC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "backendC2Verdict");
          final int _cursorIndexOfConfirmedC2IpCount = CursorUtil.getColumnIndexOrThrow(_cursor, "confirmedC2IpCount");
          final int _cursorIndexOfDetectedFrameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedFrameworks");
          final int _cursorIndexOfAiNarrativeSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "aiNarrativeSummary");
          final int _cursorIndexOfAiRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "aiRecommendedAction");
          final int _cursorIndexOfAiActionDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "aiActionDetail");
          final int _cursorIndexOfBackendRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "backendRiskScore");
          final int _cursorIndexOfBackendRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "backendRiskLevel");
          final DeepScanResultEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            final int _tmpVirusTotalDetections;
            _tmpVirusTotalDetections = _cursor.getInt(_cursorIndexOfVirusTotalDetections);
            final int _tmpVirusTotalTotalEngines;
            _tmpVirusTotalTotalEngines = _cursor.getInt(_cursorIndexOfVirusTotalTotalEngines);
            final int _tmpAbuseIpdbMaxConfidence;
            _tmpAbuseIpdbMaxConfidence = _cursor.getInt(_cursorIndexOfAbuseIpdbMaxConfidence);
            final int _tmpOtxIndicatorCount;
            _tmpOtxIndicatorCount = _cursor.getInt(_cursorIndexOfOtxIndicatorCount);
            final String _tmpMalBertLabel;
            _tmpMalBertLabel = _cursor.getString(_cursorIndexOfMalBertLabel);
            final float _tmpMalBertConfidence;
            _tmpMalBertConfidence = _cursor.getFloat(_cursorIndexOfMalBertConfidence);
            final String _tmpBackendC2Verdict;
            _tmpBackendC2Verdict = _cursor.getString(_cursorIndexOfBackendC2Verdict);
            final int _tmpConfirmedC2IpCount;
            _tmpConfirmedC2IpCount = _cursor.getInt(_cursorIndexOfConfirmedC2IpCount);
            final String _tmpDetectedFrameworks;
            _tmpDetectedFrameworks = _cursor.getString(_cursorIndexOfDetectedFrameworks);
            final String _tmpAiNarrativeSummary;
            _tmpAiNarrativeSummary = _cursor.getString(_cursorIndexOfAiNarrativeSummary);
            final String _tmpAiRecommendedAction;
            _tmpAiRecommendedAction = _cursor.getString(_cursorIndexOfAiRecommendedAction);
            final String _tmpAiActionDetail;
            _tmpAiActionDetail = _cursor.getString(_cursorIndexOfAiActionDetail);
            final int _tmpBackendRiskScore;
            _tmpBackendRiskScore = _cursor.getInt(_cursorIndexOfBackendRiskScore);
            final String _tmpBackendRiskLevel;
            _tmpBackendRiskLevel = _cursor.getString(_cursorIndexOfBackendRiskLevel);
            _result = new DeepScanResultEntity(_tmpPackageName,_tmpFetchedAt,_tmpVirusTotalDetections,_tmpVirusTotalTotalEngines,_tmpAbuseIpdbMaxConfidence,_tmpOtxIndicatorCount,_tmpMalBertLabel,_tmpMalBertConfidence,_tmpBackendC2Verdict,_tmpConfirmedC2IpCount,_tmpDetectedFrameworks,_tmpAiNarrativeSummary,_tmpAiRecommendedAction,_tmpAiActionDetail,_tmpBackendRiskScore,_tmpBackendRiskLevel);
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

  @Override
  public Object getResultByPackageOnce(final String packageName,
      final Continuation<? super DeepScanResultEntity> $completion) {
    final String _sql = "SELECT * FROM deep_scan_results WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<DeepScanResultEntity>() {
      @Override
      @Nullable
      public DeepScanResultEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfFetchedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "fetchedAt");
          final int _cursorIndexOfVirusTotalDetections = CursorUtil.getColumnIndexOrThrow(_cursor, "virusTotalDetections");
          final int _cursorIndexOfVirusTotalTotalEngines = CursorUtil.getColumnIndexOrThrow(_cursor, "virusTotalTotalEngines");
          final int _cursorIndexOfAbuseIpdbMaxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "abuseIpdbMaxConfidence");
          final int _cursorIndexOfOtxIndicatorCount = CursorUtil.getColumnIndexOrThrow(_cursor, "otxIndicatorCount");
          final int _cursorIndexOfMalBertLabel = CursorUtil.getColumnIndexOrThrow(_cursor, "malBertLabel");
          final int _cursorIndexOfMalBertConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "malBertConfidence");
          final int _cursorIndexOfBackendC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "backendC2Verdict");
          final int _cursorIndexOfConfirmedC2IpCount = CursorUtil.getColumnIndexOrThrow(_cursor, "confirmedC2IpCount");
          final int _cursorIndexOfDetectedFrameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedFrameworks");
          final int _cursorIndexOfAiNarrativeSummary = CursorUtil.getColumnIndexOrThrow(_cursor, "aiNarrativeSummary");
          final int _cursorIndexOfAiRecommendedAction = CursorUtil.getColumnIndexOrThrow(_cursor, "aiRecommendedAction");
          final int _cursorIndexOfAiActionDetail = CursorUtil.getColumnIndexOrThrow(_cursor, "aiActionDetail");
          final int _cursorIndexOfBackendRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "backendRiskScore");
          final int _cursorIndexOfBackendRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "backendRiskLevel");
          final DeepScanResultEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final long _tmpFetchedAt;
            _tmpFetchedAt = _cursor.getLong(_cursorIndexOfFetchedAt);
            final int _tmpVirusTotalDetections;
            _tmpVirusTotalDetections = _cursor.getInt(_cursorIndexOfVirusTotalDetections);
            final int _tmpVirusTotalTotalEngines;
            _tmpVirusTotalTotalEngines = _cursor.getInt(_cursorIndexOfVirusTotalTotalEngines);
            final int _tmpAbuseIpdbMaxConfidence;
            _tmpAbuseIpdbMaxConfidence = _cursor.getInt(_cursorIndexOfAbuseIpdbMaxConfidence);
            final int _tmpOtxIndicatorCount;
            _tmpOtxIndicatorCount = _cursor.getInt(_cursorIndexOfOtxIndicatorCount);
            final String _tmpMalBertLabel;
            _tmpMalBertLabel = _cursor.getString(_cursorIndexOfMalBertLabel);
            final float _tmpMalBertConfidence;
            _tmpMalBertConfidence = _cursor.getFloat(_cursorIndexOfMalBertConfidence);
            final String _tmpBackendC2Verdict;
            _tmpBackendC2Verdict = _cursor.getString(_cursorIndexOfBackendC2Verdict);
            final int _tmpConfirmedC2IpCount;
            _tmpConfirmedC2IpCount = _cursor.getInt(_cursorIndexOfConfirmedC2IpCount);
            final String _tmpDetectedFrameworks;
            _tmpDetectedFrameworks = _cursor.getString(_cursorIndexOfDetectedFrameworks);
            final String _tmpAiNarrativeSummary;
            _tmpAiNarrativeSummary = _cursor.getString(_cursorIndexOfAiNarrativeSummary);
            final String _tmpAiRecommendedAction;
            _tmpAiRecommendedAction = _cursor.getString(_cursorIndexOfAiRecommendedAction);
            final String _tmpAiActionDetail;
            _tmpAiActionDetail = _cursor.getString(_cursorIndexOfAiActionDetail);
            final int _tmpBackendRiskScore;
            _tmpBackendRiskScore = _cursor.getInt(_cursorIndexOfBackendRiskScore);
            final String _tmpBackendRiskLevel;
            _tmpBackendRiskLevel = _cursor.getString(_cursorIndexOfBackendRiskLevel);
            _result = new DeepScanResultEntity(_tmpPackageName,_tmpFetchedAt,_tmpVirusTotalDetections,_tmpVirusTotalTotalEngines,_tmpAbuseIpdbMaxConfidence,_tmpOtxIndicatorCount,_tmpMalBertLabel,_tmpMalBertConfidence,_tmpBackendC2Verdict,_tmpConfirmedC2IpCount,_tmpDetectedFrameworks,_tmpAiNarrativeSummary,_tmpAiRecommendedAction,_tmpAiActionDetail,_tmpBackendRiskScore,_tmpBackendRiskLevel);
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
