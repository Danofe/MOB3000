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
import androidx.compose.material3.Button
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
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
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.example.mob3000oblig.Database.Firestore
import kotlin.math.roundToInt
import androidx.compose.runtime.LaunchedEffect

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
        Log.e("YoOscar", "Info: $info")
      } catch (e: Exception) {
        Log.e("Oscar", "Error: ${e.message}")
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

              val error = "Ikke oppgitt"
              var merke by remember { mutableStateOf("") }
              var type by remember { mutableStateOf("") }
              var bilInf by remember { mutableStateOf("") }
              var sistGodkjent by remember { mutableStateOf("") }
              var forsteReg by remember { mutableStateOf("") }
              var sitteplasser by remember { mutableStateOf("") }
              var girinfo by remember { mutableStateOf("") }
              var farge by remember { mutableStateOf("") }
              var drivstoffinfo by remember { mutableStateOf("") }
              var maksHastighet by remember { mutableStateOf("") }
              var hk by remember { mutableStateOf("") }
              merke = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.generelt?.merke?.
                      get(0)?.merke ?: error
              type = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.kjoretoyklassifisering?.beskrivelse
                ?: error
              bilInf = bilInfo?.kjoretoydataListe?.get(0)?.kjoretoyId?.kjennemerke ?: error
              farge = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.karosseriOgLasteplan?.rFarge?.
                                getOrNull(0)?.kodeNavn ?: error
              sitteplasser = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.persontall?.
                              sitteplasserTotalt.toString()
              if (sitteplasser == "0") {
                sitteplasser = error
              }
              sistGodkjent = bilInfo?.kjoretoydataListe?.get(0)?.periodiskKjoretoyKontroll?.sistGodkjent ?: error
              forsteReg = bilInfo?.kjoretoydataListe?.get(0)?.forstegangsregistrering?.registrertForstegangNorgeDato ?: error
              drivstoffinfo = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.motor?.
                              getOrNull(0)?.drivstoff?.getOrNull(0)?.drivstoffKode?.kodeBeskrivelse ?: error
              girinfo = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.girkassetype?.kodeBeskrivelse
                ?: error

              // SJEKKER KUN DEN ÈNE MOTOREN, MÅ ENDRES SENERE
              maksHastighet =
                bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.maksimumHastighet?.getOrNull(0).toString()
              if (maksHastighet == "null") {
                maksHastighet = error
              }
              hk =
               bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.motor?.getOrNull(0)?.drivstoff?.getOrNull(0)?.maksNettoEffekt?.toInt().toString()
              if (hk == "null") {
                hk = error
                // Henter kun ut kW, så må konvertere til hk
              } else {
                var a = hk.toInt()
                hk = (a * 1.34102209).roundToInt().toString()
              }
              if (merke != error) {
                Row {
                  Column {
                    Text(text = "Merke")
                    Text(text = "Type")
                    Text(text = "Farge")
                    Text(text = "Girkassetype")
                    Text(text = "Drivstoff")
                    Text(text = "Sitteplasser")
                    Text(text = "Maks hastighet")
                    Text(text = "Hestekrefter")
                    Text(text = "Sist EU-godkjenning")
                    Text(text = "Registrert i Norge")
                  }
                  Spacer(modifier = modifier.width(20.dp))
                  Column {
                    Text(merke)
                    Text(type)
                    Text(farge)
                    Text(girinfo)
                    Text(drivstoffinfo)
                    Text(sitteplasser)
                    if (maksHastighet != error) {
                      Text("$maksHastighet km/t")
                    } else {
                      Text(maksHastighet)
                    }
                    if (hk != error) {
                      Text("≈$hk")
                    } else {
                      Text(hk)
                    }
                    Text(sistGodkjent)
                    Text(forsteReg)
                  }
                }
                Button(
                  onClick = {
                    Firestore.leggInnFavoritt(
                      name,
                      merke,
                      type,
                      farge,
                      girinfo,
                      drivstoffinfo,
                      sitteplasser,
                      maksHastighet,
                      hk,
                      sistGodkjent,
                      forsteReg
                    )
                  },
                  enabled = (Auth.innlogget())
                ) {
                  Text("Legg til i favoritter")
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