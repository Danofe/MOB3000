package com.example.mob3000oblig

import android.util.Log
import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.Column
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
import androidx.compose.material3.TextField
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.dp
import androidx.lifecycle.viewmodel.compose.viewModel


@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun FavoritterDropdownMeny(viewModel: FavoritterViewModel? = viewModel(), modifier: Modifier = Modifier) {

    var utvidet by remember { mutableStateOf(false) }
    var favorittliste = viewModel?.favoritter?.value
    var valgtFavoritt by remember { mutableStateOf("") }

    var textFiledSize by remember { mutableStateOf(0) }

    val ikon = if (utvidet) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    Column(modifier = Modifier.padding(20.dp)) {
        OutlinedTextField(value = valgtFavoritt,
            onValueChange = { valgtFavoritt = it },
            modifier = Modifier
                .fillMaxWidth()
                .onGloballyPositioned { coordinates ->
                    textFiledSize = coordinates.size.width
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
                .width(with(LocalDensity.current) { textFiledSize.toDp() })
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
}
