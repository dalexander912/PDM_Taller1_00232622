package com.pdm0126.taller1_00232622.ui.components

import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color

@Composable
fun OptionButton(
  option: String,
  selectedAnswer: String?,
  correctAnswer: String,
  onAnswerSelected: (option: String) -> Unit
) {
  val buttonColor = when {
    selectedAnswer == null -> Color.DarkGray

    option == correctAnswer -> Color.Green

    option == selectedAnswer &&
    option != correctAnswer -> Color.Red

    else -> Color.DarkGray
  }
  Button( onClick = { onAnswerSelected(option) },
    modifier = Modifier.fillMaxWidth(),
    colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
  ) { Text(text = option, color = Color.White) }
}