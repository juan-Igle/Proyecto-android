package com.example.emergitech.domain.use_cases.posts

import com.example.emergitech.domain.repository.PostsRepository
import javax.inject.Inject

class DeleteLikePost @Inject constructor(private val repository: PostsRepository){

    suspend operator fun invoke(idPost: String, idUser: String) = repository.deleteLike(idPost, idUser)

}