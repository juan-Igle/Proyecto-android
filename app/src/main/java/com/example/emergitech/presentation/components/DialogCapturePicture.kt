package com.example.emergitech.presentation.components

import androidx.compose.foundation.layout.*
import androidx.compose.material.AlertDialog
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

@Composable
fun DialogCapturePicture(
  status: MutableState<Boolean>,
  takePhoto: () -> Unit,
  pickImage: () -> Unit
){
    if (status.value){
        AlertDialog(
            modifier = Modifier
                .fillMaxWidth()
                .height(150.dp),
            onDismissRequest = { status.value = false },
            backgroundColor = Color.White,
            title = {
                Text(
                    text = "Selecciona una opción",
                    fontSize = 20.sp,
                    color = Color.Black
                )
            },
            buttons = {
                Row(
                    modifier = Modifier.fillMaxWidth().padding(vertical = 30.dp),
                    horizontalArrangement = Arrangement.SpaceEvenly
                ) {
                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            pickImage()
                        }) {
                        Text(text = "Galería")
                    }

                    Button(
                        modifier = Modifier.width(130.dp),
                        onClick = {
                            status.value = false
                            takePhoto()

                        }) {
                        Text(text = "Camara")
                    }
                }
            }
        )


    }
}