package com.example.mob3000oblig.Settings

import android.app.LocaleManager
import android.os.LocaleList
import android.widget.Toast
import androidx.compose.animation.AnimatedVisibility
import androidx.compose.foundation.Image
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
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.foundation.rememberScrollState
import androidx.compose.foundation.verticalScroll
import androidx.compose.material3.AlertDialog
import androidx.compose.material3.Button
import androidx.compose.material3.ButtonDefaults
import androidx.compose.material3.Card
import androidx.compose.material3.CardDefaults
import androidx.compose.material3.Divider
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.ExposedDropdownMenuBox
import androidx.compose.material3.ExposedDropdownMenuDefaults
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Switch
import androidx.compose.material3.SwitchDefaults
import androidx.compose.material3.Text
import androidx.compose.material3.TextField
import androidx.compose.material3.TextFieldDefaults
import androidx.compose.runtime.Composable
import androidx.compose.runtime.CompositionLocalProvider
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.graphics.Color
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalTextInputService
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.input.PasswordVisualTransformation
import androidx.compose.ui.text.intl.Locale
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth
import com.example.mob3000oblig.R
import com.example.mob3000oblig.Screen
import com.example.mob3000oblig.ui.theme.ProvideAppThemeState


class Settings {
  @Composable
  fun Profil(navController: NavController) {
    if (!Auth().innlogget()) {
      Card(
        modifier = Modifier.padding(32.dp),
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
                text = stringResource(R.string.login),
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
                text = stringResource(R.string.register),
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
              text = stringResource(R.string.profile),
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
            visualTransformation = PasswordVisualTransformation(),
            label = {
              Text(
                text = stringResource(R.string.new_password),
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
                     context.getString(R.string.password_changed),
                     Toast.LENGTH_LONG
                   ).show()
                   navController.navigate(Screen.Start.ruter)
                 }) {
            Text(
              text = stringResource(R.string.change_password),
              color = MaterialTheme.colorScheme.onSurface,
            )

          }
          Button(modifier = Modifier.align(Alignment.CenterHorizontally),
                 onClick = {
                   navController.navigate(Screen.Start.ruter)
                   Toast.makeText(
                     context,
                     context.getString(
                       R.string.user_logged_out,
                       Auth().hentBrukerEmail()
                     ),
                     Toast.LENGTH_SHORT
                   ).show()
                   Auth().loggUt()
                 }) {
            Text(
              text = stringResource(R.string.logout),
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
            text = stringResource(R.string.settings),
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
            SettingsContent()
          }
        }
      }
    }
  }

  @Composable
  fun SettingsContent(modifier: Modifier = Modifier) {
    ProvideAppThemeState { darkMode, toggleDarkmode ->
      Card(
        colors = CardDefaults.cardColors(MaterialTheme.colorScheme.tertiary)
      ) {
        Column(
          modifier = modifier
            .fillMaxWidth()
            .align(Alignment.CenterHorizontally)
        ) {
          Row(
            horizontalArrangement = Arrangement.SpaceBetween,
            verticalAlignment = Alignment.CenterVertically,
            modifier = modifier
              .padding(
                vertical = 3.dp,
                horizontal = 10.dp
              )
              .fillMaxWidth()
          ) {
            Text(
              text = stringResource(R.string.change_appearance),
              color = MaterialTheme.colorScheme.onBackground
            )
            Spacer(modifier = modifier.width(10.dp))
            Switch(
              colors = SwitchDefaults.colors(
                uncheckedThumbColor = Color.LightGray,
                uncheckedTrackColor = MaterialTheme.colorScheme.tertiary,
                checkedThumbColor = MaterialTheme.colorScheme.tertiary,
              ),
              checked = darkMode,
              onCheckedChange = { toggleDarkmode() }
            )
          }
        }
        Divider(
          color = MaterialTheme.colorScheme.onBackground,
          thickness = 2.dp,
        )
        Spacer(modifier = modifier.height(10.dp))
        Column(modifier = modifier.padding(horizontal = 10.dp)) {
          Text(
            text = stringResource(R.string.change_language),
            color = MaterialTheme.colorScheme.onBackground
          )
        }
        LanguageToggle()
      }
    }
  }

  @OptIn(ExperimentalMaterial3Api::class)
  @Composable
  fun LanguageToggle(modifier: Modifier = Modifier) {
    val context = LocalContext.current
    val språk = arrayOf(
      "English",
      "Norsk",
      "Français"
    )
    val flagg = listOf(
      R.drawable.united_kingdom,
      R.drawable.norway,
      R.drawable.france
    )
    var utvidet by remember { mutableStateOf(false) }
    var valgtSpråk = ""
    when (Locale.current.language) {
      "en" -> valgtSpråk = språk[0]
      "nb" -> valgtSpråk = språk[1]
      "fr" -> valgtSpråk = språk[2]
    }
    ExposedDropdownMenuBox(
      expanded = utvidet,
      onExpandedChange = {
        utvidet = !utvidet
      },
      modifier = modifier.padding(horizontal = 10.dp)

    ) {
      CompositionLocalProvider(LocalTextInputService provides null) {
        TextField(
          value = valgtSpråk,
          onValueChange = {},
          readOnly = true,
          colors = TextFieldDefaults.textFieldColors(
            textColor = MaterialTheme.colorScheme.onBackground,
            containerColor = MaterialTheme.colorScheme.tertiary,
            focusedIndicatorColor = MaterialTheme.colorScheme.onBackground
          ),
          trailingIcon = { ExposedDropdownMenuDefaults.TrailingIcon(expanded = utvidet) },
          modifier = Modifier
            .menuAnchor()
            .fillMaxWidth()
        )
      }

      ExposedDropdownMenu(
        expanded = utvidet,
        onDismissRequest = { utvidet = false },
        modifier = modifier.background(MaterialTheme.colorScheme.tertiary)
      ) {
        språk.forEachIndexed { index, item ->
          DropdownMenuItem(
            text = {
              Text(
                text = item,
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            leadingIcon = {
              Image(
                painter = painterResource(flagg[index]),
                contentDescription = null,
                modifier = modifier
                  .height(20.dp)
                  .width(30.dp)
              )
            },
            onClick = {
              valgtSpråk = item
              when (valgtSpråk) {
                "English" -> {
                  context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("en")
                }

                "Norsk" -> {
                  context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("nb")
                }

                "Français" -> {
                  context.getSystemService(LocaleManager::class.java)
                    .applicationLocales = LocaleList.forLanguageTags("fr")
                }
              }
            }
          )
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
            text = stringResource(R.string.terms_and_conditions),
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
          Text(
            text = stringResource(R.string.terms_text),
            color = MaterialTheme.colorScheme.onBackground
          )

        }
      }
    }
  }

  @Composable
  fun SettingsPage(navController: NavController, modifier: Modifier) {
    val slettBruker = remember { mutableStateOf(false) }
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
                 slettBruker.value = true
               }) {
          Text(
            text = stringResource(R.string.delete_user),
            color = MaterialTheme.colorScheme.tertiary,
          )
        }
        if (slettBruker.value) {
          AlertDialog(
            onDismissRequest = {
              slettBruker.value = false
            },
            title = {
              Text(
                stringResource(R.string.delete_user_question),
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            text = {
              Text(
                stringResource(
                  R.string.user_deleted_question,
                  Auth().hentBrukerEmail()
                ),
                color = MaterialTheme.colorScheme.onBackground
              )
            },
            confirmButton = {
              Button(
                colors = ButtonDefaults.buttonColors(MaterialTheme.colorScheme.primary),
                onClick = {
                  slettBruker.value = false
                  Toast.makeText(
                    context,
                    context.getString(
                      R.string.delete_confirmation,
                      Auth().hentBrukerEmail()
                    ),
                    Toast.LENGTH_LONG
                  ).show()
                  Auth().slettBruker()
                  navController.navigate(Screen.Start.ruter)
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
                  slettBruker.value = false
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
      }
      Spacer(modifier = modifier.padding(bottom = 84.dp))
    }
  }
}