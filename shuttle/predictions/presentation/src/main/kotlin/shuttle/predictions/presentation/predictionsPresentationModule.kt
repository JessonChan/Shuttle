package shuttle.predictions.presentation

import org.koin.androidx.viewmodel.dsl.viewModel
import org.koin.dsl.module
import shuttle.predictions.presentation.mapper.AppUiModelMapper
import shuttle.predictions.presentation.viewmodel.SuggestedAppsListViewModel

val predictionsPresentationModule = module {

    viewModel {
        SuggestedAppsListViewModel(
            appUiModelMapper = get(),
            observeSuggestedApps = get(),
            packageManager = get()
        )
    }
    factory { AppUiModelMapper(getSystemIconDrawableForApp = get()) }

}
