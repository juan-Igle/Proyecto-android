package com.example.emergitech.presentation.screens.new_post.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.domain.model.Response
import com.example.emergitech.presentation.components.ProgressBar
import com.example.emergitech.presentation.navigation.Graph
import com.example.emergitech.presentation.screens.login.LoginViewModel
import com.example.emergitech.presentation.screens.new_post.NewPostViewModel

@Composable
fun NewPost(viewModel: NewPostViewModel = hiltViewModel()){

    when (val response = viewModel.createPostResponse) {
        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.clearForm()
            Toast.makeText(LocalContext.current, "La publicación se ha creado correctamente", Toast.LENGTH_LONG).show()

        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
        else -> {}
    }
}