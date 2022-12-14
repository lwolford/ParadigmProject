package com.example.finalalarm

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.EditText
import android.widget.TextView

class MathActivity : AppCompatActivity() {
    private lateinit var mathing_txt : TextView
    private lateinit var textInput: EditText
    private lateinit var button: Button

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_math)

        mathing_txt = findViewById(R.id.mathing_txt)
        textInput = findViewById(R.id.textInput)
        button = findViewById(R.id.button)

        val mp = MathProblem()

        // Sets the TextView object to the given equation
        mathing_txt.text = mp.displayEquation()

        // Checks to see if the user put in the right answer
        // If wrong, nothing is done
        // If right, the alarm is turned off and the user is brought back to the AlarmList
        button.setOnClickListener {
            if (Integer.parseInt(textInput.text.toString()) == mp.mathCheckerReturn()) {
                MyRingtoneManager.ringtone?.stop()
                startActivity(Intent(this, AlarmListView::class.java))
            }
        }

    }
}