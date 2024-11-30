package com.example.emergitech.domain.repository

import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import kotlinx.coroutines.flow.Flow
import java.io.File

interface UserRepository {

    suspend fun create(user: User): Response<Boolean>
    suspend fun update(user: User): Response<Boolean>
    suspend fun saveImage(file: File): Response<String>
    fun getUserById(id: String): Flow<User>
}