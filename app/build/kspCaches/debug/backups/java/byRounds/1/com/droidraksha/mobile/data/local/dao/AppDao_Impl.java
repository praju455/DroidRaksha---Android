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
import androidx.room.util.StringUtil;
import androidx.sqlite.db.SupportSQLiteStatement;
import com.droidraksha.mobile.data.local.entity.AppEntity;
import java.lang.Class;
import java.lang.Exception;
import java.lang.Integer;
import java.lang.Object;
import java.lang.Override;
import java.lang.String;
import java.lang.StringBuilder;
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
public final class AppDao_Impl implements AppDao {
  private final RoomDatabase __db;

  private final EntityInsertionAdapter<AppEntity> __insertionAdapterOfAppEntity;

  private final SharedSQLiteStatement __preparedStmtOfMarkDeepScanAvailable;

  private final SharedSQLiteStatement __preparedStmtOfClearAll;

  public AppDao_Impl(@NonNull final RoomDatabase __db) {
    this.__db = __db;
    this.__insertionAdapterOfAppEntity = new EntityInsertionAdapter<AppEntity>(__db) {
      @Override
      @NonNull
      protected String createQuery() {
        return "INSERT OR REPLACE INTO `apps` (`packageName`,`appName`,`versionName`,`versionCode`,`installedAt`,`lastUpdated`,`apkSizeBytes`,`targetSdkVersion`,`minSdkVersion`,`installSource`,`certIssuer`,`certSubject`,`isSelfSigned`,`isDebugCert`,`riskScore`,`riskLevel`,`threatCategories`,`isFakeUpi`,`isFakeBank`,`isLoanScam`,`matchedIocDomains`,`dangerousPermissions`,`dangerousComboFlags`,`totalPermissionCount`,`c2Verdict`,`c2ConfidenceScore`,`detectedC2Frameworks`,`onnxPredictedClass`,`onnxConfidence`,`isAnomalyFlagged`,`lastScannedAt`,`deepScanAvailable`) VALUES (?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?,?)";
      }

      @Override
      protected void bind(@NonNull final SupportSQLiteStatement statement,
          @NonNull final AppEntity entity) {
        statement.bindString(1, entity.getPackageName());
        statement.bindString(2, entity.getAppName());
        statement.bindString(3, entity.getVersionName());
        statement.bindLong(4, entity.getVersionCode());
        statement.bindLong(5, entity.getInstalledAt());
        statement.bindLong(6, entity.getLastUpdated());
        statement.bindLong(7, entity.getApkSizeBytes());
        statement.bindLong(8, entity.getTargetSdkVersion());
        statement.bindLong(9, entity.getMinSdkVersion());
        statement.bindString(10, entity.getInstallSource());
        statement.bindString(11, entity.getCertIssuer());
        statement.bindString(12, entity.getCertSubject());
        final int _tmp = entity.isSelfSigned() ? 1 : 0;
        statement.bindLong(13, _tmp);
        final int _tmp_1 = entity.isDebugCert() ? 1 : 0;
        statement.bindLong(14, _tmp_1);
        statement.bindLong(15, entity.getRiskScore());
        statement.bindString(16, entity.getRiskLevel());
        statement.bindString(17, entity.getThreatCategories());
        final int _tmp_2 = entity.isFakeUpi() ? 1 : 0;
        statement.bindLong(18, _tmp_2);
        final int _tmp_3 = entity.isFakeBank() ? 1 : 0;
        statement.bindLong(19, _tmp_3);
        final int _tmp_4 = entity.isLoanScam() ? 1 : 0;
        statement.bindLong(20, _tmp_4);
        statement.bindString(21, entity.getMatchedIocDomains());
        statement.bindString(22, entity.getDangerousPermissions());
        statement.bindString(23, entity.getDangerousComboFlags());
        statement.bindLong(24, entity.getTotalPermissionCount());
        statement.bindString(25, entity.getC2Verdict());
        statement.bindLong(26, entity.getC2ConfidenceScore());
        statement.bindString(27, entity.getDetectedC2Frameworks());
        statement.bindString(28, entity.getOnnxPredictedClass());
        statement.bindDouble(29, entity.getOnnxConfidence());
        final int _tmp_5 = entity.isAnomalyFlagged() ? 1 : 0;
        statement.bindLong(30, _tmp_5);
        statement.bindLong(31, entity.getLastScannedAt());
        final int _tmp_6 = entity.getDeepScanAvailable() ? 1 : 0;
        statement.bindLong(32, _tmp_6);
      }
    };
    this.__preparedStmtOfMarkDeepScanAvailable = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "UPDATE apps SET deepScanAvailable = 1 WHERE packageName = ?";
        return _query;
      }
    };
    this.__preparedStmtOfClearAll = new SharedSQLiteStatement(__db) {
      @Override
      @NonNull
      public String createQuery() {
        final String _query = "DELETE FROM apps";
        return _query;
      }
    };
  }

  @Override
  public Object upsertAll(final List<AppEntity> apps,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppEntity.insert(apps);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object upsert(final AppEntity app, final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        __db.beginTransaction();
        try {
          __insertionAdapterOfAppEntity.insert(app);
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @Override
  public Object markDeepScanAvailable(final String packageName,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfMarkDeepScanAvailable.acquire();
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
          __preparedStmtOfMarkDeepScanAvailable.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Object clearAll(final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final SupportSQLiteStatement _stmt = __preparedStmtOfClearAll.acquire();
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
          __preparedStmtOfClearAll.release(_stmt);
        }
      }
    }, $completion);
  }

  @Override
  public Flow<List<AppEntity>> getAllAppsOrderedByRisk() {
    final String _sql = "SELECT * FROM apps ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<List<AppEntity>>() {
      @Override
      @NonNull
      public List<AppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final List<AppEntity> _result = new ArrayList<AppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppEntity _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _item = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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
  public Flow<List<AppEntity>> getAppsByRiskLevel(final String level) {
    final String _sql = "SELECT * FROM apps WHERE riskLevel = ? ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, level);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<List<AppEntity>>() {
      @Override
      @NonNull
      public List<AppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final List<AppEntity> _result = new ArrayList<AppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppEntity _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _item = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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
  public Flow<List<AppEntity>> getSideloadedApps() {
    final String _sql = "SELECT * FROM apps WHERE installSource != 'play_store' ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<List<AppEntity>>() {
      @Override
      @NonNull
      public List<AppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final List<AppEntity> _result = new ArrayList<AppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppEntity _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _item = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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
  public Flow<List<AppEntity>> getMediumAndAbove() {
    final String _sql = "SELECT * FROM apps WHERE riskScore >= 40 ORDER BY riskScore DESC";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<List<AppEntity>>() {
      @Override
      @NonNull
      public List<AppEntity> call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final List<AppEntity> _result = new ArrayList<AppEntity>(_cursor.getCount());
          while (_cursor.moveToNext()) {
            final AppEntity _item;
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _item = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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
  public Flow<AppEntity> getAppByPackage(final String packageName) {
    final String _sql = "SELECT * FROM apps WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<AppEntity>() {
      @Override
      @Nullable
      public AppEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final AppEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _result = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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
  public Object getAppByPackageOnce(final String packageName,
      final Continuation<? super AppEntity> $completion) {
    final String _sql = "SELECT * FROM apps WHERE packageName = ?";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 1);
    int _argIndex = 1;
    _statement.bindString(_argIndex, packageName);
    final CancellationSignal _cancellationSignal = DBUtil.createCancellationSignal();
    return CoroutinesRoom.execute(__db, false, _cancellationSignal, new Callable<AppEntity>() {
      @Override
      @Nullable
      public AppEntity call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final int _cursorIndexOfPackageName = CursorUtil.getColumnIndexOrThrow(_cursor, "packageName");
          final int _cursorIndexOfAppName = CursorUtil.getColumnIndexOrThrow(_cursor, "appName");
          final int _cursorIndexOfVersionName = CursorUtil.getColumnIndexOrThrow(_cursor, "versionName");
          final int _cursorIndexOfVersionCode = CursorUtil.getColumnIndexOrThrow(_cursor, "versionCode");
          final int _cursorIndexOfInstalledAt = CursorUtil.getColumnIndexOrThrow(_cursor, "installedAt");
          final int _cursorIndexOfLastUpdated = CursorUtil.getColumnIndexOrThrow(_cursor, "lastUpdated");
          final int _cursorIndexOfApkSizeBytes = CursorUtil.getColumnIndexOrThrow(_cursor, "apkSizeBytes");
          final int _cursorIndexOfTargetSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "targetSdkVersion");
          final int _cursorIndexOfMinSdkVersion = CursorUtil.getColumnIndexOrThrow(_cursor, "minSdkVersion");
          final int _cursorIndexOfInstallSource = CursorUtil.getColumnIndexOrThrow(_cursor, "installSource");
          final int _cursorIndexOfCertIssuer = CursorUtil.getColumnIndexOrThrow(_cursor, "certIssuer");
          final int _cursorIndexOfCertSubject = CursorUtil.getColumnIndexOrThrow(_cursor, "certSubject");
          final int _cursorIndexOfIsSelfSigned = CursorUtil.getColumnIndexOrThrow(_cursor, "isSelfSigned");
          final int _cursorIndexOfIsDebugCert = CursorUtil.getColumnIndexOrThrow(_cursor, "isDebugCert");
          final int _cursorIndexOfRiskScore = CursorUtil.getColumnIndexOrThrow(_cursor, "riskScore");
          final int _cursorIndexOfRiskLevel = CursorUtil.getColumnIndexOrThrow(_cursor, "riskLevel");
          final int _cursorIndexOfThreatCategories = CursorUtil.getColumnIndexOrThrow(_cursor, "threatCategories");
          final int _cursorIndexOfIsFakeUpi = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeUpi");
          final int _cursorIndexOfIsFakeBank = CursorUtil.getColumnIndexOrThrow(_cursor, "isFakeBank");
          final int _cursorIndexOfIsLoanScam = CursorUtil.getColumnIndexOrThrow(_cursor, "isLoanScam");
          final int _cursorIndexOfMatchedIocDomains = CursorUtil.getColumnIndexOrThrow(_cursor, "matchedIocDomains");
          final int _cursorIndexOfDangerousPermissions = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousPermissions");
          final int _cursorIndexOfDangerousComboFlags = CursorUtil.getColumnIndexOrThrow(_cursor, "dangerousComboFlags");
          final int _cursorIndexOfTotalPermissionCount = CursorUtil.getColumnIndexOrThrow(_cursor, "totalPermissionCount");
          final int _cursorIndexOfC2Verdict = CursorUtil.getColumnIndexOrThrow(_cursor, "c2Verdict");
          final int _cursorIndexOfC2ConfidenceScore = CursorUtil.getColumnIndexOrThrow(_cursor, "c2ConfidenceScore");
          final int _cursorIndexOfDetectedC2Frameworks = CursorUtil.getColumnIndexOrThrow(_cursor, "detectedC2Frameworks");
          final int _cursorIndexOfOnnxPredictedClass = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxPredictedClass");
          final int _cursorIndexOfOnnxConfidence = CursorUtil.getColumnIndexOrThrow(_cursor, "onnxConfidence");
          final int _cursorIndexOfIsAnomalyFlagged = CursorUtil.getColumnIndexOrThrow(_cursor, "isAnomalyFlagged");
          final int _cursorIndexOfLastScannedAt = CursorUtil.getColumnIndexOrThrow(_cursor, "lastScannedAt");
          final int _cursorIndexOfDeepScanAvailable = CursorUtil.getColumnIndexOrThrow(_cursor, "deepScanAvailable");
          final AppEntity _result;
          if (_cursor.moveToFirst()) {
            final String _tmpPackageName;
            _tmpPackageName = _cursor.getString(_cursorIndexOfPackageName);
            final String _tmpAppName;
            _tmpAppName = _cursor.getString(_cursorIndexOfAppName);
            final String _tmpVersionName;
            _tmpVersionName = _cursor.getString(_cursorIndexOfVersionName);
            final long _tmpVersionCode;
            _tmpVersionCode = _cursor.getLong(_cursorIndexOfVersionCode);
            final long _tmpInstalledAt;
            _tmpInstalledAt = _cursor.getLong(_cursorIndexOfInstalledAt);
            final long _tmpLastUpdated;
            _tmpLastUpdated = _cursor.getLong(_cursorIndexOfLastUpdated);
            final long _tmpApkSizeBytes;
            _tmpApkSizeBytes = _cursor.getLong(_cursorIndexOfApkSizeBytes);
            final int _tmpTargetSdkVersion;
            _tmpTargetSdkVersion = _cursor.getInt(_cursorIndexOfTargetSdkVersion);
            final int _tmpMinSdkVersion;
            _tmpMinSdkVersion = _cursor.getInt(_cursorIndexOfMinSdkVersion);
            final String _tmpInstallSource;
            _tmpInstallSource = _cursor.getString(_cursorIndexOfInstallSource);
            final String _tmpCertIssuer;
            _tmpCertIssuer = _cursor.getString(_cursorIndexOfCertIssuer);
            final String _tmpCertSubject;
            _tmpCertSubject = _cursor.getString(_cursorIndexOfCertSubject);
            final boolean _tmpIsSelfSigned;
            final int _tmp;
            _tmp = _cursor.getInt(_cursorIndexOfIsSelfSigned);
            _tmpIsSelfSigned = _tmp != 0;
            final boolean _tmpIsDebugCert;
            final int _tmp_1;
            _tmp_1 = _cursor.getInt(_cursorIndexOfIsDebugCert);
            _tmpIsDebugCert = _tmp_1 != 0;
            final int _tmpRiskScore;
            _tmpRiskScore = _cursor.getInt(_cursorIndexOfRiskScore);
            final String _tmpRiskLevel;
            _tmpRiskLevel = _cursor.getString(_cursorIndexOfRiskLevel);
            final String _tmpThreatCategories;
            _tmpThreatCategories = _cursor.getString(_cursorIndexOfThreatCategories);
            final boolean _tmpIsFakeUpi;
            final int _tmp_2;
            _tmp_2 = _cursor.getInt(_cursorIndexOfIsFakeUpi);
            _tmpIsFakeUpi = _tmp_2 != 0;
            final boolean _tmpIsFakeBank;
            final int _tmp_3;
            _tmp_3 = _cursor.getInt(_cursorIndexOfIsFakeBank);
            _tmpIsFakeBank = _tmp_3 != 0;
            final boolean _tmpIsLoanScam;
            final int _tmp_4;
            _tmp_4 = _cursor.getInt(_cursorIndexOfIsLoanScam);
            _tmpIsLoanScam = _tmp_4 != 0;
            final String _tmpMatchedIocDomains;
            _tmpMatchedIocDomains = _cursor.getString(_cursorIndexOfMatchedIocDomains);
            final String _tmpDangerousPermissions;
            _tmpDangerousPermissions = _cursor.getString(_cursorIndexOfDangerousPermissions);
            final String _tmpDangerousComboFlags;
            _tmpDangerousComboFlags = _cursor.getString(_cursorIndexOfDangerousComboFlags);
            final int _tmpTotalPermissionCount;
            _tmpTotalPermissionCount = _cursor.getInt(_cursorIndexOfTotalPermissionCount);
            final String _tmpC2Verdict;
            _tmpC2Verdict = _cursor.getString(_cursorIndexOfC2Verdict);
            final int _tmpC2ConfidenceScore;
            _tmpC2ConfidenceScore = _cursor.getInt(_cursorIndexOfC2ConfidenceScore);
            final String _tmpDetectedC2Frameworks;
            _tmpDetectedC2Frameworks = _cursor.getString(_cursorIndexOfDetectedC2Frameworks);
            final String _tmpOnnxPredictedClass;
            _tmpOnnxPredictedClass = _cursor.getString(_cursorIndexOfOnnxPredictedClass);
            final float _tmpOnnxConfidence;
            _tmpOnnxConfidence = _cursor.getFloat(_cursorIndexOfOnnxConfidence);
            final boolean _tmpIsAnomalyFlagged;
            final int _tmp_5;
            _tmp_5 = _cursor.getInt(_cursorIndexOfIsAnomalyFlagged);
            _tmpIsAnomalyFlagged = _tmp_5 != 0;
            final long _tmpLastScannedAt;
            _tmpLastScannedAt = _cursor.getLong(_cursorIndexOfLastScannedAt);
            final boolean _tmpDeepScanAvailable;
            final int _tmp_6;
            _tmp_6 = _cursor.getInt(_cursorIndexOfDeepScanAvailable);
            _tmpDeepScanAvailable = _tmp_6 != 0;
            _result = new AppEntity(_tmpPackageName,_tmpAppName,_tmpVersionName,_tmpVersionCode,_tmpInstalledAt,_tmpLastUpdated,_tmpApkSizeBytes,_tmpTargetSdkVersion,_tmpMinSdkVersion,_tmpInstallSource,_tmpCertIssuer,_tmpCertSubject,_tmpIsSelfSigned,_tmpIsDebugCert,_tmpRiskScore,_tmpRiskLevel,_tmpThreatCategories,_tmpIsFakeUpi,_tmpIsFakeBank,_tmpIsLoanScam,_tmpMatchedIocDomains,_tmpDangerousPermissions,_tmpDangerousComboFlags,_tmpTotalPermissionCount,_tmpC2Verdict,_tmpC2ConfidenceScore,_tmpDetectedC2Frameworks,_tmpOnnxPredictedClass,_tmpOnnxConfidence,_tmpIsAnomalyFlagged,_tmpLastScannedAt,_tmpDeepScanAvailable);
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

  @Override
  public Flow<Integer> getCriticalCount() {
    final String _sql = "SELECT COUNT(*) FROM apps WHERE riskLevel = 'CRITICAL'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getHighCount() {
    final String _sql = "SELECT COUNT(*) FROM apps WHERE riskLevel = 'HIGH'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getMediumCount() {
    final String _sql = "SELECT COUNT(*) FROM apps WHERE riskLevel = 'MEDIUM'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getLowCount() {
    final String _sql = "SELECT COUNT(*) FROM apps WHERE riskLevel = 'LOW'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getSafeCount() {
    final String _sql = "SELECT COUNT(*) FROM apps WHERE riskLevel = 'SAFE'";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Flow<Integer> getTotalCount() {
    final String _sql = "SELECT COUNT(*) FROM apps";
    final RoomSQLiteQuery _statement = RoomSQLiteQuery.acquire(_sql, 0);
    return CoroutinesRoom.createFlow(__db, false, new String[] {"apps"}, new Callable<Integer>() {
      @Override
      @NonNull
      public Integer call() throws Exception {
        final Cursor _cursor = DBUtil.query(__db, _statement, false, null);
        try {
          final Integer _result;
          if (_cursor.moveToFirst()) {
            final int _tmp;
            _tmp = _cursor.getInt(0);
            _result = _tmp;
          } else {
            _result = 0;
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
  public Object deleteRemovedApps(final List<String> currentPackages,
      final Continuation<? super Unit> $completion) {
    return CoroutinesRoom.execute(__db, true, new Callable<Unit>() {
      @Override
      @NonNull
      public Unit call() throws Exception {
        final StringBuilder _stringBuilder = StringUtil.newStringBuilder();
        _stringBuilder.append("DELETE FROM apps WHERE packageName NOT IN (");
        final int _inputSize = currentPackages.size();
        StringUtil.appendPlaceholders(_stringBuilder, _inputSize);
        _stringBuilder.append(")");
        final String _sql = _stringBuilder.toString();
        final SupportSQLiteStatement _stmt = __db.compileStatement(_sql);
        int _argIndex = 1;
        for (String _item : currentPackages) {
          _stmt.bindString(_argIndex, _item);
          _argIndex++;
        }
        __db.beginTransaction();
        try {
          _stmt.executeUpdateDelete();
          __db.setTransactionSuccessful();
          return Unit.INSTANCE;
        } finally {
          __db.endTransaction();
        }
      }
    }, $completion);
  }

  @NonNull
  public static List<Class<?>> getRequiredConverters() {
    return Collections.emptyList();
  }
}
