package com.example.mob3000oblig.ui.theme

import android.app.Activity
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.darkColorScheme
import androidx.compose.material3.lightColorScheme
import androidx.compose.runtime.Composable
import androidx.compose.runtime.SideEffect
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.toArgb
import androidx.compose.ui.platform.LocalView
import androidx.core.view.WindowCompat

private val darkColorScheme = darkColorScheme(
  primary = PrimaryColor,
  onBackground = TextDark,
  background = BackgroundDark,
  onSurface = BackgroundDark
)

private val lightColorScheme = lightColorScheme(
  primary = PrimaryColor,
  onBackground = TextLight,
  background = BackgroundLight,
  onSurface = BackgroundDark

  /* Other default colors to override
  background = Color(0xFFFFFBFE),
  surface = Color(0xFFFFFBFE),
  onPrimary = Color.White,
  onSecondary = Color.White,
  onTertiary = Color.White,
  onBackground = Color(0xFF1C1B1F),
  onSurface = Color(0xFF1C1B1F),
  */
)


@Composable
fun AppTheme(
  darkTheme: Boolean,
  content: @Composable () -> Unit,
) {
  MaterialTheme(
    colorScheme = if (darkTheme) darkColorScheme else lightColorScheme
  ) {
    val view = LocalView.current
    if (!view.isInEditMode) {
      SideEffect {
        val window = (view.context as Activity).window
        window.statusBarColor = Color.Gray.toArgb()
        WindowCompat.getInsetsController(
          window,
          view
        ).isAppearanceLightStatusBars = darkTheme
      }
    }
    content()
  }

//  val colorScheme = when {
//    dynamicColor && Build.VERSION.SDK_INT >= Build.VERSION_CODES.S -> {
//      val context = LocalContext.current
//      if (darkTheme) dynamicDarkColorScheme(context) else dynamicLightColorScheme(context)
//    }
//
//    darkTheme -> darkColorScheme
//    else -> lightColorScheme
//  }
//
//  MaterialTheme(
//    colorScheme = colorScheme,
//    typography = Typography,
//    content = content
//  )
}