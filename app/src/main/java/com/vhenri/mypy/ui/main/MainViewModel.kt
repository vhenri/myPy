package com.vhenri.mypy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.vhenri.mypy.repo.ReplitDataRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val replitDataRepository: ReplitDataRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            null,
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _navigation: MutableStateFlow<NavDirections?> = MutableStateFlow(null)
    val navigation = _navigation.asStateFlow()

    fun executeSampleCode(){
        viewModelScope.launch {
            replitDataRepository.executeCode(
                null,
                command = "x = 'Hello World!'\nprint(x)"
            )
        }
    }

}


data class UiState(
        val errorString: String?
    )

