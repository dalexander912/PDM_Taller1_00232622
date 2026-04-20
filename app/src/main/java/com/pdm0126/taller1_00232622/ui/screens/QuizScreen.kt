package com.pdm0126.taller1_00232622.ui.screens

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.safeContentPadding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableIntStateOf
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.saveable.rememberSaveable
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.unit.dp
import com.pdm0126.taller1_00232622.quizQuestions
import com.pdm0126.taller1_00232622.ui.components.OptionButton
import com.pdm0126.taller1_00232622.ui.components.QuestionCard

@Composable
fun QuizScreen(onClickScreen: () -> Unit, score: Int, onCorrectAnswer: () -> Unit) {

  var selectedAnswer by rememberSaveable { mutableStateOf<String?>(null) }
  var currentStep by rememberSaveable { mutableIntStateOf(1) }
  val currentQuestion = quizQuestions[currentStep - 1]

  fun onAnswerSelected(option: String) {
    if (selectedAnswer == null) {
      selectedAnswer = option
      if (option == currentQuestion.correctAnswer) {onCorrectAnswer()}
    }
  }

  fun onNextQuestion() {
    if(currentStep == 3) {
      currentStep = 1
      onClickScreen()
    } else {
      currentStep++
    }
    selectedAnswer = null
  }

  Column(
    modifier = Modifier.safeContentPadding().padding(8.dp).fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally
  ) {
    Text("Pregunta $currentStep de 3")
    Text("Puntaje: $score / 3")
    Spacer(Modifier.padding(64.dp))

    QuestionCard(currentQuestion.question)
    Spacer(Modifier.padding(16.dp))

    Column() {
      currentQuestion.options.forEach { option ->
        OptionButton(option, selectedAnswer, currentQuestion.correctAnswer) {
          onAnswerSelected(option)
        }
      }
    }

    if(selectedAnswer != null) {
      Text(text = currentQuestion.funFact, modifier = Modifier.padding(16.dp))
      Button(onClick = { onNextQuestion() }) {
        Text(if(currentStep == 3) "Ver Resultado" else "Siguiente")
      }
    }
  }
}