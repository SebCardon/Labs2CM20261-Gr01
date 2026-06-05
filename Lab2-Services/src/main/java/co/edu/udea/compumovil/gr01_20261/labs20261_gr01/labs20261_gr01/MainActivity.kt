package co.edu.udea.compumovil.gr01_20261.labs20261_gr01.labs20261_gr01

import android.os.Bundle
import androidx.activity.ComponentActivity
import androidx.activity.compose.setContent
import androidx.work.*
import co.edu.udea.compumovil.gr01_20261.lab2.data.worker.MessageSyncWorker
import co.edu.udea.compumovil.gr01_20261.lab2.ui.navigation.AppNavigation
import co.edu.udea.compumovil.gr01_20261.labs20261_gr01.labs20261_gr01.ui.theme.Labs20261Gr01Theme
import java.util.concurrent.TimeUnit

class MainActivity : ComponentActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        scheduleMessageSync()
        setContent {
            Labs20261Gr01Theme {
                AppNavigation()
            }
        }
    }

    private fun scheduleMessageSync() {
        val constraints = Constraints.Builder()
            .setRequiredNetworkType(NetworkType.CONNECTED)
            .build()

        val syncRequest = PeriodicWorkRequestBuilder<MessageSyncWorker>(
            15, TimeUnit.MINUTES
        )
            .setConstraints(constraints)
            .build()

        WorkManager.getInstance(applicationContext)
            .enqueueUniquePeriodicWork(
                "message_sync",
                ExistingPeriodicWorkPolicy.REPLACE,
                syncRequest
            )
    }
}