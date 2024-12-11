package com.example.emergitech.presentation.screens.new_post

import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.components.DefaultTopBar
import com.example.emergitech.presentation.screens.new_post.components.NewPost
import com.example.emergitech.presentation.screens.new_post.components.NewPostContent

@Composable
fun NewPostScreen(
    navController: NavHostController,
    viewModel: NewPostViewModel = hiltViewModel()
){

    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Nuevo Post",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = { paddingValues ->
            Box(
                modifier = Modifier
                    .fillMaxSize()
                    .padding(paddingValues)
            ){
                NewPostContent()
            }

        },
        bottomBar = {
            DefaultButton(
                modifier = Modifier.fillMaxWidth(),
                text ="PUBLICAR",
                onClick = { viewModel.onNewPost() }
            )
        }
    )

    NewPost()
}