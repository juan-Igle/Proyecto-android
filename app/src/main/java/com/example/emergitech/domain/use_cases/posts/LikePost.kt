package com.example.emergitech.domain.use_cases.posts

import com.example.emergitech.domain.repository.PostsRepository
import javax.inject.Inject

class LikePost @Inject constructor(private val repository: PostsRepository){

    suspend operator fun invoke(idPost: String, idUser: String) = repository.like(idPost, idUser)

}