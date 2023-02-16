package com.androidsquad.countriesapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.view.Window
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidsquad.countriesapp.R
import com.androidsquad.countriesapp.model.Continent
import com.androidsquad.countriesapp.model.Continents
import com.androidsquad.countriesapp.utils.Utils
import com.androidsquad.countriesapp.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        requestWindowFeature(Window.FEATURE_NO_TITLE)
        setContent {
            Surface(color = MaterialTheme.colors.background) {
                val listContinents = mainViewModel.continentListResponse
                if (listContinents.isNotEmpty()) {
                    ViewContainer(listContinents)
                }
                mainViewModel.getCountryList()
            }
        }
    }
}

@Composable
fun TopBarContainer() {
    val context = LocalContext.current
    TopAppBar(
        title = {
            Text(
                text = stringResource(R.string.title),
                fontSize = 22.sp,
                textAlign = TextAlign.Center,
                color = colorResource(id = R.color.white),
                modifier = Modifier
                    .fillMaxWidth()
            )
        },
        backgroundColor = colorResource(id = R.color.background_top_bar),
        actions = {
            IconButton(onClick = {
                //Toast.makeText(context, " account clicked!", Toast.LENGTH_SHORT).show()
            }) {
                Image(
                    painter = painterResource(id = R.drawable.account_icon),
                    contentDescription = "Account",
                    modifier = Modifier
                        .size(20.dp)
                )
            }
        }
    )
}


@Composable
fun Content(continentList: List<Continents>) {

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 50.dp)
    ) {
        items(continentList[0].continents) { continents ->
            val imageId: Int=
            when (continents.code) {
                "AF" -> R.drawable.africa
                "AN" -> R.drawable.antarctica
                "AS" -> R.drawable.asia_taj_mahal
                "EU" -> R.drawable.europa
                "NA" -> R.drawable.northamerica
                "OC" -> R.drawable.oceania
                "SA" -> R.drawable.southamerica
                else ->{
                    R.drawable.asia_taj_mahal
                }
            }
            val continentWhithImage= Continent(continents.name, continents.countries, imageId, continents.code)
            ItemContainer(continent = continentWhithImage)
        }
    }
}

@Composable
fun ItemContainer(continent: Continent) {
    val context = LocalContext.current
    val utils = Utils();
    val countriesList = utils.countriesListToArrayString(continent)
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
            .clickable {
                context.startActivity(
                    Intent(
                        context,
                        CountriesActivity::class.java
                    )
                        .putExtra("continent", continent.name)
                        .putStringArrayListExtra(
                            "countries", countriesList
                        )
                )
            },
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start) {
            Text(
                text = continent.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp, top = 17.dp)
            )
            Text(
                text = continent.countries.size.toString() + " countries",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 16.dp)
            )

        }
        Row(horizontalArrangement = Arrangement.End) {
            Image(
                painter = painterResource(id = continent.image!!),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(80.dp)
            )
        }
    }
}

@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewContainer(continentList: List<Continents>) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarContainer() },
        bottomBar = { BottomBarContainer() }
    ) {
        Content(continentList)
    }
}