package com.vhenri.mypy.ui.main

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavDirections
import com.github.michaelbull.result.mapEither
import com.vhenri.mypy.models.getUserExceptionMsg
import com.vhenri.mypy.repo.ReplitDataRepository
import javax.inject.Inject
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.flow.update
import kotlinx.coroutines.launch

class MainViewModel @Inject constructor(private val replitDataRepository: ReplitDataRepository) : ViewModel() {
    private val _uiState: MutableStateFlow<UiState> = MutableStateFlow(
        UiState(
            null,
            null,
            null,
        )
    )
    val uiState = _uiState.asStateFlow()

    private val _isLoading: MutableStateFlow<Boolean> = MutableStateFlow(false)
    val isLoading = _isLoading.asStateFlow()

    private val _navigation: MutableStateFlow<NavDirections?> = MutableStateFlow(null)
    val navigation = _navigation.asStateFlow()

    fun setInputText(text: String){
        _uiState.update {
            it.copy(
                commandString = text
            )
        }
    }

    fun executeCode(){
        val commandString = uiState.value.commandString
        if (!commandString.isNullOrEmpty()) {
            _isLoading.update { true }
            viewModelScope.launch {
                replitDataRepository.executeCode(
                    null,
                    command = commandString
                ).mapEither(
                    success = { response ->
                        _uiState.update {
                            UiState(
                                commandString = null,
                                executedCommandString = commandString,
                                executedResponse = response?.result
                            )
                        }
                    },
                    failure = { exception ->
                        _uiState.update {
                            UiState(
                                commandString = commandString,
                                executedCommandString = commandString,
                                executedResponse = "Oh no! Something went wrong. Try again?\n${exception.getUserExceptionMsg()} "
                            )
                        }
                    }
                )
                _isLoading.update { false }
            }
        }
    }

    fun clearConsoleText(){
        _uiState.update {
            it.copy(
                executedCommandString = null,
                executedResponse = null
            )
        }
    }

    fun initInput(){
        _uiState.update {
            UiState(
                commandString = "x = 'Hello World!'\nprint(x)",
                executedCommandString = null,
                executedResponse = null
            )
        }
    }

}


data class UiState(
        val commandString: String?,
        val executedCommandString: String?,
        val executedResponse: String?
    )

