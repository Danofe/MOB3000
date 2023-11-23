package com.example.mob3000oblig

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
import androidx.compose.material.icons.filled.Search
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
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.regex.Pattern


class Start {

  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun First(modifier: Modifier = Modifier, navController: NavController, Auth: Auth = Auth()) {
    val config = LocalConfiguration.current

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
          Image(
            painter = painterResource(id = R.drawable.skiltskern),
            contentDescription = "skiltskern",
            modifier = Modifier.size(200.dp)
          )

          //---- Søke skiltnr

          fun isValidLicenseNumber(licenseNumberStr: String?) = licenseNumberStr?.let {
            Pattern.compile(
              "^[A-Za-z\\d\\s]{2,7}\$"
            ).matcher(it).find()
          }

          // hentet fra https://stackoverflow.com/questions/65641875/jetpack-compose-textfield-inputfilter-to-have-only-currency-regex-inputs

          var licenseNumberQuery by remember { mutableStateOf("") }
          var showError by remember { mutableStateOf(false) }

          // Kolonne for Tekstfield og Søkknappen
          Column(
            modifier = Modifier.padding(8.dp),
            horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
            verticalArrangement = Arrangement.spacedBy(8.dp) //Spacing mellom textfield og søk-knapp
          ) {

            TextField(
              value = licenseNumberQuery,
              onValueChange = {
                licenseNumberQuery = it
                showError = !isValidLicenseNumber(it)!!
              },
              isError = showError,
              placeholder = { Text(text = stringResource(R.string.write_licence_number)) },
              colors = TextFieldDefaults.textFieldColors(
                MaterialTheme.colorScheme.onBackground,
                cursorColor = MaterialTheme.colorScheme.onBackground
              ),
            )
            if (showError) {
              Text(stringResource(R.string.write_valid_licence_number))
            }
            Button(
              colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
              onClick = {
                navController.navigate(Screen.Info.withArgs(licenseNumberQuery.uppercase()))
              },
              enabled = !licenseNumberQuery.isEmpty() && !showError
            )
            {
              Text(
                text = stringResource(R.string.search),
                fontSize = 16.sp,
                color = Color.Black
              )
            }
          }
          //-----
          //Sammenlign-knapp
          Button(
            onClick = { navController.navigate(Screen.Sammenlign.ruter) },
            colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
          ) {
            Icon(
              imageVector = Icons.Filled.List,
              contentDescription = null,
              modifier = Modifier.size(36.dp),
              tint = Color.Black
            )
            Text(
              text = stringResource(R.string.compare).uppercase(),
              fontSize = 30.sp,
              color = Color.Black,
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
                imageVector = Icons.Filled.Search,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                tint = Color.Black
              )
              Text(
                text = stringResource(R.string.camera).uppercase(),
                fontSize = 16.sp,
                color = Color.Black
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
                modifier = Modifier.size(36.dp),
                tint = Color.Black
              )
              Text(
                text = stringResource(R.string.favorites).uppercase(),
                fontSize = 16.sp,
                color = Color.Black
              )
            }
          }
          Spacer(modifier = modifier.padding(bottom = 84.dp))
        }
      }
    }
  }
}