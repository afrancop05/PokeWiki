package com.afrancop.pokewiki.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.local.PokeRepository
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch

class MainViewModel(private val repository: PokeRepository) : ViewModel() {
    private val _pokes: MutableStateFlow<List<Poke>> = MutableStateFlow(listOf())
    var pokes = _pokes.asStateFlow()

    fun loadPokes() {
        viewModelScope.launch(Dispatchers.IO) {
            pokes = repository.loadPokes() as StateFlow<List<Poke>>
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