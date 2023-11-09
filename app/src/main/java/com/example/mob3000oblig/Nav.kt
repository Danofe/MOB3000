package com.example.mob3000oblig

import android.app.Application
import androidx.compose.runtime.Composable
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.Login.Login
import com.example.mob3000oblig.Registrer.Register
import androidx.lifecycle.ViewModelProvider

@Composable
fun Nav() {

  val navController = rememberNavController()
  val viewModel: APIViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application()).create(APIViewModel::class.java)
  NavHost(
    navController = navController,
    startDestination = Screen.Start.ruter
  ) {

    composable(route = Screen.Login.ruter) {
      Login().loginSkjerm(navController = navController)
    }

    composable(route = Screen.Register.ruter) {
      Register().registerSkjerm(navController = navController)
    }

    composable(route = Screen.Start.ruter) {
      Start().First(navController = navController)
    }

    composable(route = Screen.Sok.ruter) {
      SokReg().Sok(navController = navController)
    }

    composable(route = Screen.Info.ruter + "/{name}",
               arguments = listOf(
                 navArgument("name") {
                   type = NavType.StringType
                 }
               )
    ) { search ->
      SokerInfo().SkiltInfo(viewModel = viewModel,name = search.arguments?.getString("name"))
    }

    composable(route = Screen.Favoritter.ruter) {
      Favoritter().favoritterSkjerm(navController = navController)
    }
  }
}