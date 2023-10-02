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
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class sokReg {

    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun Sok(modifier: Modifier = Modifier, navController: NavController) {
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
                        TextField(value = "Søk her", onValueChange = { })
                        Button(onClick = { navController.navigate(Screen.Info.ruter) }) {
                            Text("Hent info")
                        }
                    }
                }
            }
        }
    }

}