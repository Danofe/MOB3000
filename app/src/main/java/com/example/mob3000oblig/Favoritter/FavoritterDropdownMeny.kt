package com.example.mob3000oblig.Favoritter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.Row
import androidx.compose.foundation.layout.Spacer
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.internal.illegalDecoyCallException
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.HorizontalAlignmentLine
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritterDropdownMeny(viewModel: FavoritterViewModel? = viewModel(), modifier: Modifier = Modifier) {

    var utvidet by remember { mutableStateOf(false) }
    var favorittliste = viewModel?.favoritterSkilt?.value
    var valgtFavoritt by remember { mutableStateOf("") }
    var kjoretoy by remember { mutableStateOf("") }

    var textFieldSize by remember { mutableStateOf(0) }

    val ikon = if (utvidet) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(value = valgtFavoritt,
            onValueChange = { valgtFavoritt = it },
            readOnly = true,
            textStyle = androidx.compose.ui.text.TextStyle(fontSize = 30.sp, fontWeight = FontWeight.Bold),
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFieldSize = coordinates.size.width
                },
            label = { Text("Velg skiltnr") },
            trailingIcon = {
                Icon(ikon, "", Modifier.clickable { utvidet = !utvidet })
            }
        )
        DropdownMenu(
            expanded = utvidet,
            onDismissRequest = { utvidet = false },
            modifier = Modifier
                .width(with(LocalDensity.current) { textFieldSize.toDp() })
                .fillMaxWidth()
        ) {

            for (i in favorittliste!!) {
                DropdownMenuItem(text = { Text(text = i) }, onClick = {
                    valgtFavoritt = i
                    utvidet = false
                })
            }
        }
    }
    val index = favorittliste?.indexOf(valgtFavoritt)
    kjoretoy = viewModel?.allefavoritter?.value?.getOrNull(index!!).toString()
    val hestekrefter = kjoretoy.substringAfter("hestekrefter=").substringBefore(",")
    val maksHastighet = kjoretoy.substringAfter("maksHastighet=").substringBefore(",")

    if (index != -1) {
        Box(
            modifier = modifier.fillMaxSize(),
            contentAlignment = Alignment.Center
        ) {
            Row(
                modifier = modifier,

                ) {
                Column(

                ) {
                    Text(text = "Merke:")
                    Text(text = "Type:")
                    Text(text = "Farge:")
                    Text(text = "Girkassetype:")
                    Text(text = "Drivstoff:")
                    Text(text = "Sitteplasser:")
                    Text(text = "Maks hastighet:")
                    Text(text = "Hestekrefter:")
                    Text(text = "Sist EU-godkjenning:")
                    Text(text = "Registrert i Norge:")
                }

                Spacer(modifier = modifier.width(30.dp))
                Column {
                    Text(
                        kjoretoy.substringAfter("merke=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("type=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("farge=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("girinfo=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("drivstoffinfo=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("sitteplasser=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    if (maksHastighet != "Ikke oppgitt") {
                        Text("$maksHastighet km/t", fontWeight = FontWeight.Bold)
                    } else {
                        Text(maksHastighet, fontWeight = FontWeight.Bold)
                    }
                    if (hestekrefter != "Ikke oppgitt") {
                        Text("≈$hestekrefter hk", fontWeight = FontWeight.Bold)
                    } else {
                        Text(hestekrefter, fontWeight = FontWeight.Bold)
                    }
                    Text(
                        kjoretoy.substringAfter("sistgodkjent=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                    Text(
                        kjoretoy.substringAfter("forstereg=")
                            .substringBefore(","),
                        fontWeight = FontWeight.Bold
                    )
                }
            }
        }
    } else {

    }
}


