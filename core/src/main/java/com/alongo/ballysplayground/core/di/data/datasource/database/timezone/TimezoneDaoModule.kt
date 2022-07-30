package com.alongo.ballysplayground.core.di.data.datasource.database.timezone

import com.alongo.ballysplayground.core.data.datasource.database.AppDatabase
import com.alongo.ballysplayground.core.data.datasource.database.timezone.TimezoneDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TimezoneDaoModule {
    @Provides
    fun provideTimezoneDao(appDatabase: AppDatabase): TimezoneDao = appDatabase.timezoneDao()
}
