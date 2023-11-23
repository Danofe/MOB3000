package com.example.mob3000oblig.Settings

import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.background
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardColors
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.draw.shadow
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.input.key.Key.Companion.Refresh
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth
import com.example.mob3000oblig.Screen
import com.example.mob3000oblig.ui.theme.ProvideAppThemeState


class Settings {
  @Composable
  fun Profil(navController: NavController) {
    if (!Auth().innlogget()) {
      Card(modifier = Modifier.padding(32.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
      ) {
        Row(
          Modifier
            .fillMaxWidth(),
          horizontalArrangement = Arrangement.Center
        ) {
          Box(
            Modifier
              .padding(8.dp)
          ) {
            Button(onClick = { navController.navigate(Screen.Login.ruter) }) {
              Text(
                text = "Login",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp
              )
            }
          }
          Spacer(modifier = Modifier.padding(8.dp))
          Box(
            Modifier
              .padding(8.dp)
          ) {
            Button(onClick = { navController.navigate(Screen.Register.ruter) }) {
              Text(
                text = "Registrer",
                color = MaterialTheme.colorScheme.onSurface,
                fontSize = 12.sp
              )
            }
          }
        }
      }
    } else {
      Card(
        modifier = Modifier.padding(32.dp),
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
      ) {
        var utvidet by remember {
          mutableStateOf(false)
        }
        Column(
          Modifier
            .clickable { utvidet = !utvidet }
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
        ) {
          Box(
            modifier = Modifier.align(Alignment.CenterHorizontally)
          ) {
            Text(
              text = "Profil",
              color = MaterialTheme.colorScheme.onBackground,
              fontSize = 20.sp,
              fontWeight = androidx.compose.ui.text.font.FontWeight.Bold
            )
          }
          AnimatedVisibility(
            visible = utvidet,
            modifier = Modifier
              .fillMaxWidth()
              .padding(16.dp)
              .align(Alignment.CenterHorizontally)
          ) {
            Card(
              modifier = Modifier
                .align(Alignment.CenterHorizontally),
                      colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
            ) {
              ProfilCard(navController)
            }
          }
        }
      }
    }
  }

  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun ProfilCard(navController: NavController) {
    val context = LocalContext.current
    Card(
      modifier = Modifier.padding(16.dp),
              colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    ) {
      Column(
        Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally)
      ) {
        Box(
          Modifier
            .fillMaxWidth()
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
        ) {
          Text(
            modifier = Modifier.align(Alignment.Center),
            text = Auth().hentBrukerEmail(),
            color = MaterialTheme.colorScheme.onBackground,
            fontSize = 16.sp
          )
        }
        Column(
          Modifier
            .padding(8.dp)
            .align(Alignment.CenterHorizontally)
        ) {
          var passord by remember {
            mutableStateOf("")
          }
          OutlinedTextField(
            value = passord,
            onValueChange = { passord = it },
            label = {
              Text(
                text = "Nytt Passord",
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            colors = TextFieldDefaults.outlinedTextFieldColors(
              MaterialTheme.colorScheme.onBackground,
              cursorColor = MaterialTheme.colorScheme.onBackground,
              focusedBorderColor = MaterialTheme.colorScheme.onBackground
            ),
            modifier = Modifier
              .fillMaxWidth()
              .padding(8.dp)
          )
          Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                 onClick = {
                   Auth().byttPassord(passord)
                   Auth().loggUt()
                   Toast.makeText(
                     context,
                     "Passord endret",
                     Toast.LENGTH_LONG
                   ).show()
                   navController.navigate(Screen.Start.ruter)
                 }) {
            Text(
              text = "Bytt passord",
              color = MaterialTheme.colorScheme.onSurface,
            )

          }
          Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                 onClick = {
                   Auth().loggUt()
                   navController.navigate(Screen.Start.ruter)
                   Toast.makeText(
                     context,
                     "${Auth().hentBrukerEmail()} logget ut",
                     Toast.LENGTH_SHORT
                   ).show()
                 }) {
            Text(
              text = "Logg ut",
              color = MaterialTheme.colorScheme.onSurface,
            )
          }

        }
      }
    }
  }

