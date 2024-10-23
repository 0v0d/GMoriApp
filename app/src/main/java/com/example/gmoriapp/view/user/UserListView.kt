package com.example.gmoriapp.view.user

import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.mutableStateListOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.gmoriapp.model.User
import com.example.gmoriapp.view.component.UserListItemSkeleton
import com.example.gmoriapp.view.error.ErrorContent
import com.example.gmoriapp.viewmodel.UserListViewModel

@Composable
fun UserListView(
    navigateToUserDetail: (User) -> Unit,
    modifier: Modifier = Modifier,
    viewModel: UserListViewModel = hiltViewModel()
) {
    val uiState = viewModel.uiState.collectAsState()
    val users = remember { mutableStateListOf<User>() }
    LaunchedEffect(Unit) {
        viewModel.getUsers()
    }

    uiState.value.let { state ->
        when (state) {

            is UserListViewModel.UiState.Loading -> UserListLoading()
            is UserListViewModel.UiState.Success -> {
                UserListContent(
                    users = state.users,
                    navigateToUserDetail = navigateToUserDetail,
                    modifier = modifier
                )
                users.addAll(state.users)
            }

            is UserListViewModel.UiState.Error -> ErrorContent(
                message = state.message,
                onRetry = { viewModel.getUsers() },
                modifier = modifier,
            )
        }
    }
}

@Composable
fun UserListLoading(
    modifier: Modifier = Modifier,
    itemCount: Int = 5
) {
    LazyColumn(modifier = modifier) {
        items(itemCount) {
            UserListItemSkeleton()
        }
    }
}
