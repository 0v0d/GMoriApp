package com.example.gmoriapp.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.gmoriapp.view.error.ErrorHandler
import com.example.gmoriapp.model.User
import com.example.gmoriapp.model.toUser
import com.example.gmoriapp.repository.UserRepository
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject
import com.example.gmoriapp.repository.Result
import dagger.hilt.android.lifecycle.HiltViewModel

@HiltViewModel
class UserListViewModel @Inject constructor(
    private val repository: UserRepository,
    private val errorHandler: ErrorHandler
) : ViewModel() {

    private val _uiState = MutableStateFlow<UiState>(UiState.Loading)
    val uiState = _uiState.asStateFlow()

    sealed class UiState {
        data object Loading : UiState()
        data class Success(val users: List<User>) : UiState()
        data class Error(val message: String) : UiState()
    }

    fun getUsers() {
        viewModelScope.launch {
            _uiState.value = UiState.Loading
            fetchUsers()
        }
    }

    private suspend fun fetchUsers() {
        when (val result = repository.getUserList()) {
            is Result.Success -> _uiState.value = UiState.Success(
                result.data.map { it.toUser() }
            )
            is Result.Error -> _uiState.value =
                UiState.Error(errorHandler.getErrorMessage(result.exception))
        }
    }
}