package com.example.mob3000oblig.Navigation

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen

data class BottomNavigationItem(
  @StringRes
  val labelStringId: Int,
  val icon: ImageVector,
  val route: String,
)

val listOfBottonNavigationItem: List<BottomNavigationItem> = listOf(
  BottomNavigationItem(
    labelStringId = R.string.home,
    icon = Icons.Filled.Home,
    route = Screen.Start.ruter,

    ),
  BottomNavigationItem(
    labelStringId = R.string.search,
    icon = Icons.Filled.Search,
    route = Screen.Kamera.ruter
  ),
  BottomNavigationItem(
    labelStringId = R.string.profile,
    icon = Icons.Filled.Face,
    route = Screen.Settings.ruter
  )
)