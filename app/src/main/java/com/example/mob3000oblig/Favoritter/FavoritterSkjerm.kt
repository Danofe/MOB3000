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
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.R

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
  var index by remember { mutableStateOf(-1) }

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
      Card(colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
        Row(
          modifier = modifier
            .padding(20.dp),
        ) {
          Column() {
            Text(
              text = stringResource(R.string.brand),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.series),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.type),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.color),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.gearbox_type),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.fuel),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.hybrid),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.horsepower),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.max_speed),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.first_registration),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.number_of_seats),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.number_of_doors),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.height),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.width),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.length),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.weight),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.latest_approval),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
            Text(
              text = stringResource(R.string.next_eu_control),
              color = MaterialTheme.colorScheme.onBackground,
              fontWeight = FontWeight.Bold
            )
          }
          Spacer(modifier = modifier.width(30.dp))
          Column {
            Text(
              kjoretoy.substringAfter("merke=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("serie=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("type=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("farge=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("girinfo=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("drivstoffinfo=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("hybrid=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            if (hestekrefter != stringResource(R.string.not_specified)) {
              Text(
                "$hestekrefter hk",
                color = MaterialTheme.colorScheme.onBackground
              )
            } else {
              Text(
                hestekrefter,
                color = MaterialTheme.colorScheme.onBackground
              )
            }
            if (maksHastighet != stringResource(R.string.not_specified)) {
              Text(
                "$maksHastighet km/t",
                color = MaterialTheme.colorScheme.onBackground
              )
            } else {
              Text(
                maksHastighet,
                color = MaterialTheme.colorScheme.onBackground
              )
            }
            Text(
              kjoretoy.substringAfter("forstereg=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("sitteplasser=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("antDorer=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (hoyde != stringResource(R.string.not_specified)) {
                "$hoyde cm"
              } else {
                hoyde
              },
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (bredde != stringResource(R.string.not_specified)) {
                "$bredde cm"
              } else {
                bredde
              },
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (lengde != stringResource(R.string.not_specified)) {
                "$lengde cm"
              } else {
                lengde
              },
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (vekt != stringResource(R.string.not_specified)) {
                "$vekt kg"
              } else {
                vekt
              },
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("sistgodkjent=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              kjoretoy.substringAfter("nesteEU=")
                .substringBefore(","),
              color = MaterialTheme.colorScheme.onBackground
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
                    R.string.delete_confirmation,
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
          }
        )
      }
    } else if (favorittliste.isEmpty()) {
      Text(stringResource(R.string.you_have_no_saved_favorites))
    } else {
      Text(stringResource(R.string.choose_favorite))
    }
  }
}