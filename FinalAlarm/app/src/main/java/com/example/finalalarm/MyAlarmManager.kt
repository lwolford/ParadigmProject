package com.example.finalalarm

import android.app.AlarmManager

object MyAlarmManager {

    // Snoozes require a global alarm manager, so this is created for the snooze alarms
    var alarmManager: AlarmManager? = null

}