package com.droidraksha.mobile;

import android.app.Activity;
import android.app.Service;
import android.content.Context;
import android.view.View;
import androidx.fragment.app.Fragment;
import androidx.hilt.work.HiltWorkerFactory;
import androidx.hilt.work.WorkerAssistedFactory;
import androidx.hilt.work.WorkerFactoryModule_ProvideFactoryFactory;
import androidx.lifecycle.SavedStateHandle;
import androidx.lifecycle.ViewModel;
import androidx.work.ListenableWorker;
import androidx.work.WorkerParameters;
import com.droidraksha.mobile.data.local.AppDatabase;
import com.droidraksha.mobile.data.local.dao.AppDao;
import com.droidraksha.mobile.data.local.dao.DeepScanResultDao;
import com.droidraksha.mobile.data.local.dao.ScanHistoryDao;
import com.droidraksha.mobile.data.remote.DeepScanApi;
import com.droidraksha.mobile.data.remote.GroqAgentService;
import com.droidraksha.mobile.data.repository.AppRepository;
import com.droidraksha.mobile.data.repository.DeepScanRepository;
import com.droidraksha.mobile.di.AppModule_ProvideMoshiFactory;
import com.droidraksha.mobile.di.DatabaseModule_ProvideAppDaoFactory;
import com.droidraksha.mobile.di.DatabaseModule_ProvideDatabaseFactory;
import com.droidraksha.mobile.di.DatabaseModule_ProvideDeepScanResultDaoFactory;
import com.droidraksha.mobile.di.DatabaseModule_ProvideScanHistoryDaoFactory;
import com.droidraksha.mobile.di.NetworkModule_ProvideDeepScanApiFactory;
import com.droidraksha.mobile.di.NetworkModule_ProvideOkHttpClientFactory;
import com.droidraksha.mobile.di.NetworkModule_ProvideRetrofitFactory;
import com.droidraksha.mobile.domain.engine.AppInventoryScanner;
import com.droidraksha.mobile.domain.engine.C2BeaconDetector;
import com.droidraksha.mobile.domain.engine.IndiaIocMatcher;
import com.droidraksha.mobile.domain.engine.LocalRiskScorer;
import com.droidraksha.mobile.domain.engine.NetworkTrafficMonitor;
import com.droidraksha.mobile.domain.engine.OnDeviceMLInference;
import com.droidraksha.mobile.domain.engine.PermissionComboAnalyzer;
import com.droidraksha.mobile.domain.engine.ScanOrchestrator;
import com.droidraksha.mobile.domain.engine.YaraLiteMatcher;
import com.droidraksha.mobile.ui.MainActivity;
import com.droidraksha.mobile.ui.screens.appdetail.AppDetailViewModel;
import com.droidraksha.mobile.ui.screens.appdetail.AppDetailViewModel_HiltModules;
import com.droidraksha.mobile.ui.screens.applist.AppListViewModel;
import com.droidraksha.mobile.ui.screens.applist.AppListViewModel_HiltModules;
import com.droidraksha.mobile.ui.screens.dashboard.DashboardViewModel;
import com.droidraksha.mobile.ui.screens.dashboard.DashboardViewModel_HiltModules;
import com.droidraksha.mobile.ui.screens.deepscan.DeepScanViewModel;
import com.droidraksha.mobile.ui.screens.deepscan.DeepScanViewModel_HiltModules;
import com.droidraksha.mobile.ui.screens.history.ScanHistoryViewModel;
import com.droidraksha.mobile.ui.screens.history.ScanHistoryViewModel_HiltModules;
import com.droidraksha.mobile.ui.screens.settings.SettingsViewModel;
import com.droidraksha.mobile.ui.screens.settings.SettingsViewModel_HiltModules;
import com.droidraksha.mobile.worker.BackgroundScanWorker;
import com.droidraksha.mobile.worker.BackgroundScanWorker_AssistedFactory;
import com.droidraksha.mobile.worker.NotificationHelper;
import com.droidraksha.mobile.worker.PackageInstallReceiver;
import com.droidraksha.mobile.worker.PackageInstallReceiver_MembersInjector;
import com.squareup.moshi.Moshi;
import dagger.hilt.android.ActivityRetainedLifecycle;
import dagger.hilt.android.ViewModelLifecycle;
import dagger.hilt.android.internal.builders.ActivityComponentBuilder;
import dagger.hilt.android.internal.builders.ActivityRetainedComponentBuilder;
import dagger.hilt.android.internal.builders.FragmentComponentBuilder;
import dagger.hilt.android.internal.builders.ServiceComponentBuilder;
import dagger.hilt.android.internal.builders.ViewComponentBuilder;
import dagger.hilt.android.internal.builders.ViewModelComponentBuilder;
import dagger.hilt.android.internal.builders.ViewWithFragmentComponentBuilder;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories;
import dagger.hilt.android.internal.lifecycle.DefaultViewModelFactories_InternalFactoryFactory_Factory;
import dagger.hilt.android.internal.managers.ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory;
import dagger.hilt.android.internal.managers.SavedStateHandleHolder;
import dagger.hilt.android.internal.modules.ApplicationContextModule;
import dagger.hilt.android.internal.modules.ApplicationContextModule_ProvideContextFactory;
import dagger.internal.DaggerGenerated;
import dagger.internal.DoubleCheck;
import dagger.internal.IdentifierNameString;
import dagger.internal.KeepFieldType;
import dagger.internal.LazyClassKeyMap;
import dagger.internal.MapBuilder;
import dagger.internal.Preconditions;
import dagger.internal.Provider;
import dagger.internal.SingleCheck;
import java.util.Collections;
import java.util.Map;
import java.util.Set;
import javax.annotation.processing.Generated;
import okhttp3.OkHttpClient;
import retrofit2.Retrofit;

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
public final class DaggerDroidRakshaApp_HiltComponents_SingletonC {
  private DaggerDroidRakshaApp_HiltComponents_SingletonC() {
  }

