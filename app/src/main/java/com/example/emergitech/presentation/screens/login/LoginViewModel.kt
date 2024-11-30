package com.example.emergitech.presentation.screens.login

import android.util.Patterns
import androidx.compose.runtime.*
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.use_cases.auth.AuthUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import java.util.regex.Pattern
import javax.inject.Inject

@HiltViewModel
class LoginViewModel @Inject constructor(private val authUseCases: AuthUseCases): ViewModel() {

    //STATE FORM
    var state by mutableStateOf(LoginState())
        private set

    //EMAIL
    var isEmailValid by mutableStateOf(false)
        private set
    var emailErrMsg by mutableStateOf("")
        private set
    //Password
    var isPasswordValid by mutableStateOf(false)
        private set
    var passwordErrMsg by mutableStateOf("")
        private set

    //BUTTON
    var isEnableLoginButton = false

    //LOGIN RESPONSE
    var loginResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    val currentUser = authUseCases.getCurrentUser()

    init {
        if (currentUser != null){
            loginResponse = Response.Success(currentUser)
        }
    }

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun login() = viewModelScope.launch {

        loginResponse = Response.Loading
        val result = authUseCases.login(state.email, state.password)
        loginResponse = result
    }

    fun validateEmail(){
        if(Patterns.EMAIL_ADDRESS.matcher(state.email).matches()){
            isEmailValid = true
            emailErrMsg = ""
        }else{
            isEmailValid = false
            emailErrMsg = "El email no es valido"
        }

        enableLoginButton()
    }

    fun validatePassword(){
        if(state.password.length >= 6){
            isPasswordValid = true
            passwordErrMsg = ""
        }else{
            isPasswordValid = false
            passwordErrMsg = "Al menos 6 caracteres"
        }

        enableLoginButton()
    }

    fun enableLoginButton(){
        isEnableLoginButton = isEmailValid && isPasswordValid
    }

}