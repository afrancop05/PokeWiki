package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.unit.dp
import com.afrancop.pokewiki.data.local.Poke
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

        val selectedPoke: Poke? = viewModel.poke.value

        Button(onClick = {
            viewModel.searchPokemonByName(name)
        }) {
            Text("Enviar")
        }

        selectedPoke?.let {
            CardPoke(onNavSelected,viewModel, selectedPoke)
        }
    }
}
