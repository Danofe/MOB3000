package com.example.mob3000oblig.Kamera

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.material3.Button
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp

@Composable
fun IngenTillatelseSkjerm(
  onRequestPermission: () -> Unit
) {
  ImgenTillatelseInnhold(
    onRequestPermission = onRequestPermission
  )
}

@Composable
private fun ImgenTillatelseInnhold(
  onRequestPermission: () -> Unit
) {
  Column(
    modifier = Modifier.fillMaxSize(),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Text(
      text = "Vennligst tillat bruk av kameraet for å bruke denne funksjonen",
      textAlign = TextAlign.Center,
      color = MaterialTheme.colorScheme.onBackground,
    )
    Spacer(modifier = Modifier.height(20.dp))
    Button(onClick = onRequestPermission) {
      Text(
        text = "Tillat",
        color = Color.Black,
      )
    }
  }
}