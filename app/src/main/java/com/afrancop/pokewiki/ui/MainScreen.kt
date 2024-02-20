package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.afrancop.pokewiki.data.local.Poke
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
                    CardPoke(onNavSelected,viewModel,poke)
                }
            }
        )
    }
}