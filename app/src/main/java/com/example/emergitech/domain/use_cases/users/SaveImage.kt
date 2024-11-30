package com.example.emergitech.domain.use_cases.users

import com.example.emergitech.domain.repository.UserRepository
import java.io.File
import javax.inject.Inject

class SaveImage @Inject constructor(private val repository: UserRepository){

    suspend operator fun invoke(file: File) = repository.saveImage(file)
}