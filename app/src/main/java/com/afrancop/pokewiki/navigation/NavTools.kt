package com.afrancop.pokewiki.navigation

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.ArrowBack
import androidx.compose.material.icons.filled.ArrowForward
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.navigation.NavHostController
import androidx.navigation.compose.currentBackStackEntryAsState
import com.afrancop.pokewiki.R


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar(navController: NavHostController) {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = Color.White,
            titleContentColor = Color.Black,
        ),
        title = {
            Row(
                verticalAlignment = Alignment.CenterVertically,
                modifier = Modifier.fillMaxWidth()
            ) {
                Image(
                    painterResource(id = R.drawable.logo_pokewiki),
                    contentDescription = "Logo Aplicacion",
                    modifier = Modifier.size(48.dp)
                )
                Text(
                    text = " PokeWiki",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                    modifier = Modifier.padding(start = 8.dp)
                )
            }
        },
        actions = {
            IconButton(
                onClick = { navController.navigate(Destinations.PokeFavScreen.route) }
            ) {
                Icon(
                    imageVector = Icons.Default.Favorite,
                    contentDescription = "Favorito",
                    tint = Color.Red,
                    modifier = Modifier.size(35.dp)
                )
            }
            IconButton(
                    onClick = { navController.navigate(Destinations.FindPokeScreen.route) }
                    ) {
                Icon(
                    imageVector = Icons.Default.Search,
                    contentDescription = "Buscar",
                    tint = Color.Black,
                    modifier = Modifier.size(35.dp)
                )
            }
        }

    )
}

@Composable
fun MainBottomBar(navController:NavHostController,
    onLeftClicked: () -> Unit,
    onRightClicked: () -> Unit,
    onHomeClicked: () -> Unit,
    page: Int
) {
    val navDest = navController.currentBackStackEntryAsState().value?.destination?.route
    if (navDest  == Destinations.MainScreen.route) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onLeftClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowBack,
                    contentDescription = "Go Back",
                    tint = Color.Black
                )
            }
            Text(text = "${page}")
            IconButton(onClick = onRightClicked) {
                Icon(
                    imageVector = Icons.Default.ArrowForward,
                    contentDescription = "Go Forward",
                    tint = Color.Black
                )
            }
        }
    }
    if (navDest == Destinations.PokeFavScreen.route || navDest == Destinations.DetailPokeScreen.route || navDest == Destinations.FindPokeScreen.route) {
        Row(
            modifier = Modifier
                .fillMaxWidth()
                .padding(16.dp),
            verticalAlignment = Alignment.CenterVertically,
            horizontalArrangement = Arrangement.Center
        ) {
            IconButton(onClick = onHomeClicked) { // Agregamos el botón de inicio aquí
                Icon(
                    imageVector = Icons.Default.Home,
                    contentDescription = "Go Home",
                    tint = Color.Black
                )
            }
        }
    }
}

