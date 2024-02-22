package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.viewmodel.MainViewModel
@Composable
fun MainScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel,page:Int) {
    viewModel.loadPokes(page)
    val pokes: List<Poke> by viewModel.pokes.collectAsStateWithLifecycle()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                items(pokes) { poke ->
                    CardPoke(onNavSelected,viewModel,poke)
                }
            }
        )
    }
}
/*@Composable
fun MainScreen(onNavSelected: (String) -> Unit, viewModel: MainViewModel, page: Int) {
    var reloadTrigger by remember { mutableIntStateOf(0) }
    val pokesState = viewModel.pokes.collectAsState()
    LaunchedEffect(reloadTrigger) {
        viewModel.loadPokes(page)
    }
    LaunchedEffect(Unit) {
        while (true) {
            delay(500)
            reloadTrigger++
        }
    }
    val pokes = pokesState.value
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                itemsIndexed(pokes) { index, poke ->
                    CardPoke(onNavSelected, viewModel, poke)
                }
            }
        )
    }
}*/