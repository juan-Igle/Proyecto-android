package com.example.emergitech.domain.use_cases.auth

import com.example.emergitech.data.repository.AuthRepositoryImpl
import com.example.emergitech.domain.repository.AuthRepository
import javax.inject.Inject

class Login @Inject constructor(private val repository: AuthRepository){

    suspend operator fun invoke(email: String, password: String) = repository.login(email, password)
}