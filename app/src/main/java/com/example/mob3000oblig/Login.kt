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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController

class Login {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun loginSkjerm(modifier: Modifier = Modifier, navController: NavController) {

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
                    Column(
                        modifier = modifier
                            .padding(50.dp),
                        verticalArrangement = Arrangement.spacedBy(50.dp),
                    ) {
                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("E-post") },
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                        TextField(
                            value = "",
                            onValueChange = {},
                            label = { Text("Passord") },
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        )
                        Button(
                            onClick = { navController.navigate(Screen.Start.ruter) },
                            modifier = modifier.align(Alignment.CenterHorizontally)
                        ) {
                            Text(text = "Logg inn")
                        }
                        Button(
                            onClick = { navController.navigate(Screen.Register.ruter) },
                            modifier = modifier.align(Alignment.CenterHorizontally)

                        ) {
                            Text(text = "Registrer")
                        }
                    }
                }
            }
        }
    }



}