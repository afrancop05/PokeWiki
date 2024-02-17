package com.afrancop.pokewiki.navigation

import androidx.compose.foundation.layout.Row
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBar
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import com.afrancop.pokewiki.R
import java.lang.reflect.Modifier

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun MainTopBar() {
    TopAppBar(
        colors = TopAppBarDefaults.topAppBarColors(
            containerColor = MaterialTheme.colorScheme.primary,
            titleContentColor = MaterialTheme.colorScheme.primaryContainer,
        ),
        title = {
            Row {
                Icon(
                    painterResource(id = R.drawable.logo_pokewiki),
                    contentDescription = "",
                )
                Text(
                    text = "",
                    style = MaterialTheme.typography.titleLarge,
                    textAlign = TextAlign.Start,
                )
            }
        }
    )
}