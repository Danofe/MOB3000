package com.example.mob3000oblig.Registrer

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.R

class Register {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun registerSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController,
    registerViewModel: RegisterViewModel? = viewModel()
  ) {

    val context = LocalContext.current
    val regUiState = registerViewModel?.reguiState
    var showError by remember { mutableStateOf(false) }

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
            TextField(
              value = regUiState?.emailReg ?: "",
              onValueChange = { registerViewModel?.onEmailRegChange(it) },
              label = { Text("E-post") },
              modifier = modifier.align(Alignment.CenterHorizontally)
            )
            TextField(
              value = regUiState?.passordReg ?: "",
              onValueChange = { registerViewModel?.onPassordRegChange(it) },
              label = { Text("Passord") },
              modifier = modifier.align(Alignment.CenterHorizontally)
            )
            TextField(
              value = regUiState?.passordBekreftReg ?: "",
              onValueChange = { registerViewModel?.onPassordBekRegChange(it) },
              label = { Text("Bekreft Passord") },
              modifier = modifier.align(Alignment.CenterHorizontally)
            )
            Button(
              onClick = { registerViewModel?.lagBruker(context) },
              modifier = modifier.align(Alignment.CenterHorizontally)
            ) {
              Text(text = "Registrer")
            }
          }
        }
      }
    }
  }
}