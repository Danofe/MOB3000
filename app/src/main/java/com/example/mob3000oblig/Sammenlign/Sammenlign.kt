package com.example.mob3000oblig.Sammenlign

import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.graphics.Color
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
var valgtFavoritt by remember { mutableStateOf("") }
    Column(
      modifier = modifier
        //.fillMaxSize()
        .padding(24.dp),
      horizontalAlignment = androidx.compose.ui.Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Top) {
      Text(
        text = stringResource(R.string.compare_vehicles),
        fontSize = 30.sp,
        modifier = Modifier.padding(
          top = 20.dp,
          bottom = 20.dp
        )
      )
      Row(
        modifier = Modifier,
        horizontalArrangement = Arrangement.SpaceEvenly,
      ) {
          TextField(
              modifier = modifier.width(150.dp),
                    value = skilt1,
              onValueChange = { skilt1 = it
              },
              placeholder = { Text(text = "skilt 1") }
          )
          Spacer(modifier.width(10.dp))
                TextField(
                    modifier = modifier.width(150.dp),
              value = skilt2,
              onValueChange = { skilt2 = it
              },
              placeholder = { Text(text = "skilt 2") },
          )
      }
      Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {},
                enabled = !skilt1.isEmpty() && !skilt2.isEmpty(),
        modifier = Modifier.padding(top = 20.dp, bottom = 20.dp)
        ) {
      Text(
        text = "Gå",
          fontSize = 16.sp,
          color = Color.Black
        )
      }
    }
  }
}