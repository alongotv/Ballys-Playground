package com.alongo.feature_mock_data_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData
import com.alongo.ballysplayground.core.utils.throttleFirst
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableSharedFlow
import kotlinx.coroutines.flow.onEach
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MockDataListViewModel @Inject constructor(private val mockDataDao: MockDataDao) :
    ViewModel() {

    var state: MutableState<State> = mutableStateOf(State.Idle)
        private set

    sealed interface Event {
        object OnScreenAppeared : Event
        object RequestData : Event
        data class OnDataUpdated(val mockData: MockData, val newValue: String) : Event
        object OnDataCreated : Event
    }

    sealed interface State {
        object Idle : State
        object LoadingData : State
        data class DataReady(val data: List<MockData>) : State
    }

    fun handleEvent(event: Event) {
        viewModelScope.launch {
            when (val currentState = state.value) {
                is State.LoadingData -> reduce(event, currentState)
                is State.DataReady -> reduce(event, currentState)
                is State.Idle -> reduce(event, currentState)
            }
        }
    }

    private suspend fun reduce(
        event: Event,
        state: State.Idle
    ) {
        when (event) {
            Event.OnScreenAppeared -> subscribeToMockDataUpdates()
            Event.OnDataCreated -> Unit
            is Event.OnDataUpdated -> Unit
            Event.RequestData -> requestMockData()
        }
    }

    private suspend fun reduce(event: Event, state: State.DataReady) {
        when (event) {
            Event.RequestData -> requestMockData()
            is Event.OnDataUpdated -> enqueueMockDataUpdate(event, state)
            is Event.OnDataCreated -> createMockData()
            Event.OnScreenAppeared -> Unit
        }
    }

    private fun reduce(event: Event, state: State.LoadingData) {
        when (event) {
            Event.RequestData -> Unit
            is Event.OnDataUpdated -> throw IllegalStateException("Cannot update the data while loading is in progress")
            is Event.OnDataCreated -> throw IllegalStateException("Cannot create the data while loading is in progress")
            Event.OnScreenAppeared -> Unit
        }
    }

    private suspend fun createMockData() {
        mockDataDao.insertMockData(MockData(text = "Text"))
        state.value = State.DataReady(mockDataDao.getAllMockData())
    }

    private val dataUpdateRequestFlow =
        MutableSharedFlow<Triple<MockData, String, State.DataReady>>()

    private suspend fun enqueueMockDataUpdate(event: Event.OnDataUpdated, state: State.DataReady) {
        dataUpdateRequestFlow.emit(Triple(event.mockData, event.newValue, state))
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
                .throttleFirst(150).collect { element ->
                    mockDataDao.updateMockData(element.first.copy(text = element.second))
                }
        }
    }

    private suspend fun requestMockData() {
        state.value = State.LoadingData
        val mockDataList = mockDataDao.getAllMockData()
        state.value = State.DataReady(mockDataList)
    }
}
