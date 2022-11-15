package com.example.lemonade

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.lemonade.ui.theme.LemonadeTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            LemonadeTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    LemonadeApp()
                }
            }
        }
    }
}

@Composable
fun LemonadeApp() {
    val modifier: Modifier = Modifier
        .fillMaxSize()
        .wrapContentSize(Alignment.Center)

    MakeLemonade(modifier)
}

@Composable
fun MakeLemonade(modifier: Modifier) {

    var step by remember {
        mutableStateOf(0)
    }

    val squeeze =
        if (step == 1) (2..4).random()
        else 0

    var squeezeResult =
        if (step == 1) 1
        else -1

    val textTitle = when (step) {
        0 -> stringResource(id = R.string.tap_lemon_tree)
        1 -> stringResource(id = R.string.tap_squeeze)
        2 -> stringResource(id = R.string.tap_drink)
        else -> stringResource(id = R.string.tap_start_again)
    }

    val image = when (step) {
        0 -> painterResource(id = R.drawable.lemon_tree)
        1 -> painterResource(id = R.drawable.lemon_squeeze)
        2 -> painterResource(id = R.drawable.lemon_drink)
        else -> painterResource(id = R.drawable.lemon_restart)
    }

    val description = when (step) {
        0 -> stringResource(id = R.string.image_remon_tree)
        1 -> stringResource(id = R.string.image_lemon)
        2 -> stringResource(id = R.string.image_lemonade)
        else -> stringResource(id = R.string.image_empty)
    }

    Column(
        modifier = modifier,
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        Text(
            text = textTitle,
            fontSize = 18.sp
        )

        Spacer(modifier = Modifier.height(16.dp))

        Image(
            painter = image,
            contentDescription = description,
            modifier = Modifier
                .clickable {
                    if (step == 1 && squeeze != squeezeResult) {
                        squeezeResult += 1
                    } else {
                        if (step < 3)
                            step += 1
                        else step = 0
                    }
                }
                .border(
                    2.dp,
                    color = Color(105, 205, 216),
                    shape = RoundedCornerShape(4.dp)
                )
        )
    }
}

@Preview(showBackground = true)
@Composable
fun LemonadePreview() {
    LemonadeTheme {
        LemonadeApp()
    }
}