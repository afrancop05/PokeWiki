package com.afrancop.pokewiki.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.FavoriteBorder
import androidx.compose.material.icons.twotone.Favorite
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
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
fun MainScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel,page:Int) {
    viewModel.loadPokes(page)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                val pokes: List<Poke> = viewModel.pokes.value
                itemsIndexed(pokes) { index,poke ->

                    var isFavorite by remember { mutableStateOf(false) }

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
                                    viewModel.pokes.value.get(index)
                                    viewModel.setPoke(index)
                                    onNavSelected(Destinations.DetailPokeScreen.route) }
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
                                    if (!viewModel.pokes.value.get(index).favorite) {
                                        viewModel.favPoke(index)
                                    }else{viewModel.unfavPoke(index)}
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
        )
    }
}