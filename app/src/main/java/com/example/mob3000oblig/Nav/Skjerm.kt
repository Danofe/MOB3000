package com.example.mob3000oblig.Nav

sealed class Skjerm(val ruter: String) {
  object Login : Skjerm("Login")
  object Register : Skjerm("Register")
  object Start : Skjerm("Start")
  object Sok : Skjerm("Sok")
  object Info : Skjerm("Info")
  object Favoritter : Skjerm("Favoritter")
  object Sammenlign : Skjerm("Sammenlign")
  object Settings : Skjerm("Settings")
  object Kamera : Skjerm("Kamera")

  fun medArgumenter(vararg args: String): String {
    return buildString {
      append(ruter)
      args.forEach { arg -> append("/$arg") }
    }
  }
}