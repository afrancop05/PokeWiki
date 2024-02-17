package com.afrancop.pokewiki.ui

import androidx.compose.foundation.layout.Column
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import com.afrancop.pokewiki.viewmodel.MainViewModel

@Composable
fun DetailPokeScreen(viewModel: MainViewModel) {
    Column {
        Text(text = "Pantalla Detalles de un Pokemon")
    }
}