package com.example.mob3000oblig

import android.util.Log
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
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
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp

import com.example.mob3000oblig.DataApi.api
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import retrofit2.Call
import retrofit2.Callback
import retrofit2.Response


class sokerInfo {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun SkiltInfo(name: String?,modifier: Modifier = Modifier) {

        Scaffold(topBar = {
            CenterAlignedTopAppBar(
                title = {
                    Image(painter = painterResource(id = R.drawable.skiltskern ), contentDescription = "skiltskern")
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
                    Text(text = "$name", fontSize = 40.sp, modifier = modifier
                        .align(Alignment.TopCenter)
                        .padding(top = 40.dp))
                    Column(
                        modifier = modifier
                            .align(Alignment.CenterStart)
                            .padding(40.dp),
                        verticalArrangement = Arrangement.spacedBy(40.dp),
                        horizontalAlignment = Alignment.Start,
                    ) {
                        var url = "kjoretoydata?kjennemerke=$name"
                        var responseData by remember { mutableStateOf<String>("") }
                        var bilinfo by remember { mutableStateOf<String>("") }
                        var sistGodkjent by remember { mutableStateOf<String>("") }
                        var forsteReg by remember { mutableStateOf<String>("") }
                        var beskrivelse by remember { mutableStateOf<String>("") }
                        var sitteplasser by remember { mutableStateOf<String>("") }
                        var girinfo by remember { mutableStateOf<String>("") }
                        var merke by remember { mutableStateOf<String>("") }
                        var farge by remember { mutableStateOf<String>("") }

                        api.getKjoretoyDataListe(url).enqueue(object : Callback<KjoretoyDataListe> {
                            override fun onResponse(
                                call: Call<KjoretoyDataListe>,
                                response: Response<KjoretoyDataListe>
                            ) {
                                if(response.isSuccessful) {
                                    var data = response.body()
                                    bilinfo = data?.kjoretoydataListe?.get(0)?.kjoretoyId?.kjennemerke.toString()
                                    sistGodkjent = data?.kjoretoydataListe?.get(0)?.periodiskKjoretoyKontroll?.sistGodkjent.toString()
                                    forsteReg = data?.kjoretoydataListe?.get(0)?.forstegangsregistrering?.registrertForstegangNorgeDato.toString()
                                    beskrivelse = data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.kjoretoyklassifisering?.beskrivelse.toString()
                                    sitteplasser = data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.persontall?.sitteplasserTotalt.toString()
                                    girinfo = data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.girkassetype?.kodeBeskrivelse.toString()
                                    merke = data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.generelt?.merke?.getOrNull(0)?.merke.toString()
                                    farge = data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.karosseriOgLasteplan?.rFarge?.getOrNull(0)?.kodeNavn.toString()
                                    responseData = data.toString()
                                    Log.d("ResponseCheck", "Response: $data")
                                }
                            }
                            override fun onFailure(call: Call<KjoretoyDataListe>, t: Throwable) {
                                Log.i("ResponseCheck", "Response: ${t.message}")
                            }
                        })
                        Text(text = "RegNr fra API:         $bilinfo")
                        Text(text = "Merke:                 $merke")
                        Text(text = "Beskrivelse:           $beskrivelse")
                        Text(text = "Farge:                 $farge")
                        Text(text = "Girkassetype:          $girinfo")
                        Text(text = "Sitteplasser:          $sitteplasser")
                        Text(text = "Sist EU-godkjenning:   $sistGodkjent")
                        Text(text = "Registrert i Norge:    $forsteReg")
                    }
                }
            }
        }
    }
}