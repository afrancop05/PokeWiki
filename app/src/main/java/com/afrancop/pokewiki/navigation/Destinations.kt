package com.afrancop.pokewiki.navigation

sealed class Destinations(val route: String) {

    object MainScreen: Destinations("home")
    object PokeFavScreen: Destinations("pokefav")
    object DetailPokeScreen: Destinations("details")
    object FindPokeScreen: Destinations("find")
}