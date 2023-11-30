package com.example.mob3000oblig.Kamera

import android.content.Context
import android.view.ViewGroup.LayoutParams.MATCH_PARENT
import android.widget.LinearLayout
import androidx.camera.core.AspectRatio
import androidx.camera.view.CameraController
import androidx.camera.view.LifecycleCameraController
import androidx.camera.view.PreviewView
import androidx.compose.foundation.background
import androidx.compose.foundation.layout.Box
import androidx.compose.foundation.layout.PaddingValues
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.foundation.layout.fillMaxWidth
import androidx.compose.foundation.layout.offset
import androidx.compose.foundation.layout.padding
import androidx.compose.foundation.layout.size
import androidx.compose.material.icons.Icons
import androidx.compose.material.icons.filled.Search
import androidx.compose.material3.ExperimentalMaterial3Api
import androidx.compose.material3.FloatingActionButton
import androidx.compose.material3.Icon
import androidx.compose.material3.MaterialTheme
import androidx.compose.material3.Scaffold
import androidx.compose.material3.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.getValue
import androidx.compose.runtime.mutableStateOf
import androidx.compose.runtime.remember
import androidx.compose.runtime.setValue
import androidx.compose.ui.Alignment
import androidx.compose.ui.Modifier
import androidx.compose.ui.platform.LocalContext
import androidx.compose.ui.platform.LocalLifecycleOwner
import androidx.compose.ui.text.style.TextAlign
import androidx.compose.ui.unit.dp
import androidx.compose.ui.viewinterop.AndroidView
import androidx.core.content.ContextCompat
import androidx.lifecycle.LifecycleOwner
import androidx.navigation.NavController
import com.example.mob3000oblig.Nav.Skjerm
import com.example.mob3000oblig.R

@Composable
fun KameraSkjerm(navController: NavController) {
  KameraInnhold(navController)
}

@OptIn(ExperimentalMaterial3Api::class)
@Composable
private fun KameraInnhold(navController: NavController) {

  val context = LocalContext.current
  val lifecycleOwner: LifecycleOwner = LocalLifecycleOwner.current
  val cameraController: LifecycleCameraController = remember { LifecycleCameraController(context) }
  var detectedText: String by remember { mutableStateOf(context.getString(R.string.cant_find_license_number)) }

  fun onTextUpdated(updatedText: String) {
    detectedText = updatedText
  }

  Scaffold(
    modifier = Modifier.fillMaxSize(),
  ) { paddingValues: PaddingValues ->
    Box(
      modifier = Modifier.fillMaxSize()
    ) {
      AndroidView(
        modifier = Modifier
          .fillMaxSize()
          .padding(paddingValues),
        factory = { context ->
          PreviewView(context).apply {
            layoutParams = LinearLayout.LayoutParams(
              MATCH_PARENT,
              MATCH_PARENT
            )
            setBackgroundColor(android.graphics.Color.BLACK)
            implementationMode = PreviewView.ImplementationMode.COMPATIBLE
            scaleType = PreviewView.ScaleType.FILL_START
          }.also { previewView ->
            startTextRecognition(
              context = context,
              cameraController = cameraController,
              lifecycleOwner = lifecycleOwner,
              previewView = previewView,
              onDetectedTextUpdated = ::onTextUpdated
            )
          }
        }
      )
      Text(
        text = detectedText,
        textAlign = TextAlign.Center,
        color = MaterialTheme.colorScheme.onBackground,
        modifier = Modifier
          .fillMaxWidth()
          .background(MaterialTheme.colorScheme.background)
          .padding(16.dp),
      )
      FloatingActionButton(
        onClick = {
          navController.navigate(Skjerm.Info.medArgumenter(detectedText))
        },
        modifier = Modifier
          .size(100.dp)
          .padding(16.dp)
          .align(Alignment.BottomCenter)
          .offset(y = (-15).dp),
        containerColor = MaterialTheme.colorScheme.primary,
      ) {
        Icon(
          imageVector = Icons.Default.Search,
          contentDescription = null,
          tint = MaterialTheme.colorScheme.onSurface,
        )
      }
    }
  }
}

private fun startTextRecognition(
  context: Context,
  cameraController: LifecycleCameraController,
  lifecycleOwner: LifecycleOwner,
  previewView: PreviewView,
  onDetectedTextUpdated: (String) -> Unit
) {

  cameraController.imageAnalysisTargetSize = CameraController.OutputSize(AspectRatio.RATIO_16_9)
  cameraController.setImageAnalysisAnalyzer(
    ContextCompat.getMainExecutor(context),
    TekstGjenkjenner(onDetectedTextUpdated = onDetectedTextUpdated)
  )

  cameraController.bindToLifecycle(lifecycleOwner)
  previewView.controller = cameraController
}