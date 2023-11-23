package com.example.mob3000oblig.Sammenlign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.Favoritter.FavoritterViewModel
import com.example.mob3000oblig.R

class Sammenlign {
  @Composable
  fun SammenlignSkjerm(
    modifier: Modifier = Modifier,
    viewModel: FavoritterViewModel? = viewModel()
  ) {

    var skilt1 by remember { mutableStateOf("") }
    var skilt2 by remember { mutableStateOf("") }

    Column(
      modifier = modifier
        .padding(8.dp)
        .padding(bottom = 40.dp),
      horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    ) {
      Text(
        text = stringResource(R.string.compare_vehicles),
        fontSize = 30.sp,
        modifier = Modifier.padding(
          top = 20.dp,
          bottom = 20.dp
        )
      )
      Row(
        modifier = Modifier.fillMaxSize(),
        horizontalArrangement = Arrangement.SpaceEvenly,
      ) {
        /*  TextField(
              value = "",
              onValueChange = { skilt1 = it
              },
              placeholder = { Text(text = "skilt 1:") }
          )
          TextField(
              value = "",
              onValueChange = { skilt2 = it
              },
              placeholder = { Text(text = "skilt 2:") },
          )*/
      }
      Box(
        modifier = Modifier.fillMaxWidth(),
        contentAlignment = androidx.compose.ui.Alignment.Center
      ) {
        Meny(
          viewModel = viewModel,
          modifier = modifier,
          valgt = ""
        )
      }
    }
  }
}