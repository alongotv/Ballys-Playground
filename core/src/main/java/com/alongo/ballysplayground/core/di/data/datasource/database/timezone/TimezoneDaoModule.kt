package com.alongo.ballysplayground.core.di.data.datasource.database.timezone

import com.alongo.ballysplayground.core.data.datasource.database.AppDatabase
import com.alongo.ballysplayground.core.data.datasource.database.timezone.TimezoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn
class TimezoneDaoModule {
    @Provides
    fun provideTimezoneDao(appDatabase: AppDatabase): TimezoneDao = appDatabase.timezoneDao()
}
