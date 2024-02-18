package com.afrancop.pokewiki.viewmodel

import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
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

    private val _filteredPokes = MutableStateFlow<List<Poke>>(emptyList())
    var filteredPokes = _filteredPokes.asStateFlow()

    var position by mutableStateOf( -1 )

    fun selectPokeIndex(index: Int) {
        position = index
    }

    fun getCurrentPoke(): Poke? {
        if (position in 0 until pokes.value.size) {
            return pokes.value[position]
        }
        return null
    }

    fun loadPokes() {
        viewModelScope.launch(Dispatchers.IO) {
            pokes = repository.loadPokes() as StateFlow<List<Poke>>
        }
    }

    fun searchPokemonsByName(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            filteredPokes = repository.searchPokes(pokemonName) as StateFlow<List<Poke>>
        }
    }

    fun insertPoke(poke: Poke) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.insertPoke(poke)
        }
    }

    fun deletePoke(poke: Poke) {
        viewModelScope.launch(Dispatchers.IO) {
            repository.deletePoke(poke)
        }
    }
}