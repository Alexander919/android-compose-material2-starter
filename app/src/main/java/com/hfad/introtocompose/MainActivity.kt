package com.hfad.introtocompose

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.hfad.introtocompose.navigation.MovieNavigation
import com.hfad.introtocompose.ui.theme.Material2ThemeStarter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                MovieNavigation()
            }
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun MyApp(content: @Composable () -> Unit) {
    Material2ThemeStarter {
        content()
    }
}

@Preview
@Composable
fun DefaultPreview() {
    MyApp {
        MovieNavigation()
    }
}
