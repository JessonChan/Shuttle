package shuttle.settings.domain.usecase

import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.combine
import org.koin.core.annotation.Factory
import shuttle.settings.domain.model.AppBlacklistSetting
import shuttle.utils.kotlin.inNoCase

@Factory
class SearchAppsBlacklistSettings(
    observeAppsBlacklistSettings: ObserveAppsBlacklistSettings
) {

    private val searchQuery = MutableStateFlow("")
    private val result = combine(
        observeAppsBlacklistSettings(),
        searchQuery
    ) { appBlacklistSettings, query ->
        appBlacklistSettings.filter { query inNoCase it.app.name.value }
    }

    operator fun invoke(query: String = ""): Flow<List<AppBlacklistSetting>> {
        searchQuery.value = query
        return result
    }
}
