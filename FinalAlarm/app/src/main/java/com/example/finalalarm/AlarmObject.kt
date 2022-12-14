package com.example.finalalarm

import android.app.PendingIntent
import java.text.SimpleDateFormat
import java.util.*

class AlarmObject (year : Int, month: Int, day : Int, hour : Int, minute : Int,
                  pendingIntent: PendingIntent) : Comparable<AlarmObject> {

    // Values for the year, month, day, hour, minute, and what the alarm is supposed to do when
    // Received by the Notification class
    val year : Int = year
    val month: Int = month
    val day : Int = day
    val hour : Int = hour
    val minute : Int = minute
    val pendingIntent: PendingIntent = pendingIntent

    // Makes an ID for the Alarm based on its data
    fun getID() : String {
       return getTimeFormat(getTime())
    }

    // Gets the time of the alarm in milliseconds
    fun getTime(): Long {
        val calendar = Calendar.getInstance()
        calendar.set(Calendar.SECOND, 0)
        calendar.set(Calendar.MILLISECOND, 0)
        calendar.set(year, month, day, hour, minute)
        return calendar.timeInMillis
    }

    // Sets a date format for the alarm using the time in milliseconds
     private fun getTimeFormat(time: Long) : String {
        val date = Date(time)
        val dateFormat = SimpleDateFormat("yyMMddHHmm")
        return dateFormat.format(date)
    }

    // Used for comparing IDs to order the alarm list
    override fun compareTo(other: AlarmObject): Int {
        return this.getID().compareTo(other.getID())
    }

}