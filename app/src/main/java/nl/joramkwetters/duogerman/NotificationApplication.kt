package nl.joramkwetters.duogerman

import android.app.Application
import android.app.NotificationChannel
import android.app.NotificationManager

class NotificationApplication: Application() {

    override fun onCreate() {
        super.onCreate()

        val notificationChannel = NotificationChannel(
            "duo_german_channel",
            "Duo German Channel",
            NotificationManager.IMPORTANCE_HIGH
        )
            notificationChannel.description = "This is the notification channel for the Duo German app"
            val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
                notificationManager.createNotificationChannel(notificationChannel)
            }
}