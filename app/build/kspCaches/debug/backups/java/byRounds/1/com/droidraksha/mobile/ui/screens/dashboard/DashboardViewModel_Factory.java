package com.droidraksha.mobile.ui.screens.dashboard;

import com.droidraksha.mobile.data.repository.AppRepository;
import com.droidraksha.mobile.domain.engine.ScanOrchestrator;
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
public final class DashboardViewModel_Factory implements Factory<DashboardViewModel> {
  private final Provider<AppRepository> repositoryProvider;

  private final Provider<ScanOrchestrator> scanOrchestratorProvider;

  public DashboardViewModel_Factory(Provider<AppRepository> repositoryProvider,
      Provider<ScanOrchestrator> scanOrchestratorProvider) {
    this.repositoryProvider = repositoryProvider;
    this.scanOrchestratorProvider = scanOrchestratorProvider;
  }

  @Override
  public DashboardViewModel get() {
    return newInstance(repositoryProvider.get(), scanOrchestratorProvider.get());
  }

  public static DashboardViewModel_Factory create(Provider<AppRepository> repositoryProvider,
      Provider<ScanOrchestrator> scanOrchestratorProvider) {
    return new DashboardViewModel_Factory(repositoryProvider, scanOrchestratorProvider);
  }

  public static DashboardViewModel newInstance(AppRepository repository,
      ScanOrchestrator scanOrchestrator) {
    return new DashboardViewModel(repository, scanOrchestrator);
  }
}
