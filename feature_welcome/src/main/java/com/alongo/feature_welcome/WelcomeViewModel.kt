package com.alongo.feature_welcome

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavHostController
import com.alongo.nav_destinations.main.MainNavigationDestination
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.launch
import javax.inject.Inject

@HiltViewModel
class WelcomeViewModel @Inject constructor(private val navHostController: NavHostController) : ViewModel() {

    sealed interface Event {
        object OnOpenMockDataListClicked : Event
    }

    sealed interface State {
        object Idle : State
    }

    var state: MutableState<State> = mutableStateOf(State.Idle)
        private set

    fun handleEvent(event: Event) {
        when (val currentState = state.value) {
            is State.Idle -> {
                reduce(event, currentState)
            }
        }
    }

    private fun reduce(event: Event, state: State.Idle) {
        when(event) {
            is Event.OnOpenMockDataListClicked -> {
                navHostController.navigate(MainNavigationDestination.MockDataList)
            }
        }
    }

    fun foo() {
        viewModelScope.launch {

        }
    }
}
