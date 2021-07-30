package com.example.ageinminutes

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

//id 'kotlin-android-extensions' --make sure to put this in the build gradle file and sync it to avoid issues!!!

class MainActivity : AppCompatActivity() {

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val btnDatePicker = findViewById<Button>(R.id.btnDatePicker)

        btnDatePicker.setOnClickListener { view ->
            clickDatePicker(view)
        }
    }

        fun clickDatePicker(view: View) {

            val myCalendar = Calendar.getInstance()
            val year = myCalendar.get(Calendar.YEAR)
            val month = myCalendar.get(Calendar.MONTH)
            val day = myCalendar.get(Calendar.DAY_OF_MONTH)


            val dpd = DatePickerDialog(this,
                DatePickerDialog.OnDateSetListener { view, selectedYear, selectedMonth, selectedDayOfMonth ->

                    val selectedDate = "$selectedDayOfMonth/${selectedMonth+1}/$selectedYear"

                    tvSelectedDate.setText(selectedDate)

                    //tv means text view

                    val sdf = SimpleDateFormat("dd/MM/yyyy", Locale.ENGLISH)

                    val theDate = sdf.parse(selectedDate)

                    //parse converts from a string to the format of a date object

                    val selectedDateInMinutes = theDate!!.time / 60000

                    val currentDate = sdf.parse(sdf.format(System.currentTimeMillis()))

                    val currentDateInMinutes = currentDate!!.time / 60000

                    val differenceInMinutes = currentDateInMinutes - selectedDateInMinutes

                    tvSelectedDateInMinutes.text = differenceInMinutes.toString()

                }, year, month, day

            )
            dpd.datePicker.setMaxDate(Date().time - 86400000)
            dpd.show()
        }

    }









