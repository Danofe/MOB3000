package com.example.mob3000oblig.Login

import android.annotation.SuppressLint
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen

class Login {
  @SuppressLint("SuspiciousIndentation")
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun LoginSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController,
    loginViewModel: LoginViewModel? = viewModel()
  ) {
    val loginUiState = loginViewModel?.uiState
    val context = LocalContext.current
    val error = loginUiState?.error != null

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
            contentDescription = "skiltskern",
            modifier = Modifier.size(200.dp)
          )

          Text(
            text = "Logg inn eller registrer ny bruker for å benytte denne funksjonen.",
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground,
          )

          if (error) {
            Text(
              text = "Feil brukernavn eller passord",
              color = MaterialTheme.colorScheme.onBackground,
            )
          }

          TextField(
            value = loginUiState?.email ?: "",
            onValueChange = { loginViewModel?.onEmailChange(it) },
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
            value = loginUiState?.passord ?: "",
            onValueChange = { loginViewModel?.onPassordChange(it) },
            label = {
              Text(
                "Passord",
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
          Row {
            Button(
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
              onClick = {
                loginViewModel?.loginBruker(
                  context,
                  navController
                )
              }) {
              Text(
                text = "Logg inn",
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
            //Text(text = "eller", modifier = modifier.align(Alignment.CenterHorizontally))
            Button(
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
              onClick = { navController.navigate(Screen.Register.ruter) },

              ) {
              Text(
                text = "Registrer",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 20.sp,
              )
            }
          }

          Button(
            onClick = { navController.navigate(Screen.Start.ruter) },
            // TODO: Endre farge i dark og light
            colors = ButtonDefaults.buttonColors(Color.LightGray),
            modifier = modifier.align(Alignment.CenterHorizontally),
          ) {
            Text(
              text = "Avbryt",
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