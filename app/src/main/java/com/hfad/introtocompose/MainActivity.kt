package com.hfad.introtocompose

import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.animation.core.updateTransition
import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.shape.CornerSize
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.foundation.text.KeyboardActions
import androidx.compose.material.DropdownMenu
import androidx.compose.material.DropdownMenuItem
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Slider
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Add
import androidx.compose.material.icons.filled.Remove
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableDoubleStateOf
import androidx.compose.runtime.mutableFloatStateOf
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.tooling.preview.PreviewParameter
import androidx.compose.ui.tooling.preview.PreviewParameterProvider
import androidx.compose.ui.unit.dp
import com.hfad.introtocompose.components.InputField
import com.hfad.introtocompose.ui.theme.Material2ThemeStarter
import com.hfad.introtocompose.util.calculateTip
import com.hfad.introtocompose.util.calculateTotalPerPerson
import com.hfad.introtocompose.util.isValidState
import com.hfad.introtocompose.widgets.RoundIconButton

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
             .padding(15.dp)
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
@Preview(showBackground = true)
@Composable
fun MainContent() {
    var billPerPerson by remember { mutableDoubleStateOf(0.0) }

    val totalBillState = remember { mutableStateOf("") }
    val numberOfSplitsState = remember { mutableIntStateOf(1) }
    val sliderPositionState = remember { mutableFloatStateOf(0f) }

    Column() {
        TopHeader(billPerPerson)
        BillForm(totalBillState, numberOfSplitsState, sliderPositionState) {
            billPerPerson = it
        }
    }
}
@Composable
fun BillForm(
    totalBillState: MutableState<String>,
    splitsState: MutableState<Int>,
    sliderPositionState: MutableState<Float>,
    modifier: Modifier = Modifier,
    updateTopHeaderValue: (Double) -> Unit
) {
    val tip = calculateTip(totalBillState, sliderPositionState)
    val tipPercentage = sliderPositionState.value * 100

    updateTopHeaderValue(calculateTotalPerPerson(totalBillState, splitsState, tip))

    val keyboardController = LocalFocusManager.current
//    val keyboardController = LocalSoftwareKeyboardController.current
    Surface(
        modifier = modifier
            .padding(2.dp)
            .fillMaxWidth(),
        shape = RoundedCornerShape(corner = CornerSize(8.dp)),
        border = BorderStroke(width = 1.dp, color = Color.LightGray)
    ) {
        Column(
            modifier = Modifier.padding(6.dp),
            verticalArrangement = Arrangement.Top,
            horizontalAlignment = Alignment.Start
        ) {
            //TEXT INPUT
            InputField(
                valueState = totalBillState.value,
                labelId = "Enter Bill",
                enabled = true,
                isSingleLine = true,
                onAction = KeyboardActions {
                    if(!isValidState(totalBillState)) return@KeyboardActions //return only from KeyboardActions
                    keyboardController.clearFocus()
//                    keyboardController?.hide()
                },
                onValueChange = { totalBillState.value = it }
            )
            //SPLIT ROW
            Row(
                modifier = Modifier.padding(3.dp),
            ) {
                Text(text = "Split", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                Spacer(modifier = Modifier.width(150.dp))
                Row(
                    modifier = Modifier.padding(horizontal = 3.dp),
                    horizontalArrangement = Arrangement.End
                ) {
                    RoundIconButton(imageVector = Icons.Default.Remove) {
                        if(splitsState.value > 1) {
                            splitsState.value -= 1
                        }
                    }
                    Text(
                        text = splitsState.value.toString(),
                        modifier = Modifier
                            .align(Alignment.CenterVertically)
                            .padding(start = 9.dp, end = 9.dp))

                    RoundIconButton(imageVector = Icons.Default.Add) {
                        splitsState.value += 1
                    }
                }
            }
            //TIP ROW
            Row(
                modifier = Modifier.padding(horizontal = 3.dp, vertical = 12.dp)
            ) {
                Text(text = "Tip", modifier = Modifier.align(alignment = Alignment.CenterVertically))
                Spacer(modifier = Modifier.width(200.dp))
                Text(text = String.format("%.2f$", tip), modifier = Modifier.align(alignment = Alignment.CenterVertically))
            }
            //TIP PERCENTAGE AND SLIDER
            Column(
                verticalArrangement = Arrangement.Center, horizontalAlignment = Alignment.CenterHorizontally
            ) {
                Text(text = String.format("%.1f%%", tipPercentage))
                Spacer(modifier = Modifier.height(14.dp))
                Slider(
                    modifier = Modifier.padding(start = 16.dp, end = 16.dp),
                    value = sliderPositionState.value,
                    onValueChange = {
                        sliderPositionState.value = it
                    },
                    steps = 9
                )
            }
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