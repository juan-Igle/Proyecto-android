package com.example.emergitech.presentation.screens.my_post.components

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Edit
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.hilt.navigation.compose.hiltViewModel
import androidx.navigation.NavHostController
import coil.compose.AsyncImage
import com.example.emergitech.domain.model.Post
import com.example.emergitech.presentation.navigation.DetailsScreen
import com.example.emergitech.presentation.screens.my_post.MyPostsViewModel
import com.example.emergitech.presentation.ui.theme.IconColor
import com.example.emergitech.presentation.ui.theme.Red700
import com.example.emergitech.presentation.ui.theme.TextPrimary

@Composable
fun MyPostsCard(navController: NavHostController, post: Post, viewModel: MyPostsViewModel = hiltViewModel()) {

    Card(
        modifier = Modifier
            .padding(top = 0.dp, bottom = 15.dp)
            .clickable {
                navController.navigate(route = DetailsScreen.DetailPost.passPost(post.toJson()))
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(20.dp),
        contentColor = Color.White,

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
                modifier = Modifier.padding(start = 15.dp, end = 15.dp, top = 3.dp),
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
                modifier = Modifier.fillMaxWidth(),
                horizontalArrangement = Arrangement.SpaceBetween
            ) {
                IconButton(
                    onClick = {
                        navController.navigate(route = DetailsScreen.UpdatePost.passPost(post.toJson()))
                    }
                ) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.Edit,
                        contentDescription = "",
                        tint = IconColor
                    )
                }
                IconButton(onClick = { viewModel.delete(post.id) }) {
                    Icon(
                        modifier = Modifier.size(25.dp),
                        imageVector = Icons.Default.Delete,
                        contentDescription = "",
                        tint = Red700
                    )
                }
            }

        }
    }

}