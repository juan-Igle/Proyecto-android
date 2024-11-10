package com.example.emergitech.presentation.screens.login.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.navigation.AppScreen
import com.example.emergitech.presentation.ui.theme.BluePrimary
import com.example.emergitech.presentation.ui.theme.MediumGray

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
            color = MediumGray // Cambiado a MediumGray para un tono neutro y claro
        )

        Spacer(modifier = Modifier.width(7.dp))
        Text(
            modifier = Modifier.clickable {
                navController.navigate(route = AppScreen.Singup.route)
            },
            text = "REGÍSTRATE AQUÍ",
            color = BluePrimary, // Cambiado a BluePrimary para un acento llamativo
            fontSize = 14.sp,
            fontWeight = FontWeight.Bold
        )
    }
}
