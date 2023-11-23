package com.example.mob3000oblig.Navigation

import android.annotation.SuppressLint
import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.lifecycle.ViewModelProvider
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavType
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import androidx.navigation.navArgument
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.Favoritter.Favoritter
import com.example.mob3000oblig.Kamera.Kamera
import com.example.mob3000oblig.Login.Login
import com.example.mob3000oblig.Registrer.Register
import com.example.mob3000oblig.Sammenlign.Sammenlign
import com.example.mob3000oblig.Screen
import com.example.mob3000oblig.Settings.SettingsPage
import com.example.mob3000oblig.SokerInfo
import com.example.mob3000oblig.Start

@OptIn(ExperimentalMaterial3Api::class)
@SuppressLint("UnusedMaterial3ScaffoldPaddingParameter")
@Composable
fun Nav() {
  val navController = rememberNavController()
  val viewModel: APIViewModel = ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
    .create(APIViewModel::class.java)
  Scaffold(
    modifier = Modifier.fillMaxSize(),
    bottomBar = {
      NavigationBar(
      ) {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination
        listOfBottonNavigationItem.forEach { bottonNavigationItem: BottomNavigationItem ->
          NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == bottonNavigationItem.route } == true,
            onClick = {
              navController.navigate(bottonNavigationItem.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                  saveState = true
                }
                launchSingleTop = true
              }
            },
            icon = {
              Icon(
                imageVector = bottonNavigationItem.icon,
                contentDescription = null,
                tint = MaterialTheme.colorScheme.onBackground
              )
            },

            label = {
              Text(
                text = bottonNavigationItem.label,
                color = MaterialTheme.colorScheme.onBackground
              )
            }
          )
        }
      }
    }
  ) {

    NavHost(
      navController = navController,
      startDestination = Screen.Start.ruter,
      modifier = Modifier.fillMaxSize()
    ) {

      composable(route = Screen.Login.ruter) {
        Login().LoginSkjerm(navController = navController)
      }

      composable(route = Screen.Register.ruter) {
        Register().registerSkjerm(navController = navController)
      }

      composable(route = Screen.Start.ruter) {
        Start().First(navController = navController)
      }

      composable(route = Screen.Settings.ruter) {
        SettingsPage().SettingsPage(navController = navController)
        settings().SettingsPage(navController = navController, modifier = Modifier)
      }

      composable(route = Screen.Kamera.ruter) {
        Kamera().HovedSkjerm(navController = navController)
      }

      composable(route = Screen.Sammenlign.ruter) {
        Sammenlign().SammenlignSkjerm()
      }

      composable(route = Screen.Info.ruter + "/{name}",
                 arguments = listOf(
                   navArgument("name") {
                     type = NavType.StringType
                   }
                 )
      ) { search ->
        SokerInfo().SkiltInfo(
          viewModel = viewModel,
          name = search.arguments?.getString("name")
        )
      }

      composable(route = Screen.Favoritter.ruter) {
        Favoritter().FavoritterSkjerm(navController = navController)
      }
    }
  }
}