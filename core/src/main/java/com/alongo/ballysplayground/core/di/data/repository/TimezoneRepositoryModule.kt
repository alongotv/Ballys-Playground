package com.alongo.ballysplayground.core.di.data.repository

import com.alongo.ballysplayground.core.data.repository.TimezoneRepositoryImpl
import com.alongo.ballysplayground.core.domain.repository.TimezoneRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
abstract class TimezoneRepositoryModule {
    @Binds
    abstract fun bindTimezoneRepository(timezoneRepositoryImpl: TimezoneRepositoryImpl): TimezoneRepository

}