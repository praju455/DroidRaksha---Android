package com.droidraksha.mobile.data.remote;

import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;
import okhttp3.OkHttpClient;

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
public final class GroqAgentService_Factory implements Factory<GroqAgentService> {
  private final Provider<OkHttpClient> okHttpClientProvider;

  private final Provider<Moshi> moshiProvider;

  public GroqAgentService_Factory(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Moshi> moshiProvider) {
    this.okHttpClientProvider = okHttpClientProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public GroqAgentService get() {
    return newInstance(okHttpClientProvider.get(), moshiProvider.get());
  }

  public static GroqAgentService_Factory create(Provider<OkHttpClient> okHttpClientProvider,
      Provider<Moshi> moshiProvider) {
    return new GroqAgentService_Factory(okHttpClientProvider, moshiProvider);
  }

  public static GroqAgentService newInstance(OkHttpClient okHttpClient, Moshi moshi) {
    return new GroqAgentService(okHttpClient, moshi);
  }
}
