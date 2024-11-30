package com.example.emergitech.di

import com.example.emergitech.core.Constants.POSTS
import com.example.emergitech.core.Constants.USERS
import com.example.emergitech.data.repository.AuthRepositoryImpl
import com.example.emergitech.data.repository.PostsRepositoryImpl
import com.example.emergitech.data.repository.UsersRepositoryImplement
import com.example.emergitech.domain.repository.AuthRepository
import com.example.emergitech.domain.repository.PostsRepository
import com.example.emergitech.domain.repository.UserRepository
import com.example.emergitech.domain.use_cases.auth.*
import com.example.emergitech.domain.use_cases.posts.*
import com.example.emergitech.domain.use_cases.users.*
import com.google.firebase.auth.FirebaseAuth
import com.google.firebase.firestore.CollectionReference
import com.google.firebase.firestore.FirebaseFirestore
import com.google.firebase.firestore.ktx.firestore
import com.google.firebase.ktx.Firebase
import com.google.firebase.storage.FirebaseStorage
import com.google.firebase.storage.StorageReference
import dagger.Module
import dagger.Provides
import dagger.hilt.InstallIn
import dagger.hilt.components.SingletonComponent
import javax.inject.Named

@Module
@InstallIn(SingletonComponent::class)
object AppModule {

    @Provides
    fun provideFirebaseFireStore(): FirebaseFirestore = Firebase.firestore

    @Provides
    fun provideFirebaseStorage(): FirebaseStorage = FirebaseStorage.getInstance()

    @Named(USERS)
    @Provides
    fun provideStorageUsersRef(storage: FirebaseStorage): StorageReference = storage.reference.child(USERS)

    @Named(USERS)
    @Provides
    fun providesUsersRef(db: FirebaseFirestore): CollectionReference = db.collection(USERS)

    @Named(POSTS)
    @Provides
    fun provideStoragePostRef(storage: FirebaseStorage): StorageReference = storage.reference.child(POSTS)

    @Named(POSTS)
    @Provides
    fun providesPostRef(db: FirebaseFirestore): CollectionReference = db.collection(POSTS)

    @Provides
    fun provideFirebaseAuth(): FirebaseAuth = FirebaseAuth.getInstance()

    @Provides
    fun provideAuthRepository(impl: AuthRepositoryImpl): AuthRepository = impl

    @Provides
    fun provideUsersRepository(impl: UsersRepositoryImplement): UserRepository = impl

    @Provides
    fun providePostsRepository(impl: PostsRepositoryImpl): PostsRepository = impl

    @Provides
    fun provideAuthUseCase(repository: AuthRepository) = AuthUseCases(
        getCurrentUser = GetCurrentUser(repository),
        login = Login(repository),
        logout = Logout(repository),
        singup = Singup(repository)
    )

    @Provides
    fun provideUsersUseCases(repository: UserRepository) = UsersUseCases(
        create = Create(repository),
        getUserById = GetUserById(repository),
        update = Update(repository),
        saveImage = SaveImage(repository)
    )

    @Provides
    fun providePostsUseCases(repository: PostsRepository) = PostsUseCases(
        create = CreatePost(repository),
        getPosts = GetPosts(repository),
        getPostsByIdUser = GetPostsByIdUser(repository),
        deletePost = DeletePost(repository),
        updatePost = UpdatePost(repository),
        likePost = LikePost(repository),
        deleteLikePost = DeleteLikePost(repository)
    )
}