package com.example.mob3000oblig.Settings

import androidx.compose.animation.AnimatedVisibility
import androidx.compose.animation.animateContentSize
import androidx.compose.foundation.ExperimentalFoundationApi
import androidx.compose.foundation.Image
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.horizontalScroll
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.Card
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.paint
import androidx.compose.ui.text.input.TextFieldValue
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth


class settings{
    @Composable
    fun Profil() {
        Card(modifier = Modifier
            .padding(32.dp)
        ) {
            var utvidet by remember {
                mutableStateOf(false)
            }

            Column(
                Modifier
                    .clickable { utvidet = !utvidet }
                    .fillMaxWidth()
                    .padding(16.dp)

                    .align(Alignment.CenterHorizontally)

            ) {
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)

                ) {
                    Text(text = "Profile", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
                AnimatedVisibility(visible = utvidet ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )  {
                    Card(modifier = Modifier

                        .align(Alignment.CenterHorizontally)
                    ) {
                        ProfilCard()
                    }
                }
            }
        }
    }


    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun ProfilCard() {
        Card(modifier = Modifier
            .padding(16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)

            ) {
                Box(
                    Modifier
                        .fillMaxWidth()
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = Auth().hentBrukerEmail(), fontSize = 16.sp)
                }
                Column(
                    Modifier
                        .padding(8.dp)
                        .align(Alignment.CenterHorizontally)
                ) {
                    var passord by remember {
                        mutableStateOf("")
                    }
                    OutlinedTextField(
                        value = passord,
                        onValueChange = { passord = it },
                        label = { Text(text = "Nytt Passord") },
                        modifier = Modifier
                            .fillMaxWidth()
                            .padding(8.dp)

                    )

                    Button(
                        modifier = Modifier
                            .align(Alignment.CenterHorizontally),
                        onClick = {Auth().byttPassord(passord)}
                    ) {
                        Text(text = "Bytt passord")
                    }
                    Button(modifier = Modifier
                        .align(Alignment.CenterHorizontally),
                        onClick = { Auth().loggUt() }) {
                        Text(text = "Logg ut")
                    }
                }
            }
        }
    }


    @Composable
    fun Settings() {
        Card(modifier = Modifier
            .padding(32.dp)


        ) {
            var utvidet by remember {
                mutableStateOf(false)
            }

            Column(
                Modifier
                    .clickable { utvidet = !utvidet }
                    .fillMaxWidth()
                    .padding(16.dp)
                    .align(Alignment.CenterHorizontally)

            ) {
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)

                ) {
                    Text(text = "Settings", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
                AnimatedVisibility(visible = utvidet ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )  {
                    Card(modifier = Modifier

                        .align(Alignment.CenterHorizontally)
                    ) {
                        SettingCard()
                    }
                }
            }
        }
    }

    @Composable
    fun SettingCard() {
        Card() {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Row(
                    Modifier
                        .align(Alignment.CenterHorizontally)
                ) {
                    Box(
                        Modifier
                            .padding(2.dp)
                    ) {
                        Button(onClick = {/*TODO: darkmode */ }) {
                            Text(text = "DarkMode")
                        }
                    }
                    Box(
                        Modifier
                            .padding(2.dp)
                    ) {
                        Button(onClick = { /*TODO: lightmode */ }) {
                            Text(text = "LightMode")
                        }
                    }
                }
            }
        }
    }

    @Composable
    fun Terms() {
        Card(modifier = Modifier
            .padding(32.dp)

        ) {
            var utvidet by remember {
                mutableStateOf(false)
            }
            Column(
                Modifier
                    .clickable { utvidet = !utvidet }
                    .fillMaxWidth()
                    .padding(16.dp)
            ) {
                Box(modifier = Modifier
                    .align(Alignment.CenterHorizontally)
                ) {
                    Text(text = "Terms And Conditions", fontSize = 20.sp, fontWeight = androidx.compose.ui.text.font.FontWeight.Bold)
                }
                AnimatedVisibility(visible = utvidet ,
                    modifier = Modifier
                        .fillMaxWidth()
                        .padding(16.dp)
                        .align(Alignment.CenterHorizontally)
                )  {
                    Card(modifier = Modifier

                        .align(Alignment.CenterHorizontally)
                    ) {
                        TosCard()
                    }
                }
            }
        }
    }

    @Composable
    fun TosCard() {
        Card(modifier = Modifier
            .padding(16.dp)
        ) {
            Column(
                Modifier
                    .fillMaxWidth()
                    .align(Alignment.CenterHorizontally)
            ) {
                Column {
                    Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.")
                }
            }
        }
    }

    @Composable
    fun SettingsPage(navController: NavController) {
        Column(
            Modifier
                .fillMaxSize()
                .background(color = androidx.compose.ui.graphics.Color.White)
                .padding(16.dp)
                .verticalScroll(rememberScrollState())
        ) {
            Profil()
            Settings()
            Terms()
        }
    }
}