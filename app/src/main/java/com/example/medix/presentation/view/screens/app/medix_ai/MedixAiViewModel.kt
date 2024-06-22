package com.example.medix.presentation.view.screens.app.medix_ai

import android.util.Log
import androidx.compose.runtime.mutableStateOf
import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import androidx.navigation.NavController
import com.example.medix.domain.useCases.ai.AiModelUseCase
import com.example.medix.presentation.navigation.Screens
import dagger.hilt.android.lifecycle.HiltViewModel
import kotlinx.coroutines.Dispatchers
import kotlinx.coroutines.launch
import kotlinx.coroutines.withContext
import org.json.JSONException
import org.json.JSONObject
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MedixAiViewModel @Inject constructor(
    private val aiModelUseCase: AiModelUseCase
) : ViewModel() {
    val imageUrl = mutableStateOf("")
    val result = mutableStateOf("")

    fun predictImage(navController: NavController) {
        Log.d("MedixAiViewModel", "predictImage() called")
        viewModelScope.launch(Dispatchers.IO) {
            try {
                Log.d("MedixAiViewModel", "imageUrl.value: ${imageUrl.value}")
                val response = aiModelUseCase.predict(imageUrl.value)
                Log.d("MedixAiViewModel", "Raw response: $response")

                val cleanedResponse = if (response.startsWith("\"") && response.endsWith("\"")) {
                    response.substring(1, response.length - 1)
                } else {
                    response
                }
                Log.d("MedixAiViewModel", "Cleaned response: $cleanedResponse")

                val unescapedResponse = cleanedResponse.replace("\\\"", "\"").replace("\\\\", "\\")
                Log.d("MedixAiViewModel", "Unescaped response: $unescapedResponse")

                val trimmedResponse = unescapedResponse.trim()
                Log.d("MedixAiViewModel", "Trimmed response: $trimmedResponse")

                if (trimmedResponse.startsWith("{") && trimmedResponse.endsWith("}")) {
                    val jsonResponse = JSONObject(trimmedResponse)
                    Log.d("MedixAiViewModel", "Parsed JSON response: $jsonResponse")

                    val namesArray = jsonResponse.getJSONArray("names")
                    val resultName = namesArray.getString(0)

                    withContext(Dispatchers.Main) {
                        result.value = resultName
                        navController.navigate(Screens.MedixModel.route)
                        Log.d("MedixAiViewModel", "imageUrl: ${imageUrl.value}, result: ${result.value}")
                    }
                } else {
                    throw JSONException("Invalid JSON response: $trimmedResponse")
                }
            } catch (e: JSONException) {
                Log.e("MedixAiViewModel", "JSON Exception: ${e.message}", e)
            } catch (e: IOException) {
                Log.e("MedixAiViewModel", "IO Exception: ${e.message}", e)
            } catch (e: Exception) {
                Log.e("MedixAiViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}