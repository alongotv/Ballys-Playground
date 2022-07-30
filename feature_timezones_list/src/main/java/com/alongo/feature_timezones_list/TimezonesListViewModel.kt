package com.alongo.feature_timezones_list

import androidx.compose.runtime.mutableStateListOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.ballysplayground.core.domain.entity.networking.DisplayTimezone
import com.alongo.ballysplayground.core.domain.repository.TimezoneRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.TimeZone
import javax.inject.Inject
import kotlinx.coroutines.delay
import kotlinx.coroutines.launch

@HiltViewModel
class TimezonesListViewModel @Inject constructor(private val timezoneRepository: TimezoneRepository) : ViewModel() {
    val displayTimezones = mutableStateListOf<DisplayTimezone>()

    fun getTimezones() {
        viewModelScope.launch {
            displayTimezones.addAll(timezoneRepository.getTimezones())
            startTimeUpdates()
        }
    }

    private suspend fun startTimeUpdates() {
        val localTimezones = ArrayList(displayTimezones)

        displayTimezones.clear()
        displayTimezones.addAll(localTimezones.map {
            val simpleDateFormat = SimpleDateFormat("HH:mm:ss Z")
            simpleDateFormat.timeZone = TimeZone.getTimeZone(it.timezone)
            DisplayTimezone(name = it.name, timezone = it.timezone, currentTime = simpleDateFormat.format(Calendar.getInstance().time))
        })
        delay(1000L)
        startTimeUpdates()
    }
}
