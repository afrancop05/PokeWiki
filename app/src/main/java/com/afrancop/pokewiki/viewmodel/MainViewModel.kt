package com.afrancop.pokewiki.viewmodel

import androidx.compose.runtime.MutableState
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.PokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokeRepository) : ViewModel() {

    private var _pokes: MutableStateFlow<List<Poke>> = MutableStateFlow(listOf())
    var pokes = _pokes.asStateFlow()

    var poke: MutableState<Poke?> = mutableStateOf(null)

    fun loadPokes(page: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newPokes: List<Poke> = repository.loadPokes(page)
            _pokes.value = newPokes
        }
    }

    fun loadFavorites() {
        viewModelScope.launch(Dispatchers.IO) {
            val newPokes: List<Poke> = repository.loadFavorites()
            _pokes.value = newPokes
        }
    }

    fun searchPokemonByName(pokemonName: String) {
        viewModelScope.launch(Dispatchers.IO) {
            poke.value = repository.findPoke(pokemonName.lowercase())
        }
    }

    fun favPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val poke: Poke? = repository.favPoke(id)
            if (poke != null) {
                val pokeList = _pokes.value.toMutableList()
                val listId: Int = pokeList.indexOfFirst { it.id == id }
                pokeList[listId] = poke
                _pokes.value = pokeList
            }
        }
    }

    fun unfavPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val poke: Poke? = repository.unfavPoke(id)
            if (poke != null) {
                val pokeList = _pokes.value.toMutableList()
                val listId: Int = pokeList.indexOfFirst { it.id == id }
                pokeList[listId] = poke
                _pokes.value = pokeList
            }
        }
    }
}