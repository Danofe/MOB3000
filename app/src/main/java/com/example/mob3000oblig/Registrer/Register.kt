package com.example.mob3000oblig.Registrer

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
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
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen

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


      Column(modifier = modifier.padding()) {
        Box(
          modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
            .background(colorResource(id = R.color.LIGHT_BACKGROUNDD))
        ) {
          Column(
            modifier = modifier
            .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
          ) {
            Image(
              painter = painterResource(id = R.drawable.skiltskern),
              contentDescription = "skiltskern",
              modifier = Modifier.size(200.dp)
            )

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
            Row {

              Button(
                onClick = { navController.navigate(Screen.Start.ruter) },
                colors = ButtonDefaults.buttonColors(Color.LightGray),

                ) {
                Text(text = "Avbryt",
                  color = Color.Black,
                  fontSize = 20.sp,)
              }
              Spacer(modifier = Modifier.width(8.dp))
              
              Button(
                onClick = { registerViewModel?.lagBruker(context) },
                colors = ButtonDefaults.buttonColors(colorResource(id = R.color.PRIMARY_LIGHTOGDARK)),

              ) {
                Text(text = "Registrer",
                  color = colorResource(id = R.color.TEXTLIGHT),
                  fontSize = 20.sp,)
              }


            }

          }
        }
      }
    }
  }