package com.alongo.feature_mock_data_list

import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import com.alongo.ballysplayground.core.data.datasource.database.mock.MockDataDao
import com.alongo.ballysplayground.core.domain.entity.database.MockData
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject

@HiltViewModel
class MockDataListViewModel @Inject constructor(private val mockDataDao: MockDataDao) : ViewModel() {
    var state = mutableStateOf(MockDataListState.LoadingData)

    fun handleEvent(event: MockDataListEvent) {
        when (event) {
            MockDataListEvent.RequestData -> {
                reduce(event, state.value)
            }
        }
    }

    private fun reduce(event: MockDataListEvent, state: MockDataListState.LoadingData) {

    }
}

sealed interface MockDataListEvent {
    object RequestData : MockDataListEvent
}

sealed interface MockDataListState {
    object LoadingData : MockDataListState
    data class DataReady(val data: List<MockData>) : MockDataListState
}