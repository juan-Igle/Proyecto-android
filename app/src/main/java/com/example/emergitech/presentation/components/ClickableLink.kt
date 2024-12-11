package com.example.emergitech.presentation.components

import android.content.Intent
import android.net.Uri
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.padding
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.style.TextDecoration
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun ClickableLink(enlace: String) {
    val context = LocalContext.current

    Text(
        text = enlace,
        fontSize = 16.sp,
        color = Color.Blue,
        textDecoration = TextDecoration.Underline, // Para dar la apariencia de un enlace
        modifier = Modifier
            .padding(10.dp)
            .clickable {
            // Abre el enlace en el navegador
            val intent = Intent(Intent.ACTION_VIEW, Uri.parse(enlace))
            context.startActivity(intent)
        }
    )
}