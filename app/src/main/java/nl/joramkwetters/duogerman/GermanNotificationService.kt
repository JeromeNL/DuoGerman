package nl.joramkwetters.duogerman

import android.app.NotificationManager
import android.content.Context
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class GermanNotificationService(
    private val context: Context
) {
    private val notificationManager: NotificationManager = context.getSystemService((NotificationManager::class.java))
    fun showPracticeNotification(){

        val notification = NotificationCompat.Builder(context, "duo_german_channel")
            .setContentTitle("Duo German")
            .setContentText("Het is tijd om Duits te leren!!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    fun showNewsNotification(){
        val notification = NotificationCompat.Builder(context, "duo_german_channel")
            .setContentTitle("Duo German")
            .setContentText("Lees het Duitse nieuws eens!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }
}