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
import androidx.lifecycle.viewmodel.compose.viewModel
import androidx.navigation.NavController
import com.example.mob3000oblig.R

class Favoritter {
    @OptIn(ExperimentalMaterial3Api::class)
    @Composable
    fun favoritterSkjerm(
        modifier: Modifier = Modifier,
        viewModel: FavoritterViewModel? = viewModel(),
        navController: NavController
    ) {
        val brukerID = viewModel?.brukerID

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
                    if (brukerID.toString() == "null") {
                        Text(text = "Du må logge inn for å se dine favoritter")
                    } else {
                        FavoritterDropdownMeny()
                    }
                }
            }
        }
    }
}