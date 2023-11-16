package com.example.mob3000oblig.Favoritter

import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.List
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen

class Favoritter {
    val containerColor = Color(0xFF, 0xF1, 0x02)
    @Composable
    fun favoritterSkjerm(
        modifier: Modifier = Modifier,
        navController: NavController
    ) {

        Column(
            modifier = modifier
                .verticalScroll(rememberScrollState())
                .fillMaxSize(),
            horizontalAlignment = Alignment.CenterHorizontally, //Sentrere

        ) {
            Box(
                modifier = modifier.fillMaxSize()
                .background(colorResource(id = R.color.LIGHT_BACKGROUNDD))

            ) {
                if (Auth().currentUser == null) {
                    Column(
                        modifier = modifier
                            .padding(8.dp)
                            .fillMaxSize(),
                        horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
                        verticalArrangement = Arrangement.Center //
                    ) {
                        //Logo
                        Image(
                            painter = painterResource(id = R.drawable.skiltskern),
                            contentDescription = "skiltskern",
                            modifier = Modifier.size(200.dp)
                        )
                        Text(
                            text = "Du må logge inn for å se dine favoritter",
                            fontSize = 20.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = modifier.padding(20.dp)
                        )
                        Row {
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor),
                            onClick = { navController.navigate(Screen.Register.ruter) }) {

                        Text(
                             text = "Registrer",
                             fontSize = 20.sp,
                             color = colorResource(id = R.color.TEXTLIGHT)
                            )
                        }
                        Spacer(modifier = Modifier.width(16.dp))
                        Button(
                            colors = ButtonDefaults.buttonColors(containerColor),
                            onClick = { navController.navigate(Screen.Login.ruter) }

                        ) {


                        Text(
                            text = "Logg inn",
                            fontSize = 20.sp,
                            color = colorResource(id = R.color.TEXTLIGHT)
                                )
                            }
                        }
                    }
                    } else {
                        FavoritterDropdownMeny(
                            viewModel = viewModel(),
                            navController = navController
                        )
                    }
                }
            }
        }
    }

