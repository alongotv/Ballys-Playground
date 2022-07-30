package com.alongo.ballysplayground.core.di.domain.mapper

import com.alongo.ballysplayground.core.domain.entity.networking.DisplayTimezone
import com.alongo.ballysplayground.core.domain.entity.networking.Timezone
import com.alongo.ballysplayground.core.domain.mapper.ModelDisplayMapper
import com.alongo.ballysplayground.core.domain.mapper.timezone.TimezoneDisplayMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TimezoneDisplayMapperModule {
    @Provides
    fun provideTimezoneDisplayMapper(): ModelDisplayMapper<Timezone, DisplayTimezone> = TimezoneDisplayMapper()
}
