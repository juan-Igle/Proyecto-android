package com.example.emergitech.presentation.screens.profile_edit.components

import android.widget.Toast
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.use_cases.users.SaveImage
import com.example.emergitech.presentation.components.ProgressBar
import com.example.emergitech.presentation.screens.profile_edit.ProfileEditViewModel
import dagger.hilt.android.lifecycle.HiltViewModel

@Composable
fun SaveImage(viewModel: ProfileEditViewModel = hiltViewModel()){

    when(val response = viewModel.saveImageResponse){

        Response.Loading -> {
            ProgressBar()
        }

        is Response.Success -> {
            viewModel.onUpdate(response.data)
        }
        
        is Response.Failure -> {
            Toast.makeText(LocalContext.current, response.exception?.message ?: "Error desconocido", Toast.LENGTH_LONG).show()
        }
    }
}