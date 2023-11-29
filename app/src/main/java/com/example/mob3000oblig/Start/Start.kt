package com.example.mob3000oblig.Start

import android.content.res.Configuration
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
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.platform.LocalFocusManager
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth.Auth
import com.example.mob3000oblig.Nav.Screen
import com.example.mob3000oblig.R
import compose.icons.FontAwesomeIcons
import compose.icons.fontawesomeicons.Solid
import compose.icons.fontawesomeicons.solid.Camera
import java.util.regex.Pattern


class Start {

  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun First(modifier: Modifier = Modifier, navController: NavController, Auth: Auth = Auth()) {
    val orientation = LocalConfiguration.current.orientation
    val focusManager = LocalFocusManager.current

    Column(modifier = modifier.fillMaxSize()) {
      Box(
        modifier = modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
      ) {
        //Hoved-kolonnen
        Column(
          modifier = modifier
            .fillMaxSize(),

          horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
          verticalArrangement = Arrangement.spacedBy(8.dp), //Spacing mellom alt som er i denne kolonnen
        ) {
          //Logo
          if (orientation == Configuration.ORIENTATION_LANDSCAPE) {
            //Tom space for å fjerne logo fra landskap
          } else {
            Image(
              painter = painterResource(id = R.drawable.skiltskern),
              contentDescription = stringResource(R.string.logo_name),
              modifier = Modifier.size(200.dp)
            )
          }
          //---- Søke skiltnr

          fun erGyldigSkiltNummer(licenseNumberStr: String?) = licenseNumberStr?.let {
            Pattern.compile(
              "^[A-Za-z\\d\\s]{2,7}\$"
            ).matcher(it).find()
          }

          // hentet fra https://stackoverflow.com/questions/65641875/jetpack-compose-textfield-inputfilter-to-have-only-currency-regex-inputs

          var skiltInput by remember { mutableStateOf("") }
          var visError by remember { mutableStateOf(false) }

          // Kolonne for Tekstfield og Søkknappen
          Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
            verticalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            TextField(
              value = skiltInput,
              onValueChange = {
                skiltInput = it
                visError = !erGyldigSkiltNummer(it)!!
              },
              isError = visError,
              placeholder = { Text(text = stringResource(R.string.write_licence_number)) },
              colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colorScheme.onBackground,
                containerColor = MaterialTheme.colorScheme.tertiary,
                cursorColor = MaterialTheme.colorScheme.onBackground,
                focusedIndicatorColor = MaterialTheme.colorScheme.onBackground
              ),
            )
            if (visError) {
              Text(stringResource(R.string.write_valid_licence_number))
            }
            Button(
              colors = ButtonDefaults.buttonColors(
                disabledContainerColor = Color.LightGray
              ),
              onClick = {
                navController.navigate(Screen.Info.withArgs(skiltInput.uppercase()))
              },
              enabled = !skiltInput.isEmpty() && !visError,
            )
            {
              Text(
                text = stringResource(R.string.search),
                fontSize = 16.sp,
                color = Color.Black
              )
            }
          }
          Spacer(modifier = modifier.padding(top = 10.dp))
          //Sammenlign-knapp
          Button(
            onClick = { focusManager.clearFocus()
              navController.navigate(Screen.Sammenlign.ruter) },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
          ) {
            Icon(
              imageVector = Icons.Filled.List,
              contentDescription = null,
              modifier = Modifier.size(36.dp),
              tint = MaterialTheme.colorScheme.onSurface
            )
            Text(
              text = stringResource(R.string.compare).uppercase(),
              fontSize = 20.sp,
              color = MaterialTheme.colorScheme.onSurface,
            )
          }
          // Rad for Kamera og Favoritter
          Row(
            modifier = Modifier
              .padding(vertical = 8.dp)
              .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(8.dp)
          ) {
            //Kamera-knapp
            Button(
              onClick = { navController.navigate(Screen.Kamera.ruter) },
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
            ) {
              Icon(
                imageVector = FontAwesomeIcons.Solid.Camera,
                contentDescription = null,
                modifier = Modifier
                  .size(30.dp)
                  .padding(end = 8.dp),
                tint = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = stringResource(R.string.camera).uppercase(),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
            //Favoritter-knapp
            Button(
              onClick = { navController.navigate(Screen.Favoritter.ruter) },
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary)
            ) {
              Icon(
                imageVector = Icons.Filled.Star,
                contentDescription = null,
                modifier = Modifier.size(30.dp),
                tint = MaterialTheme.colorScheme.onSurface
              )
              Text(
                text = stringResource(R.string.favorites).uppercase(),
                fontSize = 16.sp,
                color = MaterialTheme.colorScheme.onSurface
              )
            }
          }
          Spacer(modifier = modifier.padding(bottom = 84.dp))
        }
      }
    }
  }
}