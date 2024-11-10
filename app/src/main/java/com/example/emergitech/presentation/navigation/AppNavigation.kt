package com.example.emergitech.presentation.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavController

import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.example.emergitech.presentation.screens.login.LoginScreen
import com.example.emergitech.presentation.screens.profile.ProfileScreen
import com.example.emergitech.presentation.screens.singup.SingupScreen

@Composable
fun AppNavigation(navController: NavHostController){

    NavHost(
        navController = navController,
        startDestination = AppScreen.Login.route
    ){

        composable(route = AppScreen.Login.route){
            LoginScreen(navController)
        }

        composable(route = AppScreen.Singup.route){
            SingupScreen(navController)
        }

        composable(route = AppScreen.Profile.route){
            ProfileScreen(navController)
        }
    }
}