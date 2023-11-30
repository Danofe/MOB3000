package com.example.mob3000oblig.ui.theme

import android.app.Activity
import androidx.compose.foundation.isSystemInDarkTheme
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.SideEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.compose.runtime.staticCompositionLocalOf
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
  primary = PrimaryColor,
  onBackground = TextDark,
  background = BackgroundDark,
  onSurface = BackgroundDark,
  secondary = NavColorDark,
  tertiary = CardColorDark
)

private val lightColorScheme = lightColorScheme(
  primary = PrimaryColor,
  onBackground = TextLight,
  background = BackgroundLight,
  onSurface = BackgroundDark,
  secondary = NavColorLight,
  tertiary = CardColorLight
)


object AppThemeState {
  var isDarkMode by mutableStateOf(false)
}

@Composable
fun ProvideAppThemeState(
  darkMode: Boolean = AppThemeState.isDarkMode,
  content: @Composable (darkMode: Boolean, toggleDarkMode: () -> Unit) -> Unit
) {
  val systemInDarkMode = isSystemInDarkTheme()
  CompositionLocalProvider(LocalAppThemeState provides (darkMode || systemInDarkMode)) {
    content(darkMode) {
      AppThemeState.isDarkMode = !AppThemeState.isDarkMode
    }
  }
}

private val LocalAppThemeState = staticCompositionLocalOf<Boolean> {
  error("No LocalAppThemeState provided")
}

@Composable
fun getAppThemeState(): Boolean {
  return LocalAppThemeState.current
}

@Composable
fun AppTheme(
  content: @Composable () -> Unit
) {
  val darkMode = getAppThemeState()


  MaterialTheme(
    colorScheme = if (darkMode) darkColorScheme else lightColorScheme
  ) {
    val view = LocalView.current
    if (!view.isInEditMode) {
      SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Gray.toArgb()
        WindowCompat.getInsetsController(
          window,
          view
        ).isAppearanceLightStatusBars = darkMode
      }
    }
    content()
  }
}