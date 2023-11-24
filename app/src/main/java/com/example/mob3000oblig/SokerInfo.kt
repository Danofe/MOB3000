package com.example.mob3000oblig

import android.util.Log
import android.widget.Toast
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.height
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.LaunchedEffect
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000oblig.DataApi.APIViewModel
import com.example.mob3000oblig.DataApi.bilInfoVariabler
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.example.mob3000oblig.Database.Firestore


class SokerInfo {
  @Composable
  fun SkiltInfo(
    viewModel: APIViewModel,
    name: String?,
    modifier: Modifier = Modifier,
    Auth: Auth = Auth(),
    Firestore: Firestore = Firestore()
  ) {
    var url by remember { mutableStateOf("") }
    url = "kjoretoydata?kjennemerke=$name"
    var bilInfo by remember { mutableStateOf<KjoretoyDataListe?>(null) }
    //val scope = rememberCoroutineScope()
    val context = LocalContext.current
    LaunchedEffect(url) {
      try {
        val info = viewModel.hentBilInfo(url)
        bilInfo = info
        Log.e(
          "YoOscar",
          "Info: $info"
        )
      } catch (e: Exception) {
        Log.e(
          "Oscar",
          "Error: ${e.message}"
        )
      }
    }
    Column(
      modifier = modifier
        .verticalScroll(rememberScrollState())
        .padding(
          top = 18.dp,
          bottom = 20.dp
        ),
      horizontalAlignment = Alignment.CenterHorizontally, //Sentrere
      verticalArrangement = Arrangement.spacedBy(20.dp)
    )
    {
      val context = LocalContext.current
      val verdi = bilInfoVariabler(context, bilInfo)
      var visMerKnapp by remember { mutableStateOf(false) }
      var visMerKnappText by remember { mutableStateOf(context.getString(R.string.show_more)) }
      val ikkeOppgitt = stringResource(R.string.not_specified)
      var lagtInn by remember { mutableStateOf(false) }

      if (verdi.merke != ikkeOppgitt) {
        Text(
          text = "$name",
          fontSize = 40.sp,
          color = MaterialTheme.colorScheme.onBackground,
        )

        Card(colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)) {
          Row(
            modifier = modifier
              .align(Alignment.CenterHorizontally)
              .padding(20.dp),
            horizontalArrangement = Arrangement.spacedBy(16.dp),


            ) {
            Column(
              modifier = modifier,
              verticalArrangement = Arrangement.spacedBy(8.dp)

            ) {
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
              if (visMerKnapp) {
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
            }

            Column(
              modifier = modifier,
              verticalArrangement = Arrangement.spacedBy(8.dp)
            ) {
              Text(
                text = verdi.merke,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.handelsbetegnelse,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.type,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.farge,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.girtyp,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.drivstoff,
                color = MaterialTheme.colorScheme.onBackground,
              )
              Text(
                text = verdi.hybrid,
                color = MaterialTheme.colorScheme.onBackground,
              )
              if (verdi.hk != ikkeOppgitt) {
                Text(
                  text = stringResource(
                    R.string.hp, // hestekrefter
                    verdi.hk
                  ),
                  color = MaterialTheme.colorScheme.onBackground,
                )
              } else {
                Text(
                  text = verdi.hk,
                  color = MaterialTheme.colorScheme.onBackground,
                )
              }
              if (verdi.toppHastighet != ikkeOppgitt) {
                Text(
                  text = stringResource(
                    R.string.km_h,
                    verdi.toppHastighet
                  ),
                  color = MaterialTheme.colorScheme.onBackground,
                )
              } else {
                Text(
                  text = verdi.toppHastighet,
                  color = MaterialTheme.colorScheme.onBackground,
                )
              }
              Text(
                text = verdi.forsteReg,
                color = MaterialTheme.colorScheme.onBackground,
              )
              if (visMerKnapp) {
                Text(
                  text = verdi.antSeter,
                  color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                  text = verdi.antdorer,
                  color = MaterialTheme.colorScheme.onBackground,
                )
                if (verdi.hoyde != ikkeOppgitt) {
                  Text(
                    text = stringResource(
                      R.string.cm,
                      verdi.hoyde
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                } else {
                  Text(
                    text = verdi.hoyde,
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                }
                if (verdi.bredde != ikkeOppgitt) {
                  Text(
                    text = stringResource(
                      R.string.cm,
                      verdi.bredde
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                } else {
                  Text(
                    text = verdi.bredde,
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                }
                if (verdi.lengde != ikkeOppgitt) {
                  Text(
                    text = stringResource(
                      R.string.cm,
                      verdi.lengde
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                } else {
                  Text(
                    text = verdi.lengde,
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                }
                if (verdi.vekt != ikkeOppgitt) {
                  Text(
                    text = stringResource(
                      R.string.kg,
                      verdi.vekt
                    ),
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                } else {
                  Text(
                    text = verdi.vekt,
                    color = MaterialTheme.colorScheme.onBackground,
                  )
                }
                Text(
                  text = verdi.sistgodkjent,
                  color = MaterialTheme.colorScheme.onBackground,
                )
                Text(
                  text = verdi.nesteEU,
                  color = MaterialTheme.colorScheme.onBackground,

                  )
              }
            }
          }
        }
        Row(
          modifier = Modifier
            .padding(vertical = 8.dp)
            .align(Alignment.CenterHorizontally),
          horizontalArrangement = Arrangement.spacedBy(8.dp)
        ) {
          Button(
            onClick = {
              visMerKnapp = !visMerKnapp
              visMerKnappText =
                if (visMerKnapp) context.getString(R.string.show_less) else context.getString(R.string.show_more)
            },
            modifier = modifier

          )
          {
            Text(
              visMerKnappText,
              color = MaterialTheme.colorScheme.onSurface
            )
          }
          Button(
            onClick = {
              Firestore.leggInnFavoritt(
                name,
                verdi.merke,
                verdi.hk,
                verdi.antSeter,
                verdi.farge,
                verdi.type,
                verdi.toppHastighet,
                verdi.drivstoff,
                verdi.girtyp,
                verdi.lengde,
                verdi.hoyde,
                verdi.bredde,
                verdi.vekt,
                verdi.hybrid,
                verdi.sistgodkjent,
                verdi.nesteEU,
                verdi.forsteReg,
                verdi.antdorer,
                verdi.handelsbetegnelse
              )
              lagtInn = true
              Toast.makeText(
                context,
                context.getString(R.string.added_to_favorites, name),
                Toast.LENGTH_SHORT
              ).show()
            },
            enabled = (Auth.innlogget() && !lagtInn),
            colors = ButtonDefaults.buttonColors(
              disabledContainerColor = Color.LightGray
            )
          ) {
            Text(
              stringResource(R.string.add_to_favorites),
              color = MaterialTheme.colorScheme.onSurface
            )
          }
        }
      } else {
        Column(
          horizontalAlignment = Alignment.CenterHorizontally,
          verticalArrangement = Arrangement.Center,
          modifier = modifier.fillMaxSize()
        ) {
          Image(
            painter = painterResource(id = R.drawable.skiltskern),
            contentDescription = stringResource(R.string.logo_name),
            modifier = Modifier.size(200.dp)
          )
          Text(
            text = "$name",
            fontSize = 40.sp,
            color = MaterialTheme.colorScheme.onBackground,
          )
          Spacer(modifier = Modifier.height(10.dp))
          Text(
            text = stringResource(R.string.found_no_vehicles),
            fontSize = 20.sp,
            textAlign = TextAlign.Center,
            color = MaterialTheme.colorScheme.onBackground
          )
        }
      }
      Spacer(modifier = modifier.padding(bottom = 84.dp))
    }
  }
}