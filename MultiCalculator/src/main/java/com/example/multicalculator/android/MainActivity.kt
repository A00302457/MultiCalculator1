package com.example.multicalculator.android

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.max
import com.example.multicalculator.Greeting


class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            MyApplicationTheme {
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    GreetingView(Greeting().greet())
                }
            }
        }
    }
}
@Composable
fun CalcView(){
   val displayText = mutableStateOf("0")


}
@Composable
fun CalcRow(display: MutableState<String>, startNum : Int, numButtons : Int){
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (num in startNum until numButtons)
        CalcNumericButton(number = num, display = display )

    }
}
@Composable
fun CalcDisplay(display: MutableState<String>){
    Text(
        text = display.value,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(1f)
    )

}
@Composable
fun CalcNumericButton(number : Int , display: MutableState<String>){
    Button(onClick = {}, modifier = Modifier.padding(4.dp)
    ) {
        Text(text = number.toString())
    }

}
@Composable
fun CalcEqualsButton(display: MutableState<String>){
    //Add padding 4 DP
    Button(onClick = {display.value = "0"}, modifier=Modifier.padding(4.dp))  {
        Text(text = "=" )

    }
}




@Composable
fun GreetingView(text: String) {
    Text(text = text)
}

@Preview
@Composable
fun DefaultPreview() {
    MyApplicationTheme {
        GreetingView("Hello, Android!")
    }
}
