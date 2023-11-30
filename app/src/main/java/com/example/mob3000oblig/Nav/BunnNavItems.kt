package com.example.mob3000oblig.Nav

import androidx.annotation.StringRes
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Face
import androidx.compose.material.icons.filled.Home
import androidx.compose.ui.graphics.vector.ImageVector
import com.example.mob3000oblig.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Camera

data class BunnNavItems(
  @StringRes
  val labelStringId: Int,
  val icon: ImageVector,
  val route: String,
)

val listeAvNavItems: List<BunnNavItems> = listOf(
  BunnNavItems(
    labelStringId = R.string.home,
    icon = Icons.Filled.Home,
    route = Skjerm.Start.ruter,

    ),
  BunnNavItems(
    labelStringId = R.string.search,
    icon = FontAwesomeIcons.Solid.Camera,
    route = Skjerm.Kamera.ruter,

    ),
  BunnNavItems(
    labelStringId = R.string.profile,
    icon = Icons.Filled.Face,
    route = Skjerm.Settings.ruter
  )
)