package com.pdm0126.taller1_00232622

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.enableEdgeToEdge
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
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
    1 -> WelcomePage(onClickScreen = {currentScreen++})
    2 -> QuizPage(onClickScreen = {currentScreen++}, score, {score++})
    3 -> ResultPage(onClickScreen = {currentScreen = 1; score = 0}, score)
  }
}

@Composable
fun WelcomePage(onClickScreen: () -> Unit) {
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
    ) { Text(text = "Comenzar Quiz", fontSize = 24.sp) }
  }
}

@Composable
fun QuizPage(onClickScreen: () -> Unit, score: Int, onCorrectAnswer: () -> Unit) {
  var selectedOption by remember { mutableStateOf<String?>(null) }
  var currentStep by rememberSaveable { mutableIntStateOf(1) }
  val currentQuestion = quizQuestions[currentStep - 1]
  Column(
    modifier = Modifier.safeContentPadding().padding(8.dp).fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("Pregunta $currentStep de 3")
    Text("Puntaje: $score / 3")
    Spacer(Modifier.padding(64.dp))
    Card() {
      Text(text = currentQuestion.question, modifier = Modifier.padding(16.dp))
    }
    Spacer(Modifier.padding(16.dp))
    Column() {
      currentQuestion.options.forEach { option ->
        val buttonColor = when {
          selectedOption == null -> Color.DarkGray

          option == currentQuestion.correctAnswer -> Color.Green

          option == selectedOption &&
          option != currentQuestion.correctAnswer -> Color.Red

          else -> Color.DarkGray
        }
        Button(
          onClick = {
            if (selectedOption == null) {
              selectedOption = option
              if (option == currentQuestion.correctAnswer) {onCorrectAnswer()}
            }
          },
          modifier = Modifier.fillMaxWidth(),
          colors = ButtonDefaults.buttonColors(containerColor = buttonColor)
        ) { Text(option) }
      }
    }
    if(selectedOption != null) {
      Text(text = currentQuestion.funFact, modifier = Modifier.padding(16.dp))
      Button( onClick = {
        if(currentStep == 3) {
          currentStep = 1
          onClickScreen()
        } else { currentStep++ }
        selectedOption = null
        }
      ) { Text(if(currentStep == 3) "Ver Resultado" else "Siguiente") }
    }
  }
}

@Composable
fun ResultPage(onClickScreen: () -> Unit, score: Int) {
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
    ) { Text(text = "Reiniciar Quiz", fontSize = 24.sp) }
  }
}

@Preview(showBackground = true)
@Composable
fun AndroidPediaAppPreview() {
  AndroidPediaByArmasTheme {
    AndroidPediaApp()
  }
}