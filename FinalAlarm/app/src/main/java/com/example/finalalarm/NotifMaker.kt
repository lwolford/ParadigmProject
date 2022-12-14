package com.example.finalalarm

import android.app.AlarmManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.os.SystemClock
import androidx.core.content.getSystemService
import com.example.finalalarm.databinding.ActivityMainBinding
import java.text.SimpleDateFormat
import java.util.*
import kotlin.collections.ArrayList

class NotifMaker {

    // Schedules a notification
    public fun scheduleNotification(context: Context, binding: ActivityMainBinding) {
        val intent = Intent(context, Notification::class.java)
        var title = binding.titleET.text.toString()

        // Gets year, month, day, hour, and minute
        val year : Int = getYear(binding)
        val month : Int = getMonth(binding)
        val day : Int = getDay(binding)
        val hour : Int = getHour(binding)
        val minute : Int = getMinute(binding)

        // Sets the notification's message
        val message = "Tap to turn off | Dismiss to snooze"

        // Applies the title and message to the notification object
        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        // Sets what the alarm is supposed to do when set
        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID,
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        // Changes the notification ID
        notificationID++

        // Creates an AlarmObject using the data received
        val alarm = AlarmObject(year, month, day, hour, minute, pendingIntent)

        // Adds alarm to alarm list, and then sorts the alarm list by alarm id
        AlarmList.alarmList.add(alarm)
        AlarmList.alarmList.sortBy {
            it.getID()
        }

    }

    // Schedules a snooze for 5 minutes from the current time
    fun scheduleSnooze(context: Context) {
        val intent = Intent(context, Notification::class.java)
        val title = "5-Min Snooze"
        val message = "5 Minutes have passed. Wake Up!"

        val snoozeTime = SystemClock.elapsedRealtime() + 5 * 60 * 1000

        intent.putExtra(titleExtra, title)
        intent.putExtra(messageExtra, message)

        val pendingIntent = PendingIntent.getBroadcast(
            context,
            notificationID.toInt(),
            intent,
            PendingIntent.FLAG_IMMUTABLE or PendingIntent.FLAG_UPDATE_CURRENT
        )

        MyAlarmManager.alarmManager = context.getSystemService(Context.ALARM_SERVICE) as AlarmManager
        MyAlarmManager.alarmManager?.setExactAndAllowWhileIdle(
            AlarmManager.ELAPSED_REALTIME_WAKEUP,
            snoozeTime,
            pendingIntent
        )

    }

    // Gets the minute of the TimePicker
    private fun getMinute(binding: ActivityMainBinding): Int {
        return binding.timePicker.minute
    }

    // Gets the hour of the TimePicker
    private fun getHour(binding: ActivityMainBinding) : Int {
        return binding.timePicker.hour
    }

    // Gets the day of the DatePicker
    private fun getDay(binding: ActivityMainBinding) : Int {
        return binding.datePicker.dayOfMonth
    }

    // Gets the month of the DatePicker
    private fun getMonth(binding: ActivityMainBinding) : Int {
        return binding.datePicker.month
    }

    // Gets the year of the DatePicker
    private fun getYear(binding: ActivityMainBinding) : Int {
        return binding.datePicker.year
    }

}