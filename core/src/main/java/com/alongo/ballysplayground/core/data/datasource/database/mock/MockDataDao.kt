package com.alongo.ballysplayground.core.data.datasource.database.mock

import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import com.alongo.ballysplayground.core.domain.entity.database.MockData

interface MockDataDao {
    @Insert
    suspend fun insertMockDao(mockData: MockData)

    @Update
    suspend fun updateMockDao(mockData: MockData)

    @Query("DELETE from mockData")
    suspend fun deleteAll()
}
