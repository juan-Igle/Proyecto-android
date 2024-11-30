package com.example.emergitech.presentation.screens.profile_edit

import android.content.Context
import android.net.Uri
import androidx.activity.compose.rememberLauncherForActivityResult
import androidx.activity.result.contract.ActivityResultContracts
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.SavedStateHandle
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.use_cases.users.UsersUseCases
import com.example.emergitech.presentation.utils.ComposeFileProvider
import com.example.emergitech.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class ProfileEditViewModel @Inject constructor(
    private val savedStateHandle: SavedStateHandle,
    private val usersUseCases: UsersUseCases,
    @ApplicationContext private val context: Context
    ): ViewModel(){

    //STATE FORM
    var state by mutableStateOf(ProfileEditState())
        private set

    var userNameErrMsg by  mutableStateOf("")
        private set

    //ARGUMENTS
    val data = savedStateHandle.get<String>("user")
    val user = User.fromJson(data!!)

    //RESPONSE
    var updateResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    var saveImageResponse by mutableStateOf<Response<String>?>(null)
        private set


    val resultingActivityHandler = ResultingActivityHandler()

    //FILE
    var file: File? = null

    init {
        state = state.copy(
            username = user.username,
            image = user.image
        )
    }

    fun saveImage() = viewModelScope.launch {

        if(file != null){
            saveImageResponse = Response.Loading
            val result = usersUseCases.saveImage(file!!)
            saveImageResponse = result
        }

    }

   fun pickImage() = viewModelScope.launch {
       val result = resultingActivityHandler.getContent("images/*")
       if(result != null){
           file = ComposeFileProvider.createFileFromUri(context, result)
           state = state.copy(image = result.toString())
       }

   }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)

        }
    }

    fun onUpdate(url: String){
        val myUser = User(
            id = user.id,
            username = state.username,
            image = url
        )
        update(myUser)
    }

    fun update(user: User) = viewModelScope.launch {
        updateResponse = Response.Loading
        val result = usersUseCases.update(user)
        updateResponse = result
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun validateUserName(){
        if(state.username.length >=5){
            userNameErrMsg = ""
        }else{
            userNameErrMsg = "Al menos 5 caracteres"
        }

    }
}