package com.example.mob3000oblig

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class Start {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun First(modifier: Modifier = Modifier, navController: NavController, Auth: Auth = Auth()) {
    Scaffold(topBar = {
      CenterAlignedTopAppBar(
        title = {
          Image(
            painter = painterResource(id = R.drawable.skiltskern),
            contentDescription = "skiltskern"
          )
        },
        colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
          containerColor = colorResource(R.color.purple_700),
          titleContentColor = colorResource(R.color.black),
        ),
      )
    }
    ) { it ->
      Column(modifier = modifier.padding(it)) {
        Box(
          modifier = modifier.fillMaxSize()
        ) {
          Column(
            modifier = modifier
              .padding(50.dp),
            verticalArrangement = Arrangement.spacedBy(70.dp),

            ) {
            Button(
              onClick = { navController.navigate(Screen.Sok.ruter) },
              colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_700),
              ),
            ) {
              Text(
                text = "Søk på skiltnr",
                fontSize = 40.sp
              )
            }
            Button(
              onClick = {
                if (Auth.innlogget()) {
                  navController.navigate(Screen.Sok.ruter)
                } else {
                  navController.navigate(Screen.Login.ruter)
                }
              },
              colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_700),
              ),
            ) {
              Text(
                text = "Logg inn/Søk",
                fontSize = 40.sp
              )
            }
            Button(
              onClick = { navController.navigate(Screen.Favoritter.ruter) },
              colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_700),
              ),
            ) {
              Text(
                text = "Favoritter",
                fontSize = 40.sp
              )
            }
            Button(
              onClick = { Auth.loggUt() },
              colors = ButtonDefaults.buttonColors(
                containerColor = colorResource(R.color.purple_700)
              ),
            ) {
              Text(
                text = "Logg ut",
                fontSize = 40.sp
              )
            }
          }
        }
      }
    }
  }
}