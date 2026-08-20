package com.droidraksha.mobile.data.repository;

import com.droidraksha.mobile.data.local.dao.DeepScanResultDao;
import com.droidraksha.mobile.data.remote.DeepScanApi;
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
public final class DeepScanRepository_Factory implements Factory<DeepScanRepository> {
  private final Provider<DeepScanApi> apiProvider;

  private final Provider<DeepScanResultDao> daoProvider;

  public DeepScanRepository_Factory(Provider<DeepScanApi> apiProvider,
      Provider<DeepScanResultDao> daoProvider) {
    this.apiProvider = apiProvider;
    this.daoProvider = daoProvider;
  }

  @Override
  public DeepScanRepository get() {
    return newInstance(apiProvider.get(), daoProvider.get());
  }

  public static DeepScanRepository_Factory create(Provider<DeepScanApi> apiProvider,
      Provider<DeepScanResultDao> daoProvider) {
    return new DeepScanRepository_Factory(apiProvider, daoProvider);
  }

  public static DeepScanRepository newInstance(DeepScanApi api, DeepScanResultDao dao) {
    return new DeepScanRepository(api, dao);
  }
}
