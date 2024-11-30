package com.example.emergitech.presentation.screens.singup

import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.emergitech.presentation.components.DefaultTopBar
import com.example.emergitech.presentation.screens.login.LoginScreen
import com.example.emergitech.presentation.screens.singup.components.SingUp
import com.example.emergitech.presentation.screens.singup.components.SingupContent
import com.example.emergitech.presentation.ui.theme.EmergiTechTheme

@Composable
fun SingupScreen(navController: NavHostController){

    Scaffold(
        topBar = {
                 DefaultTopBar(
                     title = "Nuevo usuario",
                     upAvailable = true,
                     navController = navController
                 )
        },
        content = {
            SingupContent(navController)
        },
        bottomBar = {}
    )

   SingUp(navController = navController)
}

