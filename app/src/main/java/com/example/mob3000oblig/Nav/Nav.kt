package com.example.mob3000oblig.Nav

import android.app.Application
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
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
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
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
import com.example.mob3000oblig.Auth.Login.Login
import com.example.mob3000oblig.Auth.Registrer.Registrer
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.Favoritter.Favoritter
import com.example.mob3000oblig.Innstillinger.Innstillinger
import com.example.mob3000oblig.Kamera.Kamera
import com.example.mob3000oblig.Sammenlign.Sammenlign
import com.example.mob3000oblig.SokerInfo.SokerInfo
import com.example.mob3000oblig.Start.Start


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Nav(modifier: Modifier = Modifier) {
  val context = LocalContext.current
  val navController = rememberNavController()
  val viewModel: APIViewModel =
    ViewModelProvider.AndroidViewModelFactory.getInstance(Application())
      .create(APIViewModel::class.java)
  Scaffold(
    modifier = modifier.fillMaxSize(),
    bottomBar = {
      NavigationBar(
        containerColor = MaterialTheme.colorScheme.secondary,
        ) {
        val navBackStackEntry: NavBackStackEntry? by navController.currentBackStackEntryAsState()
        val currentDestination: NavDestination? = navBackStackEntry?.destination
        listeAvNavItems.forEach { bunnNavItem: BunnNavItems ->
          NavigationBarItem(
            selected = currentDestination?.hierarchy?.any { it.route == bunnNavItem.route } == true,
            onClick = {
              navController.navigate(bunnNavItem.route) {
                popUpTo(navController.graph.findStartDestination().id) {
                  saveState = true
                }
                launchSingleTop = true
              }
            },
            icon = {
              Icon(
                imageVector = bunnNavItem.icon,
                contentDescription = null,
                modifier = Modifier.size(24.dp),
                tint = MaterialTheme.colorScheme.onBackground
              )
            },

            label = {
              Text(
                text = context.getString(bunnNavItem.labelStringId),
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
      startDestination = Skjerm.Start.ruter,
      modifier = modifier
        .fillMaxSize()
        .padding(it)
    ) {

      composable(route = Skjerm.Login.ruter) {
        Login().LoginSkjerm(navController = navController)
      }

      composable(route = Skjerm.Register.ruter) {
        Registrer().RegisterSkjerm(navController = navController)
      }

      composable(route = Skjerm.Start.ruter) {
        Start().First(navController = navController)
      }

      composable(route = Skjerm.Settings.ruter) {
        Innstillinger().InnstillingPage(
          navController = navController,
          modifier = modifier
        )
      }

      composable(route = Skjerm.Kamera.ruter) {
        Kamera().HovedSkjerm(navController = navController)
      }

      composable(route = Skjerm.Sammenlign.ruter) {
        Sammenlign().SammenlignSkjerm(viewModel = viewModel)
      }

      composable(route = Skjerm.Info.ruter + "/{skiltnummer}",
                 arguments = listOf(
                   navArgument("skiltnummer") {
                     type = NavType.StringType
                   }
                 )
      ) { search ->
        SokerInfo().SkiltInfo(
          viewModel = viewModel,
          skiltnummer = search.arguments?.getString("skiltnummer")
        )
      }

      composable(route = Skjerm.Favoritter.ruter) {
        Favoritter().FavoritterSkjerm(navController = navController)
      }
    }
  }
}