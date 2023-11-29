@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mob3000oblig

import android.annotation.SuppressLint
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.material3.ExperimentalMaterial3Api
import com.example.mob3000oblig.Nav.Nav
import com.example.mob3000oblig.ui.theme.AppTheme
import com.example.mob3000oblig.ui.theme.ProvideAppThemeState

class MainActivity : ComponentActivity() {

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