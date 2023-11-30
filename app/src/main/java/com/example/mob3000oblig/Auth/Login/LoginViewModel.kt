package com.example.mob3000oblig.Auth.Login

import android.content.Context
import android.widget.Toast
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.mob3000oblig.Auth.Auth
import com.example.mob3000oblig.Nav.Skjerm
import kotlinx.coroutines.launch

class LoginViewModel(

  private val auth: Auth = Auth()
) : ViewModel() {
      val loggetInn: Boolean
        get() = auth.innlogget()
      var uiStatus by mutableStateOf(LoginUiStatus())
        private set
      fun vedEmailEndring(email: String) {
        uiStatus = uiStatus.copy(email = email)
      }
      fun vedPassordEndring(passord: String) {
        uiStatus = uiStatus.copy(passord = passord)
      }
      fun error(error: String) {
          uiStatus = uiStatus.copy(error = error)
      }

      private fun validerLogin() =
          uiStatus.email.isNotBlank() &&
          uiStatus.passord.isNotBlank()

      fun loginBruker(context: Context, navController: NavController) = viewModelScope.launch {
          try {
              if (!validerLogin()) {
                  error("Fyll inn alle feltene")
              }
              val loginResultat = auth.login(uiStatus.email, uiStatus.passord)

              if (!loginResultat) {
                  error("Feil brukernavn eller passord")
              } else {
                  uiStatus = uiStatus.copy(loggetInn = true)
                  navController.navigate(Skjerm.Start.ruter)
                  Toast.makeText(context,"Logget inn", Toast.LENGTH_SHORT).show()
              }

          } catch (e: Exception) {
              uiStatus = uiStatus.copy(error = "Kunne ikke logge inn")
          }
      }
}
  data class LoginUiStatus(
      var email: String = "",
      var passord: String = "",
      var error: String? = null,
      var loader: Boolean = false,
      var loggetInn: Boolean = false,
      )