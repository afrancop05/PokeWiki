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

    var ready: MutableState<Boolean> = mutableStateOf(true)

    fun loadPokes(page: Int) {
        ready.value = false
        viewModelScope.launch(Dispatchers.IO) {
            val newPokes: List<Poke> = repository.loadPokes(page)
            _pokes.value = newPokes
            ready.value = true
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
            _pokes.value = repository.findPoke(pokemonName.lowercase())
        }
    }

    fun favPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newPoke: Poke? = repository.favPoke(id)
            updatePoke(newPoke)
        }
    }

    fun unfavPoke(id: Int) {
        viewModelScope.launch(Dispatchers.IO) {
            val newPoke: Poke? = repository.unfavPoke(id)
            updatePoke(newPoke)
        }
    }

    private fun updatePoke(newPoke: Poke?) {
        if (newPoke != null) {
            val pokeList = _pokes.value.toMutableList()
            val listId: Int = pokeList.indexOfFirst { it.id == newPoke.id }
            if (listId > -1) {
                pokeList[listId] = newPoke
                _pokes.value = pokeList
            }
            if (poke.value != null && poke.value?.id == newPoke.id) {
                poke.value = newPoke
            }
        }
    }

    fun clearPokes() {
        _pokes.value = listOf()
    }
}