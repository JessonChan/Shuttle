package shuttle.stats.data

import kotlinx.coroutines.Dispatchers
import org.koin.androidx.workmanager.dsl.worker
import org.koin.dsl.module
import shuttle.stats.data.mapper.DatabaseDateAndTimeMapper
import shuttle.stats.data.usecase.DeleteOldStats
import shuttle.stats.data.usecase.SortAppStats
import shuttle.stats.data.worker.DeleteOldStatsWorker
import shuttle.stats.domain.StatsRepository
import kotlin.time.DurationUnit.DAYS
import kotlin.time.DurationUnit.HOURS
import kotlin.time.toDuration

val statsDataModule = module {

    factory { DatabaseDateAndTimeMapper() }
    factory { DeleteOldStats(databaseDateAndTimeMapper = get(), observeCurrentDateTime = get(), statDataSource = get()) }
    worker { DeleteOldStatsWorker(appContext = get(), params = get(), deleteOldStats = get()) }
    factory {
        DeleteOldStatsWorker.Scheduler(
            workManager = get(),
            repeatInterval = Interval.DeleteOldStats.Default,
            flexInterval = Interval.DeleteOldStats.Flex
        )
    }
    factory {
        SortAppStats(
            databaseDateAndTimeMapper = get(),
            computationDispatcher = Dispatchers.Default,
            observeCurrentCoordinates = get()
        )
    }
    factory<StatsRepository> {
        StatsRepositoryImpl(
            appsRepository = get(),
            databaseDateAndTimeMapper = get(),
            deleteOldStatsScheduler = get(),
            statDataSource = get(),
            sortAppStats = get()
        )
    }
}

private object Interval {

    object DeleteOldStats {

        val Default = 12.toDuration(HOURS)
        val Flex = 1.toDuration(DAYS)
    }
}
