package com.example.emergitech.domain.use_cases.posts

import com.example.emergitech.domain.repository.PostsRepository
import javax.inject.Inject

class GetPostsByIdUser @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke(idUser: String) = repository.getPostsByUserId(idUser)

}