package shuttle.settings.domain.usecase

import org.koin.core.annotation.Factory
import shuttle.apps.domain.model.AppId
import shuttle.settings.domain.SettingsRepository
import shuttle.stats.domain.StatsRepository

@Factory
class AddToBlacklist(
    private val settingsRepository: SettingsRepository,
    private val statRepository: StatsRepository
) {

    suspend operator fun invoke(appId: AppId) {
        settingsRepository.setBlacklisted(appId, blacklisted = true)
        statRepository.deleteCountersFor(appId)
    }
}
