package com.example.emergitech.presentation.screens.login.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.Card
import androidx.compose.material.CircularProgressIndicator
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.collectAsState
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.R
import com.example.emergitech.domain.model.Response
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.components.DefaultTextField
import com.example.emergitech.presentation.navigation.AppScreen
import com.example.emergitech.presentation.screens.login.LoginViewModel
import com.example.emergitech.presentation.ui.theme.*

@Composable
fun LoginContent(navControler: NavHostController, viewModel: LoginViewModel = hiltViewModel()) {

    val loginFlow = viewModel.loginFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(250.dp)
                .background(BluePrimary) // Cambiado a BluePrimary para un encabezado atractivo
        ) {
            Column(
                modifier = Modifier.fillMaxWidth(),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    painter = painterResource(id = R.drawable.iconoapp),
                    contentDescription = "Icono de la app"
                )

                Text(
                    text = "Emergi Tech",
                    color = White, // Texto en blanco para contraste con el fondo azul
                    fontSize = 20.sp,
                    fontWeight = FontWeight.Bold
                )
            }
        }

        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 190.dp),
            backgroundColor = DarkGray500 // Fondo de la tarjeta en gris oscuro para elegancia
        ) {
            Column(
                modifier = Modifier.padding(horizontal = 20.dp)
            ) {
                Text(
                    modifier = Modifier
                        .padding(
                            top = 45.dp,
                            bottom = 0.dp,
                            start = 0.dp,
                            end = 0.dp
                        ),
                    text = "LOGIN",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White // Texto en blanco para resaltar
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor inicia sesión para continuar",
                    fontSize = 12.sp,
                    color = MediumGray // Cambiado a MediumGray para un tono neutro
                )

                Spacer(modifier = Modifier.height(25.dp))

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    validateField = {
                        viewModel.validateEmail()
                    },
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value
                )

                DefaultTextField(
                    modifier = Modifier
                        .padding(top = 5.dp)
                        .fillMaxWidth(),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    validateField = {
                        viewModel.validatePassword()
                    },
                    label = "Contraseña",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 40.dp),
                    text = "INICIAR SESIÓN",
                    onClick = {
                        viewModel.login()
                    },
                    enable = viewModel.isEnableLoginButton
                )
            }
        }
    }

    loginFlow.value.let {
        when (it) {
            Response.Loading -> {
                Box(
                    contentAlignment = Alignment.Center,
                    modifier = Modifier.fillMaxSize()
                ) {
                    CircularProgressIndicator(
                        color = BluePrimary // Cambiado a BluePrimary para un indicador consistente
                    )
                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navControler.navigate(route = AppScreen.Profile.route) {
                        popUpTo(AppScreen.Login.route) { inclusive = true }
                    }
                }
            }

            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
            else -> {}
        }
    }
}



