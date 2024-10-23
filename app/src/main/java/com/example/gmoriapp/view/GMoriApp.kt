package com.example.gmoriapp.view

import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.gmoriapp.R
import com.example.gmoriapp.navigation.GMoriAppNavHost
import com.example.gmoriapp.view.component.AppTopBar

@Composable
fun GMoriApp(
    modifier: Modifier = Modifier
) {
    val navController = rememberNavController()
    val backStackEntry by navController.currentBackStackEntryAsState()
    val currentRoute = backStackEntry?.destination?.route ?: GMoriAppScreen.UserList.name

    val currentScreen = when {
        currentRoute.startsWith(GMoriAppScreen.UserList.name) -> GMoriAppScreen.UserList
        currentRoute.startsWith(GMoriAppScreen.UserDetail.name) -> GMoriAppScreen.UserDetail
        else -> GMoriAppScreen.UserList
    }

    Scaffold(
        modifier = modifier,
        topBar = {
            AppTopBar(
                currentScreen = currentScreen,
                modifier = modifier,
                navController = navController
            )
        }
    ) { innerPadding ->
        GMoriAppNavHost(
            navController = navController,
            modifier = Modifier.padding(innerPadding)
        )
    }

}

enum class GMoriAppScreen(val title: Int) {
    UserList(R.string.user_list),
    UserDetail(R.string.user_detail)
}


