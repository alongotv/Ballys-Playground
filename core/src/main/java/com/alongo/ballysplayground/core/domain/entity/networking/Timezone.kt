package com.alongo.ballysplayground.core.domain.entity.networking

import androidx.room.Entity

@Entity(tableName = "timezone")
data class Timezone(val name: String, var timezone: String)
