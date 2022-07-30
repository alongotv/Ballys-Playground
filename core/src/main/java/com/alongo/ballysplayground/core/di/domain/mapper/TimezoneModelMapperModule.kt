package com.alongo.ballysplayground.core.di.domain.mapper

import com.alongo.ballysplayground.core.domain.entity.database.Timezone
import com.alongo.ballysplayground.core.domain.entity.networking.TimezoneDto
import com.alongo.ballysplayground.core.domain.mapper.DtoModelMapper
import com.alongo.ballysplayground.core.domain.mapper.timezone.TimezoneModelMapper
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class TimezoneModelMapperModule {

    @Provides
    fun provideTimezoneModelMapper(): DtoModelMapper<TimezoneDto, Timezone> = TimezoneModelMapper()
}
