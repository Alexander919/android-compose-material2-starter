package com.hfad.introtocompose

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.hfad.introtocompose.ui.theme.Material2ThemeStarter

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApp {
                TopHeader()
            }
        }
    }
}

@Composable
fun MyApp(content: @Composable () -> Unit) {
    Material2ThemeStarter {
        Surface(
            color = MaterialTheme.colors.background
        ) {
            content()
        }
    }
}
@Preview
@Composable
fun TopHeader(totalPerPerson: Double = 0.0) {
     Surface(
         modifier = Modifier
             .fillMaxWidth()
             .height(150.dp)
             .clip(shape = RoundedCornerShape(corner = CornerSize(12.dp))),
//             .clip(shape = CircleShape.copy(topStart = CornerSize(12.dp), bottomStart = CornerSize(12.dp)))
//         shape = RoundedCornerShape(corner = CornerSize(12.dp))
//         color = Color(0xFFE9D7F7)
         color = MaterialTheme.colors.primary
     ) {
         Column(
             modifier = Modifier.padding(12.dp),
             verticalArrangement = Arrangement.Center,
             horizontalAlignment = Alignment.CenterHorizontally
         ) {
             Text(text = "Total Per Person", style = MaterialTheme.typography.h5)
             Text(text = String.format("$%.2f", totalPerPerson), style = MaterialTheme.typography.h4, fontWeight = FontWeight.ExtraBold)
         }
     }
}

//@Preview(showBackground = true)
//@Composable
//fun MyAppPreview() {
//    MyApp {
//        Text(text = "hello world")
//    }
//}