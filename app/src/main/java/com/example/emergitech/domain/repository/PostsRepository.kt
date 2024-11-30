package com.example.emergitech.domain.repository

import com.example.emergitech.domain.model.Post
import com.example.emergitech.domain.model.Response
import kotlinx.coroutines.flow.Flow
import java.io.File

interface PostsRepository {

    suspend fun create(post: Post, file: File): Response<Boolean>
    fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>>
    suspend fun update(post: Post, file: File?): Response<Boolean>
    suspend fun delete(idPost: String): Response<Boolean>
    fun getPosts(): Flow<Response<List<Post>>>
    suspend fun like(idPost: String, idUser: String): Response<Boolean>
    suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean>
}