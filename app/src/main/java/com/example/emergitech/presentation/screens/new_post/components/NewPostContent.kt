package com.example.emergitech.presentation.screens.new_post.components

import androidx.compose.foundation.*
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.selection.selectable
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.RadioButton
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Email
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.List
import androidx.compose.runtime.Composable
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.input.KeyboardType
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import coil.compose.AsyncImage
import com.example.emergitech.R
import com.example.emergitech.presentation.components.DefaultTextField
import com.example.emergitech.presentation.components.DialogCapturePicture
import com.example.emergitech.presentation.screens.new_post.NewPostViewModel




@Composable
fun NewPostContent(viewModel: NewPostViewModel = hiltViewModel()) {

   val state = viewModel.state
   viewModel.resultingActivityHandler.handle()
   var dialogState = remember{ mutableStateOf(false) }



   DialogCapturePicture(
      status = dialogState,
      takePhoto = { viewModel.takePhoto() },
      pickImage = {viewModel.pickImage()}
   )

   Box(
      modifier = Modifier
         .fillMaxWidth()
   ) {

      Column(
         modifier = Modifier
            .fillMaxWidth()
            .verticalScroll(rememberScrollState()),
         horizontalAlignment = Alignment.CenterHorizontally
      ) {
         Box(
            modifier = Modifier
               .fillMaxWidth()
               .height(170.dp)
               .background(MaterialTheme.colors.primary) // Cambiado a BluePrimary para un encabezado atractivo
         ) {
            Column(
               modifier = Modifier.fillMaxWidth(),
               horizontalAlignment = Alignment.CenterHorizontally
            ) {
               if(viewModel.state.image != ""){
                  AsyncImage(
                     modifier = Modifier
                        .fillMaxWidth()
                        .height(170.dp)
                        .clickable {
                           dialogState.value = true
                        },
                     model = viewModel.state.image,
                     contentDescription = "Selected image",
                     contentScale = ContentScale.Crop


                  )
               }else{
                  Image(
                     modifier = Modifier
                        .height(120.dp)
                        .width(120.dp)
                        .clickable {
                           dialogState.value = true
                        },
                     painter = painterResource(id = R.drawable.add_image),
                     contentDescription = ""
                  )

                  Text(
                     text = "SELECCIONA UNA IMAGEN",
                     color = MaterialTheme.colors.onPrimary, // Texto en blanco para contraste con el fondo azul
                     fontSize = 20.sp,
                     fontWeight = FontWeight.Bold
                  )
               }


            }
         }

         DefaultTextField(
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = 25.dp, start = 20.dp, end = 20.dp),
            value = state.name,
            onValueChange = { viewModel.onNameInput(it) },
            validateField = {

            },
            label = "Nombre del post",
            icon = Icons.Default.Face,
            errorMsg = ""
         )

         DefaultTextField(
            modifier = Modifier
               .fillMaxWidth()
               .padding(top = 0.dp, start = 20.dp, end = 20.dp),
            value = state.description,
            onValueChange = { viewModel.onDescriptionInput(it)},
            validateField = {

            },
            label = "",
            icon = Icons.Default.List,
            errorMsg = ""
         )
         
         Text(
            modifier = Modifier.padding(vertical = 15.dp),
            text = "CATEGORIAS",
            fontSize = 17.sp,
            fontWeight = FontWeight.Bold

         )

         viewModel.radioOptions.forEach { option ->
            Row(
               modifier = Modifier
                  .fillMaxWidth()
                  .padding(horizontal = 16.dp)
                  .selectable(
                     selected = (option.category == state.category),
                     onClick = {
                        viewModel.onCategoryInput(option.category)
                     }

                  ),
               verticalAlignment = Alignment.CenterVertically
            ) {
               RadioButton(
                  selected = (option.category == state.category),
                  onClick = {
                     viewModel.onCategoryInput(option.category)
                  }
               )

               Row() {
                  Text(
                     modifier = Modifier
                        .width(200.dp)
                        .padding(11.dp),
                     text = option.category
                  )

                  Image(
                     modifier = Modifier
                        .height(50.dp)
                        .padding(8.dp),
                     painter = painterResource(id = option.image),
                     contentDescription = ""
                  )
               }
            }
         }
      }

   }
}