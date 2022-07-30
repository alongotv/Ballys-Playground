package com.alongo.feature_mock_data_list

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class MockDataListViewModel @Inject constructor(private val mockDataDao: MockDataDao) :
    ViewModel() {

    var state: MutableState<State> = mutableStateOf(State.LoadingData)
        private set

    sealed interface Event {
        object RequestData : Event
        data class OnDataUpdated(val mockData: MockData, val newValue: String) : Event
        object OnDataCreated : Event
    }

    sealed interface State {
        object LoadingData : State
        data class DataReady(val data: List<MockData>) : State
    }

    fun handleEvent(event: Event) {
        when (val currentState = state.value) {
            is State.LoadingData -> reduce(event, currentState)
            is State.DataReady -> reduce(event, currentState)
        }
    }

    private fun reduce(event: Event, state: State.DataReady) {
        when (event) {
            Event.RequestData -> requestMockData()
            is Event.OnDataUpdated -> updateMockData(event.mockData, event.newValue)
            is Event.OnDataCreated -> createMockData()
        }
    }

    private fun createMockData() {
        viewModelScope.launch {
            mockDataDao.insertMockData(MockData(text = "Text"))
            state.value = State.DataReady(mockDataDao.getAllMockData())
        }
    }

    private fun updateMockData(mockData: MockData, text: String) {
        viewModelScope.launch {
            mockDataDao.updateMockData(mockData.copy(text = text))
            state.value = State.DataReady(mockDataDao.getAllMockData())
        }
    }

    private fun reduce(event: Event, state: State.LoadingData) {
        when (event) {
            Event.RequestData -> requestMockData()
            is Event.OnDataUpdated -> throw IllegalStateException("Cannot update the data while loading is in progress")
            is Event.OnDataCreated -> throw IllegalStateException("Cannot create the data while loading is in progress")
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
