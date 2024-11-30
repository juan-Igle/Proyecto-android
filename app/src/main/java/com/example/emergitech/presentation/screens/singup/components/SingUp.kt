package com.example.emergitech.presentation.screens.singup.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.domain.model.Response
import com.example.emergitech.presentation.components.ProgressBar
import com.example.emergitech.presentation.navigation.AuthScreen
import com.example.emergitech.presentation.navigation.Graph
import com.example.emergitech.presentation.screens.singup.SingupViewModel

@Composable
fun SingUp(navController: NavHostController, viewModel: SingupViewModel = hiltViewModel()){

    when (val singupResponse = viewModel.singupResponse) {
        Response.Loading -> {
           ProgressBar()
        }

        is Response.Success -> {
            LaunchedEffect(Unit) {
                viewModel.createUser()
                navController.popBackStack(AuthScreen.Login.route, true)
                navController.navigate(route = Graph.HOME)
            }
        }

        is Response.Failure -> {
            Toast.makeText(LocalContext.current, singupResponse.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}