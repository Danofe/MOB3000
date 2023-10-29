package com.example.mob3000oblig.Registrer

import android.content.Context
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.setValue
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.mob3000oblig.Auth
import kotlinx.coroutines.launch

class RegisterViewModel (
private val auth: Auth = Auth()
): ViewModel() {

    var reguiState by mutableStateOf(registerUiState())
        private set

    fun onEmailRegChange(emailReg: String) {
        reguiState = reguiState.copy(emailReg = emailReg)
    }

    fun onPassordRegChange(passordReg: String) {
        reguiState = reguiState.copy(passordReg = passordReg)
    }

    fun onPassordBekRegChange(passordBekreftReg: String) {
        reguiState = reguiState.copy(passordBekreftReg = passordBekreftReg)
    }

    private fun validerRegister() =
        reguiState.emailReg.isNotBlank() &&
        reguiState.passordReg.isNotBlank() &&
        reguiState.passordBekreftReg.isNotBlank() &&
        reguiState.passordReg == reguiState.passordBekreftReg

    fun lagBruker(context: Context) = viewModelScope.launch() {
        try {
            if (!validerRegister()) {
                reguiState = reguiState.copy(error = "Fyll inn alle feltene")
            }

                auth.lagBruker(reguiState.emailReg, reguiState.passordReg)

                reguiState = reguiState.copy(registrert = true)

        } catch (e: Exception) {
            reguiState = reguiState.copy(error = "Kunne ikke lage bruker")
        }
    }





}


data class registerUiState(
    val brukernavnReg:String = "",
    val emailReg:String = "",
    val passordReg:String = "",
    val passordBekreftReg:String = "",
    val error: String? = null,
    val loaderReg : Boolean = false,
    val registrert : Boolean = false,
)