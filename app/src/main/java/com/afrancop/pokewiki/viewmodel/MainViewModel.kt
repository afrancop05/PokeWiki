package com.afrancop.pokewiki.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.PokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokeRepository) : ViewModel() {

    private val _pokes: MutableStateFlow<List<Poke>> = MutableStateFlow(listOf())
    var pokes = _pokes.asStateFlow()

    var poke: MutableState<Poke?> = mutableStateOf(null)

    fun loadPokes(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            pokes = repository.loadPokes(page) as StateFlow<List<Poke>>
        }
    }

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            pokes = repository.loadFavorites() as StateFlow<List<Poke>>
        }
    }

    fun searchPokemonByName(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            poke.value = repository.findPoke(pokemonName.lowercase())
        }
    }

    fun favPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.favPoke(id)
        }
    }

    fun unfavPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.unfavPoke(id)
        }
    }
}