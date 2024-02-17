package com.afrancop.pokewiki

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.afrancop.pokewiki.data.local.PokeRepository
import com.afrancop.pokewiki.navigation.MainTopBar
import com.afrancop.pokewiki.navigation.Navigation
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun PokeApp() {

    val viewModel = MainViewModel(PokeRepository(LocalContext.current))
    viewModel.loadPokes()
    val navController = rememberNavController()

    Scaffold(
        topBar = { MainTopBar(navController) }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Navigation(navController, viewModel)
        }
    }
}

