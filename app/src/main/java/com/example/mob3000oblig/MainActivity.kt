package com.example.mob3000oblig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mob3000oblig.ui.theme.Mob3000ObligTheme

class MainActivity : ComponentActivity() {
  //private val apiKey = BuildConfig.apiKey
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Nav()
    }
  }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Mob3000ObligTheme {
  }
}