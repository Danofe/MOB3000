package com.example.mob3000oblig.Favoritter

import android.widget.Toast
import androidx.compose.foundation.horizontalScroll
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
import androidx.compose.foundation.rememberScrollState
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
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

@Composable
fun FavoritterSkjerm(
  modifier: Modifier = Modifier,
  viewModel: FavoritterViewModel? = viewModel(),
) {
  var kjoretoy by remember { mutableStateOf("") }
  val slettemelding = remember { mutableStateOf(false) }
  var valgtFavoritt by remember { mutableStateOf("") }
  val favorittliste = viewModel?.favoritterSkilt?.value
  var index by remember { mutableStateOf(-1) }

  var ikkeOppgitt = ""

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
    val merke = kjoretoy.substringAfter("merke=").substringBefore(",")
    val nesteEU = kjoretoy.substringAfter("nesteEU=").substringBefore(",")
    val serie = kjoretoy.substringAfter("serie=").substringBefore(",")
    val type = kjoretoy.substringAfter("type=").substringBefore(",")
    val farge = kjoretoy.substringAfter("farge=").substringBefore(",")
    val girinfo = kjoretoy.substringAfter("girinfo=").substringBefore(",")
    val drivstoffinfo = kjoretoy.substringAfter("drivstoffinfo=").substringBefore(",")
    val hybrid = kjoretoy.substringAfter("hybrid=").substringBefore(",")
    val forstereg = kjoretoy.substringAfter("forstereg=").substringBefore(",")
    val sitteplasser = kjoretoy.substringAfter("sitteplasser=").substringBefore(",")
    val antDorer = kjoretoy.substringAfter("antDorer=").substringBefore(",")
    val sistGodkjent = kjoretoy.substringAfter("sistgodkjent=").substringBefore(",")

    // Sjekker språket på "Ikke oppgitt" i databasen
    if (kjoretoy.contains(
        "Ikke oppgitt",
        ignoreCase = true
      )
    ) {
      ikkeOppgitt = "Ikke oppgitt"
    }
    if (kjoretoy.contains(
        "Non spécifié",
        ignoreCase = true
      )
    ) {
      ikkeOppgitt = "Non spécifié"
    }
    if (kjoretoy.contains(
        "Not specified",
        ignoreCase = true
      )
    ) {
      ikkeOppgitt = "Not specified"
    }

    Spacer(modifier = modifier.height(80.dp))

    if (index != -1) {
      Card(
        modifier = modifier
          .horizontalScroll(rememberScrollState()),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
      ) {
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

          // Sjekker alle verdiene på de forskjellige språkene, fikk ikke til å endre språket på verdier i databasen
          Column {
            Text(
              if (merke == ikkeOppgitt) stringResource(R.string.not_specified) else merke.uppercase(),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (serie == ikkeOppgitt) stringResource(R.string.not_specified) else serie.uppercase(),
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (type == ikkeOppgitt) stringResource(R.string.not_specified) else type,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (farge == ikkeOppgitt) stringResource(R.string.not_specified) else farge,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (girinfo == ikkeOppgitt) stringResource(R.string.not_specified) else girinfo,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (drivstoffinfo == ikkeOppgitt) stringResource(R.string.not_specified) else drivstoffinfo,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (hybrid == ikkeOppgitt) stringResource(R.string.not_specified) else hybrid,
              color = MaterialTheme.colorScheme.onBackground
            )
            if (hestekrefter == ikkeOppgitt || hestekrefter == "0") {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.hp,
                  hestekrefter
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            if (maksHastighet == ikkeOppgitt) {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.km_h,
                  maksHastighet
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            Text(
              if (forstereg == ikkeOppgitt) stringResource(R.string.not_specified) else forstereg,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (sitteplasser == ikkeOppgitt) stringResource(R.string.not_specified) else sitteplasser,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (antDorer == ikkeOppgitt) stringResource(R.string.not_specified) else antDorer,
              color = MaterialTheme.colorScheme.onBackground
            )
            if (hoyde == ikkeOppgitt) {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.cm,
                  hoyde
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            if (bredde == ikkeOppgitt) {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.cm,
                  bredde
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            if (lengde == ikkeOppgitt) {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.cm,
                  lengde
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            if (vekt == ikkeOppgitt) {
              Text(
                text = stringResource(R.string.not_specified),
                color = MaterialTheme.colorScheme.onBackground,
              )
            } else {
              Text(
                text = stringResource(
                  R.string.kg,
                  vekt
                ),
                color = MaterialTheme.colorScheme.onBackground,
              )
            }
            Text(
              if (sistGodkjent == ikkeOppgitt) stringResource(R.string.not_specified) else sistGodkjent,
              color = MaterialTheme.colorScheme.onBackground
            )
            Text(
              if (nesteEU == ikkeOppgitt) stringResource(R.string.not_specified) else nesteEU,
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