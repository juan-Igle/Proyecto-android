package com.example.emergitech.data.repository

import android.net.Uri
import android.util.Log
import com.example.emergitech.core.Constants.POSTS
import com.example.emergitech.core.Constants.USERS
import com.example.emergitech.domain.model.Post
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.model.User
import com.example.emergitech.domain.repository.PostsRepository
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FieldValue
import com.google.firebase.storage.StorageReference
import kotlinx.coroutines.*
import kotlinx.coroutines.channels.awaitClose
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.callbackFlow
import kotlinx.coroutines.tasks.await
import java.io.File
import javax.inject.Inject
import javax.inject.Named

class PostsRepositoryImpl @Inject constructor(
    @Named(POSTS) private val postsRef: CollectionReference,
    @Named(USERS) private val usersRef: CollectionReference,
    @Named(POSTS) private val storagePostsRef: StorageReference,
): PostsRepository {

    override fun getPosts(): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.addSnapshotListener { snapshot, e ->

            GlobalScope.launch(Dispatchers.IO) {
                val postsResponse = if (snapshot != null) {
                    val posts = snapshot.toObjects(Post::class.java)

                    snapshot.documents.forEachIndexed { index, document ->
                        posts[index].id = document.id
                    }

                    val idUserArray = ArrayList<String>()

                    posts.forEach { post ->
                        idUserArray.add(post.idUser)
                    }

                    val idUserList = idUserArray.toSet().toList() // ELEMENTOS SIN REPETIR

                    idUserList.map { id ->
                        async {
                            val user = usersRef.document(id).get().await().toObject(User::class.java)!!
                            posts.forEach { post ->
                                if (post.idUser == id) {
                                    post.user = user
                                }
                            }

                            Log.d("PostsRepositoryImpl", "Id: ${id}")
                        }
                    }.forEach {
                        it.await()
                    }

                    Response.Success(posts)
                }
                else {
                    Response.Failure(e)
                }
                trySend(postsResponse)
            }

        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override fun getPostsByUserId(idUser: String): Flow<Response<List<Post>>> = callbackFlow {
        val snapshotListener = postsRef.whereEqualTo("idUser", idUser).addSnapshotListener { snapshot, e ->

            val postsResponse = if (snapshot != null) {
                val posts = snapshot.toObjects(Post::class.java)
                snapshot.documents.forEachIndexed { index, document ->
                    posts[index].id = document.id
                }

                Response.Success(posts)
            }
            else {
                Response.Failure(e)
            }
            trySend(postsResponse)

        }
        awaitClose {
            snapshotListener.remove()
        }
    }

    override suspend fun create(post: Post, file: File): Response<Boolean> {
        return try {
            // IMAGE
            val fromFile = Uri.fromFile(file)
            val ref = storagePostsRef.child(file.name)
            val uploadTask = ref.putFile(fromFile).await()
            val url = ref.downloadUrl.await()

            // DATA
            post.image = url.toString()
            postsRef.add(post).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun update(post: Post, file: File?): Response<Boolean> {
        return try {
            // IMAGE
            if (file != null) {
                val fromFile = Uri.fromFile(file)
                val ref = storagePostsRef.child(file.name)
                val uploadTask = ref.putFile(fromFile).await()
                val url = ref.downloadUrl.await()
                post.image = url.toString()
            }
            val map: MutableMap<String, Any> = HashMap()
            map["name"] = post.name
            map["description"] = post.description
            map["image"] = post.image
            map["category"] = post.category
            // DATA
            postsRef.document(post.id).update(map).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun delete(idPost: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).delete().await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun like(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update("likes", FieldValue.arrayUnion(idUser)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

    override suspend fun deleteLike(idPost: String, idUser: String): Response<Boolean> {
        return try {
            postsRef.document(idPost).update("likes", FieldValue.arrayRemove(idUser)).await()
            Response.Success(true)
        } catch (e: Exception) {
            e.printStackTrace()
            Response.Failure(e)
        }
    }

}