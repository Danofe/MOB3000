package com.example.mob3000oblig.Favoritter

import androidx.compose.foundation.Image
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
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth
import com.example.mob3000oblig.R

class Favoritter {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun favoritterSkjerm(
        modifier: Modifier = Modifier,
        navController: NavController
    ) {
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
                    if (Auth().currentUser == null) {
                        Text(text = "Du må logge inn for å se dine favoritter",
                            fontSize = 20.sp,
                            textAlign = androidx.compose.ui.text.style.TextAlign.Center,
                            modifier = modifier.padding(20.dp)
                            )
                    } else {
                        FavoritterDropdownMeny()
                    }
                }
            }
        }
    }
}