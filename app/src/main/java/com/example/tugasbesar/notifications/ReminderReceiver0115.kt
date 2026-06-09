package com.example.tugasbesar.notifications

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class ReminderReceiver0115 : BroadcastReceiver() {
    override fun onReceive(context0115: Context, intent0115: Intent) {
        val title0115 = intent0115.getStringExtra("title0115") ?: "PlanMate Reminder"
        val message0115 = intent0115.getStringExtra("message0115") ?: "You have an upcoming task or event"
        val id0115 = intent0115.getIntExtra("id0115", 0)
        NotificationHelper0115.showNotification0115(context0115, id0115, title0115, message0115)
    }
}
