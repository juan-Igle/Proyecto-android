package com.example.emergitech.domain.use_cases.users

import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.repository.UserRepository
import javax.inject.Inject

class Update @Inject constructor(private val repository: UserRepository) {

    suspend operator fun invoke(user: User) = repository.update(user)
}