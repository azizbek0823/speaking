package com.example.tekstospich

import android.content.Intent
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.widget.Button
import android.widget.CalendarView
import android.widget.TextView
import android.widget.TimePicker

class MainActivity : AppCompatActivity() {
    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val calendarView: CalendarView = findViewById(R.id.calendarView)
        val textCalendar: TextView = findViewById(R.id.textCalendar)
        val timePicker: TimePicker = findViewById(R.id.timePicker)
        val textTime: TextView = findViewById(R.id.textTime)
        val button: Button = findViewById(R.id.btnIntent)

        calendarView.setOnDateChangeListener{calendarView, year, month, day ->
            textCalendar.text = "$day/$month/$year"
        }
        timePicker.setOnTimeChangedListener{ timePicker, minut, hour ->
            textTime.text = "$minut/$hour"
        }
        button.setOnClickListener{
            val intent = Intent(this, SecondActivity::class.java)
            startActivity(intent)
        }
    }
}