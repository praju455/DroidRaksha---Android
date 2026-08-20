package com.droidraksha.mobile.worker;

import com.droidraksha.mobile.domain.engine.ScanOrchestrator;
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
public final class PackageInstallReceiver_MembersInjector implements MembersInjector<PackageInstallReceiver> {
  private final Provider<ScanOrchestrator> scanOrchestratorProvider;

  private final Provider<NotificationHelper> notificationHelperProvider;

  public PackageInstallReceiver_MembersInjector(Provider<ScanOrchestrator> scanOrchestratorProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    this.scanOrchestratorProvider = scanOrchestratorProvider;
    this.notificationHelperProvider = notificationHelperProvider;
  }

  public static MembersInjector<PackageInstallReceiver> create(
      Provider<ScanOrchestrator> scanOrchestratorProvider,
      Provider<NotificationHelper> notificationHelperProvider) {
    return new PackageInstallReceiver_MembersInjector(scanOrchestratorProvider, notificationHelperProvider);
  }

  @Override
  public void injectMembers(PackageInstallReceiver instance) {
    injectScanOrchestrator(instance, scanOrchestratorProvider.get());
    injectNotificationHelper(instance, notificationHelperProvider.get());
  }

  @InjectedFieldSignature("com.droidraksha.mobile.worker.PackageInstallReceiver.scanOrchestrator")
  public static void injectScanOrchestrator(PackageInstallReceiver instance,
      ScanOrchestrator scanOrchestrator) {
    instance.scanOrchestrator = scanOrchestrator;
  }

  @InjectedFieldSignature("com.droidraksha.mobile.worker.PackageInstallReceiver.notificationHelper")
  public static void injectNotificationHelper(PackageInstallReceiver instance,
      NotificationHelper notificationHelper) {
    instance.notificationHelper = notificationHelper;
  }
}
