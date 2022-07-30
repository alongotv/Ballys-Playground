package com.alongo.ballysplayground.core.domain.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "mockData")
data class MockData(
    @PrimaryKey(autoGenerate = true) val id: Int? = null,
    val text: String
)
