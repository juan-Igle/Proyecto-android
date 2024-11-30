package com.example.emergitech.presentation.screens.singup

import android.provider.ContactsContract.CommonDataKinds.Email

data class SingupState(
    val username: String = "",
    val email: String = "",
    val password: String = "",
    val confirmPassword: String = ""
)
