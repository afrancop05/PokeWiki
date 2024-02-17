package com.afrancop.pokewiki.navigation

import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.ui.DetailPokeScreen
import com.afrancop.pokewiki.ui.MainScreen
import com.afrancop.pokewiki.ui.PokeFavScreen
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun Navigation(navController: NavHostController) {
    val viewModel: MainViewModel = viewModel()
    val pokes: List<Poke> by viewModel.pokes.collectAsState()

    @Composable
    fun Navigation(navController: NavHostController, viewModel: MainViewModel) {
        NavHost(navController = navController, startDestination = Destinations.MainScreen.route) {
            composable(Destinations.MainScreen.route) { MainScreen ({ dest -> navController.navigate(dest) },viewModel) }
            composable(Destinations.PokeFavScreen.route) { PokeFavScreen ({ dest -> navController.navigate(dest) },viewModel) }
            composable(Destinations.DetailPokeScreen.route) { DetailPokeScreen(viewModel) }
        }
    }


}