  public static Builder builder() {
    return new Builder();
  }

  public static final class Builder {
    private ApplicationContextModule applicationContextModule;

    private Builder() {
    }

    public Builder applicationContextModule(ApplicationContextModule applicationContextModule) {
      this.applicationContextModule = Preconditions.checkNotNull(applicationContextModule);
      return this;
    }

    public DroidRakshaApp_HiltComponents.SingletonC build() {
      Preconditions.checkBuilderRequirement(applicationContextModule, ApplicationContextModule.class);
      return new SingletonCImpl(applicationContextModule);
    }
  }

  private static final class ActivityRetainedCBuilder implements DroidRakshaApp_HiltComponents.ActivityRetainedC.Builder {
    private final SingletonCImpl singletonCImpl;

    private SavedStateHandleHolder savedStateHandleHolder;

    private ActivityRetainedCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ActivityRetainedCBuilder savedStateHandleHolder(
        SavedStateHandleHolder savedStateHandleHolder) {
      this.savedStateHandleHolder = Preconditions.checkNotNull(savedStateHandleHolder);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ActivityRetainedC build() {
      Preconditions.checkBuilderRequirement(savedStateHandleHolder, SavedStateHandleHolder.class);
      return new ActivityRetainedCImpl(singletonCImpl, savedStateHandleHolder);
    }
  }

  private static final class ActivityCBuilder implements DroidRakshaApp_HiltComponents.ActivityC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private Activity activity;

    private ActivityCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ActivityCBuilder activity(Activity activity) {
      this.activity = Preconditions.checkNotNull(activity);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ActivityC build() {
      Preconditions.checkBuilderRequirement(activity, Activity.class);
      return new ActivityCImpl(singletonCImpl, activityRetainedCImpl, activity);
    }
  }

  private static final class FragmentCBuilder implements DroidRakshaApp_HiltComponents.FragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private Fragment fragment;

    private FragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public FragmentCBuilder fragment(Fragment fragment) {
      this.fragment = Preconditions.checkNotNull(fragment);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.FragmentC build() {
      Preconditions.checkBuilderRequirement(fragment, Fragment.class);
      return new FragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragment);
    }
  }

  private static final class ViewWithFragmentCBuilder implements DroidRakshaApp_HiltComponents.ViewWithFragmentC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private View view;

