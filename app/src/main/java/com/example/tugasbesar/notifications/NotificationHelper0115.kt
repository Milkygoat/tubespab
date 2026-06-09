package com.example.tugasbesar.notifications

import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import androidx.core.app.NotificationCompat
import com.example.tugasbesar.R

object NotificationHelper0115 {
    private const val CHANNEL_ID0115 = "planmate_reminders0115"
    fun createChannel0115(context0115: Context) {
        val nm0115 = context0115.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        if (nm0115.getNotificationChannel(CHANNEL_ID0115) == null) {
            val channel0115 = NotificationChannel(CHANNEL_ID0115, "Reminders", NotificationManager.IMPORTANCE_HIGH)
            nm0115.createNotificationChannel(channel0115)
        }
    }
    fun showNotification0115(context0115: Context, id0115: Int, title0115: String, message0115: String) {
        val intent0115 = Intent()
        val pending0115 = PendingIntent.getActivity(context0115, 0, intent0115, PendingIntent.FLAG_IMMUTABLE)
        val notif0115 = NotificationCompat.Builder(context0115, CHANNEL_ID0115)
            .setContentTitle(title0115)
            .setContentText(message0115)
            .setSmallIcon(R.mipmap.ic_launcher)
            .setContentIntent(pending0115)
            .setAutoCancel(true)
            .build()
        val nm0115 = context0115.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        nm0115.notify(id0115, notif0115)
    }
}

