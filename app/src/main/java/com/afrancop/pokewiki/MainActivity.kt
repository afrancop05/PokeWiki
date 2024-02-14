package com.afrancop.pokewiki

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxHeight
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.core.text.isDigitsOnly
import com.afrancop.pokewiki.data.local.LocalPokeDao
import com.afrancop.pokewiki.data.remote.ApiService
import com.afrancop.pokewiki.data.remote.RetrofitBuilder
import com.afrancop.pokewiki.ui.theme.PokeWikiTheme

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
                    TestingScreen()
                }
            }
        }
    }
}

@Composable
fun TestingScreen() {
    Column(
        modifier = Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {
        var id: Int by rememberSaveable { mutableIntStateOf(0) }
        TextField(
            modifier = Modifier.padding(top = 30.dp),
            value = "$id",
            onValueChange = {
                if (it.isDigitsOnly()) {
                    id = it.toInt();
                }
        })
        Button(onClick = {
            /*TODO*/
        }) {
            Text("Enviar")
        }
        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {

        })
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    PokeWikiTheme {
        TestingScreen()
    }
}