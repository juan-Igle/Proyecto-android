package com.example.emergitech.presentation.navigation

import androidx.navigation.NavGraphBuilder
import androidx.navigation.NavHostController
import androidx.navigation.compose.composable
import androidx.navigation.navigation
import com.example.emergitech.presentation.screens.login.LoginScreen
import com.example.emergitech.presentation.screens.singup.SingupScreen

fun NavGraphBuilder.authNavGraph(navController: NavHostController){
    navigation(
        route = Graph.AUTHENTICATION,
        startDestination = AuthScreen.Login.route
    ){
        composable(route = AuthScreen.Login.route){
            LoginScreen(navController)
        }

        composable(route = AuthScreen.Singup.route){
            SingupScreen(navController)
        }
    }
}


sealed class AuthScreen(val route: String){
    object Login: AuthScreen("login")
    object Singup: AuthScreen("singup")


}