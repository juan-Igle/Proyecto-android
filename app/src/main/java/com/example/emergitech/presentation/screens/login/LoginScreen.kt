package com.example.emergitech.presentation.screens.login

import android.view.View
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.lifecycle.ViewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.rememberNavController
import com.example.emergitech.presentation.screens.login.components.LoginBottomBar
import com.example.emergitech.presentation.screens.login.components.LoginContent
import com.example.emergitech.presentation.ui.theme.EmergiTechTheme

@Composable
fun LoginScreen(navController: NavHostController) {



    Scaffold(
        topBar = {},
        content = {
            LoginContent(navController)
        },
        bottomBar = {
            LoginBottomBar(navController)
        }
    )


}



@Preview(showBackground = true, showSystemUi = true)
@Composable
fun preview(){
    EmergiTechTheme (darkTheme = true){
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(),
            color = MaterialTheme.colors.background
        ) {
            LoginScreen(rememberNavController())
        }
    }
}