package co.edu.udea.compumovil.gr01_20261.lab2.data.worker

import android.content.Context
import android.util.Log
import androidx.work.CoroutineWorker
import androidx.work.WorkerParameters
import co.edu.udea.compumovil.gr01_20261.lab2.data.repository.ChatRepository
import kotlinx.coroutines.TimeoutCancellationException
import kotlinx.coroutines.withTimeout

class MessageSyncWorker(
    context: Context,
    params: WorkerParameters
) : CoroutineWorker(context, params) {

    override suspend fun doWork(): Result {
        Log.d("MessageSyncWorker", "Iniciando sincronización...")

        return try {
            withTimeout(10_000L) {  // ← timeout de 10 segundos
                val repository = ChatRepository.getInstance()
                repository.fetchAndStoreMessages()
            }
            Log.d("MessageSyncWorker", "Sincronización exitosa")
            Result.success()
        } catch (e: TimeoutCancellationException) {
            Log.e("MessageSyncWorker", "Timeout en sincronización")
            Result.retry()
        } catch (e: Exception) {
            Log.e("MessageSyncWorker", "Error: ${e.message}")
            Result.retry()
        }
    }
}