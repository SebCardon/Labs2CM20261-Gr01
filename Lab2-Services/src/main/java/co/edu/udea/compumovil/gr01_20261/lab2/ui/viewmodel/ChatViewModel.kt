package co.edu.udea.compumovil.gr01_20261.lab2.ui.viewmodel

import androidx.lifecycle.ViewModel
import androidx.lifecycle.viewModelScope
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import co.edu.udea.compumovil.gr01_20261.lab2.data.repository.ChatRepository
import kotlinx.coroutines.flow.StateFlow
import kotlinx.coroutines.launch

class ChatViewModel : ViewModel() {

    private val repository = ChatRepository.getInstance()

    val messages: StateFlow<List<ChatMessage>> = repository.messages

    fun loadMessages() {
        viewModelScope.launch {
            repository.fetchAndStoreMessages()
        }
    }
}