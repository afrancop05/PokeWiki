package com.afrancop.pokewiki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import com.afrancop.pokewiki.ui.DetailPokeScreen
import com.afrancop.pokewiki.ui.MainScreen
import com.afrancop.pokewiki.ui.PokeFavScreen
import com.afrancop.pokewiki.viewmodel.MainViewModel


@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel) {

    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {

        composable(Destinations.MainScreen.route) {
            MainScreen({ dest -> navController.navigate(dest) },viewModel)
        }

        composable(Destinations.PokeFavScreen.route) {
            PokeFavScreen({ dest -> navController.navigate(dest) }, viewModel)
        }

        composable(Destinations.DetailPokeScreen.route) { DetailPokeScreen(viewModel) }

    }
}
