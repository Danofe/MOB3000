@file:OptIn(ExperimentalMaterial3Api::class)

package com.example.mob3000oblig.Navigation

import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.material.icons.filled.Search

import androidx.compose.material3.ExperimentalMaterial3Api

import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mob3000oblig.Screen

data class BottomNavigationItem(
  val label: String,
  val icon: ImageVector,
  val route: String,
)

val listOfBottonNavigationItem: List<BottomNavigationItem> = listOf(
  BottomNavigationItem(
    label = "Hjem",
    icon = Icons.Filled.Home,
    route = Screen.Start.ruter,

    ),
  BottomNavigationItem(
    label = "Søk",
    icon = Icons.Filled.Search,
    route = Screen.Kamera.ruter
  ),
  BottomNavigationItem(
    label = "Profil",
    icon = Icons.Filled.Face,
    route = Screen.Settings.ruter
  )
)