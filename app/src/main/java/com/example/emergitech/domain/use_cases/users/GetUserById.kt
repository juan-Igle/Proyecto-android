package com.example.emergitech.domain.use_cases.users

import com.example.emergitech.domain.repository.UserRepository
import javax.inject.Inject

class GetUserById @Inject constructor(private val repository: UserRepository) {

    operator fun invoke(id: String) = repository.getUserById(id)
}