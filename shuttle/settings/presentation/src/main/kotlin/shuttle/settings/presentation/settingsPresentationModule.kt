package shuttle.settings.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import shuttle.settings.presentation.mapper.AppBlacklistSettingUiModelMapper
import shuttle.settings.presentation.mapper.IconPackSettingsUiModelMapper
import shuttle.settings.presentation.mapper.WidgetPreviewAppUiModelMapper
import shuttle.settings.presentation.mapper.WidgetSettingsUiModelMapper
import shuttle.settings.presentation.viewmodel.AboutViewModel
import shuttle.settings.presentation.viewmodel.BlacklistSettingsViewModel
import shuttle.settings.presentation.viewmodel.IconPacksSettingsViewModel
import shuttle.settings.presentation.viewmodel.SettingsViewModel
import shuttle.settings.presentation.viewmodel.WidgetLayoutViewModel

val settingsPresentationModule = module {

    viewModel {
        AboutViewModel(getProductPrice = get(), launchPurchaseFlow = get(), logger = get())
    }
    viewModel {
        BlacklistSettingsViewModel(
            appUiModelMapper = get(),
            addToBlacklist = get(),
            removeFromBlacklist = get(),
            searchAppsBlacklistSettings = get()
        )
    }
    viewModel {
        IconPacksSettingsViewModel(
            iconPackSettingsMapper = get(),
            observeInstalledIconPacks = get(),
            observeCurrentIconPack = get(),
            setCurrentIconPack = get()
        )
    }
    viewModel {
        SettingsViewModel(
            getAppVersion = get(),
            hasAllLocationPermissions = get(),
            isLaunchCounterServiceEnabled = get(),
            resetOnboardingShown = get()
        )
    }
    viewModel {
        WidgetLayoutViewModel(
            observeAllInstalledApps = get(),
            observeCurrentIconPack = get(),
            observeWidgetSettings = get(),
            updateWidgetSettings = get(),
            widgetPreviewAppUiModelMapper = get(),
            widgetSettingsUiModelMapper = get()
        )
    }
    factory { AppBlacklistSettingUiModelMapper(getSystemIconDrawableForApp = get()) }
    factory { IconPackSettingsUiModelMapper(getSystemIconDrawableForApp = get()) }
    factory { WidgetPreviewAppUiModelMapper(getIconDrawableForApp = get()) }
    factory { WidgetSettingsUiModelMapper() }
}
