package nl.joramkwetters.duogerman

import android.app.NotificationManager
import android.content.Context
import android.graphics.Bitmap
import android.graphics.BitmapFactory
import androidx.annotation.DrawableRes
import androidx.core.app.NotificationCompat
import kotlin.random.Random

class GermanNotificationService(
    private val context: Context
) {
    val notificationManager = context.getSystemService((NotificationManager::class.java))
    fun showBasicNotification(){
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

    fun showExpandableNotification(){
        val image = context.bitmapFromResource(R.drawable.ic_launcher_foreground)
        val notification = NotificationCompat.Builder(context, "duo_german_channel")
            .setContentTitle("Duo German")
            .setContentText("Time to practice German!")
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .setLargeIcon(image)
            .setStyle(
                NotificationCompat.BigPictureStyle()
                    .bigPicture(image)
                    .bigLargeIcon(null as Bitmap)
            )
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