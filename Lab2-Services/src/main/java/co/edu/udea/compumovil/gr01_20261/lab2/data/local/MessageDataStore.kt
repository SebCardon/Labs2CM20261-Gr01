package co.edu.udea.compumovil.gr01_20261.lab2.data.local

import android.content.Context
import androidx.datastore.core.DataStore
import androidx.datastore.preferences.core.Preferences
import androidx.datastore.preferences.core.edit
import androidx.datastore.preferences.core.stringPreferencesKey
import androidx.datastore.preferences.preferencesDataStore
import co.edu.udea.compumovil.gr01_20261.lab2.data.model.ChatMessage
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import kotlinx.coroutines.flow.Flow
import kotlinx.coroutines.flow.map

val Context.messageDataStore: DataStore<Preferences> by preferencesDataStore(name = "messages")

private val MESSAGES_KEY = stringPreferencesKey("messages_json")
private val gson = Gson()

suspend fun saveMessages(context: Context, messages: List<ChatMessage>) {
    context.messageDataStore.edit { prefs ->
        prefs[MESSAGES_KEY] = gson.toJson(messages)
    }
}

fun readMessages(context: Context): Flow<List<ChatMessage>> {
    return context.messageDataStore.data.map { prefs ->
        val json = prefs[MESSAGES_KEY] ?: return@map emptyList()
        val type = object : TypeToken<List<ChatMessage>>() {}.type
        gson.fromJson<List<ChatMessage>>(json, type) ?: emptyList()
    }
}
