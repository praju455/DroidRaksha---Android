package com.droidraksha.mobile.ui.screens.applist;

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
public final class AppListViewModel_Factory implements Factory<AppListViewModel> {
  private final Provider<AppRepository> repositoryProvider;

  public AppListViewModel_Factory(Provider<AppRepository> repositoryProvider) {
    this.repositoryProvider = repositoryProvider;
  }

  @Override
  public AppListViewModel get() {
    return newInstance(repositoryProvider.get());
  }

  public static AppListViewModel_Factory create(Provider<AppRepository> repositoryProvider) {
    return new AppListViewModel_Factory(repositoryProvider);
  }

  public static AppListViewModel newInstance(AppRepository repository) {
    return new AppListViewModel(repository);
  }
}
