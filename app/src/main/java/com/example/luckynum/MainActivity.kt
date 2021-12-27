package com.example.luckynum

import android.app.DatePickerDialog
import android.os.Bundle
import android.view.View
import android.widget.Button
import android.widget.TextView
import androidx.appcompat.app.AppCompatActivity
import kotlinx.android.synthetic.main.activity_main.*
import java.text.SimpleDateFormat
import java.util.*

class MainActivity : AppCompatActivity() {


    override fun onCreate(savedInstanceState: Bundle?) {
        super.onCreate(savedInstanceState)
        setContentView(R.layout.activity_main)

        var datebtn = findViewById(R.id.datebtn) as Button
        var cal = Calendar.getInstance()
        var randomNum: Int

        val dateSetListener =
            DatePickerDialog.OnDateSetListener { view, year, monthOfYear, dayOfMonth ->
                cal.set(Calendar.YEAR, year)
                cal.set(Calendar.MONTH, monthOfYear)
                cal.set(Calendar.DAY_OF_MONTH, dayOfMonth)

                val myFormat = "dd.MM.yyyy" // mention the format you need
                val sdf = SimpleDateFormat(myFormat, Locale.US)

            }

        datebtn.setOnClickListener(object : View.OnClickListener {
            override fun onClick(view: View): Unit {
                // Handler code here.

                DatePickerDialog(
                    this@MainActivity, dateSetListener,
                    cal.get(Calendar.YEAR),
                    cal.get(Calendar.MONTH),
                    cal.get(Calendar.DAY_OF_MONTH)
                ).show()
            }
        })

        val rnum = findViewById(R.id.number) as TextView

        val thread: Thread = object : Thread() {
            override fun run() {
                try {
                    while (!this.isInterrupted) {
                        sleep(500)
                        runOnUiThread {
                            randomNum = (0..9).random()
                            rnum.setText(Integer.toString(randomNum))
                            // update TextView here!
                        }
                    }
                } catch (e: InterruptedException) {
                }
            }
        }

        thread.start()





    }
}
