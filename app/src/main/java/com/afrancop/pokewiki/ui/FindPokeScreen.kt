package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afrancop.pokewiki.data.local.Metrics
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun FindPokeScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel) {
    val newMetric = Metrics( null,"002", "find poke screen")
    viewModel.insertMetric(newMetric)
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var name: String by rememberSaveable { mutableStateOf("") }

        TextField(
            modifier = Modifier
                .padding(top = 10.dp)
                .clip(RoundedCornerShape(32.dp)),
            value = name,
            onValueChange = {
                if (name != it) {
                    name = it
                    if (name.isNotEmpty()) viewModel.searchPokemonByName(name)
                    else viewModel.clearPokes()
                }
            }
        )

        val foundPokes: List<Poke> by viewModel.pokes.collectAsStateWithLifecycle()

        Spacer(modifier = Modifier.size(12.dp))

        LazyColumn(
            modifier = Modifier.fillMaxHeight(),
            content = {
                items(foundPokes) {poke ->
                    CardPoke(onNavSelected,viewModel, poke)
                }
            }
        )
    }

}
