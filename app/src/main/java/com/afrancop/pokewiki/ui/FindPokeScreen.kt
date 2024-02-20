package com.afrancop.pokewiki.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import coil.compose.rememberAsyncImagePainter
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.navigation.Destinations
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun FindPokeScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel) {
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name: String by rememberSaveable { mutableStateOf("") }
        var selectedPoke: Poke? by rememberSaveable { mutableStateOf(null) }

        TextField(
            modifier = Modifier.padding(top = 30.dp),
            value = name,
            onValueChange = {
                try {
                    name = it
                } catch (e: NumberFormatException) {
                    if (it == "") name = ""
                }
            }
        )

        Button(onClick = {
            viewModel.searchPokemonByName(name)?.let { searchedPokemon ->
                selectedPoke = searchedPokemon
            }
        }) {
            Text("Enviar")
        }

        selectedPoke?.let { poke ->
            var isFavorite = poke.favorite

            Card(
                border = BorderStroke(2.dp, Color.Red),
                modifier = Modifier
                    .fillMaxWidth()
                    .height(100.dp)
                    .padding(8.dp)
            ) {
                Row(
                    verticalAlignment = Alignment.CenterVertically,
                    horizontalArrangement = Arrangement.SpaceBetween,
                    modifier = Modifier
                        .fillMaxSize()
                        .clickable {
                            viewModel.poke = poke
                            onNavSelected(Destinations.DetailPokeScreen.route)
                        }
                ) {
                    Image(
                        painter = rememberAsyncImagePainter(poke.sprite),
                        contentDescription = null,
                        modifier = Modifier.size(100.dp)
                    )
                    Text(text = poke.name)

                    IconButton(
                        onClick = {
                            isFavorite = !isFavorite
                            if (!poke.favorite) {
                                viewModel.favPoke(poke.id!!)
                            } else {
                                viewModel.unfavPoke(poke.id!!)
                            }
                        }
                    ) {
                        Icon(
                            if (isFavorite) Icons.TwoTone.Favorite else Icons.Default.FavoriteBorder,
                            contentDescription = if (isFavorite) "Eliminar de Favoritos" else "Añadir Favorito",
                            tint = Color.Red,
                            modifier = Modifier.size(30.dp)
                        )
                    }
                }
            }
        }
    }
}
