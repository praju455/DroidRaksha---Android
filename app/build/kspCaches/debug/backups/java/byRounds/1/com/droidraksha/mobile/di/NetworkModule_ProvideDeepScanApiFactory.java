package com.droidraksha.mobile.di;

import com.droidraksha.mobile.data.remote.DeepScanApi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import retrofit2.Retrofit;

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
public final class NetworkModule_ProvideDeepScanApiFactory implements Factory<DeepScanApi> {
  private final Provider<Retrofit> retrofitProvider;

  public NetworkModule_ProvideDeepScanApiFactory(Provider<Retrofit> retrofitProvider) {
    this.retrofitProvider = retrofitProvider;
  }

  @Override
  public DeepScanApi get() {
    return provideDeepScanApi(retrofitProvider.get());
  }

  public static NetworkModule_ProvideDeepScanApiFactory create(
      Provider<Retrofit> retrofitProvider) {
    return new NetworkModule_ProvideDeepScanApiFactory(retrofitProvider);
  }

  public static DeepScanApi provideDeepScanApi(Retrofit retrofit) {
    return Preconditions.checkNotNullFromProvides(NetworkModule.INSTANCE.provideDeepScanApi(retrofit));
  }
}
