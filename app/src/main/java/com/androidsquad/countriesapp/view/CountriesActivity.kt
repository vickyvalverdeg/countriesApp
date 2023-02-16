package com.androidsquad.countriesapp.view

import android.annotation.SuppressLint
import android.content.Intent
import android.os.Bundle
import android.util.Log
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.*
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material.*
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.geometry.Offset
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.Shadow
import androidx.compose.ui.layout.ContentScale
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.androidsquad.countriesapp.R
import com.androidsquad.countriesapp.model.Country
import com.androidsquad.countriesapp.utils.Utils
import java.util.ArrayList
import kotlin.random.Random

class CountriesActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        //los extras son para enviar y recibir datos entre actividades
        //entonces esta variable guarda los datos que trae el intent
        val extras = intent.extras
        setContent {
            // en la variable name se guardara el dato que queramos recuperar
            //en este caso se pregunta por la key 'continent' (que se crea en la activity que envia el dato)
            // y de tener un valor de tipo string lo guarda
            //la idea de esto es que podamos saber que continente presiono el usuario
            //ahora mismo no esta siendo usada
            val countries = extras?.getStringArrayList("countries")
            ViewCountriesContainer(countries)
        }
    }
}

@Composable
fun TopBarCountriesContainer() {
    val context = LocalContext.current
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
        backgroundColor = colorResource(id = R.color.background_top_bar),
        //tuve que agregar el navigation icon para poner el boton back, porque
        //para lo del manifest que te contaba es para una vesion antigua
        navigationIcon = {
            //en el onclick tengo que decirle a donde regresar
            IconButton(onClick = {
                //esta context.startActivity es la encargada de mostrar la actividad
                //que se le declare
                context.startActivity(
                    Intent(context,
                    MainActivity::class.java))
            }) {
                Icon(Icons.Filled.ArrowBack, "backIcon",
                tint = colorResource(id = R.color.white))
            }
        },
    )
}

@Composable
fun CardContainer(countries: ArrayList<String>?) {
    //numero random para generar la url de la imagen
    val number = Random.nextInt(1, 100)
    LazyColumn(
        contentPadding = PaddingValues(horizontal = 16.dp, vertical = 8.dp),
        modifier = Modifier
            .fillMaxSize()
            .background(color = colorResource(id = R.color.background))
            .padding(start = 0.dp, top = 0.dp, end = 0.dp, bottom = 50.dp)
    ) {
        //items es como hacer un for each
        if (countries != null) {
            items(countries.toMutableList()){country->
                val utils = Utils()
                val name = utils.getNameCountryByJson(country)
                val capital = utils.getCapitalByJson(country)
                val currency = utils.getCurrencyByJson(country)
                Log.i("Country", country)
                var countrySelected = Country(name, capital, currency)
                CountryCard(country = countrySelected)
            }
        }
    }
}

@Composable
fun CountryCard(country: Country) {
    Card(
        modifier = Modifier
            .padding(8.dp)
            .fillMaxWidth()
                //con sizeIn le doy el alto que tendra la card propiamente tal
                //en este caso 120dp porque ese el tamaño que sale en figma
            .sizeIn(maxHeight = 120.dp),
        elevation = 4.dp,
        shape = RoundedCornerShape(size = 12.dp)
    ) {
        //box principal que contendra imagen y textos
        //tambien se le da el alto, falta probar si con .fillMaxSize funciona
        Box(modifier = Modifier.height(120.dp)
        ) {
            Image(
                //este painter comentado es el clasico para pintar una imagen
                //painter = painterResource(id = country.image),
                //esta painter ocupa rememberAsyncImagePainter(URL) para cargar una imagen
                //desde una url de modos async
                painter = rememberAsyncImagePainter("https://picsum.photos/640/400/?random=1"),
                contentDescription = "test",
                contentScale = ContentScale.Crop,
                //aca le digo a la imagen que ocupe to do el espacio disponible
                //asi queda a tamaño completo de la card
                modifier = Modifier
                    .fillMaxWidth()
                    .fillMaxHeight()
            )
            //este box es solo para mostrar el lenguaje
            //tambien ocupa to do el tamaño disponible
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
                //con esto especifico donde debe mostrarse
                contentAlignment = Alignment.BottomStart) {
                Text(
                    //desde country.lenguaje recogo el valor del lenguaje
                    //tambien en style ocupo el TextStyle para darle estilo al texto
                    //en este style le di sombra para que haga mejor contraste con las imagenes
                    text = "Language " + "country.language",
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400,
                        color = colorResource(id = R.color.white), shadow = Shadow(Color.Black, offset = Offset(3.0f, 2f),
                            blurRadius = 0.5f))
                )
            }
            //en este box se da el tamaño maximo disponible
            //y su alineacion que es centro
            //tambien de agrega una columna para poder texto uno debajo del otro
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
            contentAlignment = Alignment.Center
            ){
                Column(horizontalAlignment = Alignment.CenterHorizontally) {
                    Text(
                        text = country.name.uppercase(),
                        style = TextStyle(fontSize = 20.sp, fontWeight = FontWeight.W400,
                            color = colorResource(id = R.color.white), shadow = Shadow(Color.Black, offset = Offset(3.0f, 2f),
                                blurRadius = 0.5f))
                    )
                    Text(
                        text = country.capital,
                        style = TextStyle(fontSize = 16.sp, fontWeight = FontWeight.W400,
                            color = colorResource(id = R.color.white), shadow = Shadow(Color.Black, offset = Offset(3.0f, 2f),
                                blurRadius = 0.5f))
                    )
                }

            }
            //ultimo box para mostrar la moneda
            //se le da la alineacion del final abajo
            Box(modifier = Modifier
                .padding(10.dp)
                .fillMaxSize(),
                contentAlignment = Alignment.BottomEnd
            ){
                Text(
                    text = "Currency " + country.currency,
                    style = TextStyle(fontSize = 12.sp, fontWeight = FontWeight.W400,
                        color = colorResource(id = R.color.white), shadow = Shadow(Color.Black, offset = Offset(3.0f, 2f),
                            blurRadius = 0.5f))
                )
            }


        }
    }
}

//@Preview(showBackground = true)
@SuppressLint("UnusedMaterialScaffoldPaddingParameter")
@Composable
fun ViewCountriesContainer(countries: ArrayList<String>?) {
    val scaffoldState = rememberScaffoldState()
    Scaffold(
        scaffoldState = scaffoldState,
        topBar = { TopBarCountriesContainer() },
        bottomBar = { BottomBarContainer() }
    ) {
        CardContainer(countries)
    }
}