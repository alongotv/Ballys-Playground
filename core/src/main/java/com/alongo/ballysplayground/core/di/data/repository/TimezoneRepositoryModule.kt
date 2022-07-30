package com.alongo.ballysplayground.core.di.data.repository

import com.alongo.ballysplayground.core.data.repository.TimezoneRepositoryImpl
import com.alongo.ballysplayground.core.domain.repository.TimezoneRepository
import dagger.Binds
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn

@Module
@InstallIn
abstract class TimezoneRepositoryModule {
    @Binds
    abstract fun bindTimezoneRepository(timezoneRepositoryImpl: TimezoneRepositoryImpl): TimezoneRepository

}