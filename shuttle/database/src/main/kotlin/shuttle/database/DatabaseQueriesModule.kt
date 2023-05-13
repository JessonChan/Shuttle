package shuttle.database

import org.koin.core.annotation.Factory
import org.koin.core.annotation.Module

@Module
internal class DatabaseQueriesModule {

    @Factory
    fun appQueries(database: Database) = database.appQueries

    @Factory
    fun appBlacklistSettingQueries(database: Database) = database.appBlacklistSettingQueries

    @Factory
    fun lastLocationQueries(database: Database) = database.lastLocationQueries

    @Factory
    fun statQueries(database: Database) = database.statQueries
}