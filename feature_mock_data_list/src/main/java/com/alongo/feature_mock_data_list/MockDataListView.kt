package com.alongo.feature_mock_data_list

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.runtime.Composable

@Composable
fun MockDataListView(viewModel: MockDataListViewModel) {
    when (val state = viewModel.state.value) {
        is MockDataListState.DataReady -> {
            LazyColumn {
                items(state.data) { item->
                    Text(text = item.text)
                }
            }
        }

        is MockDataListState.LoadingData -> {
            CircularProgressIndicator()
        }
    }
}
