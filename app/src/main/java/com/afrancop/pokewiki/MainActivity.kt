package com.afrancop.pokewiki

import android.content.Context
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.items
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Delete
import androidx.compose.material.icons.filled.Favorite
import androidx.compose.material.icons.filled.Info
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.Icon
import androidx.compose.material3.IconButton
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewModelScope
import com.afrancop.pokewiki.data.local.Poke
import com.afrancop.pokewiki.data.PokeRepository
import com.afrancop.pokewiki.data.remote.RemotePokeDataSource
import com.afrancop.pokewiki.data.remote.RetrofitBuilder
import com.afrancop.pokewiki.data.remote.toLocalEntity
import com.afrancop.pokewiki.ui.theme.PokeWikiTheme
import com.afrancop.pokewiki.viewmodel.MainViewModel
import kotlinx.coroutines.launch
import java.lang.NumberFormatException

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            PokeWikiTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {
                    /*val viewModel = MainViewModel(PokeRepository(LocalContext.current))
                    viewModel.loadPokes()
                    TestingScreen(viewModel)*/
                    PokeApp()
                }
            }
        }
    }
}
/*


@Composable
fun TestingScreen(viewModel: MainViewModel) {

    val pokes by viewModel.pokes.collectAsState();

    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        val context: Context = LocalContext.current
        var id: Int by rememberSaveable { mutableIntStateOf(0) }
        TextField(
            modifier = Modifier.padding(top = 30.dp),
            value = "$id",
            onValueChange = {
                try {
                    id = it.toInt()
                } catch (_e: NumberFormatException) { // Nada
                    if (it == "") id = 0
                }
            })
        Button(onClick = {
            val remote = RemotePokeDataSource(RetrofitBuilder.apiService)
            viewModel.viewModelScope.launch {
                val newPoke: Poke = remote.getPokemon(id).toLocalEntity()
                viewModel.insertPoke(newPoke)
                viewModel.loadPokes()
            }
        }) {
            Text("Enviar")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                items(pokes) { poke ->
                    Card(
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(30.dp)
                    ) {
                        Row(verticalAlignment = Alignment.CenterVertically) {
                            Text(text = "${poke.id}: ${poke.name}")
                            IconButton(onClick = {  }) {
                                Icon(Icons.Filled.Favorite, contentDescription = "Añadir Favorito")
                            }
                            IconButton(onClick = { viewModel.deletePoke(poke) }) {
                                Icon(Icons.Filled.Delete, contentDescription = "Borrar Pokemon")
                            }
                            IconButton(onClick = { }) {
                                Icon(Icons.Filled.Info, contentDescription = "Detalles Pokemon")
                            }
                        }
                    }
                }
            }
        )
    }

}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokeWikiTheme {
        val viewModel: MainViewModel = MainViewModel(PokeRepository(LocalContext.current))
        TestingScreen(viewModel)
    }
}
*/
