package com.example.emergitech.presentation.screens.posts.components

import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.ColorFilter
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.emergitech.domain.model.Post
import com.example.emergitech.presentation.navigation.DetailsScreen
import com.example.emergitech.presentation.screens.posts.PostsViewModel
import com.example.emergitech.R
import com.example.emergitech.presentation.ui.theme.IconColor
import com.example.emergitech.presentation.ui.theme.SurfaceCard
import com.example.emergitech.presentation.ui.theme.TextPrimary

@Composable
fun PostsCard(navController: NavHostController, post: Post, viewModel: PostsViewModel = hiltViewModel()) {

    Card(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        contentColor = TextPrimary,

        ) {
        Column() {
            AsyncImage(
                modifier = Modifier
                    .fillMaxWidth()
                    .height(170.dp),
                model = post.image,
                contentDescription = "",
                contentScale = ContentScale.Crop
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 10.dp),
                text = post.name,
                fontWeight = FontWeight.Bold,
                color = TextPrimary
            )
            Text(
                modifier = Modifier.padding(horizontal = 15.dp, vertical = 3.dp),
                text = post.user?.username ?: "",
                fontSize = 12.sp,
                color = TextPrimary
            )
            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 10.dp),
                text = post.description,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )

            Text(
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp, bottom = 10.dp),
                text = post.enlace,
                fontSize = 13.sp,
                maxLines = 2,
                color = Color.Gray
            )
            Row(
                modifier = Modifier.padding(start = 15.dp, bottom = 15.dp)
            ) {
                if (post.likes.contains(viewModel.currentUser?.uid)) {
                    Image(
                        modifier = Modifier
                            .size(23.dp)
                            .clickable {
                                viewModel.deleteLike(post.id, viewModel.currentUser?.uid ?: "")
                            },
                        painter = painterResource(id = R.drawable.like),
                        colorFilter = ColorFilter.tint(IconColor),
                        contentDescription = ""
                    )
                }
                else {
                    Image(
                        modifier = Modifier
                            .size(23.dp)
                            .clickable {
                                viewModel.like(post.id, viewModel.currentUser?.uid ?: "")
                            },
                        painter = painterResource(id = R.drawable.like_outline),
                        colorFilter = ColorFilter.tint(TextPrimary),
                        contentDescription = ""
                    )
                }

                Text(
                    modifier = Modifier.padding(start = 5.dp),
                    text = post.likes.size.toString(),
                    fontWeight = FontWeight.Bold,
                    fontSize = 17.sp
                )
            }
        }
    }

}