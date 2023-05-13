package shuttle.settings.presentation.mapper

import arrow.core.Either
import arrow.core.Option
import arrow.core.continuations.either
import org.koin.core.annotation.Factory
import shuttle.apps.domain.model.AppId
import shuttle.apps.domain.model.AppModel
import shuttle.design.model.WidgetPreviewAppUiModel
import shuttle.icons.domain.error.GetSystemIconError
import shuttle.icons.domain.usecase.GetIconDrawableForApp

@Factory
class WidgetPreviewAppUiModelMapper(
    private val getIconDrawableForApp: GetIconDrawableForApp
) {

    suspend fun toUiModels(
        apps: Collection<AppModel>,
        iconPackId: Option<AppId>
    ): List<Either<GetSystemIconError, WidgetPreviewAppUiModel>> =
        apps.map { toUiModel(app = it, iconPackId = iconPackId) }

    private suspend fun toUiModel(
        app: AppModel,
        iconPackId: Option<AppId>
    ): Either<GetSystemIconError, WidgetPreviewAppUiModel> = either {
        WidgetPreviewAppUiModel(
            name = app.name.value,
            icon = getIconDrawableForApp(id = app.id, iconPackId = iconPackId).bind()
        )
    }
}
