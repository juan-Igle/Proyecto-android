package com.example.emergitech.presentation.screens.posts

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.screens.posts.components.DeleteLikePost
import com.example.emergitech.presentation.screens.posts.components.GetPosts
import com.example.emergitech.presentation.screens.posts.components.LikePost

@Composable
fun PostsScreen(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()) {

    Scaffold(
        content = {
            GetPosts(navController)
        }
    )
    LikePost()
    DeleteLikePost()

}