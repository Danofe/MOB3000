package com.example.mob3000oblig.Auth.Login

import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.AccountCircle
import androidx.compose.material.icons.filled.Lock
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
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
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Nav.Screen

class Login {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun LoginSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel? = viewModel()
  ) {
    val loginUiStatus = loginViewModel?.uiStatus
    val context = LocalContext.current
    val error = loginUiStatus?.error != null
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier) {
      Box(
        modifier = modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
      ) {
        Column(
          modifier = modifier
            .fillMaxSize(),
          horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
          verticalArrangement = Arrangement.spacedBy(8.dp),
        ) {
          Image(
            painter = painterResource(id = R.drawable.skiltskern),
            contentDescription = stringResource(R.string.logo_name),
            modifier = Modifier.size(200.dp)
          )
          if (error) {
            Text(
              text = stringResource(R.string.wrong_credentials),
              color = MaterialTheme.colorScheme.onBackground,
            )
          }
          TextField(
            value = loginUiStatus?.email ?: "",
            onValueChange = { loginViewModel?.vedEmailEndring(it) },
            label = {
              Text(
                "Email",
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            modifier = modifier.align(Alignment.CenterHorizontally),
            enabled = true,
            colors = TextFieldDefaults.textFieldColors(
              MaterialTheme.colorScheme.onBackground,
              cursorColor = MaterialTheme.colorScheme.onBackground
            ),
            leadingIcon = {
              Icon(
                imageVector = Icons.Filled.AccountCircle,
                contentDescription = "Lock",
                tint = MaterialTheme.colorScheme.onBackground,
              )
            }
          )
          TextField(
            value = loginUiStatus?.passord ?: "",
            onValueChange = { loginViewModel?.vedPassordEndring(it) },
            label = {
              Text(
                stringResource(R.string.password),
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            modifier = modifier.align(Alignment.CenterHorizontally),
            visualTransformation = PasswordVisualTransformation(),
            colors = TextFieldDefaults.textFieldColors(
              MaterialTheme.colorScheme.onBackground,
              cursorColor = MaterialTheme.colorScheme.onBackground
            ),
            leadingIcon = {
              Icon(
                imageVector = Icons.Filled.Lock,
                contentDescription = "Lock",
                tint = MaterialTheme.colorScheme.onBackground,
              )
            }
          )
          Spacer(modifier = Modifier.height(20.dp))
          Row {
            Button(
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
              onClick = {
                loginViewModel?.loginBruker(
                  context,
                  navController
                )
                focusManager.clearFocus()
              }) {
              Text(
                text = stringResource(R.string.login),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
              )
            }
            LaunchedEffect(key1 = loginViewModel?.loggetInn) {
              if (loginViewModel?.loggetInn == true) {
                navController.navigate(Screen.Sok.ruter)
              }
            }
            Spacer(modifier = Modifier.padding(8.dp))
            Button(
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
              onClick = { navController.navigate(Screen.Register.ruter) },

              ) {
              Text(
                text = stringResource(R.string.register),
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
              )
            }
          }
          Button(
            onClick = { navController.navigate(Screen.Start.ruter) },
            // TODO: Endre farge i dark og light
            colors = ButtonDefaults.buttonColors(Color.LightGray, ),
            modifier = modifier.align(Alignment.CenterHorizontally),
          ) {
            Text(
              text = stringResource(R.string.cancel),
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