package com.example.mob3000oblig

import android.content.res.Configuration
import androidx.browser.trusted.ScreenOrientation
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Info
import androidx.compose.material.icons.filled.List
import androidx.compose.material.icons.filled.Search
import androidx.compose.material.icons.filled.Star
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldColors
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.graphics.vector.ImageVector
import androidx.compose.ui.platform.LocalConfiguration
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.font.Font
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import io.grpc.okhttp.internal.Platform.TlsExtensionType
import java.util.regex.Pattern


class Start {

  enum class ScreenRotation(val degrees: Float) {
    VENSTRE_90(-90f), HOYRE_90(90f), NORMAL(0f)
  }
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun First(modifier: Modifier = Modifier, navController: NavController, Auth: Auth = Auth()) {
    val containerColor = Color(0xFF, 0xF1, 0x02)
    val config = LocalConfiguration.current

    Column(modifier = modifier.fillMaxSize()) {
      Box(
        modifier = modifier
          .fillMaxSize()
          .verticalScroll(rememberScrollState())
          .background(colorResource(id = R.color.LIGHT_BACKGROUND))
      ) {
        //Hoved-kolonnen
        Column(
          modifier = modifier
            .padding(16.dp)
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
              "^(?![\\s]{2})(?=.{2,7}\$)([A-Z]\\s|[A-Z\\d\\s]{2,})+\$"
            ).matcher(it).find()
          }

          // hentet fra https://stackoverflow.com/questions/65641875/jetpack-compose-textfield-inputfilter-to-have-only-currency-regex-inputs

          var licenseNumberQuery by remember { mutableStateOf("") }
          var showError by remember { mutableStateOf(false) }

          // Kolonne for Tekstfield og Søkknappen
          Column(
            modifier = Modifier.padding(24.dp),
            horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
            verticalArrangement = Arrangement.spacedBy(16.dp) //Spacing mellom textfield og søk-knapp
          ) {

              TextField(
                value = licenseNumberQuery,
                onValueChange = {
                  licenseNumberQuery = it
                  showError = !isValidLicenseNumber(it)!!
                },
                isError = showError,
                placeholder = { Text(text = "Skriv skiltnr her:") },
              )
              if (showError) {
                Text("Skriv inn gyldig skiltnummer!")
              }
              Button(
                colors = ButtonDefaults.buttonColors(containerColor),
                onClick = {
                  navController.navigate(Screen.Info.withArgs(licenseNumberQuery))
                },
                enabled = !licenseNumberQuery.isEmpty() && !showError
              )
              {
                Text(
                  text = "Søk",
                  fontSize = 16.sp,
                  color = colorResource(id = R.color.TEXTLIGHT)
                )
              }
          }
            //-----
            //Sammenlign-knapp
            Button(
              onClick = { navController.navigate(Screen.Sammenlign.ruter) },
              colors = ButtonDefaults.buttonColors(containerColor),
              ) {
              Icon(
                imageVector = Icons.Filled.List,
                contentDescription = null,
                modifier = Modifier.size(36.dp),
                colorResource(id = R.color.TEXTLIGHT)

              )
              Text(
                text = "SAMMENLIGN",
                fontSize = 30.sp,
                color = colorResource(id = R.color.TEXTLIGHT)
              )

            }
            // Rad for Kamera og Favoritter
            Row(
              modifier = Modifier.padding(vertical = 8.dp),
              horizontalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              //Kamera-knapp
              Button(
                onClick = { navController.navigate(Screen.Kamera.ruter) },
                colors = ButtonDefaults.buttonColors(
                  containerColor
                ),
              ) {
                Icon(
                  imageVector = Icons.Filled.Search,
                  contentDescription = null,
                  modifier = Modifier.size(36.dp),
                  colorResource(id = R.color.TEXTLIGHT)
                )
                Text(
                  text = "KAMERA",
                  fontSize = 20.sp,
                  color = colorResource(id = R.color.TEXTLIGHT)

                )
              }

              //Favoritter-knapp
              Button(
                onClick = { navController.navigate(Screen.Favoritter.ruter) },
                colors = ButtonDefaults.buttonColors(
                  containerColor
                ),
              ) {
                Icon(
                  imageVector = Icons.Filled.Star,
                  contentDescription = null,
                  modifier = Modifier.size(36.dp),
                  colorResource(id = R.color.TEXTLIGHT)
                )
                Text(
                  text = "FAVORITTER",
                  fontSize = 20.sp,
                  color = colorResource(id = R.color.TEXTLIGHT)
                )
              }
            }

            }

          }
        }

      }




}







