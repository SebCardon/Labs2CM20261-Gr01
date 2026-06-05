package co.edu.udea.compumovil.gr01_20261.lab2.data.api

import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import retrofit2.http.GET

interface ChatApiService {
    @GET("Messages")    // ← M mayúscula exactamente como en la URL
    suspend fun getMessages(): List<ChatMessage>
}