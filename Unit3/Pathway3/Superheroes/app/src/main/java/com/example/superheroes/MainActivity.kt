package com.example.superheroes

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.Card
import androidx.compose.material.MaterialTheme
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.superheroes.data.HeroesRepository.heroes
import com.example.superheroes.model.Hero
import com.example.superheroes.ui.theme.SuperheroesTheme

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            SuperheroesTheme {
                SuperheroesApp()
            }
        }
    }
}

@Composable
fun SuperheroesApp() {
    Scaffold(
        topBar = {
            SuperheroTopAppBar()
        }
    ) {
        LazyColumn(
            modifier = Modifier
                .background(MaterialTheme.colors.background)
                .padding(it),
//            verticalArrangement = Arrangement.spacedBy(8.dp)
        ) {
            items(heroes) { hero ->
                SuperheroCard(hero = hero)
            }
        }
    }
}

@Composable
fun SuperheroTopAppBar() {
    Text(
        text = stringResource(id = R.string.app_name),
        style = MaterialTheme.typography.h1,
        modifier = Modifier
            .height(56.dp)
            .fillMaxWidth()
            .wrapContentSize(Alignment.Center),
    )
}

@Composable
private fun SuperheroCard(
    hero: Hero,
    modifier: Modifier = Modifier
) {
    Card(
        modifier = Modifier
            .sizeIn(minHeight = 72.dp)
            .fillMaxWidth()
            .padding(start = 16.dp, end = 16.dp, bottom = 8.dp),
        elevation = 2.dp
    ) {
        Row(
            modifier = Modifier
                .padding(16.dp)
        ) {

            SuperheroInfo(
                heroNameRes = hero.nameRes,
                heroDescriptionRes = hero.descriptionRes,
                modifier = Modifier.weight(1f)
            )
            Spacer(modifier = Modifier.padding(16.dp))
            Image(
                painter = painterResource(id = hero.imageRes),
                contentDescription = stringResource(id = hero.descriptionRes),
                modifier = Modifier
                    .clip(MaterialTheme.shapes.small)
                    .size(72.dp)
            )
        }
    }
}

@Composable
private fun SuperheroInfo(
    heroNameRes: Int,
    heroDescriptionRes: Int,
    modifier: Modifier = Modifier
) {
    Column(
        modifier = modifier
//            .wrapContentSize()
//            .padding(end = 16.dp)
    ) {
        Text(
            text = stringResource(id = heroNameRes),
            style = MaterialTheme.typography.h3
        )

        Text(
            text = stringResource(id = heroDescriptionRes),
            style = MaterialTheme.typography.body1
        )
    }
}


@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    SuperheroesTheme {
        SuperheroesApp()
    }
}


@Preview(showBackground = true)
@Composable
fun DarkPreview() {
    SuperheroesTheme(darkTheme = true) {
        SuperheroesApp()
    }
}