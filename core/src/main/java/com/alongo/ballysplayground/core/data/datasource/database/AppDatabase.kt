package com.alongo.ballysplayground.core.data.datasource.database

import androidx.room.Database
import androidx.room.RoomDatabase
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData

@Database(entities = [MockData::class], version = 1)
abstract class AppDatabase : RoomDatabase() {
    abstract fun mockDataDao(): MockDataDao
}
