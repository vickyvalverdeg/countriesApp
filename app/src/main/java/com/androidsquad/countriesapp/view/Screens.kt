package com.androidsquad.countriesapp.view

sealed class Screens (val route: String) {
    object SplashScreen: Screens("splash_screen")
}