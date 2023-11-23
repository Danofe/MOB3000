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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
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
            Text(text = stringResource(R.string.brand))
            Text(text = stringResource(R.string.series))
            Text(text = stringResource(R.string.type))
            Text(text = stringResource(R.string.color))
            Text(text = stringResource(R.string.gearbox_type))
            Text(text = stringResource(R.string.fuel))
            Text(text = stringResource(R.string.hybrid))
            Text(text = stringResource(R.string.horsepower))
            Text(text = stringResource(R.string.max_speed))
            Text(text = stringResource(R.string.first_registration))
            Text(text = stringResource(R.string.number_of_seats))
            Text(text = stringResource(R.string.number_of_doors))
            Text(text = stringResource(R.string.height))
            Text(text = stringResource(R.string.width))
            Text(text = stringResource(R.string.length))
            Text(text = stringResource(R.string.weight))
            Text(text = stringResource(R.string.latest_approval))
            Text(text = stringResource(R.string.next_eu_control))
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
            if (hestekrefter != stringResource(R.string.not_specified)) {
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
            if (maksHastighet != stringResource(R.string.not_specified)) {
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
            text = stringResource(R.string.delete_favorite),
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
                stringResource(R.string.delete_favorite_question),
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            text = {
              Text(
                stringResource(
                  R.string.delete_favorite_do_you_want,
                  valgtFavoritt
                ),
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
                    context.getString(
                      R.string.delete_favorite_confirmation,
                      valgtFavoritt
                    ),
                    Toast.LENGTH_LONG
                  ).show()
                  valgtFavoritt = ""
                },

                ) {
                Text(
                  stringResource(R.string.delete_favorite),
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
                  stringResource(R.string.cancel),
                  color = MaterialTheme.colorScheme.onSurface
                )
              }
            })
        }
      } else if (favorittliste.isEmpty()) {
        Text(stringResource(R.string.you_have_no_saved_favorites))
      } else {
        Text(stringResource(R.string.choose_favorite))
      }
    }
  }