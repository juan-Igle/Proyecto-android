package com.example.emergitech.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.navigation.AuthScreen

@Composable
fun LoginBottomBar(navController: NavHostController) {
    Row(
        modifier = Modifier
            .fillMaxWidth()
            .padding(bottom = 15.dp),
        horizontalArrangement = Arrangement.Center
    ) {
        Text(
            text = "No tienes cuenta?",
            fontSize = 14.sp,
            color = MaterialTheme.colors.onSurface        )

        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AuthScreen.Singup.route)
            },
            text = "REGÍSTRATE AQUÍ",
            color = MaterialTheme.colors.primary,
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
