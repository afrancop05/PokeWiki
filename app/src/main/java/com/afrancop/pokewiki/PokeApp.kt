package com.afrancop.pokewiki

import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.navigation.compose.rememberNavController
import com.afrancop.pokewiki.data.PokeRepository
import com.afrancop.pokewiki.navigation.Destinations
import com.afrancop.pokewiki.navigation.MainBottomBar
import com.afrancop.pokewiki.navigation.MainTopBar
import com.afrancop.pokewiki.navigation.Navigation
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun PokeApp() {
    val viewModel = MainViewModel(PokeRepository(LocalContext.current))
    var page: Int by remember { mutableIntStateOf(0) }
    viewModel.loadPokes(page)
    val navController = rememberNavController()
    val colorBg = Color(android.graphics.Color.parseColor("#d8cf05"))

    Scaffold(
        topBar = { MainTopBar(navController) },
        bottomBar = {
                MainBottomBar(
                    navController,
                    onLeftClicked = {
                        if (page > 0) {
                            page--
                        }
                    },
                    onRightClicked = { page++ },
                    onHomeClicked = {navController.navigate(Destinations.MainScreen.route)},
                    page
                )
        }
    ) { innerPadding ->
        Column(
            modifier = Modifier.padding(innerPadding).background(colorBg),
            verticalArrangement = Arrangement.spacedBy(16.dp)
        ) {
            Navigation(navController, viewModel,page)
        }
    }
}


