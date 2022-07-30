package com.alongo.feature_mock_data_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData
import com.alongo.ballysplayground.core.utils.throttleLatest
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.NonCancellable
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import kotlin.time.Duration.Companion.seconds

@HiltViewModel
class MockDataListViewModel @Inject constructor(private val mockDataDao: MockDataDao) :
    ViewModel() {

    var state: MutableState<State> = mutableStateOf(State.Idle)
        private set

    sealed interface Event {
        object OnScreenAppeared : Event
        object RequestData : Event
        data class OnDataUpdated(val mockData: MockData, val newValue: String) : Event
        data class OnDataDismissed(val mockData: MockData) : Event
        object OnDataCreated : Event
    }

    sealed interface State {
        object Idle : State
        object LoadingData : State
        data class DataReady(val data: List<MockData>) : State
    }

    fun handleEvent(event: Event) {
        when (val currentState = state.value) {
            is State.LoadingData -> reduce(event, currentState)
            is State.DataReady -> reduce(event, currentState)
            is State.Idle -> reduce(event, currentState)
        }
    }

    private fun reduce(
        event: Event,
        state: State.Idle
    ) {
        when (event) {
            Event.OnScreenAppeared -> subscribeToMockDataUpdates()
            Event.OnDataCreated -> Unit
            is Event.OnDataUpdated -> Unit
            Event.RequestData -> requestMockData()
            is Event.OnDataDismissed -> Unit
        }
    }

    private fun reduce(event: Event, state: State.DataReady) {
        when (event) {
            Event.RequestData -> requestMockData()
            is Event.OnDataUpdated -> enqueueMockDataUpdate(event, state)
            is Event.OnDataCreated -> createMockData()
            Event.OnScreenAppeared -> Unit
            is Event.OnDataDismissed -> removeMockData(event)
        }
    }

    private fun removeMockData(event: Event.OnDataDismissed) {
        viewModelScope.launch {
            withContext(NonCancellable) {
                mockDataDao.deleteMockData(event.mockData)
                val newData = mockDataDao.getAllMockData()
                state.value = State.DataReady(newData)
                println("Item has been removed")
            }
        }
    }

    private fun reduce(event: Event, state: State.LoadingData) {
        when (event) {
            Event.RequestData -> Unit
            is Event.OnDataUpdated -> throw IllegalStateException("Cannot update the data while loading is in progress")
            is Event.OnDataCreated -> throw IllegalStateException("Cannot create the data while loading is in progress")
            Event.OnScreenAppeared -> Unit
            is Event.OnDataDismissed -> throw IllegalStateException("Cannot dismiss the data while loading is in progress")
        }
    }

    private fun createMockData() {
        viewModelScope.launch {
            withContext(NonCancellable) {
                mockDataDao.insertMockData(MockData(text = ""))
                state.value = State.DataReady(mockDataDao.getAllMockData())
            }
        }
    }

    private val dataUpdateRequestFlow =
        MutableSharedFlow<Triple<MockData, String, State.DataReady>>()

    private fun enqueueMockDataUpdate(event: Event.OnDataUpdated, state: State.DataReady) {
        viewModelScope.launch {
            dataUpdateRequestFlow.emit(Triple(event.mockData, event.newValue, state))
        }
    }

    private fun subscribeToMockDataUpdates() {
        viewModelScope.launch {
            dataUpdateRequestFlow.onEach { triple ->
                val updatedDataList = (triple.third.data.map { mockData ->
                    if (triple.first == mockData) {
                        mockData.copy(text = triple.second)
                    } else mockData
                })

                state.value = State.DataReady(updatedDataList)
            }
                .throttleLatest(1.seconds)
                .collect { element ->
                    withContext(NonCancellable) {
                        mockDataDao.updateMockData(element.first.copy(text = element.second))
                    }
                }
        }
    }

    private fun requestMockData() {
        viewModelScope.launch {
            state.value = State.LoadingData
            val mockDataList = mockDataDao.getAllMockData()
            state.value = State.DataReady(mockDataList)
        }
    }
}
