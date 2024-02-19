package com.afrancop.pokewiki.viewmodel

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

    fun searchPokemonByName(pokemonName: String): Poke? {
        var poke: Poke? = null
        viewModelScope.launch(Dispatchers.IO) {
            poke = repository.findPoke(pokemonName)
        }
        return poke
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