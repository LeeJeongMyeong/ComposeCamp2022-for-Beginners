package com.example.artspace

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.*
import androidx.compose.material.Button
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.RectangleShape
import androidx.compose.ui.graphics.painter.Painter
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.artspace.ui.theme.ArtSpaceTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ArtSpaceTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colors.background
                ) {
                    ArtSpaceApp()
                }
            }
        }
    }
}

data class ArtData(
    var image: Painter,
    var image_title: String,
    var image_artist: String,
    var image_year: String,
)

@Composable
fun ArtSpaceApp() {
    val artDataList: ArrayList<ArtData> = ArrayList()
    artDataList.addAll(initArtData())

    var currentPosition by remember {
        mutableStateOf(0)
    }

    var currentData by remember {
        mutableStateOf(artDataList[currentPosition])
    }

    val mModifier = Modifier
        .fillMaxWidth()
        .wrapContentWidth(Alignment.CenterHorizontally)
        .border(
            1.dp,
            color = colorResource(id = R.color.black),
            shape = RectangleShape
        )



    Box(
        modifier = Modifier
            .fillMaxSize()
            .padding(start = 16.dp, end = 16.dp)
    ) {
        Column(
            modifier = Modifier.align(Alignment.Center),
        ) {

            SetImage(
                artData = currentData,
                mModifier
            )

            Spacer(modifier = Modifier.padding(16.dp))

            SetImageText(
                artData = currentData,
                mModifier
            )
        }

        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(8.dp)
                .align(Alignment.BottomCenter)
        ) {
            Button(
                onClick = {
                    if (currentPosition > 0) currentPosition -= 1
                    else currentPosition = artDataList.size - 1

                    currentData = artDataList[currentPosition]
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(end = 4.dp)

            ) {
                Text(text = "Previous")
            }

            Button(
                onClick = {
                    if (currentPosition < (artDataList.size - 1)) currentPosition += 1
                    else currentPosition = 0

                    currentData = artDataList[currentPosition]
                },
                modifier = Modifier
                    .weight(1f)
                    .padding(start = 4.dp)
            ) {
                Text(text = "Next")
            }
        }

    }
}

@Composable
fun SetImage(
    artData: ArtData,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier
            .wrapContentHeight(Alignment.CenterVertically),
//            .wrapContentHeight(Alignment.CenterVertically),
        elevation = 8.dp,
    ) {
        Image(
            painter = artData.image,
            contentDescription = artData.image_title,
            modifier = Modifier
                .padding(16.dp)
                .wrapContentWidth(Alignment.CenterHorizontally)
                .wrapContentHeight(Alignment.CenterVertically),
        )
    }
}

@Composable
fun SetImageText(
    artData: ArtData,
    modifier: Modifier = Modifier
) {
    Surface(
        modifier = modifier,
        elevation = 8.dp
    ) {
        Column(
            modifier = Modifier
                .padding(8.dp)
        ) {
            Text(
                text = artData.image_title,
                fontSize = 22.sp,
                fontWeight = FontWeight.Bold,
                textAlign = TextAlign.Justify
            )

            Text(
                text = stringResource(
                    id = R.string.image_description,
                    artData.image_artist,
                    artData.image_year
                ),
                textAlign = TextAlign.Justify,
                modifier = Modifier
                    .padding(top = 8.dp)
            )
        }
    }
}

@Composable
fun initArtData(): ArrayList<ArtData> {
    val tmpList: ArrayList<ArtData> = ArrayList()
    tmpList.add(
        ArtData(
            painterResource(id = R.drawable.starry_night),
            stringResource(id = R.string.starry_night),
            stringResource(id = R.string.starry_night_artist),
            stringResource(id = R.string.starry_night_year)
        )
    )
    tmpList.add(
        ArtData(
            painterResource(id = R.drawable.meisje_met_de_parel),
            stringResource(id = R.string.meisje_met_de_parel),
            stringResource(id = R.string.meisje_met_de_parel_artist),
            stringResource(id = R.string.meisje_met_de_parel_year)
        )
    )

    tmpList.add(
        ArtData(
            painterResource(id = R.drawable.broken_cat),
            stringResource(id = R.string.broken_cat),
            stringResource(id = R.string.unknown_artist),
            stringResource(id = R.string.unknown_year)
        )
    )

    tmpList.add(
        ArtData(
            painterResource(id = R.drawable.cat),
            stringResource(id = R.string.waiting_cat),
            stringResource(id = R.string.unknown_artist),
            stringResource(id = R.string.unknown_year)
        )
    )

    return tmpList
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    ArtSpaceTheme {
        ArtSpaceApp()
    }
}