package com.alongo.ballysplayground.core.di.data.datasource.networking

import com.alongo.ballysplayground.core.data.datasource.networking.timezone.TimezoneApi
import dagger.Module
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class TimezoneApiModule {
    fun provideTimezoneApi(retrofit: Retrofit) = retrofit.create(TimezoneApi::class.java)
}
