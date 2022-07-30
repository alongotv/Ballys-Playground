package com.alongo.ballysplayground.core.data.datasource.database.timezone

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import com.alongo.ballysplayground.core.domain.entity.networking.Timezone

@Dao
interface TimezoneDao {
    @Query("SELECT * FROM timezone")
    suspend fun getAll(): List<Timezone>

    @Insert
    suspend fun insertAll(timezones: List<Timezone>)

    @Query("DELETE FROM timezone")
    suspend fun deleteAll()
}