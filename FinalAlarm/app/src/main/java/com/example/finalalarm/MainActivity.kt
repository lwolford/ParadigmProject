package com.example.finalalarm

import android.app.AlarmManager
import android.app.AlertDialog
import android.app.NotificationChannel
import android.app.NotificationManager
import android.app.PendingIntent
import android.content.Context
import android.content.Intent
import android.content.SharedPreferences
import android.media.AudioAttributes
import android.os.Build
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.ToggleButton
import androidx.annotation.RequiresApi
import com.example.finalalarm.databinding.ActivityMainBinding
import com.google.gson.Gson
import com.google.gson.reflect.TypeToken
import java.lang.reflect.Type
import java.text.DateFormat
import java.text.SimpleDateFormat
import java.util.Calendar
import java.util.Date

class MainActivity : AppCompatActivity() {

    private lateinit var binding: ActivityMainBinding
    @RequiresApi(Build.VERSION_CODES.O)
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        binding = ActivityMainBinding.inflate(layoutInflater)
        setContentView(binding.root)

        createNotificationChannel()
        val nf = NotifMaker()

        // Creates an alarm using NotifMaker
        binding.submitButton.setOnClickListener {
            nf.scheduleNotification(applicationContext, binding)
            startActivity(Intent(this, AlarmListView::class.java))
        }

        // Returns to AlarmListView
        binding.goToList.setOnClickListener {
            startActivity(Intent(this, AlarmListView::class.java))
        }
    }

    // All notifications require a notification channel in order to be sent
    // Creates a notification channel that all notifications can be sent to
    // Importance is et to low in order for the notification to make no noise, but allows the
    // Ringtone to still make noise
    @RequiresApi(Build.VERSION_CODES.O)
    private fun createNotificationChannel() {
        val name = "Notif Channel"
        val desc = "A Description of the Channel (Not really too important)"
        val importance = NotificationManager.IMPORTANCE_LOW
        val channel = NotificationChannel(channelID, name, importance)
        channel.description = desc
        val notificationManager = getSystemService(NOTIFICATION_SERVICE) as NotificationManager
        notificationManager.createNotificationChannel(channel)
    }

}