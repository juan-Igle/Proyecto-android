package com.example.emergitech.presentation.screens.singup.components

import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material.icons.filled.Person
import androidx.compose.material.icons.outlined.Lock
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import com.example.emergitech.R
import com.example.emergitech.domain.model.Response
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.components.DefaultTextField
import com.example.emergitech.presentation.navigation.AppScreen
import com.example.emergitech.presentation.screens.singup.SingupViewModel
import com.example.emergitech.presentation.ui.theme.*

@Composable
fun SingupContent(navController: NavHostController, viewModel: SingupViewModel = hiltViewModel()) {

    val singupFlow = viewModel.singupFlow.collectAsState()

    Box(
        modifier = Modifier
            .fillMaxWidth()
    ) {

        Box(
            modifier = Modifier
                .fillMaxWidth()
                .height(230.dp)
                .background(BluePrimary) // Cambiado a BluePrimary para un encabezado moderno
        ) {
            Column(
                modifier = Modifier
                    .fillMaxWidth()
                    .padding(top = 15.dp),
                horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Image(
                    modifier = Modifier.height(100.dp),
                    painter = painterResource(id = R.drawable.user),
                    contentDescription = ""
                )
            }
        }

        Card(
            modifier = Modifier.padding(start = 40.dp, end = 40.dp, top = 150.dp),
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
                    text = "REGISTRO",
                    fontSize = 18.sp,
                    fontWeight = FontWeight.Bold,
                    color = White // Cambiado a blanco para destacar el texto
                )
                Spacer(modifier = Modifier.height(10.dp))
                Text(
                    text = "Por favor ingresa estos datos para continuar",
                    fontSize = 12.sp,
                    color = MediumGray // Cambiado a MediumGray para un tono neutro
                )
                Spacer(modifier = Modifier.height(25.dp))
                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.userName.value,
                    onValueChange = { viewModel.userName.value = it },
                    label = "Nombre de usuario",
                    icon = Icons.Default.Person,
                    errorMsg = viewModel.userNameErrMsg.value,
                    validateField = {
                        viewModel.validateUserName()
                    }
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.email.value,
                    onValueChange = { viewModel.email.value = it },
                    label = "Correo electrónico",
                    icon = Icons.Default.Email,
                    keyboardType = KeyboardType.Email,
                    errorMsg = viewModel.emailErrMsg.value,
                    validateField = { viewModel.validateEmail() }
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.password.value,
                    onValueChange = { viewModel.password.value = it },
                    label = "Password",
                    icon = Icons.Default.Lock,
                    hideText = true,
                    errorMsg = viewModel.passwordErrMsg.value,
                    validateField = { viewModel.validatePassword() }
                )

                DefaultTextField(
                    modifier = Modifier
                        .fillMaxWidth(),
                    value = viewModel.confirmPassword.value,
                    onValueChange = { viewModel.confirmPassword.value = it },
                    label = "Confirmar Password",
                    icon = Icons.Outlined.Lock,
                    hideText = true,
                    errorMsg = viewModel.confirmPasswordErrMsg.value,
                    validateField = { viewModel.validateConfirmPassword() }
                )

                DefaultButton(
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(vertical = 20.dp),
                    text = "REGISTRARSE",
                    color = BluePrimary, // Cambiado a BluePrimary para el botón de registro
                    onClick = { viewModel.onSingup() },
                    enable = viewModel.isEnableLoginButton
                )
            }
        }
    }

    singupFlow.value.let {
        when (it) {
            Response.Loading -> {
                Box(
                    modifier = Modifier.fillMaxSize(),
                    contentAlignment = Alignment.Center
                ) {
                    CircularProgressIndicator(
                        color = BluePrimary // Cambiado a BluePrimary para el indicador de carga
                    )
                }
            }

            is Response.Success -> {
                LaunchedEffect(Unit) {
                    navController.popBackStack(AppScreen.Login.route, true)
                    navController.navigate(route = AppScreen.Profile.route)
                }
            }

            is Response.Failure -> {
                Toast.makeText(LocalContext.current, it.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
            }
        }
    }
}




