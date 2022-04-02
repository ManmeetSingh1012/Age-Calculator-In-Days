package com.example.ageindays

import android.app.DatePickerDialog
import androidx.appcompat.app.AppCompatActivity
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import android.widget.Toast
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {
    private var selectedDate: TextView?=null
    private var InMinutes:TextView?=null

    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        val datepicker:Button=findViewById(R.id.btnDatePicker)
        selectedDate=findViewById(R.id.tvtext)
        InMinutes=findViewById(R.id.texts)
       datepicker.setOnClickListener { clickdatepicker() }
    }

    private fun clickdatepicker() {
        val mycalender = Calendar.getInstance()
        // getting todays date from system
        val year = mycalender.get(Calendar.YEAR)
        val month = mycalender.get(Calendar.MONTH)
        val day = mycalender.get(Calendar.DAY_OF_MONTH)
        val picker = DatePickerDialog(this, DatePickerDialog.OnDateSetListener
        { _, year, month, dayofMonth ->
            Toast.makeText(
                this,
                "Date selected is $dayofMonth/${month + 1}/$year",
                Toast.LENGTH_SHORT
            ).show()

            var date = "$dayofMonth/${month + 1}/$year"
            selectedDate?.text = date

            val sdf= SimpleDateFormat("dd/MM/yyyy",Locale.ENGLISH)
            val thedate=sdf.parse(date)

            thedate?.let {
                val selectedDateInMinutes=thedate.time/86400000


                val currentDate=sdf.parse(sdf.format(System.currentTimeMillis())) // fromat change time to string because parse done whith string
                currentDate?.let {
                    val currentDateInMinutes=currentDate.time/86400000 // its millisecond equal to one day

                    val DifrrenceInTime=currentDateInMinutes-selectedDateInMinutes

                    InMinutes?.text= DifrrenceInTime.toString() // to string because time difference is in minutes
                }
            }

        }, year, month, day
        )
        picker.datePicker.maxDate = System.currentTimeMillis() - 86400000
        picker.show()

    }
}