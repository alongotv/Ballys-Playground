package com.alongo.ballysplayground.core.di

import com.alongo.ballysplayground.core.common.API_ENDPOINT
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import retrofit2.Retrofit
import retrofit2.converter.gson.GsonConverterFactory

@Module
@InstallIn(SingletonComponent::class)
class AppModule {

    @Provides
    fun provideRetrofit(): Retrofit = Retrofit.Builder().addConverterFactory(GsonConverterFactory.create())
        .baseUrl(API_ENDPOINT).build()
}
