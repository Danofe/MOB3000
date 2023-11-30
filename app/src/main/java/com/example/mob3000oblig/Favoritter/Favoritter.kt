package com.example.mob3000oblig.Favoritter

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
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth.Auth
import com.example.mob3000oblig.Nav.Skjerm
import com.example.mob3000oblig.R

class Favoritter {

  @Composable
  fun FavoritterSkjerm(
    modifier: Modifier = Modifier,
    navController: NavController
  ) {
    Column(
      modifier = modifier.fillMaxSize()
    ) {
      Box(
        modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
      ) {
        if (Auth().bruker == null) {
          Column(
            modifier = modifier
              .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            //Logo
            Image(
              painter = painterResource(id = R.drawable.skiltskern),
              contentDescription = stringResource(R.string.logo_name),
              modifier = Modifier.size(200.dp)
            )
            Text(
              text = stringResource(R.string.login_to_see_favorites),
              fontSize = 20.sp,
              textAlign = androidx.compose.ui.text.style.TextAlign.Center,
              modifier = modifier.padding(20.dp),
              color = MaterialTheme.colorScheme.onBackground,
            )
            Row(
              modifier
                .padding(vertical = 8.dp)
                .align(Alignment.CenterHorizontally),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = { navController.navigate(Skjerm.Register.ruter) }) {

                Text(
                  text = stringResource(R.string.register),
                  fontSize = 20.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }

              Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = { navController.navigate(Skjerm.Login.ruter) }
              ) {
                Text(
                  text = stringResource(R.string.login),
                  fontSize = 20.sp,
                  color = MaterialTheme.colorScheme.onSurface,
                )
              }
              Spacer(modifier = modifier.padding(bottom = 200.dp))
            }
          }

        } else {
          FavoritterSkjerm(
            viewModel = viewModel()
          )
        }
      }
    }
  }
}