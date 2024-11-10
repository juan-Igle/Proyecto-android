package com.example.emergitech.presentation.screens.singup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //USER NAME
    var userName: MutableState<String> = mutableStateOf("")
    var isUserNameValid: MutableState<Boolean> = mutableStateOf(false)
    var userNameErrMsg: MutableState<String> = mutableStateOf("")

    //EMAIL
    var email: MutableState<String> = mutableStateOf("")
    var isEmailValid: MutableState<Boolean> = mutableStateOf(false)
    var emailErrMsg: MutableState<String> = mutableStateOf("")

    //Password
    var password: MutableState<String> = mutableStateOf("")
    var isPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var passwordErrMsg: MutableState<String> = mutableStateOf("")

    //CONFIRM PASSWORD
    var confirmPassword: MutableState<String> = mutableStateOf("")
    var isConfirmPasswordValid: MutableState<Boolean> = mutableStateOf(false)
    var confirmPasswordErrMsg: MutableState<String> = mutableStateOf("")

    //BUTTON
    var isEnableLoginButton = false

    private val _singupFlow = MutableStateFlow<Response<FirebaseUser>?>(null)
    val singupFlow: StateFlow<Response<FirebaseUser>?> = _singupFlow

    fun onSingup(){
        val user = User(
            username = userName.value,
            email = email.value,
            password = password.value
        )

        signup(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        _singupFlow.value = Response.Loading
        val result = authUseCases.singup(user)
        _singupFlow.value = result
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(email.value).matches()){
            isEmailValid.value = true
            emailErrMsg.value = ""
        }else{
            isEmailValid.value = false
            emailErrMsg.value = "El email no es valido"
        }

        enableLoginButton()
    }

    fun validatePassword(){
        if(password.value.length >= 6){
            isPasswordValid.value = true
            passwordErrMsg.value = ""
        }else{
            isPasswordValid.value = false
            passwordErrMsg.value = "Al menos 6 caracteres"
        }

        enableLoginButton()
    }

    fun validateConfirmPassword(){
        if(confirmPassword.value == password.value){
            isConfirmPasswordValid.value = true
            confirmPasswordErrMsg.value = ""
        }else{
            isConfirmPasswordValid.value = false
            confirmPasswordErrMsg.value = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

    fun validateUserName(){
        if(userName.value.length >=5){
            isUserNameValid.value = true
            userNameErrMsg.value = ""
        }else{
            isUserNameValid.value = false
            userNameErrMsg.value = "Al menos 5 caracteres"
        }
        enableLoginButton()

    }
    fun enableLoginButton(){
        isEnableLoginButton =
                isEmailValid.value &&
                isPasswordValid.value &&
                isUserNameValid.value &&
                isConfirmPasswordValid.value
    }



}