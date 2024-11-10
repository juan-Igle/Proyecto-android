package com.example.emergitech.domain.repository

import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import com.google.firebase.auth.FirebaseUser


interface AuthRepository {
    val currentUser: FirebaseUser?
    suspend fun login(email: String, password: String): Response<FirebaseUser>
    suspend fun singUp(user: User): Response<FirebaseUser>
    fun logout()
}