package com.example.finalalarm

import android.app.Notification.DEFAULT_ALL
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.BroadcastReceiver
import android.content.Context
import android.content.Intent
import android.media.AudioAttributes
import android.media.AudioManager
import android.media.MediaPlayer
import android.media.Ringtone
import android.media.RingtoneManager
import android.net.Uri
import android.os.Build
import androidx.annotation.RequiresApi
import androidx.core.app.NotificationCompat
import androidx.core.app.NotificationCompat.DEFAULT_ALL
import java.text.SimpleDateFormat
import java.util.*
import java.util.concurrent.CountDownLatch

var notificationID = 1
const val channelID = "channel1"
const val titleExtra = "titleExtra"
const val messageExtra = "messageExtra"

class Notification : BroadcastReceiver()
{

    // Where any alarm is received
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onReceive(context: Context, intent: Intent) {

        // mainIntent = when a user taps the notification
        // Calls the MathActivity
        val mainIntent = Intent(context, MathActivity::class.java)
        mainIntent.flags = Intent.FLAG_ACTIVITY_NEW_TASK or Intent.FLAG_ACTIVITY_CLEAR_TASK
        val mainPendingIntent = PendingIntent.getActivity(context, 1, mainIntent, PendingIntent.FLAG_IMMUTABLE)

        // deleteIntent = when a user swipes a notification away
        val deleteIntent = Intent(context, SnoozeReceiver::class.java).apply {
            action = "com.example.MY_NOTIFICATION_DELETED"
        }

        // Used for ringtone
        // All defaults must be set for the ringtone to work properly
        val defaults = android.app.Notification.DEFAULT_ALL
            .or(NotificationCompat.DEFAULT_SOUND)
            .or(NotificationCompat.DEFAULT_VIBRATE)
            .or(NotificationCompat.DEFAULT_LIGHTS)

        // Notification is created with these requirements here
        val notification = NotificationCompat.Builder(context, channelID)
            .setSmallIcon(R.drawable.ic_launcher_foreground)
            .setContentTitle(intent.getStringExtra(titleExtra))
            .setContentTitle(intent.getStringExtra(messageExtra))
            .setAutoCancel(true)
            .setDefaults(defaults)
            .setSound(null)
            .setContentIntent(mainPendingIntent)
            .setDeleteIntent(PendingIntent.getBroadcast(context, 0, deleteIntent, 0))
            .build()

        // Creates a new ringtone manager, and then sets it to the global ringtone manager
        val ringtoneManager = RingtoneManager.getDefaultUri(RingtoneManager.TYPE_RINGTONE)
        MyRingtoneManager.ringtone = RingtoneManager.getRingtone(context, ringtoneManager)

        // Notification manager (where the notification is sent)
        val manager = context.getSystemService(Context.NOTIFICATION_SERVICE) as NotificationManager
        manager.notify(notificationID.toInt(), notification)
        MyRingtoneManager.ringtone?.play()

        // Removes the first element in the AlarmList (the alarm that goes off)
        AlarmList.alarmList.removeAt(0)
    }

}