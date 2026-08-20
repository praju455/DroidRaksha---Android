package com.droidraksha.mobile.domain.engine;

import android.content.Context;
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
public final class YaraLiteMatcher_Factory implements Factory<YaraLiteMatcher> {
  private final Provider<Context> contextProvider;

  public YaraLiteMatcher_Factory(Provider<Context> contextProvider) {
    this.contextProvider = contextProvider;
  }

  @Override
  public YaraLiteMatcher get() {
    return newInstance(contextProvider.get());
  }

  public static YaraLiteMatcher_Factory create(Provider<Context> contextProvider) {
    return new YaraLiteMatcher_Factory(contextProvider);
  }

  public static YaraLiteMatcher newInstance(Context context) {
    return new YaraLiteMatcher(context);
  }
}
