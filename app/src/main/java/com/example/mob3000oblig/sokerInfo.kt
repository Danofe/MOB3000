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

    //Test
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

                        var responseData by remember { mutableStateOf<String>("") }
                        var bilinfo by remember { mutableStateOf<String>("") }
                        var data by remember { mutableStateOf<String>("") }
                        api.getKjoretoyDataListe().enqueue(object : Callback<KjoretoyDataListe> {
                            override fun onResponse(
                                call: Call<KjoretoyDataListe>,
                                response: Response<KjoretoyDataListe>
                            ) {

                                if(response.isSuccessful) {
                                    var data = response.body()
                                    var informasjon = data?.kjoretoydataListe?.get(0)?.kjoretoyId?.understellsnummer + "\n" +
                                                      data?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.dimensjoner + "\n"

                                    Log.d("ResponseCheck", "Response: $data")
                                    bilinfo = informasjon
                                }
                            }

                            override fun onFailure(call: Call<KjoretoyDataListe>, t: Throwable) {
                                Log.i("ResponseCheck", "Response: ${t.message}")
                            }
                        })

                        Text(text = bilinfo)

                    }
                }
            }
        }
    }

}