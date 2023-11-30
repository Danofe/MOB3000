package com.example.mob3000oblig.Auth.Registrer

import androidx.compose.foundation.Image
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
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth.Auth
import com.example.mob3000oblig.Nav.Skjerm
import com.example.mob3000oblig.R

class Registrer {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun RegisterSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController,
    registrerViewModel: RegistrerViewModel? = viewModel()
  ) {

    if (Auth().innlogget()) {
      navController.navigate(Skjerm.Start.ruter)
    } else {

      val context = LocalContext.current
      val regUiStatus = registrerViewModel?.reguiStatus

      val focusManager = LocalFocusManager.current

      Column(modifier = modifier.padding()) {
        Box(
          modifier = modifier
            .fillMaxSize()
            .verticalScroll(rememberScrollState())
        ) {
          Column(
            modifier = modifier
              .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(16.dp),
          ) {
            Image(
              painter = painterResource(id = R.drawable.skiltskern),
              contentDescription = stringResource(R.string.logo_name),
              modifier = Modifier.size(200.dp)
            )

            TextField(
              value = regUiStatus?.emailReg ?: "",
              onValueChange = { registrerViewModel?.onEmailRegChange(it) },
              label = {
                Text(
                  text = stringResource(R.string.email),
                  color = MaterialTheme.colorScheme.onBackground
                )
              },
              modifier = modifier.align(Alignment.CenterHorizontally),
              colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground
              )
            )
            TextField(
              value = regUiStatus?.passordReg ?: "",
              visualTransformation = PasswordVisualTransformation(),
              onValueChange = { registrerViewModel?.onPassordRegChange(it) },
              label = {
                Text(
                  stringResource(R.string.password),
                  color = MaterialTheme.colorScheme.onBackground
                )
              },
              modifier = modifier.align(Alignment.CenterHorizontally),
              colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground
              )
            )
            TextField(
              value = regUiStatus?.passordBekreftReg ?: "",
              visualTransformation = PasswordVisualTransformation(),
              onValueChange = { registrerViewModel?.onPassordBekRegChange(it) },
              label = {
                Text(
                  stringResource(R.string.confirm_password),
                  color = MaterialTheme.colorScheme.onBackground
                )
              },
              modifier = modifier.align(Alignment.CenterHorizontally),
              colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground
              )
            )
            Row {
              Button(
                onClick = {
                  navController.navigate(Skjerm.Start.ruter)
                  focusManager.clearFocus()
                },
                colors = ButtonDefaults.buttonColors(Color.LightGray),
              ) {
                Text(
                  text = stringResource(R.string.cancel),
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 20.sp,
                )
              }
              Spacer(modifier = Modifier.width(8.dp))
              Button(
                onClick = {
                  registrerViewModel?.lagBruker(context)
                  focusManager.clearFocus()
                },
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),

                ) {
                Text(
                  text = stringResource(R.string.register),
                  color = MaterialTheme.colorScheme.onSurface,
                  fontSize = 20.sp,
                )
              }

              Spacer(modifier = modifier.padding(bottom = 200.dp))
            }

          }
        }
      }
    }
  }
}