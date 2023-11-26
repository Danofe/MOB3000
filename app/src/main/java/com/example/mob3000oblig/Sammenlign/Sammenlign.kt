package com.example.mob3000oblig.Sammenlign

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.DataApi.bilInfoVariabler
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.example.mob3000oblig.Favoritter.FavoritterViewModel
import com.example.mob3000oblig.R
import kotlinx.coroutines.awaitAll
import kotlinx.coroutines.launch


class Sammenlign {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun SammenlignSkjerm(
      modifier: Modifier = Modifier,
      viewModel: APIViewModel
  ) {

      var skilt1 by remember { mutableStateOf("") }
      var skilt2 by remember { mutableStateOf("") }
      var url1 by remember { mutableStateOf("") }
      var url2 by remember { mutableStateOf("") }
      var bilInfo1 by remember { mutableStateOf<KjoretoyDataListe?>(null) }
      var bilInfo2 by remember { mutableStateOf<KjoretoyDataListe?>(null) }
      var viserInfo by remember { mutableStateOf(false) }
      val context = LocalContext.current

      fun hentInfo(skilt1: String, skilt2: String) {
            url1 = "kjoretoydata=kjennemerke=$skilt1"
            url2 = "kjoretoydata=kjennemerke=$skilt2"
      }
      LaunchedEffect(url1) {
          try {
              bilInfo1 = viewModel.hentBilInfo(url1)
              val verdi1 = bilInfoVariabler(context, bilInfo1)
              Log.d("LaunchedEffect hitter", verdi1.toString())
              Log.d("URL SOM API SKAL CALLE PÅ", url1)
          } catch (e: Exception) {
              Log.d("Bilinfo1", e.toString())
          }
          viserInfo = true
      }
      LaunchedEffect(url2) {
          try {
              bilInfo2 = viewModel?.hentBilInfo(url2)
          } catch (e: Exception) {
              Log.d("Bilinfo2", e.toString())
          }
      }
      Column(
          modifier = modifier
              //.fillMaxSize()
              .padding(24.dp),
          horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Top
      ) {
          Text(
              text = stringResource(R.string.compare_vehicles),
              fontSize = 30.sp,
              modifier = Modifier.padding(
                  top = 20.dp,
                  bottom = 20.dp
              )
          )
          Row(
              modifier = Modifier,
              horizontalArrangement = Arrangement.SpaceEvenly,
          ) {
              TextField(
                  modifier = modifier.width(150.dp),
                  value = skilt1,
                  onValueChange = {
                      skilt1 = it
                  },
                  placeholder = { Text(text = "skilt 1") }
              )
              Spacer(modifier.width(10.dp))
              TextField(
                  modifier = modifier.width(150.dp),
                  value = skilt2,
                  onValueChange = {
                      skilt2 = it
                  },
                  placeholder = { Text(text = "skilt 2") },
              )
          }
          Button(
              colors = ButtonDefaults.buttonColors(
                  disabledContainerColor = Color.LightGray),
              onClick = {hentInfo(skilt1, skilt2)},
              enabled = !skilt1.isEmpty() && !skilt2.isEmpty(),
              modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
          ) {
              Text(
                  text = "Gå",
                  fontSize = 16.sp,
                  color = Color.Black
              )
          }
        if (viserInfo) {
              Card(colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
                  Row(
                      modifier = modifier
                          .padding(20.dp),
                  ) {
                      Column() {
                          Text(
                              text = stringResource(R.string.license_number),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.brand),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.series),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.type),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.color),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.gearbox_type),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.fuel),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.hybrid),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.horsepower),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.max_speed),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.first_registration),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.number_of_seats),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.number_of_seats),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.height),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.weight),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.width),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.length),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.weight),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.latest_approval),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.next_eu_control),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                      }
                      // Placeholders for bil 1
                      Column() {
                          Text(
                              text = skilt1.uppercase(),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          /*Text(
                              text = verdi1.merke,
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = verdi1.handelsbetegnelse,
                              color = MaterialTheme.colorScheme.onBackground
                          )*/
                          Text(
                              text = stringResource(R.string.type),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.color),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.gearbox_type),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.fuel),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.hybrid),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.horsepower),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.max_speed),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.first_registration),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.number_of_seats),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.number_of_seats),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.height),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.weight),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.width),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.length),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.weight),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.latest_approval),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                          Text(
                              text = stringResource(R.string.next_eu_control),
                              color = MaterialTheme.colorScheme.onBackground
                          )
                      }
                  }
              }
          }
      }
  }
}