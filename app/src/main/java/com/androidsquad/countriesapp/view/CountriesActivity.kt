package com.androidsquad.countriesapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.androidsquad.countriesapp.R
import com.androidsquad.countriesapp.model.Country

class CountriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        val extras = intent.extras
        setContent {
            val name = extras?.getString("continent")
            ViewCountriesContainer()
        }
    }
}

@Composable
fun TopBarCountriesContainer() {
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.country_title),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                modifier = Modifier.fillMaxWidth()
            )
        },
        backgroundColor = colorResource(id = R.color.background_top_bar)
    )
}

@Composable
fun CardContainer(modifier: Modifier = Modifier) {
    val countryList2= mutableListOf<Country>()
    countryList2.add(Country("Chile", 1))
    countryList2.add(Country("Ecuador", 2))
    countryList2.add(Country("Perú", 3))

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 50.dp)
    ) {
        items(countryList2){country->
            CountryCard(country = country)
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth(),
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Column(horizontalAlignment = Alignment.CenterHorizontally) {
            Text(
                text = country.name,
                style = MaterialTheme.typography.h6
            )
            Text(
                text = "Capital",
                style = MaterialTheme.typography.h6
            )

        }
    }
}

@Preview(showBackground = true)
@Composable
fun ViewCountriesContainer(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarCountriesContainer() },
        bottomBar = { BottomBarContainer() }
    ) { padding ->
        CardContainer(modifier = Modifier.padding(padding))
    }
}