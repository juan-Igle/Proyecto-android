package com.example.emergitech.domain.use_cases.auth

import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.repository.AuthRepository
import javax.inject.Inject

class Singup @Inject constructor(private val repository: AuthRepository) {

    suspend operator fun invoke(user: User) = repository.singUp(user)
}