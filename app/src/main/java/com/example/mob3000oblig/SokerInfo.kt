package com.example.mob3000oblig

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.lazy.LazyColumn
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.DataApi.bilInfoVariabler
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.example.mob3000oblig.Database.Firestore
import kotlin.math.roundToInt


class SokerInfo {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable

  fun SkiltInfo(
    viewModel: APIViewModel,
    name: String?,
    modifier: Modifier = Modifier,
    Auth: Auth = Auth(),
    Firestore: Firestore = Firestore()
  ) {
    var url by remember { mutableStateOf("") }
    url = "kjoretoydata?kjennemerke=$name"
    var bilInfo by remember { mutableStateOf<KjoretoyDataListe?>(null) }
    //val scope = rememberCoroutineScope()
    LaunchedEffect(url) {
      try {
        val info = viewModel.hentBilInfo(url)
        bilInfo = info
        Log.e(
          "YoOscar",
          "Info: $info"
        )
      } catch (e: Exception) {
        Log.e(
          "Oscar",
          "Error: ${e.message}"
        )
      }
    }

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
            text = "$name",
            fontSize = 40.sp,
            modifier = modifier
              .align(Alignment.TopCenter)
              .padding(top = 40.dp)
          )
          Column(
            modifier = modifier
              .align(Alignment.CenterStart)
              .padding(40.dp),
            verticalArrangement = Arrangement.spacedBy(40.dp),
            horizontalAlignment = Alignment.Start,
          ) {
            val verdi = bilInfoVariabler(bilInfo)
            var visMerKnapp by remember { mutableStateOf(false) }
            var visMerKnappText by remember { mutableStateOf("Vis mer") }
            val error = "Ikke oppgitt"
            var maksHastighet by remember { mutableStateOf("") }
            var hk by remember { mutableStateOf("") }

            // SJEKKER KUN DEN ÈNE MOTOREN, MÅ ENDRES SENERE
            maksHastighet =
              bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.maksimumHastighet?.getOrNull(0)
                .toString()
            if (maksHastighet == "null") {
              maksHastighet = error
            }
            hk =
              bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.motor?.getOrNull(0)?.drivstoff?.getOrNull(0)?.maksNettoEffekt?.toInt()
                .toString()
            if (hk == "null") {
              hk = error
              // Henter kun ut kW, så må konvertere til hk
            } else {
              var a = hk.toInt()
              hk = (a * 1.34102209).roundToInt().toString()
            }
            // Gjør at "legg til i favoritter"-knappen kan kun trykkes 1 gang, litt scuffed metode
            var lagtInn by remember { mutableStateOf(false) }
            if (verdi.merke != error) {
              Row {
                LazyColumn {
                  items(1) { index ->
                    Text(text = "Merke")
                    Text(text = "Serie")
                    Text(text = "Type")
                    Text(text = "Farge")
                    Text(text = "Girkassetype")
                    Text(text = "Drivstoff")
                    Text(text = "Hybrid")
                    Text(text = "Maks hastighet")
                    Text(text = "Forste registrering")
                    if (visMerKnapp) {
                      Text(text = "Sitteplasser")
                      Text(text = "Antall dører:")
                      Text(text = "Høyde")
                      Text(text = "Bredde")
                      Text(text = "Lengde")
                      Text(text = "Egenvekt")
                      Text(text = "Sist godkjent:")
                      Text(text = "Neste EU kontroll:")
                    }
                  }
                }
                Spacer(modifier = modifier.width(20.dp))
                Column {
                  Text(verdi.merke)
                  Text(verdi.handelsbetegnelse)
                  Text(verdi.type)
                  Text(verdi.farge)
                  Text(verdi.girtyp)
                  Text(verdi.drivstoff)

                  Text(verdi.hybrid)
                  if (verdi.toppHastighet != error) {
                    Text("${verdi.toppHastighet} km/t")
                  } else {
                    Text(verdi.toppHastighet)
                  }
                  Text(verdi.forsteReg)
                  /*if (hk != error) {
                    Text("≈$hk")
                  } else {
                    Text(hk)
                  } */
                  if (visMerKnapp) {
                    Text(verdi.antSeter)
                    Text(verdi.antdorer)
                    if (verdi.hoyde != error) {
                      Text("${verdi.hoyde} cm")
                    } else {
                      Text(verdi.hoyde)
                    }
                    if (verdi.bredde != error) {
                      Text("${verdi.bredde} cm")
                    } else {
                      Text(verdi.bredde)
                    }
                    if (verdi.lengde != error) {
                      Text("${verdi.lengde} cm")
                    } else {
                      Text(verdi.lengde)
                    }
                    if (verdi.vekt != error) {
                      Text("${verdi.vekt} kg")
                    } else {
                      Text(verdi.vekt)
                    }
                    Text(verdi.sistgodkjent)
                    Text(verdi.nesteEU)
                  }
                }
              }
              Button(
                onClick = {
                  Firestore.leggInnFavoritt(
                    name,
                    verdi.merke,
                    verdi.antSeter,
                    verdi.farge,
                    verdi.type,
                    verdi.toppHastighet,
                    verdi.drivstoff,
                    verdi.girtyp,
                    verdi.lengde,
                    verdi.hoyde,
                    verdi.bredde,
                    verdi.vekt,
                    verdi.hybrid,
                    verdi.sistgodkjent,
                    verdi.nesteEU,
                    verdi.forsteReg,
                    verdi.antdorer,
                    verdi.handelsbetegnelse
                  )
                  lagtInn = true
                },
                enabled = (Auth.innlogget() && !lagtInn)
              ) {
                Text("Legg til i favoritter")
              }
              Button(
                onClick = {
                  visMerKnapp = !visMerKnapp
                  visMerKnappText = if (visMerKnapp) "Vis mindre" else "Vis mer"
                },
              )
              {
                Text(visMerKnappText)
              }

            } else {
              Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
              ) {
                Text(
                  text = "Fant ingen kjøretøy med dette skiltnummeret.",
                  fontSize = 20.sp,
                  textAlign = TextAlign.Center
                )
              }
            }
          }
        }
      }
    }
  }
}