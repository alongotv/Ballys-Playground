package com.alongo.ballysplayground.core.domain.entity.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "timezone")
data class Timezone(@PrimaryKey(autoGenerate = true) val id: Int? = null, val name: String, var timezone: String)
