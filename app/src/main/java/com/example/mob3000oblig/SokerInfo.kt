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
import com.example.mob3000oblig.DataApi.api
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response
class SokerInfo {
  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun SkiltInfo(name: String?, modifier: Modifier = Modifier) {
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
            val error = "Ikke spesifisert"
            val url = "kjoretoydata?kjennemerke=$name"
            var responseData by remember { mutableStateOf("") }
            var bilinfo by remember { mutableStateOf("") }
            var sistGodkjent by remember { mutableStateOf("") }
            var forsteReg by remember { mutableStateOf("") }
            var beskrivelse by remember { mutableStateOf("") }
            var sitteplasser by remember { mutableStateOf("") }
            var girinfo by remember { mutableStateOf("") }
            var merke by remember { mutableStateOf("") }
            var farge by remember { mutableStateOf("") }
            var drivstoff by remember { mutableStateOf("") }

            api.getKjoretoyDataListe(url).enqueue(object : Callback<KjoretoyDataListe> {
              override fun onResponse(
                call: Call<KjoretoyDataListe>,
                response: Response<KjoretoyDataListe>
              ) {
                if (response.isSuccessful) {
                  val data = response.body()
                  bilinfo = data?.kjoretoydataListe?.get(0)?.kjoretoyId?.kjennemerke ?: error
                  sistGodkjent =
                    data?.kjoretoydataListe?.get(0)?.periodiskKjoretoyKontroll?.sistGodkjent
                      ?: error
                  forsteReg =
                    data?.kjoretoydataListe?.get(0)?.forstegangsregistrering?.registrertForstegangNorgeDato
                      ?: error
                  beskrivelse =
                    data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.kjoretoyklassifisering?.beskrivelse
                      ?: error
                  sitteplasser =
                    data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.persontall?.sitteplasserTotalt.toString()
                      ?: error
                  girinfo =
                    data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.girkassetype?.kodeBeskrivelse
                      ?: error
                  merke =
                    data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.generelt?.merke?.get(0)?.merke ?: error
                  farge =
                    data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.karosseriOgLasteplan?.rFarge?.getOrNull(0)?.kodeNavn
                      ?: error
                  responseData = data.toString()
                  Log.d(
                    "ResponseCheck",
                    "Response: $data"
                  )
                }
              }

              override fun onFailure(call: Call<KjoretoyDataListe>, t: Throwable) {
                Log.i(
                  "ResponseCheck",
                  "Response: ${t.message}"
                )
              }
            })
            if (merke != error) {
              Row {
                Column {
                  Text(text = "Merke")
                  Text(text = "Beskrivelse")
                  Text(text = "Farge")
                  Text(text = "Girkassetype")
                  Text(text = "Sitteplasser")
                  Text(text = "Sist EU-godkjenning")
                  Text(text = "Registrert i Norge")
                }
                Spacer(modifier = modifier.width(20.dp))
                Column {
                  Text(merke)
                  Text(beskrivelse)
                  Text(farge)
                  Text(girinfo)
                  Text(sitteplasser)
                  Text(sistGodkjent)
                  Text(forsteReg)
                }
              }
            } else {
              Column(
                horizontalAlignment = Alignment.CenterHorizontally,
                verticalArrangement = Arrangement.Center,
                modifier = modifier.fillMaxSize()
              ) {
              Text(text = "Ingen kjøretøy på dette skiltnummeret.", fontSize = 20.sp, textAlign = TextAlign.Center)
              }
            }
          }
        }
      }
    }
  }
}