package com.example.mob3000oblig.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen

class Login {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun loginSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel? = viewModel()
  ) {
    val loginUiState = loginViewModel?.uiState
    val context = LocalContext.current
    val error = loginUiState?.error != null

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
            verticalArrangement = Arrangement.spacedBy(50.dp),
          ) {
            Text(text = "Logg inn eller registrer ny bruker for å benytte denne funksjonen.");
            if (error) {
              Text(text = "Feil brukernavn eller passord")
            }

            TextField(
              value = loginUiState?.email ?: "",
              onValueChange = { loginViewModel?.onEmailChange(it) },
              label = { Text("Email") },
              modifier = modifier.align(Alignment.CenterHorizontally),
              enabled = true,

              leadingIcon = {
                Icon(
                  imageVector = Icons.Filled.AccountCircle,
                  contentDescription = "Lock"
                )
              }

            )
            TextField(
              value = loginUiState?.passord ?: "",
              onValueChange = { loginViewModel?.onPassordChange(it) },
              label = { Text("Passord") },
              modifier = modifier.align(Alignment.CenterHorizontally),
              visualTransformation = PasswordVisualTransformation(),
              leadingIcon = {
                Icon(
                  imageVector = Icons.Filled.Lock,
                  contentDescription = "Lock"
                )
              }
            )
            Button(
              onClick = {
                loginViewModel?.loginBruker(
                  context,
                  navController
                )
              },
              modifier = modifier.align(Alignment.CenterHorizontally),

              ) {
              Text(text = "Logg inn")
            }
            LaunchedEffect(key1 = loginViewModel?.loggetInn) {
              if (loginViewModel?.loggetInn == true) {
                navController.navigate(Screen.Sok.ruter)
              }
            }
            //Text(text = "eller", modifier = modifier.align(Alignment.CenterHorizontally))
            Button(
              onClick = { navController.navigate(Screen.Register.ruter) },
              modifier = modifier.align(Alignment.CenterHorizontally),
            ) {
              Text(text = "Registrer ny bruker")
            }
            Button(
              onClick = { navController.navigate(Screen.Start.ruter) },
              modifier = modifier.align(Alignment.CenterHorizontally),
            ) {
              Text(text = "Avbryt")
            }
          }
        }
      }
    }
  }
}