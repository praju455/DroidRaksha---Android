package com.droidraksha.mobile.ui.screens.history;

import com.droidraksha.mobile.data.repository.AppRepository;
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
public final class ScanHistoryViewModel_Factory implements Factory<ScanHistoryViewModel> {
  private final Provider<AppRepository> repositoryProvider;

  public ScanHistoryViewModel_Factory(Provider<AppRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public ScanHistoryViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static ScanHistoryViewModel_Factory create(Provider<AppRepository> repositoryProvider) {
    return new ScanHistoryViewModel_Factory(repositoryProvider);
  }

  public static ScanHistoryViewModel newInstance(AppRepository repository) {
    return new ScanHistoryViewModel(repository);
  }
}
