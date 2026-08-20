package com.droidraksha.mobile.data.local;

import androidx.annotation.NonNull;
import androidx.room.DatabaseConfiguration;
import androidx.room.InvalidationTracker;
import androidx.room.RoomDatabase;
import androidx.room.RoomOpenHelper;
import androidx.room.migration.AutoMigrationSpec;
import androidx.room.migration.Migration;
import androidx.room.util.DBUtil;
import androidx.room.util.TableInfo;
import androidx.sqlite.db.SupportSQLiteDatabase;
import androidx.sqlite.db.SupportSQLiteOpenHelper;
import com.droidraksha.mobile.data.local.dao.AppDao;
import com.droidraksha.mobile.data.local.dao.AppDao_Impl;
import com.droidraksha.mobile.data.local.dao.DeepScanResultDao;
import com.droidraksha.mobile.data.local.dao.DeepScanResultDao_Impl;
import com.droidraksha.mobile.data.local.dao.ScanHistoryDao;
import com.droidraksha.mobile.data.local.dao.ScanHistoryDao_Impl;
import java.lang.Class;
import java.lang.Override;
import java.lang.String;
import java.lang.SuppressWarnings;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;

@Generated("androidx.room.RoomProcessor")
@SuppressWarnings({"unchecked", "deprecation"})
public final class AppDatabase_Impl extends AppDatabase {
  private volatile AppDao _appDao;

  private volatile ScanHistoryDao _scanHistoryDao;

  private volatile DeepScanResultDao _deepScanResultDao;

  @Override
  @NonNull
  protected SupportSQLiteOpenHelper createOpenHelper(@NonNull final DatabaseConfiguration config) {
    final SupportSQLiteOpenHelper.Callback _openCallback = new RoomOpenHelper(config, new RoomOpenHelper.Delegate(1) {
      @Override
      public void createAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("CREATE TABLE IF NOT EXISTS `apps` (`packageName` TEXT NOT NULL, `appName` TEXT NOT NULL, `versionName` TEXT NOT NULL, `versionCode` INTEGER NOT NULL, `installedAt` INTEGER NOT NULL, `lastUpdated` INTEGER NOT NULL, `apkSizeBytes` INTEGER NOT NULL, `targetSdkVersion` INTEGER NOT NULL, `minSdkVersion` INTEGER NOT NULL, `installSource` TEXT NOT NULL, `certIssuer` TEXT NOT NULL, `certSubject` TEXT NOT NULL, `isSelfSigned` INTEGER NOT NULL, `isDebugCert` INTEGER NOT NULL, `riskScore` INTEGER NOT NULL, `riskLevel` TEXT NOT NULL, `threatCategories` TEXT NOT NULL, `isFakeUpi` INTEGER NOT NULL, `isFakeBank` INTEGER NOT NULL, `isLoanScam` INTEGER NOT NULL, `matchedIocDomains` TEXT NOT NULL, `dangerousPermissions` TEXT NOT NULL, `dangerousComboFlags` TEXT NOT NULL, `totalPermissionCount` INTEGER NOT NULL, `c2Verdict` TEXT NOT NULL, `c2ConfidenceScore` INTEGER NOT NULL, `detectedC2Frameworks` TEXT NOT NULL, `onnxPredictedClass` TEXT NOT NULL, `onnxConfidence` REAL NOT NULL, `isAnomalyFlagged` INTEGER NOT NULL, `lastScannedAt` INTEGER NOT NULL, `deepScanAvailable` INTEGER NOT NULL, PRIMARY KEY(`packageName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS `scan_history` (`id` INTEGER PRIMARY KEY AUTOINCREMENT NOT NULL, `scanStartedAt` INTEGER NOT NULL, `scanCompletedAt` INTEGER NOT NULL, `totalAppsScanned` INTEGER NOT NULL, `criticalCount` INTEGER NOT NULL, `highCount` INTEGER NOT NULL, `mediumCount` INTEGER NOT NULL, `lowCount` INTEGER NOT NULL, `safeCount` INTEGER NOT NULL, `newlyFlaggedPackages` TEXT NOT NULL, `deviceOverallScore` INTEGER NOT NULL, `triggeredBy` TEXT NOT NULL)");
        db.execSQL("CREATE TABLE IF NOT EXISTS `deep_scan_results` (`packageName` TEXT NOT NULL, `fetchedAt` INTEGER NOT NULL, `virusTotalDetections` INTEGER NOT NULL, `virusTotalTotalEngines` INTEGER NOT NULL, `abuseIpdbMaxConfidence` INTEGER NOT NULL, `otxIndicatorCount` INTEGER NOT NULL, `malBertLabel` TEXT NOT NULL, `malBertConfidence` REAL NOT NULL, `backendC2Verdict` TEXT NOT NULL, `confirmedC2IpCount` INTEGER NOT NULL, `detectedFrameworks` TEXT NOT NULL, `aiNarrativeSummary` TEXT NOT NULL, `aiRecommendedAction` TEXT NOT NULL, `aiActionDetail` TEXT NOT NULL, `backendRiskScore` INTEGER NOT NULL, `backendRiskLevel` TEXT NOT NULL, PRIMARY KEY(`packageName`))");
        db.execSQL("CREATE TABLE IF NOT EXISTS room_master_table (id INTEGER PRIMARY KEY,identity_hash TEXT)");
        db.execSQL("INSERT OR REPLACE INTO room_master_table (id,identity_hash) VALUES(42, '150f5707138c6cc238cf21032592d147')");
      }

      @Override
      public void dropAllTables(@NonNull final SupportSQLiteDatabase db) {
        db.execSQL("DROP TABLE IF EXISTS `apps`");
        db.execSQL("DROP TABLE IF EXISTS `scan_history`");
        db.execSQL("DROP TABLE IF EXISTS `deep_scan_results`");
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onDestructiveMigration(db);
          }
        }
      }