    private ViewWithFragmentCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;
    }

    @Override
    public ViewWithFragmentCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ViewWithFragmentC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewWithFragmentCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl, view);
    }
  }

  private static final class ViewCBuilder implements DroidRakshaApp_HiltComponents.ViewC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private View view;

    private ViewCBuilder(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
    }

    @Override
    public ViewCBuilder view(View view) {
      this.view = Preconditions.checkNotNull(view);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ViewC build() {
      Preconditions.checkBuilderRequirement(view, View.class);
      return new ViewCImpl(singletonCImpl, activityRetainedCImpl, activityCImpl, view);
    }
  }

  private static final class ViewModelCBuilder implements DroidRakshaApp_HiltComponents.ViewModelC.Builder {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private SavedStateHandle savedStateHandle;

    private ViewModelLifecycle viewModelLifecycle;

    private ViewModelCBuilder(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
    }

    @Override
    public ViewModelCBuilder savedStateHandle(SavedStateHandle handle) {
      this.savedStateHandle = Preconditions.checkNotNull(handle);
      return this;
    }

    @Override
    public ViewModelCBuilder viewModelLifecycle(ViewModelLifecycle viewModelLifecycle) {
      this.viewModelLifecycle = Preconditions.checkNotNull(viewModelLifecycle);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ViewModelC build() {
      Preconditions.checkBuilderRequirement(savedStateHandle, SavedStateHandle.class);
      Preconditions.checkBuilderRequirement(viewModelLifecycle, ViewModelLifecycle.class);
      return new ViewModelCImpl(singletonCImpl, activityRetainedCImpl, savedStateHandle, viewModelLifecycle);
    }
  }

  private static final class ServiceCBuilder implements DroidRakshaApp_HiltComponents.ServiceC.Builder {
    private final SingletonCImpl singletonCImpl;

    private Service service;

    private ServiceCBuilder(SingletonCImpl singletonCImpl) {
      this.singletonCImpl = singletonCImpl;
    }

    @Override
    public ServiceCBuilder service(Service service) {
      this.service = Preconditions.checkNotNull(service);
      return this;
    }

    @Override
    public DroidRakshaApp_HiltComponents.ServiceC build() {
      Preconditions.checkBuilderRequirement(service, Service.class);
      return new ServiceCImpl(singletonCImpl, service);
    }
  }

  private static final class ViewWithFragmentCImpl extends DroidRakshaApp_HiltComponents.ViewWithFragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl;

    private final ViewWithFragmentCImpl viewWithFragmentCImpl = this;

    private ViewWithFragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        FragmentCImpl fragmentCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;
      this.fragmentCImpl = fragmentCImpl;


    }
  }

  private static final class FragmentCImpl extends DroidRakshaApp_HiltComponents.FragmentC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final FragmentCImpl fragmentCImpl = this;

    private FragmentCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, ActivityCImpl activityCImpl,
        Fragment fragmentParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return activityCImpl.getHiltInternalFactoryFactory();
    }

    @Override
    public ViewWithFragmentComponentBuilder viewWithFragmentComponentBuilder() {
      return new ViewWithFragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl, fragmentCImpl);
    }
  }

  private static final class ViewCImpl extends DroidRakshaApp_HiltComponents.ViewC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl;

    private final ViewCImpl viewCImpl = this;

    private ViewCImpl(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
        ActivityCImpl activityCImpl, View viewParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;
      this.activityCImpl = activityCImpl;


    }
  }

  private static final class ActivityCImpl extends DroidRakshaApp_HiltComponents.ActivityC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ActivityCImpl activityCImpl = this;

    private ActivityCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, Activity activityParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;


    }

    @Override
    public void injectMainActivity(MainActivity mainActivity) {
    }

    @Override
    public DefaultViewModelFactories.InternalFactoryFactory getHiltInternalFactoryFactory() {
      return DefaultViewModelFactories_InternalFactoryFactory_Factory.newInstance(getViewModelKeys(), new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl));
    }

    @Override
    public Map<Class<?>, Boolean> getViewModelKeys() {
      return LazyClassKeyMap.<Boolean>of(MapBuilder.<String, Boolean>newMapBuilder(6).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel, AppDetailViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_applist_AppListViewModel, AppListViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel, DashboardViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel, DeepScanViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel, ScanHistoryViewModel_HiltModules.KeyModule.provide()).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_settings_SettingsViewModel, SettingsViewModel_HiltModules.KeyModule.provide()).build());
    }

    @Override
    public ViewModelComponentBuilder getViewModelComponentBuilder() {
      return new ViewModelCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public FragmentComponentBuilder fragmentComponentBuilder() {
      return new FragmentCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @Override
    public ViewComponentBuilder viewComponentBuilder() {
      return new ViewCBuilder(singletonCImpl, activityRetainedCImpl, activityCImpl);
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel = "com.droidraksha.mobile.ui.screens.dashboard.DashboardViewModel";

      static String com_droidraksha_mobile_ui_screens_applist_AppListViewModel = "com.droidraksha.mobile.ui.screens.applist.AppListViewModel";

      static String com_droidraksha_mobile_ui_screens_settings_SettingsViewModel = "com.droidraksha.mobile.ui.screens.settings.SettingsViewModel";

      static String com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel = "com.droidraksha.mobile.ui.screens.history.ScanHistoryViewModel";

      static String com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel = "com.droidraksha.mobile.ui.screens.appdetail.AppDetailViewModel";

      static String com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel = "com.droidraksha.mobile.ui.screens.deepscan.DeepScanViewModel";

      @KeepFieldType
      DashboardViewModel com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel2;

      @KeepFieldType
      AppListViewModel com_droidraksha_mobile_ui_screens_applist_AppListViewModel2;

      @KeepFieldType
      SettingsViewModel com_droidraksha_mobile_ui_screens_settings_SettingsViewModel2;

      @KeepFieldType
      ScanHistoryViewModel com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel2;

      @KeepFieldType
      AppDetailViewModel com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel2;

      @KeepFieldType
      DeepScanViewModel com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel2;
    }
  }

  private static final class ViewModelCImpl extends DroidRakshaApp_HiltComponents.ViewModelC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl;

    private final ViewModelCImpl viewModelCImpl = this;

    private Provider<AppDetailViewModel> appDetailViewModelProvider;

    private Provider<AppListViewModel> appListViewModelProvider;

    private Provider<DashboardViewModel> dashboardViewModelProvider;

    private Provider<DeepScanViewModel> deepScanViewModelProvider;

    private Provider<ScanHistoryViewModel> scanHistoryViewModelProvider;

    private Provider<SettingsViewModel> settingsViewModelProvider;

    private ViewModelCImpl(SingletonCImpl singletonCImpl,
        ActivityRetainedCImpl activityRetainedCImpl, SavedStateHandle savedStateHandleParam,
        ViewModelLifecycle viewModelLifecycleParam) {
      this.singletonCImpl = singletonCImpl;
      this.activityRetainedCImpl = activityRetainedCImpl;

      initialize(savedStateHandleParam, viewModelLifecycleParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandle savedStateHandleParam,
        final ViewModelLifecycle viewModelLifecycleParam) {
      this.appDetailViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 0);
      this.appListViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 1);
      this.dashboardViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 2);
      this.deepScanViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 3);
      this.scanHistoryViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 4);
      this.settingsViewModelProvider = new SwitchingProvider<>(singletonCImpl, activityRetainedCImpl, viewModelCImpl, 5);
    }

    @Override
    public Map<Class<?>, javax.inject.Provider<ViewModel>> getHiltViewModelMap() {
      return LazyClassKeyMap.<javax.inject.Provider<ViewModel>>of(MapBuilder.<String, javax.inject.Provider<ViewModel>>newMapBuilder(6).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel, ((Provider) appDetailViewModelProvider)).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_applist_AppListViewModel, ((Provider) appListViewModelProvider)).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel, ((Provider) dashboardViewModelProvider)).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel, ((Provider) deepScanViewModelProvider)).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel, ((Provider) scanHistoryViewModelProvider)).put(LazyClassKeyProvider.com_droidraksha_mobile_ui_screens_settings_SettingsViewModel, ((Provider) settingsViewModelProvider)).build());
    }

    @Override
    public Map<Class<?>, Object> getHiltViewModelAssistedMap() {
      return Collections.<Class<?>, Object>emptyMap();
    }

    @IdentifierNameString
    private static final class LazyClassKeyProvider {
      static String com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel = "com.droidraksha.mobile.ui.screens.appdetail.AppDetailViewModel";

      static String com_droidraksha_mobile_ui_screens_settings_SettingsViewModel = "com.droidraksha.mobile.ui.screens.settings.SettingsViewModel";

      static String com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel = "com.droidraksha.mobile.ui.screens.dashboard.DashboardViewModel";

      static String com_droidraksha_mobile_ui_screens_applist_AppListViewModel = "com.droidraksha.mobile.ui.screens.applist.AppListViewModel";

      static String com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel = "com.droidraksha.mobile.ui.screens.history.ScanHistoryViewModel";

      static String com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel = "com.droidraksha.mobile.ui.screens.deepscan.DeepScanViewModel";

      @KeepFieldType
      AppDetailViewModel com_droidraksha_mobile_ui_screens_appdetail_AppDetailViewModel2;

      @KeepFieldType
      SettingsViewModel com_droidraksha_mobile_ui_screens_settings_SettingsViewModel2;

      @KeepFieldType
      DashboardViewModel com_droidraksha_mobile_ui_screens_dashboard_DashboardViewModel2;

      @KeepFieldType
      AppListViewModel com_droidraksha_mobile_ui_screens_applist_AppListViewModel2;

      @KeepFieldType
      ScanHistoryViewModel com_droidraksha_mobile_ui_screens_history_ScanHistoryViewModel2;

      @KeepFieldType
      DeepScanViewModel com_droidraksha_mobile_ui_screens_deepscan_DeepScanViewModel2;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final ViewModelCImpl viewModelCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          ViewModelCImpl viewModelCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.viewModelCImpl = viewModelCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.droidraksha.mobile.ui.screens.appdetail.AppDetailViewModel 
          return (T) new AppDetailViewModel(singletonCImpl.appRepositoryProvider.get(), singletonCImpl.groqAgentServiceProvider.get());

          case 1: // com.droidraksha.mobile.ui.screens.applist.AppListViewModel 
          return (T) new AppListViewModel(singletonCImpl.appRepositoryProvider.get());

          case 2: // com.droidraksha.mobile.ui.screens.dashboard.DashboardViewModel 
          return (T) new DashboardViewModel(singletonCImpl.appRepositoryProvider.get(), singletonCImpl.scanOrchestratorProvider.get());

          case 3: // com.droidraksha.mobile.ui.screens.deepscan.DeepScanViewModel 
          return (T) new DeepScanViewModel(singletonCImpl.deepScanRepositoryProvider.get(), singletonCImpl.appRepositoryProvider.get());

          case 4: // com.droidraksha.mobile.ui.screens.history.ScanHistoryViewModel 
          return (T) new ScanHistoryViewModel(singletonCImpl.appRepositoryProvider.get());

          case 5: // com.droidraksha.mobile.ui.screens.settings.SettingsViewModel 
          return (T) new SettingsViewModel(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ActivityRetainedCImpl extends DroidRakshaApp_HiltComponents.ActivityRetainedC {
    private final SingletonCImpl singletonCImpl;

    private final ActivityRetainedCImpl activityRetainedCImpl = this;

    private Provider<ActivityRetainedLifecycle> provideActivityRetainedLifecycleProvider;

    private ActivityRetainedCImpl(SingletonCImpl singletonCImpl,
        SavedStateHandleHolder savedStateHandleHolderParam) {
      this.singletonCImpl = singletonCImpl;

      initialize(savedStateHandleHolderParam);

    }

    @SuppressWarnings("unchecked")
    private void initialize(final SavedStateHandleHolder savedStateHandleHolderParam) {
      this.provideActivityRetainedLifecycleProvider = DoubleCheck.provider(new SwitchingProvider<ActivityRetainedLifecycle>(singletonCImpl, activityRetainedCImpl, 0));
    }

    @Override
    public ActivityComponentBuilder activityComponentBuilder() {
      return new ActivityCBuilder(singletonCImpl, activityRetainedCImpl);
    }

    @Override
    public ActivityRetainedLifecycle getActivityRetainedLifecycle() {
      return provideActivityRetainedLifecycleProvider.get();
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final ActivityRetainedCImpl activityRetainedCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, ActivityRetainedCImpl activityRetainedCImpl,
          int id) {
        this.singletonCImpl = singletonCImpl;
        this.activityRetainedCImpl = activityRetainedCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // dagger.hilt.android.ActivityRetainedLifecycle 
          return (T) ActivityRetainedComponentManager_LifecycleModule_ProvideActivityRetainedLifecycleFactory.provideActivityRetainedLifecycle();

          default: throw new AssertionError(id);
        }
      }
    }
  }

  private static final class ServiceCImpl extends DroidRakshaApp_HiltComponents.ServiceC {
    private final SingletonCImpl singletonCImpl;

    private final ServiceCImpl serviceCImpl = this;

    private ServiceCImpl(SingletonCImpl singletonCImpl, Service serviceParam) {
      this.singletonCImpl = singletonCImpl;


    }
  }

  private static final class SingletonCImpl extends DroidRakshaApp_HiltComponents.SingletonC {
    private final ApplicationContextModule applicationContextModule;

    private final SingletonCImpl singletonCImpl = this;

    private Provider<AppInventoryScanner> appInventoryScannerProvider;

    private Provider<Moshi> provideMoshiProvider;

    private Provider<PermissionComboAnalyzer> permissionComboAnalyzerProvider;

    private Provider<IndiaIocMatcher> indiaIocMatcherProvider;

    private Provider<YaraLiteMatcher> yaraLiteMatcherProvider;

    private Provider<OnDeviceMLInference> onDeviceMLInferenceProvider;

    private Provider<NetworkTrafficMonitor> networkTrafficMonitorProvider;

    private Provider<C2BeaconDetector> c2BeaconDetectorProvider;

    private Provider<LocalRiskScorer> localRiskScorerProvider;

    private Provider<AppDatabase> provideDatabaseProvider;

    private Provider<AppRepository> appRepositoryProvider;

    private Provider<ScanOrchestrator> scanOrchestratorProvider;

    private Provider<NotificationHelper> notificationHelperProvider;

    private Provider<BackgroundScanWorker_AssistedFactory> backgroundScanWorker_AssistedFactoryProvider;

    private Provider<OkHttpClient> provideOkHttpClientProvider;

    private Provider<GroqAgentService> groqAgentServiceProvider;

    private Provider<Retrofit> provideRetrofitProvider;

    private Provider<DeepScanApi> provideDeepScanApiProvider;

    private Provider<DeepScanRepository> deepScanRepositoryProvider;

    private SingletonCImpl(ApplicationContextModule applicationContextModuleParam) {
      this.applicationContextModule = applicationContextModuleParam;
      initialize(applicationContextModuleParam);

    }

    private AppDao appDao() {
      return DatabaseModule_ProvideAppDaoFactory.provideAppDao(provideDatabaseProvider.get());
    }

    private ScanHistoryDao scanHistoryDao() {
      return DatabaseModule_ProvideScanHistoryDaoFactory.provideScanHistoryDao(provideDatabaseProvider.get());
    }

    private Map<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>> mapOfStringAndProviderOfWorkerAssistedFactoryOf(
        ) {
      return Collections.<String, javax.inject.Provider<WorkerAssistedFactory<? extends ListenableWorker>>>singletonMap("com.droidraksha.mobile.worker.BackgroundScanWorker", ((Provider) backgroundScanWorker_AssistedFactoryProvider));
    }

    private HiltWorkerFactory hiltWorkerFactory() {
      return WorkerFactoryModule_ProvideFactoryFactory.provideFactory(mapOfStringAndProviderOfWorkerAssistedFactoryOf());
    }

    private DeepScanResultDao deepScanResultDao() {
      return DatabaseModule_ProvideDeepScanResultDaoFactory.provideDeepScanResultDao(provideDatabaseProvider.get());
    }

    @SuppressWarnings("unchecked")
    private void initialize(final ApplicationContextModule applicationContextModuleParam) {
      this.appInventoryScannerProvider = DoubleCheck.provider(new SwitchingProvider<AppInventoryScanner>(singletonCImpl, 2));
      this.provideMoshiProvider = DoubleCheck.provider(new SwitchingProvider<Moshi>(singletonCImpl, 4));
      this.permissionComboAnalyzerProvider = DoubleCheck.provider(new SwitchingProvider<PermissionComboAnalyzer>(singletonCImpl, 3));
      this.indiaIocMatcherProvider = DoubleCheck.provider(new SwitchingProvider<IndiaIocMatcher>(singletonCImpl, 5));
      this.yaraLiteMatcherProvider = DoubleCheck.provider(new SwitchingProvider<YaraLiteMatcher>(singletonCImpl, 6));
      this.onDeviceMLInferenceProvider = DoubleCheck.provider(new SwitchingProvider<OnDeviceMLInference>(singletonCImpl, 7));
      this.networkTrafficMonitorProvider = DoubleCheck.provider(new SwitchingProvider<NetworkTrafficMonitor>(singletonCImpl, 8));
      this.c2BeaconDetectorProvider = DoubleCheck.provider(new SwitchingProvider<C2BeaconDetector>(singletonCImpl, 9));
      this.localRiskScorerProvider = DoubleCheck.provider(new SwitchingProvider<LocalRiskScorer>(singletonCImpl, 10));
      this.provideDatabaseProvider = DoubleCheck.provider(new SwitchingProvider<AppDatabase>(singletonCImpl, 12));
      this.appRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<AppRepository>(singletonCImpl, 11));
      this.scanOrchestratorProvider = DoubleCheck.provider(new SwitchingProvider<ScanOrchestrator>(singletonCImpl, 1));
      this.notificationHelperProvider = DoubleCheck.provider(new SwitchingProvider<NotificationHelper>(singletonCImpl, 13));
      this.backgroundScanWorker_AssistedFactoryProvider = SingleCheck.provider(new SwitchingProvider<BackgroundScanWorker_AssistedFactory>(singletonCImpl, 0));
      this.provideOkHttpClientProvider = DoubleCheck.provider(new SwitchingProvider<OkHttpClient>(singletonCImpl, 15));
      this.groqAgentServiceProvider = DoubleCheck.provider(new SwitchingProvider<GroqAgentService>(singletonCImpl, 14));
      this.provideRetrofitProvider = DoubleCheck.provider(new SwitchingProvider<Retrofit>(singletonCImpl, 18));
      this.provideDeepScanApiProvider = DoubleCheck.provider(new SwitchingProvider<DeepScanApi>(singletonCImpl, 17));
      this.deepScanRepositoryProvider = DoubleCheck.provider(new SwitchingProvider<DeepScanRepository>(singletonCImpl, 16));
    }

    @Override
    public void injectDroidRakshaApp(DroidRakshaApp droidRakshaApp) {
      injectDroidRakshaApp2(droidRakshaApp);
    }

    @Override
    public void injectPackageInstallReceiver(PackageInstallReceiver packageInstallReceiver) {
      injectPackageInstallReceiver2(packageInstallReceiver);
    }

    @Override
    public Set<Boolean> getDisableFragmentGetContextFix() {
      return Collections.<Boolean>emptySet();
    }

    @Override
    public ActivityRetainedComponentBuilder retainedComponentBuilder() {
      return new ActivityRetainedCBuilder(singletonCImpl);
    }

    @Override
    public ServiceComponentBuilder serviceComponentBuilder() {
      return new ServiceCBuilder(singletonCImpl);
    }

    private DroidRakshaApp injectDroidRakshaApp2(DroidRakshaApp instance) {
      DroidRakshaApp_MembersInjector.injectWorkerFactory(instance, hiltWorkerFactory());
      return instance;
    }

    private PackageInstallReceiver injectPackageInstallReceiver2(PackageInstallReceiver instance) {
      PackageInstallReceiver_MembersInjector.injectScanOrchestrator(instance, scanOrchestratorProvider.get());
      PackageInstallReceiver_MembersInjector.injectNotificationHelper(instance, notificationHelperProvider.get());
      return instance;
    }

    private static final class SwitchingProvider<T> implements Provider<T> {
      private final SingletonCImpl singletonCImpl;

      private final int id;

      SwitchingProvider(SingletonCImpl singletonCImpl, int id) {
        this.singletonCImpl = singletonCImpl;
        this.id = id;
      }

      @SuppressWarnings("unchecked")
      @Override
      public T get() {
        switch (id) {
          case 0: // com.droidraksha.mobile.worker.BackgroundScanWorker_AssistedFactory 
          return (T) new BackgroundScanWorker_AssistedFactory() {
            @Override
            public BackgroundScanWorker create(Context context, WorkerParameters params) {
              return new BackgroundScanWorker(context, params, singletonCImpl.scanOrchestratorProvider.get(), singletonCImpl.notificationHelperProvider.get());
            }
          };

          case 1: // com.droidraksha.mobile.domain.engine.ScanOrchestrator 
          return (T) new ScanOrchestrator(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.appInventoryScannerProvider.get(), singletonCImpl.permissionComboAnalyzerProvider.get(), singletonCImpl.indiaIocMatcherProvider.get(), singletonCImpl.yaraLiteMatcherProvider.get(), singletonCImpl.onDeviceMLInferenceProvider.get(), singletonCImpl.networkTrafficMonitorProvider.get(), singletonCImpl.c2BeaconDetectorProvider.get(), singletonCImpl.localRiskScorerProvider.get(), singletonCImpl.appRepositoryProvider.get(), singletonCImpl.provideMoshiProvider.get());

          case 2: // com.droidraksha.mobile.domain.engine.AppInventoryScanner 
          return (T) new AppInventoryScanner(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 3: // com.droidraksha.mobile.domain.engine.PermissionComboAnalyzer 
          return (T) new PermissionComboAnalyzer(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideMoshiProvider.get());

          case 4: // com.squareup.moshi.Moshi 
          return (T) AppModule_ProvideMoshiFactory.provideMoshi();

          case 5: // com.droidraksha.mobile.domain.engine.IndiaIocMatcher 
          return (T) new IndiaIocMatcher(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideMoshiProvider.get());

          case 6: // com.droidraksha.mobile.domain.engine.YaraLiteMatcher 
          return (T) new YaraLiteMatcher(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 7: // com.droidraksha.mobile.domain.engine.OnDeviceMLInference 
          return (T) new OnDeviceMLInference(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideMoshiProvider.get());

          case 8: // com.droidraksha.mobile.domain.engine.NetworkTrafficMonitor 
          return (T) new NetworkTrafficMonitor(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 9: // com.droidraksha.mobile.domain.engine.C2BeaconDetector 
          return (T) new C2BeaconDetector(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule), singletonCImpl.provideMoshiProvider.get());

          case 10: // com.droidraksha.mobile.domain.engine.LocalRiskScorer 
          return (T) new LocalRiskScorer();

          case 11: // com.droidraksha.mobile.data.repository.AppRepository 
          return (T) new AppRepository(singletonCImpl.appDao(), singletonCImpl.scanHistoryDao(), singletonCImpl.provideMoshiProvider.get());

          case 12: // com.droidraksha.mobile.data.local.AppDatabase 
          return (T) DatabaseModule_ProvideDatabaseFactory.provideDatabase(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 13: // com.droidraksha.mobile.worker.NotificationHelper 
          return (T) new NotificationHelper(ApplicationContextModule_ProvideContextFactory.provideContext(singletonCImpl.applicationContextModule));

          case 14: // com.droidraksha.mobile.data.remote.GroqAgentService 
          return (T) new GroqAgentService(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideMoshiProvider.get());

          case 15: // okhttp3.OkHttpClient 
          return (T) NetworkModule_ProvideOkHttpClientFactory.provideOkHttpClient();

          case 16: // com.droidraksha.mobile.data.repository.DeepScanRepository 
          return (T) new DeepScanRepository(singletonCImpl.provideDeepScanApiProvider.get(), singletonCImpl.deepScanResultDao());

          case 17: // com.droidraksha.mobile.data.remote.DeepScanApi 
          return (T) NetworkModule_ProvideDeepScanApiFactory.provideDeepScanApi(singletonCImpl.provideRetrofitProvider.get());

          case 18: // retrofit2.Retrofit 
          return (T) NetworkModule_ProvideRetrofitFactory.provideRetrofit(singletonCImpl.provideOkHttpClientProvider.get(), singletonCImpl.provideMoshiProvider.get());

          default: throw new AssertionError(id);
        }
      }
    }
  }
}
