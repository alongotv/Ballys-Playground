package com.alongo.ballysplayground.core.di.data.datasource.database

import android.content.Context
import androidx.room.Room
import androidx.room.RoomDatabase
import com.alongo.ballysplayground.core.data.datasource.database.AppDatabase
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.android.qualifiers.ApplicationContext
import dagger.hilt.components.SingletonComponent

@Module
@InstallIn(SingletonComponent::class)
class AppDatabaseModule {

    @Provides
    fun provideAppDatabase(@ApplicationContext context: Context): AppDatabase =
        Room.databaseBuilder(context, AppDatabase::class.java, "app-database.db").build()
}
