package com.example.emergitech.presentation.screens.new_post

import android.content.Context
import android.util.Log
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.emergitech.R
import com.example.emergitech.domain.model.Post
import com.example.emergitech.domain.model.Response
import com.example.emergitech.domain.use_cases.auth.AuthUseCases
import com.example.emergitech.domain.use_cases.posts.PostsUseCases
import com.example.emergitech.presentation.utils.ComposeFileProvider
import com.example.emergitech.presentation.utils.ResultingActivityHandler
import dagger.hilt.android.lifecycle.HiltViewModel
import dagger.hilt.android.qualifiers.ApplicationContext
import kotlinx.coroutines.launch
import java.io.File
import javax.inject.Inject

@HiltViewModel
class NewPostViewModel @Inject constructor(
    @ApplicationContext private val context: Context,
    private val postsUsesCases: PostsUseCases,
    private val authUseCases: AuthUseCases

): ViewModel() {

    var state by mutableStateOf(NewPostState())

    //FILE
    var file: File? = null

    val resultingActivityHandler = ResultingActivityHandler()

    //RESPONSE
    var createPostResponse by mutableStateOf<Response<Boolean>?>(null)
        private set

    //USER SESSION
    val currenUSer = authUseCases.getCurrentUser()

    val radioOptions = listOf(
        CategoryRadioButton("Inteligencia Artificial", R.drawable.icon_ia),
        CategoryRadioButton("Realidad Aumentada", R.drawable.icon_ar),
        CategoryRadioButton("Realidad Virtual", R.drawable.icon_vr),
        CategoryRadioButton("BlockChain", R.drawable.icon_bc),
        CategoryRadioButton("Robótica Autónoma", R.drawable.icon_ra)

    )

    fun createPost(post: Post) = viewModelScope.launch {

        if(file != null){
            createPostResponse = Response.Loading
            val result = postsUsesCases.create(post, file!!)
            Log.d("resultado", "result: ${result}")
            createPostResponse = result
        }else{
            println("valor de file: ${file}")
        }


    }

    fun onNewPost(){
       val post = Post(
           name = state.name,
           description = state.description,
           category = state.category,
           idUser = currenUSer?.uid!!
       )
        createPost(post = post)

    }

    fun clearForm(){
        state = state.copy(
            name = "",
            category = "",
            description = "",
            image = ""
        )
    }

    fun pickImage() = viewModelScope.launch {
        val result = resultingActivityHandler.getContent("image/*")
        if(result != null){
            file = ComposeFileProvider.createFileFromUri(context, result)
            state = state.copy(image = result.toString())
        }

    }

    fun takePhoto() = viewModelScope.launch {
        val result = resultingActivityHandler.takePicturePreview()
        if(result != null){
            state = state.copy(image = ComposeFileProvider.getPathFromBitmap(context, result))
            file = File(state.image)

        }
    }

    fun onNameInput(name:String){
        state = state.copy(name = name)
    }

    fun onCategoryInput(category: String){
        state = state.copy(category = category)
    }

    fun onDescriptionInput(description : String){
        state = state.copy(description = description)
    }

    fun onImageInput(image: String){
        state = state.copy(image = image)
    }


}

data class CategoryRadioButton(
    var category: String,
    var image: Int
)