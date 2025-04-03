package com.example.struku.presentation.debug

import android.graphics.Bitmap
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import com.example.struku.data.recognition.ImageRepository
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.asStateFlow
import kotlinx.coroutines.launch
import javax.inject.Inject

data class PreprocessingDebugState(
    val currentImage: Bitmap? = null,
    val currentStepIndex: Int = 0
)

@HiltViewModel
class PreprocessingDebugViewModel @Inject constructor(
    private val imageRepository: ImageRepository
) : ViewModel() {

    private val _state = MutableStateFlow(PreprocessingDebugState())
    
    // Fix: Use asStateFlow() instead of direct stateIn() for better lifecycle handling
    val state: StateFlow<PreprocessingDebugState> = _state.asStateFlow()

    init {
        loadNextImage()
    }

    fun onStepSelected(stepIndex: Int) {
        _state.value = _state.value.copy(currentStepIndex = stepIndex)
    }

    fun loadNextImage() {
        viewModelScope.launch {
            try {
                val image = imageRepository.getNextDebugImage()
                _state.value = _state.value.copy(
                    currentImage = image,
                    currentStepIndex = 0
                )
            } catch (e: Exception) {
                // Handle error
            }
        }
    }
}
