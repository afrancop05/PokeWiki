package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afrancop.pokewiki.data.local.Metrics
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun PokeFavScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel) {
    val newMetric = Metrics( null,"004", "fav poke screen")
    viewModel.insertMetric(newMetric)
    val pokes: List<Poke> by viewModel.pokes.collectAsStateWithLifecycle()
    viewModel.loadFavorites()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                itemsIndexed(pokes) { index,poke ->
                    CardPoke(onNavSelected,viewModel,poke)
                }
            }
        )
    }

}