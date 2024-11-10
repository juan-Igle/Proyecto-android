package com.example.emergitech.domain.use_cases.auth

import com.example.emergitech.domain.repository.AuthRepository
import javax.inject.Inject

class GetCurrentUser @Inject constructor(private val repository: AuthRepository) {

    operator fun invoke() = repository.currentUser
}