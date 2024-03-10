package nl.joramkwetters.duogerman

import android.app.NotificationManager
import android.content.Context
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class GermanNotificationService(
    private val context: Context
) {
    val notificationManager = context.getSystemService((NotificationManager::class.java))
    fun showPracticeNotification(){
        val notification = NotificationCompat.Builder(context, "duo_german_channel")
            .setContentTitle("Duo German")
            .setContentText("Time to practice German!")
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
            .setContentText("Let's read some German news!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()

        notificationManager.notify(
            Random.nextInt(),
            notification
        )
    }

    private fun Context.bitmapFromResource(
        @DrawableRes resId: Int
    ) = BitmapFactory.decodeResource(
        resources,
        resId
    )
}