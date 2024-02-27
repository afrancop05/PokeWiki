package com.afrancop.pokewiki.ui

import androidx.compose.foundation.BorderStroke
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.foundation.lazy.itemsIndexed
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import androidx.lifecycle.compose.collectAsStateWithLifecycle
import com.afrancop.pokewiki.data.local.Metrics
import com.afrancop.pokewiki.viewmodel.MainViewModel
@Composable
fun MetricasScreen(viewModel: MainViewModel){
    val metrics: List<Metrics> by viewModel.metrics.collectAsStateWithLifecycle()
    viewModel.loadMetrics()
    Column(
        modifier = Modifier
            .fillMaxSize()
            .padding(40.dp),
        horizontalAlignment = Alignment.CenterHorizontally
    ) {

        LazyColumn(
            modifier = Modifier.fillMaxSize(),
            content = {
                itemsIndexed(metrics) { index,metric->
                    Card(
                        border = BorderStroke(2.dp, Color.Red),
                        modifier = Modifier
                            .fillMaxWidth()
                            .height(100.dp)
                            .padding(8.dp),
                        elevation = CardDefaults.cardElevation(defaultElevation = 10.dp)
                    ) {
                        Row(
                            verticalAlignment = Alignment.CenterVertically,
                            horizontalArrangement = Arrangement.SpaceBetween,
                            modifier = Modifier
                                .fillMaxSize()
                        ) {

                            Text(text = "${metric.id}")
                            Text(text = metric.itemId)
                            Text(text = metric.itemType)
                        }
                    }
                }
            }
        )
    }
}