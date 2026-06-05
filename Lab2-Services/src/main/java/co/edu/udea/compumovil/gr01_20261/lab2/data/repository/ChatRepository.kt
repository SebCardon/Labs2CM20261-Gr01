package co.edu.udea.compumovil.gr01_20261.lab2.data.repository

import android.util.Log
import co.edu.udea.compumovil.gr01_20261.lab2.data.api.RetrofitInstance
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow

class ChatRepository {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    suspend fun fetchAndStoreMessages() {
        try {
            val result = RetrofitInstance.api.getMessages()
            _messages.value = result
        } catch (e: Exception) {
            Log.e("ChatRepository", "Error fetching: ${e.message}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ChatRepository? = null

        fun getInstance(): ChatRepository {
            return INSTANCE ?: synchronized(this) {
                ChatRepository().also { INSTANCE = it }
            }
        }
    }
}