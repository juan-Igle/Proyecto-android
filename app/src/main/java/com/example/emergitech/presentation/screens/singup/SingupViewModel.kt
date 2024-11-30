package com.example.emergitech.presentation.screens.singup

import android.util.Patterns
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.use_cases.auth.AuthUseCases
import com.example.emergitech.domain.use_cases.users.UsersUseCases
import com.google.firebase.auth.FirebaseUser
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject


@HiltViewModel
class SingupViewModel @Inject constructor(private val authUseCases: AuthUseCases, private val usersUseCases: UsersUseCases): ViewModel() {

    //STATE FORM
    var state by mutableStateOf(SingupState())
        private set
    //USER NAME
    var isUserNameValid by mutableStateOf(false)
        private set
    var userNameErrMsg by  mutableStateOf("")
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

    //CONFIRM PASSWORD
    var isConfirmPasswordValid by mutableStateOf(false)
        private set
    var confirmPasswordErrMsg by mutableStateOf("")
        private set
    //BUTTON
    var isEnableLoginButton = false

    var singupResponse by mutableStateOf<Response<FirebaseUser>?>(null)

    var user = User()

    fun onEmailInput(email: String){
        state = state.copy(email = email)
    }

    fun onUsernameInput(username: String){
        state = state.copy(username = username)
    }

    fun onPasswordInput(password: String){
        state = state.copy(password = password)
    }

    fun onConfirmPasswordInput(confirmPassword: String){
        state = state.copy(confirmPassword = confirmPassword)
    }

    fun onSingup(){

        user.username = state.username
        user.email = state.email
        user.password = state.password

        signup(user)
    }

    fun createUser() = viewModelScope.launch {
        user.id = authUseCases.getCurrentUser()!!.uid
       usersUseCases.create(user)
    }

    fun signup(user: User) = viewModelScope.launch {
        singupResponse = Response.Loading
        val result = authUseCases.singup(user)
        singupResponse = result
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

    fun validateConfirmPassword(){
        if(state.confirmPassword == state.password){
            isConfirmPasswordValid = true
            confirmPasswordErrMsg = ""
        }else{
            isConfirmPasswordValid = false
            confirmPasswordErrMsg = "Las contraseñas no coinciden"
        }
        enableLoginButton()
    }

    fun validateUserName(){
        if(state.username.length >=5){
            isUserNameValid = true
            userNameErrMsg = ""
        }else{
            isUserNameValid = false
            userNameErrMsg = "Al menos 5 caracteres"
        }
        enableLoginButton()

    }
    fun enableLoginButton(){
        isEnableLoginButton =
                isEmailValid &&
                isPasswordValid &&
                isUserNameValid &&
                isConfirmPasswordValid
    }



}