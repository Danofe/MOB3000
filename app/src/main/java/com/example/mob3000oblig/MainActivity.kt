package com.example.mob3000oblig

import android.icu.text.Transliterator.Position
import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.compose.foundation.Image
import androidx.compose.foundation.layout.Arrangement
import androidx.compose.foundation.layout.Box

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.padding
import androidx.compose.material3.CenterAlignedTopAppBar
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Surface
import androidx.compose.material3.Text
import androidx.compose.material3.TopAppBarDefaults
import androidx.compose.runtime.Composable
import androidx.compose.ui.Alignment
import androidx.compose.ui.Alignment.Companion.Center
import androidx.compose.ui.Alignment.Companion.CenterHorizontally
import androidx.compose.ui.Alignment.Companion.CenterStart
import androidx.compose.ui.Alignment.Companion.TopCenter
import androidx.compose.ui.Modifier
import androidx.compose.ui.res.colorResource
import androidx.compose.ui.res.painterResource
import androidx.compose.ui.res.stringResource
import androidx.compose.ui.text.font.FontWeight
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.tooling.preview.Preview
import androidx.compose.ui.unit.dp
import androidx.compose.ui.unit.sp
import com.example.mob3000oblig.ui.theme.Mob3000ObligTheme
import org.intellij.lang.annotations.JdkConstants.HorizontalAlignment

class MainActivity : ComponentActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContent {
            Mob3000ObligTheme {
                // A surface container using the 'background' color from the theme
                Surface(
                    modifier = Modifier.fillMaxSize(),
                    color = MaterialTheme.colorScheme.background
                ) {

                }
            }
        }
    }
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
fun SkiltsokerenApp(modifier: Modifier = Modifier) {
    Scaffold(topBar = {
        CenterAlignedTopAppBar(
            title = {
                Image(painter = painterResource(id = R.drawable.skiltskern ), contentDescription = "skiltskern")


            },
            colors = TopAppBarDefaults.centerAlignedTopAppBarColors(
                containerColor = colorResource(R.color.purple_700),
                titleContentColor = colorResource(R.color.black),
            ),
        )
    }
    ) { it ->
        Column(modifier = modifier.padding(it)) {
            Box(
                modifier = modifier.fillMaxSize()
               ) {
                Text(text = "Skiltnr ", fontSize = 40.sp, modifier = modifier.align(TopCenter).padding(top = 40.dp))



                Column(
                    modifier = modifier.align(CenterStart).padding(40.dp),
                    verticalArrangement = Arrangement.spacedBy(40.dp),
                    horizontalAlignment = Alignment.Start,
                ) {

                    Text(text = "Mål: ")
                    Text(text = "Vekt: ")
                    Text(text = "EU-Kontroll: ")
                    Text(text = "Registreringsdata: ")
                    Text(text = "Utslipp: ")
                    Text(text = "Dekk: ")
                    Text(text = "Felg: ")
                    Text(text = "Etc: ")
                }
            }
        }
    }
}

@Preview(showBackground = true)
@Composable
fun GreetingPreview() {
    Mob3000ObligTheme {
    SkiltsokerenApp()
    }
}