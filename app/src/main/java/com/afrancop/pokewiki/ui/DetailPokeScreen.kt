package com.afrancop.pokewiki.ui

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.border
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.shape.RoundedCornerShape
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.clip
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.buildAnnotatedString
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.withStyle
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import coil.compose.rememberAsyncImagePainter
import com.afrancop.pokewiki.viewmodel.MainViewModel


@Composable
fun DetailPokeScreen(viewModel: MainViewModel) {

    val pokeSelected = viewModel.poke.value

    Column(
        Modifier.fillMaxSize(),
        horizontalAlignment = Alignment.CenterHorizontally,

    ) {

        Spacer(modifier = Modifier.size(12.dp))
        
        Image(
            painter = rememberAsyncImagePainter(pokeSelected?.sprite),
            contentDescription = "Imagen Pokemon",
            modifier = Modifier
                .align(Alignment.CenterHorizontally)
                .size(300.dp)
                .clip(shape = RoundedCornerShape(32.dp))
                .background(color = Color.White)
                .border(
                    width = 4.dp,
                    color = Color.Black,
                    shape = RoundedCornerShape(32.dp)
                )
        )

        Spacer(modifier = Modifier.size(6.dp))

        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Nombre:")
                }
                append(" ${pokeSelected?.name}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Tipo 1:")
                }
                append(" ${pokeSelected?.type1}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Tipo 2:")
                }
                append(" ${pokeSelected?.type2.takeIf { it?.isNotBlank() == true } ?: "No tiene"}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Habilidad 1:")
                }
                append(" ${pokeSelected?.skill1}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Habilidad 2:")
                }
                append(" ${pokeSelected?.skill2}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Habilidad Oculta 1:")
                }
                append(" ${pokeSelected?.skill3.takeIf { it?.isNotBlank() == true } ?: "No tiene"}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Habilidad Oculta 2:")
                }
                append(" ${pokeSelected?.skill4.takeIf { it?.isNotBlank() == true } ?: "No tiene"}")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Altura:")
                }
                append(" ${pokeSelected?.height}0cm")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
        Text(
            text = buildAnnotatedString {
                withStyle(style = SpanStyle(fontWeight = FontWeight.Bold)) {
                    append("Peso:")
                }
                append(" ${pokeSelected?.weight}g")
            },
            fontSize = 18.sp,
            modifier = Modifier
                .padding(start = 100.dp)
                .padding(1.dp)
                .fillMaxWidth()
        )
    }
}