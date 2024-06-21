package com.example.medix.domain.useCases.ai

import okhttp3.MediaType.Companion.toMediaType
import okhttp3.OkHttpClient
import okhttp3.Request
import okhttp3.RequestBody.Companion.toRequestBody
import org.json.JSONObject
import java.io.IOException

class AiModelUseCase(private val client: OkHttpClient) {

    @Throws(IOException::class)
    fun predict(imageUrl: String): String {
        val jsonObject = JSONObject().apply {
            put("image_url", imageUrl)
        }

        val requestBody = jsonObject.toString().toRequestBody("application/json; charset=utf-8".toMediaType())

        val request = Request.Builder()
            .url("https://braintumor-workspace-guyap.eastus.inference.ml.azure.com/score")
            .addHeader("Content-Type", "application/json")
            .addHeader("Authorization", "Bearer 6MUJqtpiLKfbYv3Hgn8OBcNr1ciaXjzd")
            .addHeader("azureml-model-deployment", "braintumor-1")
            .post(requestBody)
            .build()

        client.newCall(request).execute().use { response ->
            if (!response.isSuccessful) throw IOException("Unexpected code $response")
            return response.body?.string() ?: ""
        }
    }
}