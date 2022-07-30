package com.alongo.ballysplayground.core.di.data.datasource.database.mock

import com.alongo.ballysplayground.core.data.datasource.database.AppDatabase
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Inject

@Module
@InstallIn(SingletonComponent::class)
class MockDataDaoModule {
    @Provides
    fun provideMockDataDao(appDatabase: AppDatabase): MockDataDao = appDatabase.mockDataDao()
}
