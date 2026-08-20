package com.droidraksha.mobile.di;

import com.droidraksha.mobile.data.local.AppDatabase;
import com.droidraksha.mobile.data.local.dao.DeepScanResultDao;
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
public final class DatabaseModule_ProvideDeepScanResultDaoFactory implements Factory<DeepScanResultDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideDeepScanResultDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public DeepScanResultDao get() {
    return provideDeepScanResultDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideDeepScanResultDaoFactory create(
      Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideDeepScanResultDaoFactory(dbProvider);
  }

  public static DeepScanResultDao provideDeepScanResultDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideDeepScanResultDao(db));
  }
}
