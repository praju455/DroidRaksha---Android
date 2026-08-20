package com.droidraksha.mobile.domain.engine;

import android.content.Context;
import com.squareup.moshi.Moshi;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;
import javax.inject.Provider;

@ScopeMetadata("javax.inject.Singleton")
@QualifierMetadata("dagger.hilt.android.qualifiers.ApplicationContext")
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
public final class IndiaIocMatcher_Factory implements Factory<IndiaIocMatcher> {
  private final Provider<Context> contextProvider;

  private final Provider<Moshi> moshiProvider;

  public IndiaIocMatcher_Factory(Provider<Context> contextProvider, Provider<Moshi> moshiProvider) {
    this.contextProvider = contextProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public IndiaIocMatcher get() {
    return newInstance(contextProvider.get(), moshiProvider.get());
  }

  public static IndiaIocMatcher_Factory create(Provider<Context> contextProvider,
      Provider<Moshi> moshiProvider) {
    return new IndiaIocMatcher_Factory(contextProvider, moshiProvider);
  }

  public static IndiaIocMatcher newInstance(Context context, Moshi moshi) {
    return new IndiaIocMatcher(context, moshi);
  }
}
