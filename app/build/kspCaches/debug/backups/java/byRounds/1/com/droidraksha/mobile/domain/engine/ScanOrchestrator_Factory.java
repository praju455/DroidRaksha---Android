package com.droidraksha.mobile.domain.engine;

import android.content.Context;
import com.droidraksha.mobile.data.repository.AppRepository;
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
public final class ScanOrchestrator_Factory implements Factory<ScanOrchestrator> {
  private final Provider<Context> contextProvider;

  private final Provider<AppInventoryScanner> inventoryScannerProvider;

  private final Provider<PermissionComboAnalyzer> permissionAnalyzerProvider;

  private final Provider<IndiaIocMatcher> iocMatcherProvider;

  private final Provider<YaraLiteMatcher> yaraLiteMatcherProvider;

  private final Provider<OnDeviceMLInference> mlInferenceProvider;

  private final Provider<NetworkTrafficMonitor> networkMonitorProvider;

  private final Provider<C2BeaconDetector> c2DetectorProvider;

  private final Provider<LocalRiskScorer> riskScorerProvider;

  private final Provider<AppRepository> repositoryProvider;

  private final Provider<Moshi> moshiProvider;

  public ScanOrchestrator_Factory(Provider<Context> contextProvider,
      Provider<AppInventoryScanner> inventoryScannerProvider,
      Provider<PermissionComboAnalyzer> permissionAnalyzerProvider,
      Provider<IndiaIocMatcher> iocMatcherProvider,
      Provider<YaraLiteMatcher> yaraLiteMatcherProvider,
      Provider<OnDeviceMLInference> mlInferenceProvider,
      Provider<NetworkTrafficMonitor> networkMonitorProvider,
      Provider<C2BeaconDetector> c2DetectorProvider, Provider<LocalRiskScorer> riskScorerProvider,
      Provider<AppRepository> repositoryProvider, Provider<Moshi> moshiProvider) {
    this.contextProvider = contextProvider;
    this.inventoryScannerProvider = inventoryScannerProvider;
    this.permissionAnalyzerProvider = permissionAnalyzerProvider;
    this.iocMatcherProvider = iocMatcherProvider;
    this.yaraLiteMatcherProvider = yaraLiteMatcherProvider;
    this.mlInferenceProvider = mlInferenceProvider;
    this.networkMonitorProvider = networkMonitorProvider;
    this.c2DetectorProvider = c2DetectorProvider;
    this.riskScorerProvider = riskScorerProvider;
    this.repositoryProvider = repositoryProvider;
    this.moshiProvider = moshiProvider;
  }

  @Override
  public ScanOrchestrator get() {
    return newInstance(contextProvider.get(), inventoryScannerProvider.get(), permissionAnalyzerProvider.get(), iocMatcherProvider.get(), yaraLiteMatcherProvider.get(), mlInferenceProvider.get(), networkMonitorProvider.get(), c2DetectorProvider.get(), riskScorerProvider.get(), repositoryProvider.get(), moshiProvider.get());
  }

  public static ScanOrchestrator_Factory create(Provider<Context> contextProvider,
      Provider<AppInventoryScanner> inventoryScannerProvider,
      Provider<PermissionComboAnalyzer> permissionAnalyzerProvider,
      Provider<IndiaIocMatcher> iocMatcherProvider,
      Provider<YaraLiteMatcher> yaraLiteMatcherProvider,
      Provider<OnDeviceMLInference> mlInferenceProvider,
      Provider<NetworkTrafficMonitor> networkMonitorProvider,
      Provider<C2BeaconDetector> c2DetectorProvider, Provider<LocalRiskScorer> riskScorerProvider,
      Provider<AppRepository> repositoryProvider, Provider<Moshi> moshiProvider) {
    return new ScanOrchestrator_Factory(contextProvider, inventoryScannerProvider, permissionAnalyzerProvider, iocMatcherProvider, yaraLiteMatcherProvider, mlInferenceProvider, networkMonitorProvider, c2DetectorProvider, riskScorerProvider, repositoryProvider, moshiProvider);
  }

  public static ScanOrchestrator newInstance(Context context, AppInventoryScanner inventoryScanner,
      PermissionComboAnalyzer permissionAnalyzer, IndiaIocMatcher iocMatcher,
      YaraLiteMatcher yaraLiteMatcher, OnDeviceMLInference mlInference,
      NetworkTrafficMonitor networkMonitor, C2BeaconDetector c2Detector, LocalRiskScorer riskScorer,
      AppRepository repository, Moshi moshi) {
    return new ScanOrchestrator(context, inventoryScanner, permissionAnalyzer, iocMatcher, yaraLiteMatcher, mlInference, networkMonitor, c2Detector, riskScorer, repository, moshi);
  }
}
