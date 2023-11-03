package com.example.mob3000oblig.Login

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth
import com.example.mob3000oblig.Screen
import kotlinx.coroutines.launch

class LoginViewModel(

  private val auth: Auth = Auth()
) : ViewModel() {
  val currentUser = auth.currentUser

  val loggetInn: Boolean
    get() = auth.innlogget()

  var uiState by mutableStateOf(LoginUiState())
    private set

  fun onEmailChange(email: String) {
    uiState = uiState.copy(email = email)
  }

  fun onPassordChange(passord: String) {
    uiState = uiState.copy(passord = passord)
  }

  fun onLoggetInnChange(loggetInn: Boolean) {
      uiState = uiState.copy(loggetInn = loggetInn)
  }

  fun onLoaderChange(loader: Boolean) {
      uiState = uiState.copy(loader = loader)
  }

  fun onLoggetUtChange(loggetUt: Boolean) {
      uiState = uiState.copy(loggetInn = loggetUt)
  }

  fun error(error: String) {
      uiState = uiState.copy(error = error)
  }

  private fun validerLogin() =
      uiState.email.isNotBlank() &&
      uiState.passord.isNotBlank()

  fun loginBruker(context: Context, navController: NavController) = viewModelScope.launch {
      try {
          if (!validerLogin()) {
              error("Fyll inn alle feltene")
          }
          val loginResult = auth.login(uiState.email, uiState.passord)

          if (!loginResult) {
              error("Feil brukernavn eller passord")
          } else {
              uiState = uiState.copy(loggetInn = true)
              navController.navigate(Screen.Start.ruter)
          }

      } catch (e: Exception) {
          uiState = uiState.copy(error = "Kunne ikke logge inn")
      }
  }

  fun logutBruker(context: Context) = viewModelScope.launch {
      try {
          auth.loggUt()
          uiState = uiState.copy(loggetInn = false)
      } catch (e: Exception) {
          uiState = uiState.copy(error = "Kunne ikke logge ut")
          System.out.println(LoginUiState().error)
      }
  }
}
  data class LoginUiState(
      var email: String = "",
      var passord: String = "",
      var error: String? = null,
      var loader: Boolean = false,
      var loggetInn: Boolean = false,
      )


