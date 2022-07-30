package com.alongo.feature_welcome

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import dagger.hilt.android.lifecycle.HiltViewModel
import javax.inject.Inject
import kotlinx.coroutines.launch

@HiltViewModel
class WelcomeViewModel @Inject constructor() : ViewModel() {
    val welcomeText = "Welcome to playground!"

    fun foo() {
        viewModelScope.launch {

        }
    }
}
