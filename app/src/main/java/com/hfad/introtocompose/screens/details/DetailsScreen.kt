package com.hfad.introtocompose.screens.details

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyRow
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.Divider
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.navigation.NavController
import coil.compose.rememberImagePainter
import com.hfad.introtocompose.model.Movie
import com.hfad.introtocompose.model.getMovies
import com.hfad.introtocompose.widgets.MovieRow

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun DetailsScreen(navController: NavController, movieId: String) {
    val moviesList = getMovies()
    var movie = moviesList.find {
        it.id == movieId
    }
    if (movie == null) {
        movie = moviesList[0]
    }
    Scaffold(topBar = {
        TopAppBar(backgroundColor = Color.LightGray, elevation = 5.dp) {
            Row {
                Icon(imageVector = Icons.Default.ArrowBack, contentDescription = "back arrow", modifier = Modifier.clickable {
                    navController.popBackStack()
                })
                Spacer(modifier = Modifier.width(10.dp))
                Text(text = "Details")
            }
        }
    }) {
        Surface(
            modifier = Modifier.fillMaxSize()
        ) {
            Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Top
            ) {
//                MovieRow(movie)
                Spacer(modifier = Modifier.height(8.dp))
                Divider()
                Text(text = "${movie.title} gallery")
                LazyRow() {
                    items(movie.images) {
                        Card(
                            modifier = Modifier
                                .padding(12.dp)
                                .size(240.dp),
                            elevation = 4.dp
                        ) {
                            Image(painter = rememberImagePainter(data = it), contentDescription = "movies poster")
                        }
                    }
                }
            }
        }

    }
}