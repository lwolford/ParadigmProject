package com.example.finalalarm

import android.app.AlarmManager
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.ListView
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type

class AlarmListView : AppCompatActivity() {

    lateinit var listView: ListView
    lateinit var alarmButton : Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_alarm_list_view)

        listView = findViewById(R.id.customListView)
        alarmButton = findViewById(R.id.alarmButton)

        val cba = CustomBaseAdapter(applicationContext, AlarmList.alarmList)
        listView.adapter = cba

        // Sets all alarms when this activity is loaded
        for (alarmObject in AlarmList.alarmList) {
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            val time = alarmObject.getTime()
            val pendingIntent = alarmObject.pendingIntent;
            alarmManager.setExactAndAllowWhileIdle(
                AlarmManager.RTC_WAKEUP,
                time,
                pendingIntent
            )
        }

        // Updates the list view to display all current alarms
        cba.notifyDataSetChanged()

        // Moves to the main activity so the user can set the alarm
        alarmButton.setOnClickListener {
            startActivity(Intent(this, MainActivity::class.java))
        }

        // If an element of the list view is tapped, the alarm at that position is canceled
        listView.setOnItemClickListener { parent, view, position, id ->
            val element = cba.getItem(position)
            val alarmManager = getSystemService(Context.ALARM_SERVICE) as AlarmManager
            alarmManager.cancel(AlarmList.alarmList[element as Int].pendingIntent)
            removeItem(element)
            cba.notifyDataSetChanged()
        }

    }

    // Removes the item from the AlarmList
    fun removeItem(element : Int) {
        AlarmList.alarmList.removeAt(element)
    }

}