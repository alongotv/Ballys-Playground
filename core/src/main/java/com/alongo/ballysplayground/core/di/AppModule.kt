package com.alongo.ballysplayground.core.di

import com.alongo.ballysplayground.core.common.API_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    @Named("TimezoneEndpoint")
    fun provideRetrofit(): Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_ENDPOINT).build()
}
