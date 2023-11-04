package com.example.mob3000oblig

sealed class Screen(val ruter: String) {
  object Login : Screen("Login")
  object Register : Screen("Register")
  object Start : Screen("Start")
  object Sok : Screen("Sok")
  object Info : Screen("Info")
  object Favoritter : Screen("Favoritter")

  fun withArgs(vararg args: String): String {
    return buildString {
      append(ruter)
      args.forEach { arg -> append("/$arg") }
    }
  }
}