package com.example.emergitech.presentation.screens.profile_edit

import android.util.Log
import androidx.compose.material.Scaffold
import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import com.example.emergitech.domain.use_cases.users.Update
import com.example.emergitech.presentation.components.DefaultTopBar
import com.example.emergitech.presentation.navigation.DetailsScreen
import com.example.emergitech.presentation.screens.profile_edit.components.ProfileEditContent
import com.example.emergitech.presentation.screens.profile_edit.components.SaveImage
import com.example.emergitech.presentation.screens.profile_edit.components.Update

@Composable
fun ProfileEditScreen(
    navController: NavHostController,
    user: String
){
    Log.d("ProfileEditScreen", "Usuario: $user")

    Scaffold(
        topBar = {
            DefaultTopBar(
                title = "Nuevo usuario",
                upAvailable = true,
                navController = navController
            )
        },
        content = {
            ProfileEditContent(navController)
        },
        bottomBar = {}
    )
    SaveImage()
    Update()
}