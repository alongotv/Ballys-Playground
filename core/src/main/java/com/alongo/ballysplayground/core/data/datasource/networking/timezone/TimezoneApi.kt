package com.alongo.ballysplayground.core.data.datasource.networking.timezone

import com.alongo.ballysplayground.core.domain.entity.networking.TimezoneDto
import retrofit2.http.GET

interface TimezoneApi {

    @GET("/timezone.json")
    suspend fun getTimezones(): List<TimezoneDto>
}
