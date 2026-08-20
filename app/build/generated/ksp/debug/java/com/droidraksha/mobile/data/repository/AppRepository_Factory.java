package com.droidraksha.mobile.data.repository;

import com.droidraksha.mobile.data.local.dao.AppDao;
import com.droidraksha.mobile.data.local.dao.ScanHistoryDao;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
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
public final class AppRepository_Factory implements Factory<AppRepository> {
  private final Provider<AppDao> appDaoProvider;

  private final Provider<ScanHistoryDao> scanHistoryDaoProvider;

  private final Provider<Moshi> moshiProvider;

  public AppRepository_Factory(Provider<AppDao> appDaoProvider,
      Provider<ScanHistoryDao> scanHistoryDaoProvider, Provider<Moshi> moshiProvider) {
    this.appDaoProvider = appDaoProvider;
    this.scanHistoryDaoProvider = scanHistoryDaoProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public AppRepository get() {
    return newInstance(appDaoProvider.get(), scanHistoryDaoProvider.get(), moshiProvider.get());
  }

  public static AppRepository_Factory create(Provider<AppDao> appDaoProvider,
      Provider<ScanHistoryDao> scanHistoryDaoProvider, Provider<Moshi> moshiProvider) {
    return new AppRepository_Factory(appDaoProvider, scanHistoryDaoProvider, moshiProvider);
  }

  public static AppRepository newInstance(AppDao appDao, ScanHistoryDao scanHistoryDao,
      Moshi moshi) {
    return new AppRepository(appDao, scanHistoryDao, moshi);
  }
}
