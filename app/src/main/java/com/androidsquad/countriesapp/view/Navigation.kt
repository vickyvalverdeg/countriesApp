package com.androidsquad.countriesapp.view

import androidx.compose.runtime.Composable
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController

@Composable
fun Navigation (){
    val navController = rememberNavController()
    NavHost(navController = navController,
        startDestination = Screens.SplashScreen.route){
        composable(Screens.SplashScreen.route){
            SplashScreen(navController)
        }
    }
}