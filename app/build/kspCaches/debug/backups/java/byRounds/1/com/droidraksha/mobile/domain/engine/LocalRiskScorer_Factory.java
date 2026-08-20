package com.droidraksha.mobile.domain.engine;

import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.QualifierMetadata;
import dagger.internal.ScopeMetadata;
import javax.annotation.processing.Generated;

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
public final class LocalRiskScorer_Factory implements Factory<LocalRiskScorer> {
  @Override
  public LocalRiskScorer get() {
    return newInstance();
  }

  public static LocalRiskScorer_Factory create() {
    return InstanceHolder.INSTANCE;
  }

  public static LocalRiskScorer newInstance() {
    return new LocalRiskScorer();
  }

  private static final class InstanceHolder {
    private static final LocalRiskScorer_Factory INSTANCE = new LocalRiskScorer_Factory();
  }
}
