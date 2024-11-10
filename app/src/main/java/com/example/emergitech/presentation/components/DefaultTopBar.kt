package com.example.emergitech.presentation.components

import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.sp
import androidx.navigation.NavHostController
import com.example.emergitech.presentation.ui.theme.DarkGray700
import com.example.emergitech.presentation.ui.theme.Red500
import com.example.emergitech.presentation.ui.theme.Red700
import com.example.emergitech.presentation.ui.theme.White

@Composable
fun DefaultTopBar(
    title: String,
    upAvailable: Boolean = false,
    navController: NavHostController? = null
) {
    TopAppBar(
        title = {
            Text(
                text = title,
                fontSize = 19.sp,
                color = White // Manteniendo el texto en blanco para un buen contraste
            )
        },
        backgroundColor = DarkGray700, // Usando DarkGray700 para un fondo elegante y oscuro
        navigationIcon = {
            if (upAvailable) {
                IconButton(onClick = { navController?.popBackStack() }) {
                    Icon(
                        imageVector = Icons.Default.ArrowBack,
                        contentDescription = "",
                        tint = White // Manteniendo el icono en blanco para visibilidad
                    )
                }
            }
        }
    )
}
