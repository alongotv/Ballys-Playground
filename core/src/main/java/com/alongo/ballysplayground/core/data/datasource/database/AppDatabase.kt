package com.alongo.ballysplayground.core.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.data.datasource.database.timezone.TimezoneDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData
import com.alongo.ballysplayground.core.domain.entity.database.Timezone

@Database(entities = [MockData::class, Timezone::class], version = 1, exportSchema = false)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mockDataDao(): MockDataDao
    abstract fun timezoneDao(): TimezoneDao
}
