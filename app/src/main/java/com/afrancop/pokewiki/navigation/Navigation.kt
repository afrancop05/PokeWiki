package com.afrancop.pokewiki.navigation

import androidx.compose.runtime.Composable
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afrancop.pokewiki.ui.DetailPokeScreen
import com.afrancop.pokewiki.ui.FindPokeScreen
import com.afrancop.pokewiki.ui.MainScreen
import com.afrancop.pokewiki.ui.PokeFavScreen
import com.afrancop.pokewiki.viewmodel.MainViewModel


@Composable
fun Navigation(navController: NavHostController, viewModel: MainViewModel, page: Int) {

    NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {

        composable(Destinations.MainScreen.route) {
            MainScreen({ dest -> navController.navigate(dest) },viewModel,page)
        }

        composable(Destinations.PokeFavScreen.route) {
            PokeFavScreen({ dest -> navController.navigate(dest) }, viewModel)
        }

        composable(Destinations.DetailPokeScreen.route) { DetailPokeScreen(viewModel) }

        composable(Destinations.FindPokeScreen.route) { FindPokeScreen({ dest -> navController.navigate(dest) },viewModel) }

    }
}
