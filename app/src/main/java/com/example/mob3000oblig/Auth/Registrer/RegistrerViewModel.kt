package com.example.mob3000oblig.Auth.Registrer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000oblig.Auth.Auth
import kotlinx.coroutines.launch

class RegistrerViewModel(
  private val auth: Auth = Auth()
) : ViewModel() {

  var reguiStatus by mutableStateOf(registrerUiStatus())
    private set

  fun onEmailRegChange(emailReg: String) {
    reguiStatus = reguiStatus.copy(emailReg = emailReg)
  }

  fun onPassordRegChange(passordReg: String) {
    reguiStatus = reguiStatus.copy(passordReg = passordReg)
  }

  fun onPassordBekRegChange(passordBekreftReg: String) {
    reguiStatus = reguiStatus.copy(passordBekreftReg = passordBekreftReg)
  }

  private fun validerRegister() =
    reguiStatus.emailReg.isNotBlank() &&
            reguiStatus.passordReg.isNotBlank() &&
            reguiStatus.passordBekreftReg.isNotBlank() &&
            reguiStatus.passordReg == reguiStatus.passordBekreftReg

  fun lagBruker(context: Context) = viewModelScope.launch() {
    try {
      if (!validerRegister()) {
        reguiStatus = reguiStatus.copy(error = "Fyll inn alle feltene")
      }

      auth.lagBruker(
        reguiStatus.emailReg,
        reguiStatus.passordReg
      )

      reguiStatus = reguiStatus.copy(registrert = true)

    } catch (e: Exception) {
      reguiStatus = reguiStatus.copy(error = "Kunne ikke lage bruker")
    }
  }


}


data class registrerUiStatus(
  val brukernavnReg: String = "",
  val emailReg: String = "",
  val passordReg: String = "",
  val passordBekreftReg: String = "",
  val error: String? = null,
  val loaderReg: Boolean = false,
  val registrert: Boolean = false,
)