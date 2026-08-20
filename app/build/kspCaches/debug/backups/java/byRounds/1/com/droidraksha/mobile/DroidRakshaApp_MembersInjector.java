package com.droidraksha.mobile;

import androidx.hilt.work.HiltWorkerFactory;
import dagger.MembersInjector;
import dagger.internal.DaggerGenerated;
import dagger.internal.InjectedFieldSignature;
import dagger.internal.QualifierMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

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
public final class DroidRakshaApp_MembersInjector implements MembersInjector<DroidRakshaApp> {
  private final Provider<HiltWorkerFactory> workerFactoryProvider;

  public DroidRakshaApp_MembersInjector(Provider<HiltWorkerFactory> workerFactoryProvider) {
    this.workerFactoryProvider = workerFactoryProvider;
  }

  public static MembersInjector<DroidRakshaApp> create(
      Provider<HiltWorkerFactory> workerFactoryProvider) {
    return new DroidRakshaApp_MembersInjector(workerFactoryProvider);
  }

  @Override
  public void injectMembers(DroidRakshaApp instance) {
    injectWorkerFactory(instance, workerFactoryProvider.get());
  }

  @InjectedFieldSignature("com.droidraksha.mobile.DroidRakshaApp.workerFactory")
  public static void injectWorkerFactory(DroidRakshaApp instance, HiltWorkerFactory workerFactory) {
    instance.workerFactory = workerFactory;
  }
}
