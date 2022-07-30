package com.alongo.ballysplayground.core.di.data.datasource.networking

import com.alongo.ballysplayground.core.data.datasource.networking.timezone.TimezoneApi
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import retrofit2.Retrofit

@Module
@InstallIn(SingletonComponent::class)
class TimezoneApiModule {
    @Provides
    fun provideTimezoneApi(
        @Named("TimezoneEndpoint")
        retrofit: Retrofit
    ): TimezoneApi = retrofit.create(TimezoneApi::class.java)
}
