@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mob3000oblig

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.mutableStateOf
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.Navigation.Nav
import com.example.mob3000oblig.ui.theme.AppTheme
import com.example.mob3000oblig.ui.theme.ProvideAppThemeState

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
      ProvideAppThemeState { darkMode, toggleDarkMode ->
        AppTheme()
         {
          Nav()
        }
      }
    }
  }
}
//@Preview(showBackground = true)
//@Composable
//fun GreetingPreview() {
//}