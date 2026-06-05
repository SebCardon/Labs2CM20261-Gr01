package co.edu.udea.compumovil.gr01_20261.lab2.data.model

data class ChatMessage(
    val id: String = "",
    val user: String = "",
    val message: String = "",
    val timestamp: String = ""
) {
    // Se calcula después de que user ya está inicializado
    val avatarInitial: String
        get() = if (user.isNotEmpty()) user.trim().first().uppercaseChar().toString() else "?"
}