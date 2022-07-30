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

    var state: MutableState<MockDataListState> = mutableStateOf(MockDataListState.LoadingData)
    private set

    fun handleEvent(event: MockDataListEvent) {
        when (val currentState = state.value) {
            is MockDataListState.LoadingData -> reduce(event, currentState)
            is MockDataListState.DataReady -> reduce(event, currentState)
        }
    }

    private fun reduce(event: MockDataListEvent, state: MockDataListState.DataReady) {

    }

    private fun reduce(event: MockDataListEvent, state: MockDataListState.LoadingData) {
        when (event) {
            MockDataListEvent.RequestData -> requestMockData()
        }
    }

    private fun requestMockData() {
        viewModelScope.launch {
            val mockDataList = mockDataDao.getAllMockData()
            state.value = MockDataListState.DataReady(mockDataList)
        }
    }
}

sealed interface MockDataListEvent {
    object RequestData : MockDataListEvent
}

sealed interface MockDataListState {
    object LoadingData : MockDataListState
    data class DataReady(val data: List<MockData>) : MockDataListState
}