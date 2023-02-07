package com.androidsquad.countriesapp.view

import android.os.Bundle
import android.widget.Toast
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.runtime.*
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.text.style.TextOverflow
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidsquad.countriesapp.R
import com.androidsquad.countriesapp.model.Country
import com.androidsquad.countriesapp.viewModel.MainViewModel

class MainActivity : ComponentActivity() {
    val mainViewModel by viewModels<MainViewModel>()
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            ViewContainer()
            // Greeting("Android")
            Surface(color = MaterialTheme.colors.background) {
                mainViewModel.getCountryList()
            }
        }
    }
}

@Composable
fun TopBarContainer(modifier: Modifier = Modifier) {
    val context= LocalContext.current
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
                Toast.makeText( context, " account clicked!", Toast.LENGTH_SHORT).show()
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
fun BottomBarContainer(modifier: Modifier = Modifier) {
    val selectedIndex = remember { mutableStateOf(0) }
    val context= LocalContext.current
    BottomNavigation(
        backgroundColor = colorResource(id = R.color.background_bottom_bar),
       elevation = 0.dp
    ) {
        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.dot_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 0),
            onClick = {
                Toast.makeText( context, "1 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 0

            })

        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.triangle_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 1),
            onClick = {
                Toast.makeText( context, "2 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 1
            })

        BottomNavigationItem(icon = {
            Image(
                painter = painterResource(id = R.drawable.triangle_icon),
                contentDescription = "Label",
                modifier = Modifier
                    .size(20.dp)
            )
        },
            label = { Text(text = "Label") },
            selected = (selectedIndex.value == 2),
            onClick = {
                Toast.makeText( context, "3 clicked!", Toast.LENGTH_SHORT).show()
                selectedIndex.value = 2
            })
    }
}

@Composable
fun Content(modifier: Modifier = Modifier) {
    val scrollState = rememberScrollState()
    val countryList = mutableListOf<Country>()
    countryList.add(Country("Asia", 1))
    countryList.add(Country("Africa", 1))
    countryList.add(Country("Europe", 1))
    countryList.add(Country("America", 1))
    countryList.add(Country("Ocenia", 1))
    countryList.add(Country("Ocenia", 1))
    countryList.add(Country("Ocenia", 1))
    countryList.add(Country("Ocenia", 1))
    countryList.add(Country("Ocenia", 1))
    countryList.add(Country("Ocenia", 1))

    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 50.dp)
            ) {
        items(countryList){model ->
            ItemContainer(model = model)
        }
    }
}

@Composable
fun ItemContainer(model: Country) {
    val context= LocalContext.current
    Card(modifier = Modifier
        .padding(8.dp)
        .fillMaxWidth()
        .clickable {  Toast.makeText( context, "clicked on: "+model.name, Toast.LENGTH_SHORT).show() },
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        Column(horizontalAlignment = Alignment.Start,) {
            Text(text = model.name,
                style = MaterialTheme.typography.h6,
                modifier = Modifier.padding(start = 16.dp))
            Text(text = "Test",
                style = MaterialTheme.typography.body1,
                modifier = Modifier.padding(start = 16.dp))

        }
        Row (horizontalArrangement = Arrangement.End){
            Image(
                painter = rememberAsyncImagePainter("https://media.istockphoto.com/id/1218071177/es/foto/monumento-a-la-l%C3%ADnea-ecuatorial-quito-ecuador.jpg?s=1024x1024&w=is&k=20&c=00tiBq_JgFyWDJk7pwGZGWWSV0kcB5-qa35Grlws75Q="),
                contentDescription = null,
                contentScale = ContentScale.Crop,
                modifier = Modifier
                    .size(88.dp)
            )
        }
    }
}






@Preview(showBackground = true)
@Composable
fun ViewContainer(modifier: Modifier = Modifier) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarContainer() },
        bottomBar = { BottomBarContainer() }
    ) { padding ->
        Content(modifier = Modifier.padding(padding))
    }
}