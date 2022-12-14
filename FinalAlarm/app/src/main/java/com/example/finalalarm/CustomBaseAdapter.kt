package com.example.finalalarm

import android.content.Context
import android.view.LayoutInflater
import android.view.View
import android.view.ViewGroup
import android.widget.BaseAdapter
import android.widget.ImageView
import android.widget.TextView
import androidx.core.content.getSystemService
import org.w3c.dom.Text
import java.util.*
import kotlin.collections.ArrayList

class CustomBaseAdapter(context: Context, alarmList: ArrayList<AlarmObject>) : BaseAdapter() {

    val context: Context = context
    val alarmList: ArrayList<AlarmObject> = alarmList

    // Gets the size of the alarm list
    override fun getCount(): Int {
        return alarmList.size
    }

    // Gets the element of the list view
    override fun getItem(listElement: Int): Any {
        return listElement
    }

    // Gets the ID of the list view
    override fun getItemId(listElement: Int): Long {
        return listElement.toLong()
    }

    // Changes the view of each specific element in the list view
    // Sets the date and time on each element
    override fun getView(listElement : Int, convertView: View?, parent : ViewGroup): View {
        //alarmList.sort()
        val inflater = context.getSystemService(Context.LAYOUT_INFLATER_SERVICE) as LayoutInflater
        val view = inflater.inflate(R.layout.activity_custom_list_view, parent, false)
        val alarm = alarmList[listElement]
        val nameView = view.findViewById(R.id.textView) as TextView
        val imageView = view.findViewById(R.id.imageIcon) as ImageView
        imageView.setImageResource(R.drawable.clockimage)
        nameView.text = returnAlarm(alarm)
        return view
    }
    // Returns information about the alarm in a string so it can be displayed on an element
    fun returnAlarm(currentAlarm : AlarmObject) : String {
        var alarmText = ""
        alarmText += currentAlarm.month.toString() + "/"
        alarmText += currentAlarm.day.toString() + "/"
        alarmText += currentAlarm.year.toString() + " "
        alarmText += returnAMPM(currentAlarm)
        return alarmText
    }

    // Switches the alarm from military time to AM/PM time for display purposes
    fun returnAMPM(currentAlarm: AlarmObject) : String {
        var hour = currentAlarm.hour
        var AM = true

        if (hour > 12) {
            hour -= 12
            var AM = false
        }

        var timeText = ""
        timeText += "$hour:"

        var minute = String.format("%02d", currentAlarm.minute)

        timeText += "$minute "
        timeText += if (AM) "AM"
        else "PM"

        return timeText
    }


}