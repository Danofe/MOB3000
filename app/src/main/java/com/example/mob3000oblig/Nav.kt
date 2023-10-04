package com.example.mob3000oblig

import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument


@Composable
fun Nav() {
  val navController = rememberNavController()
  NavHost(
    navController = navController,
    startDestination = Screen.Start.ruter
  ) {
    composable(route = Screen.Start.ruter) {
      Start().First(navController = navController)
    }
    composable(route = Screen.Sok.ruter) {
      sokReg().Sok(navController = navController)

    }
    composable(route = Screen.Info.ruter + "/{name}",
               arguments = listOf(
                 navArgument("name") {
                   type = NavType.StringType
                 }
               )
    ) { search ->
      sokerInfo().SkiltInfo(name = search.arguments?.getString("name"))
    }

  }

}