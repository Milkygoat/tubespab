package com.example.tugasbesar.notifications

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent

object AlarmHelper0115 {
    fun scheduleReminder0115(context0115: Context, id0115: Int, timeMillis0115: Long, title0115: String, message0115: String) {
        val am0115 = context0115.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent0115 = Intent(context0115, ReminderReceiver0115::class.java)
        intent0115.putExtra("title0115", title0115)
        intent0115.putExtra("message0115", message0115)
        intent0115.putExtra("id0115", id0115)
        val pi0115 = PendingIntent.getBroadcast(context0115, id0115, intent0115, PendingIntent.FLAG_IMMUTABLE)
        am0115.setExactAndAllowWhileIdle(AlarmManager.RTC_WAKEUP, timeMillis0115, pi0115)
    }
    fun cancelReminder0115(context0115: Context, id0115: Int) {
        val am0115 = context0115.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        val intent0115 = Intent(context0115, ReminderReceiver0115::class.java)
        val pi0115 = PendingIntent.getBroadcast(context0115, id0115, intent0115, PendingIntent.FLAG_IMMUTABLE)
        am0115.cancel(pi0115)
    }
}

