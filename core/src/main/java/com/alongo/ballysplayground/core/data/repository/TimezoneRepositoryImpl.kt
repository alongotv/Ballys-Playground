package com.alongo.ballysplayground.core.data.repository

import com.alongo.ballysplayground.core.data.datasource.database.timezone.TimezoneDao
import com.alongo.ballysplayground.core.data.datasource.networking.timezone.TimezoneApi
import com.alongo.ballysplayground.core.domain.entity.networking.DisplayTimezone
import com.alongo.ballysplayground.core.domain.entity.networking.Timezone
import com.alongo.ballysplayground.core.domain.entity.networking.TimezoneDto
import com.alongo.ballysplayground.core.domain.repository.TimezoneRepository
import com.alongo.ballysplayground.core.domain.mapper.DtoModelMapper
import com.alongo.ballysplayground.core.domain.mapper.ModelDisplayMapper
import javax.inject.Inject

class TimezoneRepositoryImpl @Inject constructor(
    private val timezoneApi: TimezoneApi,
    private val timezoneDao: TimezoneDao,
    private val modelMapper: DtoModelMapper<TimezoneDto, Timezone>,
    private val displayMapper: ModelDisplayMapper<Timezone, DisplayTimezone>
) : TimezoneRepository {

    override suspend fun getTimezones(): List<DisplayTimezone> {
        val localTimezones = timezoneDao.getAll()
        return if (localTimezones.isEmpty()) {
            val remoteTimezones = timezoneApi.getTimezones()
            val timezones = remoteTimezones.map { timezoneDto ->
                modelMapper.fromDtoToModel(timezoneDto)
            }
            timezoneDao.insertAll(timezones)
            timezones.map { displayMapper.fromModelToDisplay(it) }
        } else {
            localTimezones.map { displayMapper.fromModelToDisplay(it) }
        }
    }
}
