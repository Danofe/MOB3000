package com.example.mob3000oblig.Favoritter

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.R


import com.example.mob3000oblig.Sammenlign.Meny

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritterDropdownMeny(
  viewModel: FavoritterViewModel? = viewModel(),
  modifier: Modifier = Modifier
) {
  var kjoretoy by remember { mutableStateOf("") }
  val slettemelding = remember { mutableStateOf(false) }
  var valgtFavoritt by remember { mutableStateOf("") }
  val favorittliste = viewModel?.favoritterSkilt?.value
  var index by remember { mutableStateOf(-1)}

  val context = LocalContext.current

  Column(
    modifier = Modifier
      .fillMaxSize()
      .padding(24.dp),
    horizontalAlignment = Alignment.CenterHorizontally,
    verticalArrangement = Arrangement.Center
  ) {
    Box(
      modifier = Modifier.fillMaxWidth(),
      contentAlignment = Alignment.Center
    ) {
      Meny(
        viewModel = viewModel,
        valgt = valgtFavoritt,
        onValueChanged = { updatedValue ->
          valgtFavoritt = updatedValue
        },
        modifier = modifier
      )
    }
      index = favorittliste?.indexOf(valgtFavoritt)!!
      kjoretoy = viewModel.allefavoritter.value.getOrNull(index).toString()
      val hestekrefter = kjoretoy.substringAfter("hestekrefter=").substringBefore(",")
      val maksHastighet = kjoretoy.substringAfter("maksHastighet=").substringBefore(",")

      Spacer(modifier = modifier.height(80.dp))

      if (index != -1) {
        Row(
          modifier = modifier,
        ) {
          Column() {
            Text(text = "Merke")
            Text(text = "Serie")
            Text(text = "Type")
            Text(text = "Farge")
            Text(text = "Girkassetype")
            Text(text = "Drivstoff")
            Text(text = "Hybrid")
            Text(text = "Hestekrefter")
            Text(text = "Maks hastighet")
            Text(text = "Forste registrering")
            Text(text = "Sitteplasser")
            Text(text = "Antall dører:")
            Text(text = "Høyde")
            Text(text = "Bredde")
            Text(text = "Lengde")
            Text(text = "Egenvekt")
            Text(text = "Sist godkjent:")
            Text(text = "Neste EU kontroll:")
          }
          Spacer(modifier = modifier.width(30.dp))
          Column {
            Text(
              kjoretoy.substringAfter("merke=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("serie=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("type=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("farge=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("girinfo=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("drivstoffinfo=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("hybrid=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            if (hestekrefter != "Ikke oppgitt") {
              Text(
                "≈$hestekrefter hk",
                fontWeight = FontWeight.Bold
              )
            } else {
              Text(
                hestekrefter,
                fontWeight = FontWeight.Bold
              )
            }
            if (maksHastighet != "Ikke oppgitt") {
              Text(
                "$maksHastighet km/t",
                fontWeight = FontWeight.Bold
              )
            } else {
              Text(
                maksHastighet,
                fontWeight = FontWeight.Bold
              )
            }
            Text(
              kjoretoy.substringAfter("forstereg=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("sitteplasser=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("antDorer=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("hoyde=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("bredde=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("lengde=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("vekt=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("sistgodkjent=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("nesteEU=")
                .substringBefore(","),
              fontWeight = FontWeight.Bold
            )
          }
        }
        Button(
          onClick = {
            slettemelding.value = true
          },
          modifier = modifier
            .align(Alignment.CenterHorizontally)
            .padding(20.dp)
        ) {
          Text(
            text = "Slett favoritt",
            color = MaterialTheme.colorScheme.onSurface
          )
        }
        if (slettemelding.value) {
          AlertDialog(
            onDismissRequest = {
              slettemelding.value = false
            },
            title = {
              Text(
                "Slette favoritt?",
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            text = {
              Text(
                "$valgtFavoritt vil for alltid bli slettet fra dine favoritter.",
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            confirmButton = {
              Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {
                  viewModel.slettFavoritt(valgtFavoritt)
                  slettemelding.value = false
                  Toast.makeText(
                    context,
                    "$valgtFavoritt er slettet",
                    Toast.LENGTH_LONG
                  ).show()
                  valgtFavoritt = ""
                },

                ) {
                Text(
                  "Slett favoritt",
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            },
            dismissButton = {
              Button(
                onClick = {
                  slettemelding.value = false
                }
              ) {
                Text(
                  "Avbryt",
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            })
        }
      } else if (favorittliste?.isEmpty() == true) {
        Text("Du har ingen lagrede favoritter")
      } else {
        Text("Velg en favoritt fra menyen")
      }
    }
  }


