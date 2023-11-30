package com.example.mob3000oblig.DataApi

import android.content.Context
import android.util.Log
import androidx.compose.runtime.Composable
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.res.stringResource
import com.example.mob3000oblig.DataModeller.KjoretoyDataListe
import com.example.mob3000oblig.R
import kotlin.math.roundToInt

data class HentBilInfo(
    val merke: String,
    val antSeter: String,
    val farge: String,
    val type: String,
    val toppHastighet: String,
    val drivstoff: String,
    val girtyp: String,
    val lengde: String,
    val hoyde: String,
    val bredde: String,
    val vekt: String,
    val hybrid: String,
    val hk: String,
    val sistgodkjent: String,
    val nesteEU: String,
    val forsteReg: String,
    val antdorer: String,
    val handelsbetegnelse: String
)

fun bilInfoVariabler(context: Context, bilInfo: KjoretoyDataListe?): HentBilInfo {

    // Sjekker om "maksNettoEffekt" er tilgjengelig, hvis ikke brukes "maksEffektPrTime", hvis ikke brukes 0
    // Måtte gjøres slik for å regne ut hestekrefter av både elektriske, fossile og hybride biler
    // Får ut i kw, så må konvertere til hk med å gange med 1.34102209
    val motorOgDrivverk = bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk
    var hk = 0
    motorOgDrivverk?.motor?.forEach { motor ->
        motor?.drivstoff?.forEach { drivstoff ->
            // Sjekker om "maksNettoEffekt" er tilgjengelig
            val nettoEffekt = drivstoff?.maksNettoEffekt ?: 0.0

            // Hvis ikke, bruk "maksEffektPrTime"
            if (nettoEffekt == 0.0) {
                val effektPrTime = drivstoff?.maksEffektPrTime ?: 0.0
                hk += (effektPrTime.toInt() * 1.34102209).roundToInt()
            } else {
                hk += (nettoEffekt.toInt() * 1.34102209).roundToInt()
            }
        }
    }
    val error = context.getString(R.string.not_specified)
    val merke =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.generelt?.merke?.get(
            0
        )?.merke ?: error
    val type =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.kjoretoyklassifisering?.beskrivelse
            ?: error
    var antSeter =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.persontall?.sitteplasserTotalt.toString()
    if (antSeter == "0") {
        antSeter = error
    }
    val farge =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.karosseriOgLasteplan?.rFarge?.getOrNull(
            0
        )?.kodeNavn ?: error
    var toppHastighet =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.maksimumHastighet?.getOrNull(
            0
        ).toString()
    if (toppHastighet == "null") {
        toppHastighet = error
    }
    val drivstoff =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.miljodata?.miljoOgdrivstoffGruppe?.getOrNull(
            0
        )?.drivstoffKodeMiljodata?.kodeNavn ?: error
    val girtyp =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.girkassetype?.kodeBeskrivelse
            ?: error
    var hoyde =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.dimensjoner?.hoyde.toString()
    if (hoyde == "0") {
        hoyde = error
    }
    var bredde =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.dimensjoner?.bredde.toString()
    if (bredde == "0") {
        bredde = error
    }
    var lengde =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.dimensjoner?.lengde.toString()
    if (lengde == "0") {
        lengde = error
    }
    var vekt =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.vekter?.egenvekt.toString()
    if (vekt == "0") {
        vekt = error
    }
    var hybrid =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.motorOgDrivverk?.hybridKategori?.kodeNavn
            ?: error
    if (hybrid == "Ingen") {
        hybrid = "Nei"
    }
    val sistgodkjent =
        bilInfo?.kjoretoydataListe?.get(0)?.periodiskKjoretoyKontroll?.sistGodkjent ?: error
    val nesteEU =
        bilInfo?.kjoretoydataListe?.get(0)?.periodiskKjoretoyKontroll?.kontrollfrist ?: error
    val forsteReg =
        bilInfo?.kjoretoydataListe?.get(0)?.forstegangsregistrering?.registrertForstegangNorgeDato
            ?: error
    var antdorer =
        bilInfo?.kjoretoydataListe?.get(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.karosseriOgLasteplan?.antallDorer?.getOrNull(
            0
        ).toString()
    if (antdorer == "null") {
        antdorer = error
    }
    var handelsbetegnelse =
        bilInfo?.kjoretoydataListe?.getOrNull(0)?.godkjenning?.tekniskGodkjenning?.tekniskeData?.generelt?.handelsbetegnelse.toString()
    handelsbetegnelse = handelsbetegnelse.substringAfter("[").substringBefore("]")
    if (handelsbetegnelse == "-") {
        handelsbetegnelse = error
    }

    return HentBilInfo(
        merke,
        antSeter,
        farge,
        type,
        toppHastighet,
        drivstoff,
        girtyp,
        lengde,
        hoyde,
        bredde,
        vekt,
        hybrid,
        hk.toString(),
        sistgodkjent,
        nesteEU,
        forsteReg,
        antdorer,
        handelsbetegnelse
    )
}