package com.example.emergitech.presentation.navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.List
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emergitech.presentation.screens.my_post.MyPostsScreen
import com.example.emergitech.presentation.screens.posts.PostsScreen
import com.example.emergitech.presentation.screens.profile.ProfileScreen

@Composable
fun HomeBottomBarNavGraph(navController: NavHostController){

    NavHost(
        navController = navController,
        route = Graph.HOME,
        startDestination = HomeBottomBarScreen.Post.route
    )
    {
        composable(route = HomeBottomBarScreen.Post.route){
            PostsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.MyPost.route){
            MyPostsScreen(navController)
        }

        composable(route = HomeBottomBarScreen.Profile.route){
            ProfileScreen(navController)
        }

        detailsNavGraph(navController)
    }



}

sealed class HomeBottomBarScreen(
    val route: String,
    var title: String,
    var icon: ImageVector
){
    object Post: HomeBottomBarScreen(
        route = "post",
        title = "Post",
        icon = Icons.Default.List
    )
    object MyPost: HomeBottomBarScreen(
        route = "my_post",
        title = "Mis Post",
        icon = Icons.Outlined.List
    )

    object Profile: HomeBottomBarScreen(
        route = "profile",
        title = "Perfil",
        icon = Icons.Default.Person
    )
}