      @Override
      public void onCreate(@NonNull final SupportSQLiteDatabase db) {
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onCreate(db);
          }
        }
      }

      @Override
      public void onOpen(@NonNull final SupportSQLiteDatabase db) {
        mDatabase = db;
        internalInitInvalidationTracker(db);
        final List<? extends RoomDatabase.Callback> _callbacks = mCallbacks;
        if (_callbacks != null) {
          for (RoomDatabase.Callback _callback : _callbacks) {
            _callback.onOpen(db);
          }
        }
      }

      @Override
      public void onPreMigrate(@NonNull final SupportSQLiteDatabase db) {
        DBUtil.dropFtsSyncTriggers(db);
      }

      @Override
      public void onPostMigrate(@NonNull final SupportSQLiteDatabase db) {
      }

      @Override
      @NonNull
      public RoomOpenHelper.ValidationResult onValidateSchema(
          @NonNull final SupportSQLiteDatabase db) {
        final HashMap<String, TableInfo.Column> _columnsApps = new HashMap<String, TableInfo.Column>(32);
        _columnsApps.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("appName", new TableInfo.Column("appName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("versionName", new TableInfo.Column("versionName", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("versionCode", new TableInfo.Column("versionCode", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("installedAt", new TableInfo.Column("installedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("lastUpdated", new TableInfo.Column("lastUpdated", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("apkSizeBytes", new TableInfo.Column("apkSizeBytes", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("targetSdkVersion", new TableInfo.Column("targetSdkVersion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("minSdkVersion", new TableInfo.Column("minSdkVersion", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("installSource", new TableInfo.Column("installSource", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("certIssuer", new TableInfo.Column("certIssuer", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("certSubject", new TableInfo.Column("certSubject", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isSelfSigned", new TableInfo.Column("isSelfSigned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isDebugCert", new TableInfo.Column("isDebugCert", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("riskScore", new TableInfo.Column("riskScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("riskLevel", new TableInfo.Column("riskLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("threatCategories", new TableInfo.Column("threatCategories", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isFakeUpi", new TableInfo.Column("isFakeUpi", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isFakeBank", new TableInfo.Column("isFakeBank", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isLoanScam", new TableInfo.Column("isLoanScam", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("matchedIocDomains", new TableInfo.Column("matchedIocDomains", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("dangerousPermissions", new TableInfo.Column("dangerousPermissions", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("dangerousComboFlags", new TableInfo.Column("dangerousComboFlags", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("totalPermissionCount", new TableInfo.Column("totalPermissionCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("c2Verdict", new TableInfo.Column("c2Verdict", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("c2ConfidenceScore", new TableInfo.Column("c2ConfidenceScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("detectedC2Frameworks", new TableInfo.Column("detectedC2Frameworks", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("onnxPredictedClass", new TableInfo.Column("onnxPredictedClass", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("onnxConfidence", new TableInfo.Column("onnxConfidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("isAnomalyFlagged", new TableInfo.Column("isAnomalyFlagged", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("lastScannedAt", new TableInfo.Column("lastScannedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsApps.put("deepScanAvailable", new TableInfo.Column("deepScanAvailable", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysApps = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesApps = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoApps = new TableInfo("apps", _columnsApps, _foreignKeysApps, _indicesApps);
        final TableInfo _existingApps = TableInfo.read(db, "apps");
        if (!_infoApps.equals(_existingApps)) {
          return new RoomOpenHelper.ValidationResult(false, "apps(com.droidraksha.mobile.data.local.entity.AppEntity).\n"
                  + " Expected:\n" + _infoApps + "\n"
                  + " Found:\n" + _existingApps);
        }
        final HashMap<String, TableInfo.Column> _columnsScanHistory = new HashMap<String, TableInfo.Column>(12);
        _columnsScanHistory.put("id", new TableInfo.Column("id", "INTEGER", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("scanStartedAt", new TableInfo.Column("scanStartedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("scanCompletedAt", new TableInfo.Column("scanCompletedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("totalAppsScanned", new TableInfo.Column("totalAppsScanned", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("criticalCount", new TableInfo.Column("criticalCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("highCount", new TableInfo.Column("highCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("mediumCount", new TableInfo.Column("mediumCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("lowCount", new TableInfo.Column("lowCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("safeCount", new TableInfo.Column("safeCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("newlyFlaggedPackages", new TableInfo.Column("newlyFlaggedPackages", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("deviceOverallScore", new TableInfo.Column("deviceOverallScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsScanHistory.put("triggeredBy", new TableInfo.Column("triggeredBy", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysScanHistory = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesScanHistory = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoScanHistory = new TableInfo("scan_history", _columnsScanHistory, _foreignKeysScanHistory, _indicesScanHistory);
        final TableInfo _existingScanHistory = TableInfo.read(db, "scan_history");
        if (!_infoScanHistory.equals(_existingScanHistory)) {
          return new RoomOpenHelper.ValidationResult(false, "scan_history(com.droidraksha.mobile.data.local.entity.ScanHistoryEntity).\n"
                  + " Expected:\n" + _infoScanHistory + "\n"
                  + " Found:\n" + _existingScanHistory);
        }
        final HashMap<String, TableInfo.Column> _columnsDeepScanResults = new HashMap<String, TableInfo.Column>(16);
        _columnsDeepScanResults.put("packageName", new TableInfo.Column("packageName", "TEXT", true, 1, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("fetchedAt", new TableInfo.Column("fetchedAt", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("virusTotalDetections", new TableInfo.Column("virusTotalDetections", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("virusTotalTotalEngines", new TableInfo.Column("virusTotalTotalEngines", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("abuseIpdbMaxConfidence", new TableInfo.Column("abuseIpdbMaxConfidence", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("otxIndicatorCount", new TableInfo.Column("otxIndicatorCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("malBertLabel", new TableInfo.Column("malBertLabel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("malBertConfidence", new TableInfo.Column("malBertConfidence", "REAL", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("backendC2Verdict", new TableInfo.Column("backendC2Verdict", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("confirmedC2IpCount", new TableInfo.Column("confirmedC2IpCount", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("detectedFrameworks", new TableInfo.Column("detectedFrameworks", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("aiNarrativeSummary", new TableInfo.Column("aiNarrativeSummary", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("aiRecommendedAction", new TableInfo.Column("aiRecommendedAction", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("aiActionDetail", new TableInfo.Column("aiActionDetail", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("backendRiskScore", new TableInfo.Column("backendRiskScore", "INTEGER", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        _columnsDeepScanResults.put("backendRiskLevel", new TableInfo.Column("backendRiskLevel", "TEXT", true, 0, null, TableInfo.CREATED_FROM_ENTITY));
        final HashSet<TableInfo.ForeignKey> _foreignKeysDeepScanResults = new HashSet<TableInfo.ForeignKey>(0);
        final HashSet<TableInfo.Index> _indicesDeepScanResults = new HashSet<TableInfo.Index>(0);
        final TableInfo _infoDeepScanResults = new TableInfo("deep_scan_results", _columnsDeepScanResults, _foreignKeysDeepScanResults, _indicesDeepScanResults);
        final TableInfo _existingDeepScanResults = TableInfo.read(db, "deep_scan_results");
        if (!_infoDeepScanResults.equals(_existingDeepScanResults)) {
          return new RoomOpenHelper.ValidationResult(false, "deep_scan_results(com.droidraksha.mobile.data.local.entity.DeepScanResultEntity).\n"
                  + " Expected:\n" + _infoDeepScanResults + "\n"
                  + " Found:\n" + _existingDeepScanResults);
        }
        return new RoomOpenHelper.ValidationResult(true, null);
      }
    }, "150f5707138c6cc238cf21032592d147", "1ff5b5e8c7687c81229c2d890fad0edd");
    final SupportSQLiteOpenHelper.Configuration _sqliteConfig = SupportSQLiteOpenHelper.Configuration.builder(config.context).name(config.name).callback(_openCallback).build();
    final SupportSQLiteOpenHelper _helper = config.sqliteOpenHelperFactory.create(_sqliteConfig);
    return _helper;
  }

  @Override
  @NonNull
  protected InvalidationTracker createInvalidationTracker() {
    final HashMap<String, String> _shadowTablesMap = new HashMap<String, String>(0);
    final HashMap<String, Set<String>> _viewTables = new HashMap<String, Set<String>>(0);
    return new InvalidationTracker(this, _shadowTablesMap, _viewTables, "apps","scan_history","deep_scan_results");
  }

  @Override
  public void clearAllTables() {
    super.assertNotMainThread();
    final SupportSQLiteDatabase _db = super.getOpenHelper().getWritableDatabase();
    try {
      super.beginTransaction();
      _db.execSQL("DELETE FROM `apps`");
      _db.execSQL("DELETE FROM `scan_history`");
      _db.execSQL("DELETE FROM `deep_scan_results`");
      super.setTransactionSuccessful();
    } finally {
      super.endTransaction();
      _db.query("PRAGMA wal_checkpoint(FULL)").close();
      if (!_db.inTransaction()) {
        _db.execSQL("VACUUM");
      }
    }
  }

  @Override
  @NonNull
  protected Map<Class<?>, List<Class<?>>> getRequiredTypeConverters() {
    final HashMap<Class<?>, List<Class<?>>> _typeConvertersMap = new HashMap<Class<?>, List<Class<?>>>();
    _typeConvertersMap.put(AppDao.class, AppDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(ScanHistoryDao.class, ScanHistoryDao_Impl.getRequiredConverters());
    _typeConvertersMap.put(DeepScanResultDao.class, DeepScanResultDao_Impl.getRequiredConverters());
    return _typeConvertersMap;
  }

  @Override
  @NonNull
  public Set<Class<? extends AutoMigrationSpec>> getRequiredAutoMigrationSpecs() {
    final HashSet<Class<? extends AutoMigrationSpec>> _autoMigrationSpecsSet = new HashSet<Class<? extends AutoMigrationSpec>>();
    return _autoMigrationSpecsSet;
  }

  @Override
  @NonNull
  public List<Migration> getAutoMigrations(
      @NonNull final Map<Class<? extends AutoMigrationSpec>, AutoMigrationSpec> autoMigrationSpecs) {
    final List<Migration> _autoMigrations = new ArrayList<Migration>();
    return _autoMigrations;
  }

  @Override
  public AppDao appDao() {
    if (_appDao != null) {
      return _appDao;
    } else {
      synchronized(this) {
        if(_appDao == null) {
          _appDao = new AppDao_Impl(this);
        }
        return _appDao;
      }
    }
  }

  @Override
  public ScanHistoryDao scanHistoryDao() {
    if (_scanHistoryDao != null) {
      return _scanHistoryDao;
    } else {
      synchronized(this) {
        if(_scanHistoryDao == null) {
          _scanHistoryDao = new ScanHistoryDao_Impl(this);
        }
        return _scanHistoryDao;
      }
    }
  }

  @Override
  public DeepScanResultDao deepScanResultDao() {
    if (_deepScanResultDao != null) {
      return _deepScanResultDao;
    } else {
      synchronized(this) {
        if(_deepScanResultDao == null) {
          _deepScanResultDao = new DeepScanResultDao_Impl(this);
        }
        return _deepScanResultDao;
      }
    }
  }
}
