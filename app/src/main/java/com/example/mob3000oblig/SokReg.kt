package com.example.mob3000oblig

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
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import java.util.regex.Pattern

class SokReg {
  fun isValidLicenseNumber(licenseNumberStr: String?) =
    Pattern
      .compile(
        "^[A-Z]{2}\\d{5}\$"
      ).matcher(licenseNumberStr).find()

  // hentet fra https://stackoverflow.com/questions/65641875/jetpack-compose-textfield-inputfilter-to-have-only-currency-regex-inputs
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun Sok(modifier: Modifier = Modifier, navController: NavController) {

    var licenseNumberQuery by remember { mutableStateOf("") }
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
          Text(
            text = "Finn info om kjøretøy",
            fontSize = 30.sp,
            modifier = modifier
              .align(TopCenter)
              .padding(top = 40.dp)
          )
          Column(
            modifier = modifier
              .align(Center)
              .padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.CenterHorizontally,
          ) {
            TextField(
              value = licenseNumberQuery,
              onValueChange = {
                licenseNumberQuery = it
                showError = !isValidLicenseNumber(it)
              },
              isError = showError,
              placeholder = { Text(text = "Skriv skiltnr her:") }
            )
            if (showError) {
              Text("Skriv inn gyldig skiltnummer!")
            }
            Button(
              onClick = { navController.navigate(Screen.Info.withArgs(licenseNumberQuery)) },
              enabled = !licenseNumberQuery.isEmpty() && !showError
            ) {
              Text("Hent info")
            }
          }
        }
      }
    }
  }

}