package com.example.emergitech.presentation.screens.profile

import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.navigation.AppNavigation
import com.example.emergitech.presentation.navigation.AppScreen
import com.example.emergitech.presentation.screens.profile.components.ProfileContent

@Composable
fun ProfileScreen(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()){
    Scaffold(
        topBar = {},
        content = {
            ProfileContent(navController)
        },
        bottomBar = {}
    )
}