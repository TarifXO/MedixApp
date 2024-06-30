package com.example.medix.presentation.view.screens.app

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
import okhttp3.MultipartBody
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.asRequestBody
import org.json.JSONException
import org.json.JSONObject
import java.io.File
import java.io.IOException
import javax.inject.Inject

@HiltViewModel
class MedixAiViewModel @Inject constructor(
    private val aiModelUseCase: AiModelUseCase
) : ViewModel() {
    val imageUrl = mutableStateOf("")
    val result = mutableStateOf("")

    fun resetData() {
        imageUrl.value = ""
        result.value = ""
    }

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


    fun uploadImageAndPredict(file: File, navController: NavController) {
        viewModelScope.launch(Dispatchers.IO) {
            try {
                val client = OkHttpClient()
                val requestBody = MultipartBody.Builder()
                    .setType(MultipartBody.FORM)
                    .addFormDataPart("image", file.name, file.asRequestBody())
                    .build()

                val request = Request.Builder()
                    .url("https://api.imgbb.com/1/upload?expiration=600&key=5dde1938587cd962bb46deb732884ae3")
                    .post(requestBody)
                    .build()

                val response = client.newCall(request).execute()
                val responseBody = response.body?.string()

                val imgUrl = responseBody?.let { JSONObject(it).getJSONObject("data").getString("display_url") }

                if (imgUrl != null) {
                    imageUrl.value = imgUrl
                }

                predictImage(navController)
            } catch (e: Exception) {
                Log.e("MedixAiViewModel", "Exception: ${e.message}", e)
            }
        }
    }
}