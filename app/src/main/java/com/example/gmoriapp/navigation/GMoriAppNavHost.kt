package com.example.gmoriapp.navigation

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavHostController
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.navArgument
import com.example.gmoriapp.view.GMoriAppScreen
import com.example.gmoriapp.model.User
import com.example.gmoriapp.view.user.UserDetailView
import com.example.gmoriapp.view.user.UserListView
import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun GMoriAppNavHost(
    navController: NavHostController,
    modifier: Modifier
) {
    NavHost(
        navController = navController,
        startDestination = GMoriAppScreen.UserList.name,
        modifier = modifier
    ) {
        composable(route = GMoriAppScreen.UserList.name) {
            UserListView(
                navigateToUserDetail = { user ->
                    navController.navigate(
                        "${GMoriAppScreen.UserDetail.name}/${user.id}/${
                            URLEncoder.encode(user.name, StandardCharsets.UTF_8.toString())
                        }/${URLEncoder.encode(user.imageUrl, StandardCharsets.UTF_8.toString())}/${
                            URLEncoder.encode(user.description, StandardCharsets.UTF_8.toString())
                        }"
                    )
                }
            )
        }
        composable(
            route = "${GMoriAppScreen.UserDetail.name}/{user_id}/{user_name}/{user_image_url}/{user_description}",
            arguments = listOf(
                navArgument("user_id") { type = NavType.IntType },
                navArgument("user_name") { type = NavType.StringType },
                navArgument("user_image_url") { type = NavType.StringType },
                navArgument("user_description") { type = NavType.StringType },
            )
        ) {
            UserDetailView(
                user = User(
                    id = it.arguments?.getInt("user_id") ?: 0,
                    name = it.arguments?.getString("user_name") ?: "",
                    imageUrl = it.arguments?.getString("user_image_url") ?: "",
                    description = it.arguments?.getString("user_description") ?: "",
                )
            )
        }
    }
}
