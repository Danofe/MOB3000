package com.example.mob3000oblig.Favoritter

import androidx.compose.foundation.clickable
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.width
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.KeyboardArrowDown
import androidx.compose.material.icons.filled.KeyboardArrowUp
import androidx.compose.material3.DropdownMenu
import androidx.compose.material3.DropdownMenuItem
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.OutlinedTextField
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Modifier
import androidx.compose.ui.layout.onGloballyPositioned
import androidx.compose.ui.platform.LocalDensity
import androidx.compose.ui.text.TextStyle
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.unit.sp
import androidx.compose.ui.zIndex
import androidx.lifecycle.viewmodel.compose.viewModel
import com.example.mob3000oblig.Favoritter.FavoritterViewModel

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun Meny(
    viewModel: FavoritterViewModel? = viewModel(),
    onValueChanged: (String) -> Unit = {},
    valgt: String,
    modifier: Modifier = Modifier
) {
    var utvidet by remember { mutableStateOf(false) }
    val favorittliste = viewModel?.favoritterSkilt?.value
    var valgt = valgt
    var textFieldSize by remember { mutableStateOf(0) }

    val ikon = if (utvidet) {
        Icons.Filled.KeyboardArrowUp
    } else {
        Icons.Filled.KeyboardArrowDown
    }

    OutlinedTextField(
        value = valgt,
        onValueChange = {
            onValueChanged(it) },
        readOnly = true,
        textStyle = TextStyle(
            fontSize = 30.sp,
            fontWeight = FontWeight.Bold
        ),
        modifier = Modifier
            .fillMaxWidth()
            .onGloballyPositioned { coordinates ->
                textFieldSize = coordinates.size.width
            },

        label = { Text("Velg skiltnr") },

        trailingIcon = {
            Icon(
                ikon,
                "",
                Modifier.clickable { utvidet = !utvidet }
            )
        },
    )
    DropdownMenu(
        expanded = utvidet,
        onDismissRequest = { utvidet = false },
        modifier = Modifier
            .width(with(LocalDensity.current) { textFieldSize.toDp() })
            .fillMaxWidth()
            .zIndex(1f)
    ) {
        for (i in favorittliste!!) {
            DropdownMenuItem(
                text = {
                    Text(
                        text = i,
                        color = MaterialTheme.colorScheme.onBackground
                    )
                },
                onClick = {
                    valgt = i
                    utvidet = false
                    onValueChanged(valgt)
                }
            )
        }
    }
}
