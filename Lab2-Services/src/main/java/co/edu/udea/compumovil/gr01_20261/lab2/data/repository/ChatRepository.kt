package co.edu.udea.compumovil.gr01_20261.lab2.data.repository

import android.content.Context
import android.util.Log
import co.edu.udea.compumovil.gr01_20261.lab2.data.api.RetrofitInstance
import co.edu.udea.compumovil.gr01_20261.lab2.data.local.readMessages
import co.edu.udea.compumovil.gr01_20261.lab2.data.local.saveMessages
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import kotlinx.coroutines.flow.MutableStateFlow
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.flow.first

class ChatRepository private constructor(private val context: Context) {

    private val _messages = MutableStateFlow<List<ChatMessage>>(emptyList())
    val messages: StateFlow<List<ChatMessage>> = _messages

    suspend fun loadCachedMessages() {
        val cached = readMessages(context).first()
        if (cached.isNotEmpty()) _messages.value = cached
    }

    suspend fun fetchAndStoreMessages() {
        try {
            val result = RetrofitInstance.api.getMessages()
            _messages.value = result
            saveMessages(context, result)
        } catch (e: Exception) {
            Log.e("ChatRepository", "Error fetching: ${e.message}")
        }
    }

    companion object {
        @Volatile
        private var INSTANCE: ChatRepository? = null

        fun getInstance(context: Context): ChatRepository {
            return INSTANCE ?: synchronized(this) {
                ChatRepository(context.applicationContext).also { INSTANCE = it }
            }
        }
    }
}
