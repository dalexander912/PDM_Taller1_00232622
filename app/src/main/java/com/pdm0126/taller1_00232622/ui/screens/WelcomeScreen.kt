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
fun WelcomeScreen(onClickScreen: () -> Unit) {
  Column(
    modifier = Modifier.safeContentPadding().fillMaxSize(),
    verticalArrangement = Arrangement.Center,
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text(
      text = stringResource(R.string.app_name2),
      modifier = Modifier.padding(16.dp),
      fontSize = 48.sp,
      fontWeight = FontWeight.Bold
    )
    Text(
      text = stringResource(R.string.subtitle),
      modifier = Modifier.padding(bottom = 48.dp),
      fontWeight = FontWeight.Bold
    )
    Text(stringResource(R.string.student_name))
    Text(stringResource(R.string.student_id))
    Spacer(Modifier.padding(64.dp))
    Button(
      onClick = onClickScreen,
      colors = ButtonDefaults.buttonColors(containerColor = Color.DarkGray)
    ) { Text(text = "Comenzar Quiz", fontSize = 24.sp, color = Color.White) }
  }
}