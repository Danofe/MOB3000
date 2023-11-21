@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mob3000oblig

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Switch
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.Navigation.Nav
import com.example.mob3000oblig.ui.theme.AppTheme

class MainActivity : ComponentActivity() {
  // Lagres i Datastore??
  val isDark = mutableStateOf(false)

  fun toggleTheme() {
    isDark.value = !isDark.value
  }

  private val viewModel: APIViewModel by viewModels()

  @SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      var isDarkTheme by remember { mutableStateOf(false) }
      AppTheme(
        darkTheme = isSystemInDarkTheme(),
      ) {
        Nav()
      }
    }

  }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//}