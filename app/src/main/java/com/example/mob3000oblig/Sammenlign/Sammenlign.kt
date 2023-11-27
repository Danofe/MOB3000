package com.example.mob3000oblig.Sammenlign

import android.util.Log
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewModelScope
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.DataApi.HentBilInfo
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
    var verdi1 by remember { mutableStateOf<HentBilInfo?>(null) }
    var verdi2 by remember { mutableStateOf<HentBilInfo?>(null) }
    var viserInfo by remember { mutableStateOf(false) }
    var visError by remember { mutableStateOf(false) }
    val context = LocalContext.current

    suspend fun hentInfo(skilt1: String, skilt2: String) {
      url1 = "kjoretoydata?kjennemerke=$skilt1"
      url2 = "kjoretoydata?kjennemerke=$skilt2"

      bilInfo1 = viewModel.hentBilInfo(url1)
      bilInfo2 = viewModel.hentBilInfo(url2)

      verdi1 = bilInfoVariabler(
        context,
        bilInfo1
      )
      verdi2 = bilInfoVariabler(
        context,
        bilInfo2
      )

      if (bilInfo1 != null && bilInfo2 != null) {
        viserInfo = true
      } else {
        visError = true;
      }
    }
    Column(
      modifier = modifier
        .padding(10.dp)
        .verticalScroll(rememberScrollState())
        .padding(
          top = 18.dp,
          bottom = 20.dp
        ),
      horizontalAlignment = Alignment.CenterHorizontally,
      //verticalArrangement = Arrangement.Top,
      verticalArrangement = Arrangement.spacedBy(10.dp)
    ) {
      Text(
        text = stringResource(R.string.compare_vehicles),
        fontSize = 30.sp,
        modifier = Modifier
          .padding(
            top = 20.dp,
            bottom = 20.dp
          )
      )
      Row(
        modifier = Modifier
          .align(Alignment.CenterHorizontally)
          .padding(15.dp),
        horizontalArrangement = Arrangement.spacedBy(5.dp),
      ) {
        TextField(
          modifier = modifier.width(150.dp),
          value = skilt1,
          onValueChange = {
            skilt1 = it
          },
          placeholder = { Text(text = "skilt 1") },
          colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onBackground)
        )
        TextField(
          modifier = modifier.width(150.dp),
          value = skilt2,
          onValueChange = {
            skilt2 = it
          },
          placeholder = { Text(text = "skilt 2") },
          colors = TextFieldDefaults.textFieldColors(textColor = MaterialTheme.colorScheme.onBackground)
        )
      }
      Button(
        colors = ButtonDefaults.buttonColors(
          disabledContainerColor = Color.LightGray
        ),
        onClick = {
          viewModel.viewModelScope.launch {
            hentInfo(
              skilt1,
              skilt2
            )
          }
        },
        enabled = !skilt1.isEmpty() && !skilt2.isEmpty(),
        modifier = Modifier.padding(
          top = 20.dp,
          bottom = 20.dp
        )
      ) {
        Text(
          text = stringResource(R.string.run),
          fontSize = 16.sp,
          color = Color.Black
        )
      }
      if (viserInfo) {
        visError = false
        Card(colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
          Row(
            modifier = modifier
              .padding(10.dp)
              .align(Alignment.CenterHorizontally),
            horizontalArrangement = Arrangement.spacedBy(10.dp)
          ) {
            Column() {
              Text(
                text = stringResource(R.string.license_number),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.brand),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.series),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.type),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.color),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.gearbox_type),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.fuel),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.hybrid),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.horsepower),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.max_speed),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.first_registration),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.number_of_seats),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.number_of_doors),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.height),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.width),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.length),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.weight),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.latest_approval),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
              Text(
                text = stringResource(R.string.next_eu_control),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp,
                fontWeight = FontWeight.Bold
              )
            }
            // bil 1
            Column() {
              Text(
                text = skilt1.uppercase(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.merke.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.handelsbetegnelse.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.type.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.farge.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.girtyp.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.drivstoff.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.hybrid.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.hp,
                  verdi1?.hk.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.km_h,
                  verdi1?.toppHastighet.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.forsteReg.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.antSeter.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.antdorer.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi1?.hoyde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi1?.bredde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi1?.lengde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.kg,
                  verdi1?.vekt.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.sistgodkjent.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi1?.nesteEU.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
            }
            // bil 2
            Column() {
              Text(
                text = skilt2.uppercase(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.merke.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.handelsbetegnelse.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.type.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.farge.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.girtyp.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.drivstoff.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.hybrid.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.hp,
                  verdi2?.hk.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.km_h,
                  verdi2?.toppHastighet.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.forsteReg.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.antSeter.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.antdorer.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi2?.hoyde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi2?.bredde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.cm,
                  verdi2?.lengde.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = stringResource(
                  R.string.kg,
                  verdi2?.vekt.toString()
                ),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.sistgodkjent.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
              Text(
                text = verdi2?.nesteEU.toString(),
                color = MaterialTheme.colorScheme.onBackground,
                fontSize = 15.sp
              )
            }
          }
        }
        Spacer(modifier = modifier.padding(bottom = 84.dp))
      } else if (visError) {
        Text(text = stringResource(R.string.write_two_valid_numbers))

      }
    }
  }
}