package com.alongo.ballysplayground.core.domain.mapper.timezone

import com.alongo.ballysplayground.core.domain.entity.networking.DisplayTimezone
import com.alongo.ballysplayground.core.domain.entity.networking.Timezone
import com.alongo.ballysplayground.core.domain.mapper.ModelDisplayMapper

class TimezoneDisplayMapper : ModelDisplayMapper<Timezone, DisplayTimezone>() {
    override fun fromModelToDisplay(model: Timezone) = DisplayTimezone(model.name, model.timezone)
}