  @Composable
  fun SettingsCard() {
    Card(
      modifier = Modifier.padding(32.dp),
      colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    ) {
      var utvidet by remember {
        mutableStateOf(false)
      }

      Column(
        Modifier
          .clickable { utvidet = !utvidet }
          .fillMaxWidth()
          .padding(16.dp)
          .align(Alignment.CenterHorizontally)

      ) {
        Box(
          modifier = Modifier.align(Alignment.CenterHorizontally)

        ) {
          Text(
            text = "Instillinger",
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
          )
        }
        AnimatedVisibility(
          visible = utvidet,
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
        ) {
          Card(
            modifier = Modifier

              .align(Alignment.CenterHorizontally),
            colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
          ) {
            SettingCard()
          }
        }
      }
    }
  }


  @Composable
  fun SettingCard() {
    ProvideAppThemeState { darkMode, toggleDarkmode ->
      Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
      ) {
        Column(
          Modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
        ) {
          Row(
            Modifier.align(Alignment.CenterHorizontally)
          ) {
            Column(
              Modifier.padding(2.dp)
            ) {
              Text(text = "Endre utseende",color = MaterialTheme.colorScheme.onBackground,)
              Switch(
                modifier = Modifier.align(Alignment.CenterHorizontally),
                colors = SwitchDefaults.colors(
                  uncheckedThumbColor = MaterialTheme.colorScheme.secondary,
                  uncheckedTrackColor = MaterialTheme.colorScheme.tertiary,
                  checkedThumbColor = MaterialTheme.colorScheme.tertiary,
                ),
                checked = darkMode,
                onCheckedChange = { toggleDarkmode() }
              )
            }
          }
        }
      }
    }
  }

  @Composable
  fun Terms() {
    Card(
      modifier = Modifier.padding(32.dp),
      colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    ) {
      var utvidet by remember {
        mutableStateOf(false)
      }
      Column(
        Modifier
          .clickable { utvidet = !utvidet }
          .fillMaxWidth()
          .padding(16.dp)) {
        Box(
          modifier = Modifier.align(Alignment.CenterHorizontally)
        ) {
          Text(
            text = "Terms And Conditions",
            fontSize = 20.sp,
            fontWeight = androidx.compose.ui.text.font.FontWeight.Bold,
                    color = MaterialTheme.colorScheme.onBackground
          )
        }
        AnimatedVisibility(
          visible = utvidet,
          modifier = Modifier
            .fillMaxWidth()
            .padding(16.dp)
            .align(Alignment.CenterHorizontally)
        ) {
          Card(
            modifier = Modifier

              .align(Alignment.CenterHorizontally),
                    colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
          ) {
            TosCard()
          }
        }
      }
    }
  }

  @Composable
  fun TosCard() {
    Card(
      modifier = Modifier.padding(16.dp),
      colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
    ) {
      Column(
        Modifier
          .fillMaxWidth()
          .align(Alignment.CenterHorizontally)
      ) {
        Column {
          Text(text = "Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco laboris nisi ut aliquip ex ea commodo consequat. Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia deserunt mollit anim id est laborum.", color = MaterialTheme.colorScheme.onBackground,)
        }
      }
    }
  }

  @Composable
  fun SettingsPage(navController: NavController, modifier: Modifier) {
    val context = LocalContext.current
    Column(
      Modifier
        .fillMaxSize()
        .padding(16.dp)
        .verticalScroll(rememberScrollState())
    ) {
      Profil(navController)
      SettingsCard()
      Terms()
      if (Auth().innlogget()) {
        Divider(
          color = MaterialTheme.colorScheme.tertiary,
          thickness = 2.dp,
          modifier = modifier.padding(16.dp)
        )
        Spacer(modifier = Modifier.padding(16.dp))
        Button(modifier = Modifier.align(Alignment.CenterHorizontally),
          colors = ButtonDefaults.buttonColors(Color(0xFFeb4949)),
          onClick = {
            Toast.makeText(
              context,
              "${Auth().hentBrukerEmail()} slettet",
              Toast.LENGTH_SHORT
            ).show()
            Auth().slettBruker()
            navController.navigate(Screen.Start.ruter)
          }) {
          Text(
            text = "Slett bruker",
            color = MaterialTheme.colorScheme.tertiary,
          )
        }
      }
        Spacer(modifier = modifier.padding(bottom = 84.dp))
    }
  }
}