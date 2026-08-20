package com.droidraksha.mobile.di;

import com.droidraksha.mobile.data.local.AppDatabase;
import com.droidraksha.mobile.data.local.dao.AppDao;
import dagger.internal.DaggerGenerated;
import dagger.internal.Factory;
import dagger.internal.Preconditions;
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
public final class DatabaseModule_ProvideAppDaoFactory implements Factory<AppDao> {
  private final Provider<AppDatabase> dbProvider;

  public DatabaseModule_ProvideAppDaoFactory(Provider<AppDatabase> dbProvider) {
    this.dbProvider = dbProvider;
  }

  @Override
  public AppDao get() {
    return provideAppDao(dbProvider.get());
  }

  public static DatabaseModule_ProvideAppDaoFactory create(Provider<AppDatabase> dbProvider) {
    return new DatabaseModule_ProvideAppDaoFactory(dbProvider);
  }

  public static AppDao provideAppDao(AppDatabase db) {
    return Preconditions.checkNotNullFromProvides(DatabaseModule.INSTANCE.provideAppDao(db));
  }
}
