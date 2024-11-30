package com.example.emergitech.domain.use_cases.posts

import com.example.emergitech.domain.repository.PostsRepository
import javax.inject.Inject

class GetPosts @Inject constructor(private val repository: PostsRepository) {

    operator fun invoke() = repository.getPosts()

}