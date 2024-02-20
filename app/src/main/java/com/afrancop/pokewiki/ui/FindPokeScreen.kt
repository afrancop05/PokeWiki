package com.afrancop.pokewiki.ui

import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
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
            modifier = Modifier
                .padding(top = 30.dp)
                .clip(RoundedCornerShape(32.dp)),
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

        Spacer(modifier = Modifier.size(8.dp))

        Button(onClick = {
            viewModel.searchPokemonByName(name)
        }, colors = ButtonDefaults.buttonColors(Color.White),
            modifier = Modifier.border(
                width = 2.dp,
        color = Color.Red,
        shape = RoundedCornerShape(64.dp)
        )
        ) {
            Text("Buscar", color = Color.Black)
        }

        selectedPoke?.let {
            CardPoke(onNavSelected,viewModel, selectedPoke)
        }

    }

}
