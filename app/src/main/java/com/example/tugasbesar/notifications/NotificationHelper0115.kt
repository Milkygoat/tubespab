package com.example.tugasbesar.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.Build
import androidx.core.app.NotificationCompat
import com.example.tugasbesar.R
import com.example.tugasbesar.ui.main.MainActivity0115

object NotificationHelper0115 {
    private const val CHANNEL_ID0115 = "planmate_reminders0115"
    private const val CHANNEL_NAME0115 = "PlanMate Reminders"

    fun createChannel0115(context0115: Context) {
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            val nm0115 = context0115.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
            if (nm0115.getNotificationChannel(CHANNEL_ID0115) == null) {
                val channel0115 = NotificationChannel(CHANNEL_ID0115, CHANNEL_NAME0115, NotificationManager.IMPORTANCE_HIGH)
                channel0115.description = "Reminders for tasks and events"
                nm0115.createNotificationChannel(channel0115)
            }
        }
    }

    fun showNotification0115(context0115: Context, id0115: Int, title0115: String, message0115: String) {
        createChannel0115(context0115)
        val intent0115 = Intent(context0115, MainActivity0115::class.java)
        intent0115.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val pending0115 = PendingIntent.getActivity(context0115, id0115, intent0115, PendingIntent.FLAG_UPDATE_CURRENT or PendingIntent.FLAG_IMMUTABLE)
        val notif0115 = NotificationCompat.Builder(context0115, CHANNEL_ID0115)
            .setContentTitle(title0115)
            .setContentText(message0115)
            .setSmallIcon(R.drawable.ic_notification_0115)
            .setContentIntent(pending0115)
            .setAutoCancel(true)
            .setPriority(NotificationCompat.PRIORITY_HIGH)
            .build()
        val nm0115 = context0115.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm0115.notify(id0115, notif0115)
    }
}
