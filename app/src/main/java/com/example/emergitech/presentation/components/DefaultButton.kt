package com.example.emergitech.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.ButtonDefaults
import androidx.compose.material.Icon
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.emergitech.presentation.ui.theme.BluePrimary
import com.example.emergitech.presentation.ui.theme.Red500
import com.example.emergitech.presentation.ui.theme.Red700
import com.example.emergitech.presentation.ui.theme.White

@Composable
fun DefaultButton(
    modifier: Modifier,
    text: String,
    errorMsg: String = "",
    onClick: () -> Unit,
    color: Color = BluePrimary, // Cambiado a BluePrimary para acento tecnológico
    icon: ImageVector = Icons.Default.ArrowForward,
    enable: Boolean = true
) {
    Column {
        Button(
            modifier = modifier,
            onClick = { onClick() },
            colors = ButtonDefaults.buttonColors(color),
            enabled = enable
        ) {
            Icon(
                imageVector = icon,
                contentDescription = "",
                tint = White // Asegura que el icono tenga un buen contraste con el botón
            )
            Spacer(modifier = Modifier.width(10.dp))
            Text(
                text = text,
                color = White // Texto en blanco para contraste claro
            )
        }

        Text(
            modifier = Modifier.padding(top = 2.dp),
            text = errorMsg,
            fontSize = 10.sp,
            color = Red700 // Manteniendo Red700 para mensajes de error
        )
    }
}
