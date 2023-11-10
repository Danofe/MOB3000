@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mob3000oblig

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.activity.viewModels
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.runtime.Composable
import androidx.compose.ui.tooling.preview.Preview
import com.example.mob3000oblig.BottomNav.AppNavigation
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.ui.theme.Mob3000ObligTheme

class MainActivity : ComponentActivity() {
  private val viewModel: APIViewModel by viewModels()
  //private val apiKey = BuildConfig.apiKey
  override fun onCreate(savedInstanceState: Bundle?) {
    super.onCreate(savedInstanceState)
    setContent {
      Nav()
      AppNavigation().BottomNav()

    }
  }
}


@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
  Mob3000ObligTheme {
  }
}