package com.droidraksha.mobile.ui.screens.appdetail;

import com.droidraksha.mobile.data.remote.GroqAgentService;
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
public final class AppDetailViewModel_Factory implements Factory<AppDetailViewModel> {
  private final Provider<AppRepository> repositoryProvider;

  private final Provider<GroqAgentService> groqAgentServiceProvider;

  public AppDetailViewModel_Factory(Provider<AppRepository> repositoryProvider,
      Provider<GroqAgentService> groqAgentServiceProvider) {
    this.repositoryProvider = repositoryProvider;
    this.groqAgentServiceProvider = groqAgentServiceProvider;
  }

  @Override
  public AppDetailViewModel get() {
    return newInstance(repositoryProvider.get(), groqAgentServiceProvider.get());
  }

  public static AppDetailViewModel_Factory create(Provider<AppRepository> repositoryProvider,
      Provider<GroqAgentService> groqAgentServiceProvider) {
    return new AppDetailViewModel_Factory(repositoryProvider, groqAgentServiceProvider);
  }

  public static AppDetailViewModel newInstance(AppRepository repository,
      GroqAgentService groqAgentService) {
    return new AppDetailViewModel(repository, groqAgentService);
  }
}
