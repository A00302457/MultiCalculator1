package com.example.multicalculator.android

import android.os.Bundle
import android.widget.Button
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.runtime.MutableState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.textInputServiceFactory
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
                   // GreetingView(Greeting().greet())
                    CalcView()
                }
            }
        }
    }
}
@Composable
fun CalcView() {
    var displayText by remember { mutableStateOf("0") }
    var leftNumber by rememberSaveable { mutableStateOf(0.0) }
    var rightNumber by rememberSaveable { mutableStateOf(0.0) }
    var operation by rememberSaveable { mutableStateOf("") }
    var complete by rememberSaveable { mutableStateOf(false) }

    if (complete && operation != "") {

        var answer by rememberSaveable { mutableStateOf(0.0) }
        when (operation) {
            "+" -> answer = leftNumber + rightNumber
            "-" -> answer = leftNumber - rightNumber
            "*" -> answer = leftNumber * rightNumber
            "/" -> answer = leftNumber / rightNumber
        }
            displayText = answer.toString()


   } else if (operation != "" && !complete)
        displayText= rightNumber.toString()

    else
      displayText = leftNumber.toString()


    fun numberPress(btnNum: Int) {

        if (complete) {
            leftNumber = 0.0
            rightNumber = 0.0
            operation = ""
            complete = false
        }
        if (operation != "" && !complete)
            rightNumber = (rightNumber * 10) + btnNum
        if (operation == "" && !complete)
            leftNumber = (leftNumber * 10) + btnNum

    }

    fun operationPress(op: String) {
        if (!complete)
            operation = op
    }

    fun equalsPress() {
        complete = true
    }
    fun ac(){

            leftNumber = 0.0
            rightNumber = 0.0
            operation = ""
            complete = false

    }



    Column(
        modifier = Modifier
            .background(Color.LightGray)
            .padding(50.dp)
    ) {
        Row {
            CalcDisplay(onPress = displayText)
        }
        Row {
            Column {
                for (i in 7 downTo 1 step 3) {

                    CalcRow(onPress = { number -> numberPress(number) }, i, 3)

                }


                Row {
                    val equalsPress: () -> Unit = {
                        equalsPress()
                    }
                    CalcNumericButton(number = 0, onPress = { number ->
                        numberPress(number)
                    })
                    CalcEqualsButton(onPress =  {equalsPress() })
                    CalcAC(onPress = {ac()})

                }
            }

        Column {

            CalcOperationButton(operation = "+", onPress = { op ->
                operationPress(op)
            })
            CalcOperationButton(operation = "-", onPress = { op ->
                operationPress(op)
            })
            CalcOperationButton(operation = "*", onPress = { op ->
                operationPress(op)
            })
            CalcOperationButton(operation = "/", onPress = { op ->
                operationPress(op)
            })

        }
        }
    }
}



@Composable
fun CalcRow(onPress: (number: Int) -> Unit, startNum: Int, numButtons: Int) {
    val endNum = startNum + numButtons
    Row(modifier = Modifier.padding(0.dp)) {
        for (num in startNum until endNum)
            CalcNumericButton( number= num,onPress)
    }
}

@Composable
fun CalcDisplay(onPress: String) {
    //Text(text = "    ")
    Text(
        text = onPress,
        modifier = Modifier
            .height(50.dp)
            .padding(5.dp)
            .fillMaxWidth(1f)
            .border(1.dp, Color.Black)
    )

}

@Composable
fun CalcNumericButton(number: Int, onPress: (number: Int) -> Unit) {
    //display.value=""
    Button(
        onClick = { onPress(number) }, modifier = Modifier.padding(4.dp)
    ) {

        Text(text = number.toString())
    }

}
@Composable
fun CalcEqualsButton(onPress: () -> Unit ) {

    Button(onClick = { onPress() }, modifier = Modifier.padding(4.dp)) {
        Text(text = "=")
    }
}
@Composable
fun CalcAC(onPress: () -> Unit ) {

    Button(onClick = { onPress() }, modifier = Modifier.padding(4.dp)) {

        Text(text = "AC")
    }
}

@Composable
fun CalcOperationButton(operation: String, onPress: (operation: String) -> Unit) {
    Button(
        onClick = { onPress(operation)},
        modifier = Modifier.padding(4.dp)
    ) {
        Text(text = operation)
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


