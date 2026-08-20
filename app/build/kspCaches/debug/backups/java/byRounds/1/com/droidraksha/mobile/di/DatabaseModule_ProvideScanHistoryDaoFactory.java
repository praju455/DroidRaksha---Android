package com.droidraksha.mobile.di;

import com.droidraksha.mobile.data.local.AppDatabase;
import com.droidraksha.mobile.data.local.dao.ScanHistoryDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata
@QualifierMetadata
@DaggerGenerated
@Generated(
    value = "dagger.internal.codegen.ComponentProcessor",
    comments = "https://dagger.dev"
)
@SuppressWarnings({
    "unchecked",
    "rawtypes",
    "KotlinInternal",
    "KotlinInternalInJava",
    "cast"
})
public final class DatabaseModule_ProvideScanHistoryDaoFactory implements Factory<ScanHistoryDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideScanHistoryDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public ScanHistoryDao get() {
    return provideScanHistoryDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideScanHistoryDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideScanHistoryDaoFactory(dbProvider);
  }

  public static ScanHistoryDao provideScanHistoryDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideScanHistoryDao(db));
  }
}
