package com.example.finalalarm

import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent

class SnoozeReceiver : BroadcastReceiver() {
    // Used to receive when a user wants to snooze
    // Call scheduleSnooze in the NotifMaker to schedule the snooze
    override fun onReceive(context: Context, intent: Intent) {
        if (intent.action == "com.example.MY_NOTIFICATION_DELETED") {
            MyRingtoneManager.ringtone?.stop()
            val nm = NotifMaker()
            nm.scheduleSnooze(context)
        }
    }
}