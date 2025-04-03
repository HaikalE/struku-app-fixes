package com.example.struku.presentation.debug

import androidx.compose.foundation.layout.Column
import androidx.compose.foundation.layout.fillMaxSize
import androidx.compose.material.Button
import androidx.compose.material.Text
import androidx.compose.runtime.Composable
import androidx.compose.runtime.collectAsState
import androidx.compose.runtime.getValue
import androidx.compose.ui.Modifier
import androidx.hilt.navigation.compose.hiltViewModel
import com.example.struku.presentation.components.PreprocessingVisualizer

@Composable
fun PreprocessingDebugScreen(
    viewModel: PreprocessingDebugViewModel = hiltViewModel()
) {
    val state by viewModel.state.collectAsState()
    
    Column(modifier = Modifier.fillMaxSize()) {
        PreprocessingVisualizer(
            image = state.currentImage,
            // Fix: Use ProcessingStep enum instead of Int
            currentStep = PreprocessingVisualizer.ProcessingStep.values()[state.currentStepIndex],
            // Fix: Use ProcessingStep enum instead of Int
            onStepSelected = { step ->
                viewModel.onStepSelected(step.ordinal)
            }
        )
        
        Button(onClick = { viewModel.loadNextImage() }) {
            Text("Load Next Image")
        }
    }
}
