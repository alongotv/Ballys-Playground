package com.alongo.ballysplayground.core.data.datasource.database.mock

import androidx.room.Dao
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alongo.ballysplayground.core.domain.entity.database.MockData

@Dao
interface MockDataDao {

    @Query("SELECT * FROM mockData")
    suspend fun getAllMockData(): List<MockData>

    @Insert
    suspend fun insertMockData(mockData: MockData)

    @Update
    suspend fun updateMockData(mockData: MockData)

    @Query("DELETE from mockData")
    suspend fun deleteAll()
}
