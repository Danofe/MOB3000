package com.example.mob3000oblig.Favoritter

import android.widget.Toast
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritterSkjerm(
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
      val bredde = kjoretoy.substringAfter("bredde=").substringBefore(",")
      val lengde = kjoretoy.substringAfter("lengde=").substringBefore(",")
      val hoyde = kjoretoy.substringAfter("hoyde=").substringBefore(",")
      val vekt = kjoretoy.substringAfter("vekt=").substringBefore(",")

      Spacer(modifier = modifier.height(80.dp))

      if (index != -1) {
        Card (colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)){
        Row(
          modifier = modifier
          .padding(20.dp),
        ) {
          Column() {
            Text(text = "Merke", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Serie", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Type", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Farge", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Girkassetype", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Drivstoff", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Hybrid", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Hestekrefter", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Maks hastighet", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Forste registrering", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Sitteplasser", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Antall dører:", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Høyde", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Bredde", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Lengde", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Egenvekt", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Sist godkjent:", color = MaterialTheme.colorScheme.onBackground)
            Text(text = "Neste EU-kontroll:", color = MaterialTheme.colorScheme.onBackground)
          }
          Spacer(modifier = modifier.width(30.dp))
          Column {
            Text(
              kjoretoy.substringAfter("merke=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("serie=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("type=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("farge=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("girinfo=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("drivstoffinfo=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("hybrid=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            if (hestekrefter != "Ikke oppgitt") {
              Text(
                "$hestekrefter hk",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
              )
            } else {
              Text(
                hestekrefter,
                fontWeight = FontWeight.Bold,
                        color = MaterialTheme.colorScheme.onBackground
              )
            }
            if (maksHastighet != "Ikke oppgitt") {
              Text(
                "$maksHastighet km/t",
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
              )
            } else {
              Text(
                maksHastighet,
                color = MaterialTheme.colorScheme.onBackground,
                fontWeight = FontWeight.Bold
              )
            }
            Text(
              kjoretoy.substringAfter("forstereg=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("sitteplasser=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("antDorer=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              if (hoyde != "Ikke oppgitt") {
                "$hoyde cm"
              } else {
                hoyde
              },
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              if (bredde != "Ikke oppgitt") {
                "$bredde cm"
              } else {
                bredde
              },
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              if (lengde != "Ikke oppgitt") {
                "$lengde cm"
              } else {
                lengde
              },
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              if (vekt != "Ikke oppgitt") {
                "$vekt kg"
              } else {
                vekt
              },
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("sistgodkjent=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              kjoretoy.substringAfter("nesteEU=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
          }
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


