package com.androidsquad.countriesapp.view

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.shape.CircleShape
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.Button
import androidx.compose.material.Scaffold
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidsquad.countriesapp.R
import com.androidsquad.countriesapp.model.Country
import androidx.compose.material.Icon
import androidx.compose.material.IconButton
import androidx.compose.material.Text
import androidx.compose.material.TopAppBar
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Menu
import androidx.compose.material.icons.filled.Person

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Wrapper()
        }
    }
}

/*@Composable
fun Greeting(name: String) {
    Text(text = "Hello $name!")
}

@Preview(showBackground = true)
@Composable
fun DefaultPreview() {
    Greeting("Android")
}*/

@Composable
fun TitleContainer(modifier: Modifier = Modifier) {
    /*Row(modifier = modifier) {
        Text(text = stringResource(R.string.title),
            fontSize = 36.sp,
            modifier = Modifier
                .fillMaxWidth()
                .wrapContentWidth(Alignment.CenterHorizontally)
                .padding(start = 16.dp, top = 16.dp))
    }*/

            TopAppBar(
                title = { Text(text = stringResource(R.string.title),
                    fontSize = 30.sp,
                    modifier = Modifier
                        .fillMaxWidth()
                        .wrapContentWidth(Alignment.CenterHorizontally)
                        .padding(start = 16.dp, top = 5.dp)) },

                actions = {
                    // RowScope here, so these icons will be placed horizontally
                    IconButton(onClick = { /* doSomething() */ }) {
                        Icon(Icons.Filled.Person, contentDescription = "Localized description")
                    }
                }
            )
}
@Composable
fun ListContainer(modifier: Modifier = Modifier){
    val arrayOfCountries = arrayOf(
        Country("Ecuador1", 1),
        Country("Ecuador2", 2),
        Country("Ecuador3", 3),
        Country("Ecuador4", 4),
        Country("Ecuador5", 5),
        Country("Ecuador6", 6),
        Country("Ecuador7", 7),
        Country("Ecuador8", 8),
        Country("Ecuador9", 9),
        Country("Ecuador10", 10)
    )
    val scrollState = rememberScrollState()
    // Column is a composable that places its children in a vertical sequence.
    Column(
        modifier = Modifier.verticalScroll(scrollState)
    )
    {
        for (country in arrayOfCountries){
            ItemContainer(country.name, country.image)
        }
    }
}
@Composable
fun ItemContainer(name: String, img:Int, modifier: Modifier = Modifier){
    Row(modifier = modifier) {
        Text(text = name)
        Image(
            //painter = painterResource(R.drawable.androidparty),
            painter = rememberAsyncImagePainter("https://media.istockphoto.com/id/1218071177/es/foto/monumento-a-la-l%C3%ADnea-ecuatorial-quito-ecuador.jpg?s=1024x1024&w=is&k=20&c=00tiBq_JgFyWDJk7pwGZGWWSV0kcB5-qa35Grlws75Q="),
            contentDescription = null,
            contentScale = ContentScale.Crop,
            modifier = Modifier
                .size(88.dp)
                .clip(CircleShape)
        )
    }
}
@Composable
fun ButtonContainer(modifier: Modifier = Modifier){
    Row(modifier = modifier){
        Button(onClick = { },
            modifier = Modifier.padding(10.dp)) {
            Text(text = "boton1")
        }
        Button(onClick = { },
            modifier = Modifier.padding(10.dp)) {
            Text(text = "boton2")
        }
        Button(onClick = { },
            modifier = Modifier.padding(10.dp)) {
            Text(text = "boton3")
        }
    }
}
@Preview(showBackground = true)
@Composable
fun Wrapper (modifier: Modifier = Modifier){
    Column(modifier = modifier) {
        TitleContainer()
        Column(modifier = modifier.height(400.dp)) {
            ListContainer()
        }
        ButtonContainer()
    }
}