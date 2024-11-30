package com.example.emergitech.presentation.screens.profile.components

import android.app.Activity
import android.content.Intent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Close
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.emergitech.R
import com.example.emergitech.presentation.MainActivity
import com.example.emergitech.presentation.components.DefaultButton
import com.example.emergitech.presentation.navigation.AuthScreen
import com.example.emergitech.presentation.navigation.DetailsScreen
import com.example.emergitech.presentation.navigation.Graph
import com.example.emergitech.presentation.screens.profile.ProfileViewModel

import java.net.URLEncoder
import java.nio.charset.StandardCharsets

@Composable
fun ProfileContent(navController: NavHostController, viewModel: ProfileViewModel = hiltViewModel()) {

    val actvity = LocalContext.current as? Activity

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
                    color = MaterialTheme.colors.onBackground
                )

                Spacer(modifier = Modifier.height(55.dp))

                if(viewModel.userData.image != null){

                    AsyncImage(
                        modifier = Modifier.size(120.dp).clip(CircleShape),
                        model = viewModel.userData.image,
                        contentDescription = "User image",
                        contentScale = ContentScale.Crop
                    )
                }else{
                    Image(
                        modifier = Modifier.size(120.dp).clip(CircleShape),
                        painter = painterResource(id = R.drawable.user),
                        contentDescription = ""
                    )
                }

            }
        }

        Spacer(modifier = Modifier.height(55.dp))
        Text(
            text = viewModel.userData.username,
            fontSize = 20.sp,
            fontWeight = FontWeight.Bold,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.primary
        )
        Text(
            text = viewModel.userData.email,
            fontSize = 15.sp,
            fontStyle = FontStyle.Italic,
            color = MaterialTheme.colors.onSurface
        )

        Spacer(modifier = Modifier.height(20.dp))

        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Editar datos",
            color = MaterialTheme.colors.primary,
            icon = Icons.Default.Edit,
            onClick = {
                navController.navigate(
                    route = DetailsScreen.ProfileUpdate.passUser(viewModel.userData.toJson())
                )
            }
        )

        DefaultButton(
            modifier = Modifier.width(250.dp),
            text = "Cerrar sesión",
            color = MaterialTheme.colors.error, // Cambiado a Red500 para destacar el botón de cerrar sesión
            icon = Icons.Default.Close,
            onClick = {
                viewModel.logout()
                actvity?.finish()
                actvity?.startActivity(Intent(actvity, MainActivity::class.java))
            }
        )
    }
}

