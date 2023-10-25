package com.hfad.introtocompose

import android.annotation.SuppressLint
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.Icon
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountBox
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.hfad.introtocompose.ui.theme.Material2ThemeStarter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MainContent()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Material2ThemeStarter {
        Scaffold(
            topBar = {
                TopAppBar(
                    backgroundColor = Color.Magenta,
                    elevation = 5.dp
                ) {
                    Text(text = "Movies")
                }
            }
        ) {
            content()
        }
    }
}

@Composable
fun MainContent(movieList: List<String> = listOf("Avatar", "300", "Harry Potter", "Life", "bla", "bla bla")) {
    Column(modifier = Modifier.padding(12.dp)) {
        LazyColumn {
            items(items = movieList) { movie ->
                MovieRow(movie) {
                    Log.d("movie_row", "MainContent: $it")
                }
            }
        }
    }
}

@Composable
fun MovieRow(movie: String, clickedCard: (String) -> Unit) {
    Card(
        modifier = Modifier
            .padding(4.dp)
            .fillMaxWidth()
            .height(130.dp)
            .clickable {
                clickedCard.invoke(movie)
            },
        shape = RoundedCornerShape(16.dp),
        elevation = 6.dp
    ) {
        Row(
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Start
        ) {
            Surface(
                modifier = Modifier
                    .padding(12.dp)
                    .size(100.dp),
                shape = RectangleShape,
                elevation = 4.dp
            ) {
                Icon(imageVector = Icons.Default.AccountBox, contentDescription = "moview image" )
            }
            Text(text = movie)
        }
    }
}

@Preview
@Composable
fun CheckScaffold() {
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MainContent()
    }
}
