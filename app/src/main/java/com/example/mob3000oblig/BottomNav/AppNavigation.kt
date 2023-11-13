package com.example.mob3000oblig.BottomNav

import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.NavigationBar
import androidx.compose.material3.NavigationBarItem
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.navigation.NavBackStackEntry
import androidx.navigation.NavDestination
import androidx.navigation.NavDestination.Companion.hierarchy
import androidx.navigation.NavGraph.Companion.findStartDestination
import androidx.navigation.NavHostController
import androidx.navigation.compose.NavHost
import androidx.navigation.compose.composable
import androidx.navigation.compose.currentBackStackEntryAsState
import androidx.navigation.compose.rememberNavController
import com.example.mob3000oblig.Screen
import com.example.mob3000oblig.Start

@ExperimentalMaterial3Api
class AppNavigation {
    @Composable
    fun BottomNav() {
        val navController : NavHostController = rememberNavController()

        Scaffold (
         bottomBar = {
            NavigationBar {
                val navBackStackEntry : NavBackStackEntry? by navController.currentBackStackEntryAsState()
                val currentDestination: NavDestination? = navBackStackEntry?.destination

                listOfBottonNavigationItem.forEach { bottonNavigationItem : BottonNavigationItem ->
                    NavigationBarItem(
                        selected = currentDestination?.hierarchy?.any {it.route == bottonNavigationItem.route } == true,
                        onClick = {
                                  navController.navigate(bottonNavigationItem.route){
                                      popUpTo(navController.graph.findStartDestination().id){
                                          saveState = true
                                      }
                                      launchSingleTop = true
                                      restoreState = true
                                  }
                          },
                          icon = {
                            Icon(
                                imageVector = bottonNavigationItem.icon,
                                contentDescription = null)

                          },

                        label = {
                            Text(text = bottonNavigationItem.label
                            )
                        }
                    )
                }

            }
         }
        ) {paddingValues: PaddingValues ->
            NavHost(
                navController = navController,
                startDestination = Screen.Start.ruter,
                modifier = Modifier
                    .padding(paddingValues)
            ) {
                composable(route = Screen.Start.ruter){
                    Start()
}
                composable(route = Screen.Start.ruter){
                    Start()
                }
                composable(route = Screen.Start.ruter){
                    Start()
                }
            }
        }

    }
}