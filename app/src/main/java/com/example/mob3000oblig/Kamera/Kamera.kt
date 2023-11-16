package com.example.mob3000oblig.Kamera

import androidx.compose.runtime.Composable
import androidx.compose.ui.Modifier
import androidx.navigation.NavController
import com.google.accompanist.permissions.ExperimentalPermissionsApi
import com.google.accompanist.permissions.isGranted
import com.google.accompanist.permissions.rememberPermissionState
import com.google.api.Context

class Kamera {
  @OptIn(ExperimentalPermissionsApi::class)
  @Composable
  fun HovedSkjerm(navController: NavController) {
    val cameraPermissionState = rememberPermissionState(android.Manifest.permission.CAMERA)
    InnholdSkjerm(
      navController = navController,
      hasPermission = cameraPermissionState.status.isGranted,
      onRequestPermission = cameraPermissionState::launchPermissionRequest,
    )
  }

  @Composable
  fun InnholdSkjerm(navController: NavController, hasPermission: Boolean, onRequestPermission: () -> Unit) {
    if(hasPermission) {
      KameraSkjerm(navController)
    } else {
      IngenTillatelseSkjerm(onRequestPermission)
    }
  }
}