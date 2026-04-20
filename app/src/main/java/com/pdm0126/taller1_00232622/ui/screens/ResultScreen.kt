package com.pdm0126.taller1_00232622.ui.screens

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.pdm0126.taller1_00232622.R

@Composable
fun ResultScreen(onClickScreen: () -> Unit, score: Int) {

  val finalMessage = when (score) {
    0 -> stringResource(R.string.final_message1)
    1 -> stringResource(R.string.final_message2)
    2 -> stringResource(R.string.final_message3)
    3 -> stringResource(R.string.final_message4)
    else -> ""
  }

  Column(
    modifier = Modifier.safeContentPadding().fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = "Obtuviste $score de 3",
      modifier = Modifier.padding(16.dp),
      fontSize = 32.sp,
      fontWeight = FontWeight.Bold
    )
    Text(text = finalMessage, fontSize = 16.sp, modifier = Modifier.padding(16.dp))
    Spacer(Modifier.padding(64.dp))
    Button(
      onClick = onClickScreen,
      colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
    ) { Text(text = "Reiniciar Quiz", fontSize = 24.sp, color = Color.White) }
  }
}