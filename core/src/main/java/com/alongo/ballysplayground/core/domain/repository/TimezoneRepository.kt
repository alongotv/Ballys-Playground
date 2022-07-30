package com.alongo.ballysplayground.core.domain.repository

import com.alongo.ballysplayground.core.domain.entity.networking.DisplayTimezone

interface TimezoneRepository {
   suspend fun getTimezones(): List<DisplayTimezone>
}
