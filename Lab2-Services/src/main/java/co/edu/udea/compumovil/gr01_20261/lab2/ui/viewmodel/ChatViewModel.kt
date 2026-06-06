package co.edu.udea.compumovil.gr01_20261.lab2.ui.viewmodel

import android.app.Application
import androidx.lifecycle.AndroidViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import co.edu.udea.compumovil.gr01_20261.lab2.data.repository.ChatRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel(app: Application) : AndroidViewModel(app) {

    private val repository = ChatRepository.getInstance(app)

    val messages: StateFlow<List<ChatMessage>> = repository.messages

    init {
        viewModelScope.launch {
            repository.loadCachedMessages()
        }
    }

    fun loadMessages() {
        viewModelScope.launch {
            repository.fetchAndStoreMessages()
        }
    }
}
