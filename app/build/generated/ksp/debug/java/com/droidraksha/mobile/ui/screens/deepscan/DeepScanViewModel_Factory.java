package com.droidraksha.mobile.ui.screens.deepscan;

import com.droidraksha.mobile.data.repository.AppRepository;
import com.droidraksha.mobile.data.repository.DeepScanRepository;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
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
public final class DeepScanViewModel_Factory implements Factory<DeepScanViewModel> {
  private final Provider<DeepScanRepository> deepScanRepositoryProvider;

  private final Provider<AppRepository> appRepositoryProvider;

  public DeepScanViewModel_Factory(Provider<DeepScanRepository> deepScanRepositoryProvider,
      Provider<AppRepository> appRepositoryProvider) {
    this.deepScanRepositoryProvider = deepScanRepositoryProvider;
    this.appRepositoryProvider = appRepositoryProvider;
  }

  @Override
  public DeepScanViewModel get() {
    return newInstance(deepScanRepositoryProvider.get(), appRepositoryProvider.get());
  }

  public static DeepScanViewModel_Factory create(
      Provider<DeepScanRepository> deepScanRepositoryProvider,
      Provider<AppRepository> appRepositoryProvider) {
    return new DeepScanViewModel_Factory(deepScanRepositoryProvider, appRepositoryProvider);
  }

  public static DeepScanViewModel newInstance(DeepScanRepository deepScanRepository,
      AppRepository appRepository) {
    return new DeepScanViewModel(deepScanRepository, appRepository);
  }
}
