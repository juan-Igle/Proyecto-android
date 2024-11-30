package com.example.emergitech.presentation.screens.posts.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.domain.model.Response
import com.example.emergitech.presentation.components.ProgressBar
import com.example.emergitech.presentation.screens.posts.PostsViewModel

@Composable
fun GetPosts(navController: NavHostController, viewModel: PostsViewModel = hiltViewModel()) {

    when(val response = viewModel.postsResponse) {
        // MOSTRAR QUE SE ESTA REALIZANDO LA PETICION Y TODAVIA ESTA EN PROCESO
        Response.Loading -> {
            ProgressBar()
        }
        is Response.Success -> {
            PostsContent(navController = navController, posts = response.data)
        }
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconido", Toast.LENGTH_LONG).show()
        }

    }

}