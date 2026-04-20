package com.pdm0126.taller1_00232622

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Scaffold
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import com.pdm0126.taller1_00232622.ui.screens.QuizScreen
import com.pdm0126.taller1_00232622.ui.screens.ResultScreen
import com.pdm0126.taller1_00232622.ui.screens.WelcomeScreen
import com.pdm0126.taller1_00232622.ui.theme.AndroidPediaByArmasTheme

class MainActivity : ComponentActivity() {
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    enableEdgeToEdge()
    setContent {
      AndroidPediaByArmasTheme {
        Scaffold(modifier = Modifier.fillMaxSize()) { innerPadding ->
          AndroidPediaApp(modifier = Modifier.padding(innerPadding))
        }
      }
    }
  }
}

@Composable
fun AndroidPediaApp(modifier: Modifier = Modifier) {

  var currentScreen by rememberSaveable { mutableIntStateOf(1) }
  var score by rememberSaveable { mutableIntStateOf(0) }

  when (currentScreen) {
    1 -> WelcomeScreen(onClickScreen = {currentScreen++})
    2 -> QuizScreen(onClickScreen = {currentScreen++}, score, {score++})
    3 -> ResultScreen(onClickScreen = {currentScreen = 1; score = 0}, score)
  }
}

@Preview(showBackground = true)
@Composable
fun AndroidPediaAppPreview() {
  AndroidPediaByArmasTheme {
    AndroidPediaApp()
  }
}