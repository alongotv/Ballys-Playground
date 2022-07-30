package com.alongo.ballysplayground.core.domain.mapper.timezone

import com.alongo.ballysplayground.core.domain.entity.database.Timezone
import com.alongo.ballysplayground.core.domain.entity.networking.TimezoneDto
import com.alongo.ballysplayground.core.domain.mapper.DtoModelMapper

class TimezoneModelMapper : DtoModelMapper<TimezoneDto, Timezone>() {
    override fun fromDtoToModel(dto: TimezoneDto) = Timezone(dto.timezone, dto.name)
}
