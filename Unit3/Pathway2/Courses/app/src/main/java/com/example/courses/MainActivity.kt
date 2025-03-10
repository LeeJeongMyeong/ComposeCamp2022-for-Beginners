package com.example.courses

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.grid.GridCells
import androidx.compose.foundation.lazy.grid.LazyVerticalGrid
import androidx.compose.foundation.lazy.grid.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Surface
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.courses.data.DataSource
import com.example.courses.model.Topic
import com.example.courses.ui.theme.CoursesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            CoursesApp()
        }
    }
}

@Composable
fun CoursesApp() {
    CoursesTheme {
        // A surface container using the 'background' color from the theme
        Surface(
            modifier = Modifier.fillMaxSize(), color = MaterialTheme.colors.background
        ) {
            CoursesList(topics = DataSource.topics)
        }
    }
}

@Composable
fun CoursesList(
    topics: List<Topic>,
    modifier: Modifier = Modifier
) {

    LazyVerticalGrid(
        columns = GridCells.Fixed(2),
        verticalArrangement = Arrangement.spacedBy(8.dp),
        horizontalArrangement = Arrangement.spacedBy(8.dp),
        modifier = Modifier
            .padding(top = 8.dp, start = 8.dp, end = 8.dp)
    ) {
        items(topics) { topic ->
            CoursesCard(topic = topic)
        }
    }
}

@Composable
fun CoursesCard(
    topic: Topic, modifier: Modifier = Modifier
) {

    Card {
        Row(modifier = Modifier.fillMaxWidth()) {
            Image(
                painter = painterResource(id = topic.image),
                contentDescription = stringResource(id = topic.title),
                modifier = Modifier
                    .width(68.dp)
                    .height(68.dp)
            )

            Column(
                modifier = Modifier.padding(top = 16.dp, start = 16.dp, end = 16.dp)
            ) {
                Text(
                    text = stringResource(id = topic.title), style = MaterialTheme.typography.body2
                )

                Row(
                    modifier = Modifier
                        .padding(top = 8.dp)
                        .wrapContentHeight()
                ) {
                    Image(
                        painter = painterResource(id = R.drawable.ic_grain),
                        contentDescription = "grain",
                        modifier = Modifier.size(16.dp)
                    )
                    Text(
                        text = topic.likes.toString(),
                        style = MaterialTheme.typography.caption,
                        modifier = Modifier.padding(start = 8.dp)

                    )
                }
            }
        }
    }
}


@Preview(showBackground = true)
@Composable
fun CoursesPreview() {
    CoursesTheme {
        CoursesList(topics = DataSource.topics)
    }
}