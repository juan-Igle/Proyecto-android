package com.example.emergitech.presentation.screens.profile.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.R
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.navigation.AppScreen
import com.example.emergitech.presentation.screens.profile.ProfileViewModel
import com.example.emergitech.presentation.ui.theme.BluePrimary
import com.example.emergitech.presentation.ui.theme.MediumGray
import com.example.emergitech.presentation.ui.theme.Red500
import com.example.emergitech.presentation.ui.theme.White

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {
    Column(
        modifier = Modifier.fillMaxWidth(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        Box {

            Image(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(200.dp),
                painter = painterResource(id = R.drawable.imagen_prueba),
                contentDescription = "",
                contentScale = ContentScale.Crop,
                alpha = 0.6f
            )

            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Spacer(modifier = Modifier.height(30.dp))

                Text(
                    text = "Bienvenido",
                    fontSize = 30.sp,
                    fontWeight = FontWeight.Bold,
                    color = White // Cambiado a blanco para que contraste con el fondo de la imagen
                )

                Spacer(modifier = Modifier.height(55.dp))
                Image(
                    modifier = Modifier.size(120.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = ""
                )
            }
        }

        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = "Nombre del usuario",
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = BluePrimary // Cambiado a BluePrimary para un acento tecnológico
        )
        Text(
            text = "email del usuario",
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            color = MediumGray // Cambiado a MediumGray para un tono neutro
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            color = BluePrimary, // Cambiado a BluePrimary para consistencia con el tema
            icon = Icons.Default.Edit,
            onClick = {
                viewModel.logout()
                navController.navigate(route = AppScreen.Login.route) {
                    popUpTo(route = AppScreen.Profile.route) { inclusive = true }
                }
            }
        )

        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar sesión",
            color = Red500, // Cambiado a Red500 para destacar el botón de cerrar sesión
            icon = Icons.Default.Close,
            onClick = {
                viewModel.logout()
                navController.navigate(route = AppScreen.Login.route) {
                    popUpTo(route = AppScreen.Profile.route) { inclusive = true }
                }
            }
        )
    }
}

