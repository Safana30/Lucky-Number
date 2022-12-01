package com.example.luckynum

import android.app.AlertDialog
import android.app.DatePickerDialog
import android.app.Dialog
import android.content.DialogInterface.BUTTON_POSITIVE
import android.content.Intent
import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.view.Menu
import android.view.MenuItem
import android.view.View
import android.widget.RadioButton
import android.widget.RadioGroup
import android.widget.Toast
import androidx.appcompat.app.AppCompatActivity
import androidx.appcompat.app.AppCompatDelegate
import com.example.luckynum.databinding.ActivityMainBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.custom_dialog.*
import java.util.*

class MainActivity : AppCompatActivity(){

     private var binding: ActivityMainBinding? = null

     lateinit var sharedPreferences: SharedPreferences
     lateinit var editor: SharedPreferences.Editor
     private var bstate=false
    private var bval =""


     override fun onCreate(savedInstanceState: Bundle?) {
         super.onCreate(savedInstanceState)
         binding = ActivityMainBinding.inflate(layoutInflater)
         setContentView(binding?.root)



         sharedPreferences = getSharedPreferences("pref", 0)
         editor = sharedPreferences.edit()

         bstate = sharedPreferences.getBoolean("ButtonState",false)
         bval = sharedPreferences.getString("ButVal", "").toString()

         if(bstate == true){
             if(bval.equals("Light")){
                 AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)
             }else if(bval.equals("Dark")){
                 AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)
             }else if(bval.equals("Default")){
                 AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)
             }
         }


         var randomNum: Int
         var cal = Calendar.getInstance()
         var year = cal.get(Calendar.YEAR)
         var monthOfYear = cal.get(Calendar.MONTH)
         var dayOfMonth = cal.get(Calendar.DAY_OF_MONTH)

         numtext1.visibility = View.INVISIBLE
         numtext2.visibility = View.INVISIBLE
         numtext3.visibility = View.INVISIBLE


         binding?.btnDate?.setOnClickListener {

             val dpd = DatePickerDialog(
                 this,
                 DatePickerDialog.OnDateSetListener { _, year, month, dayOfMonth ->

                     val month = monthOfYear + 1
                     val selectedDate = "Your Lucky Number for the Date:"
                     val selectedDate2 = "$dayOfMonth/$month/$year"
                     tvText2.setText(selectedDate)
                     tvDatetext.setText(selectedDate2)
                     text3.setText("Other Lucky Numbers:")
                     numtext1.visibility = View.VISIBLE
                     numtext2.visibility = View.VISIBLE
                     numtext3.visibility = View.VISIBLE


                     numtext1.setText("1-99")
                     numtext2.setText("100-999")
                     numtext3.setText("1000-9999")


                     var result = 0
                     var temp = 0
                     temp = dayOfMonth
                     do {
                         result = result + (temp % 10)
                         temp = temp / 10
                     } while (temp != 0)

                     temp = month
                     do {
                         result = result + (temp % 10)
                         temp = temp / 10
                     } while (temp != 0)

                     temp = year
                     do {
                         result = result + (temp % 10)
                         temp = temp / 10
                     } while (temp != 0)

                     while (result >= 10) {
                         temp = result
                         result = 0
                         do {
                             result = result + (temp % 10)
                             temp = temp / 10
                         } while (temp != 0)
                     }
                     var secondResult = result
                     tvLnumber.text = secondResult.toString()


                     var passingData = tvLnumber.text.toString()
                     var passingData2 = tvLnumber.text.toString()
                     var passingData3 = tvLnumber.text.toString()
                     binding?.numtext1?.setOnClickListener {
                         val intent = Intent(this, Second::class.java)
                         intent.putExtra("key", passingData)
                         intent.putExtra("keytoId", numtext1.id)
                         startActivity(intent)
                     }
                     binding?.numtext2?.setOnClickListener {
                         val intent = Intent(this, Second::class.java)
                         intent.putExtra("key", passingData2)
                         intent.putExtra("keytoId", numtext2.id)
                         startActivity(intent)
                     }
                     binding?.numtext3?.setOnClickListener {
                         val intent = Intent(this, Second::class.java)
                         intent.putExtra("key", passingData3)
                         intent.putExtra("keytoId", numtext3.id)
                         startActivity(intent)
                     }

                 },
                 year,
                 monthOfYear,
                 dayOfMonth
             )
             dpd.show()


         }

         var thread: Thread = object : Thread() {
             override fun run() {
                 try {
                     while (!this.isInterrupted) {

                         sleep(500)
                         runOnUiThread {
                             randomNum = (0..9).random()
                             if (tvDatetext.text.equals("")) {
                                 tvLnumber.text = randomNum.toString()
                             }
                         }
                     }
                 } catch (e: InterruptedException) {
                 }
             }
         }
         thread.start()



     }

     private fun customDialogFunction() {
         val customDialog = Dialog(this,R.style.MyDialogTheme)
         customDialog.setContentView(R.layout.custom_dialog)

         val light = customDialog.Rbtn_light
         val dark = customDialog.Rbtn_dark
         val default = customDialog.Rbtn_default
         val rGroup=customDialog.Radiogroup

         rGroup.setOnCheckedChangeListener { group, checkedId ->
             when(checkedId){
                 R.id.Rbtn_light->{
                     light.setOnClickListener {

                         val ischecked:Boolean=light.isChecked
                         editor.putBoolean("ButtonState", ischecked)
                         editor.putString("ButVal", "Light")
                         editor.commit()
                         customDialog.tv_ok.setOnClickListener {
                             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_NO)

                             customDialog.dismiss()
                         }
                     }

                 }
                R.id.Rbtn_dark->{
                    dark.setOnClickListener {

                        val ischecked:Boolean=dark.isChecked
                        editor.putBoolean("ButtonState",ischecked)
                        editor.putString("ButVal", "Dark")
                        editor.commit()
                        customDialog.tv_ok.setOnClickListener {
                            AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_YES)

                            customDialog.dismiss()
                        }
                    }

                 }
                 R.id.Rbtn_default->{
                     default.setOnClickListener {
                         val ischecked:Boolean=default.isChecked
                         editor.putBoolean("ButtonState",ischecked)
                         editor.putString("ButVal", "Default")
                         editor.commit()
                         customDialog.tv_ok.setOnClickListener {
                             AppCompatDelegate.setDefaultNightMode(AppCompatDelegate.MODE_NIGHT_FOLLOW_SYSTEM)

                             customDialog.dismiss()
                         }
                     }
                 }
             }
         }
         customDialog.tv_ok.setOnClickListener {
             customDialog.dismiss()
         }

         customDialog.tv_cancel.setOnClickListener {
             customDialog.dismiss()
         }
         customDialog.show()

          bstate = sharedPreferences.getBoolean("ButtonState",false)
          bval = sharedPreferences.getString("ButVal", "").toString()
         if(bstate == true){
             if(bval.equals("Light")){
                 rGroup.check(light.id)
             }else if(bval.equals("Dark")){
                 rGroup.check(dark.id)
             }else if(bval.equals("Default")){
                 rGroup.check(default.id)
             }
         }
     }
    override fun onCreateOptionsMenu(menu: Menu?): Boolean {
        val inflater=menuInflater
        inflater.inflate(R.menu.menu,menu)
        return  true
    }

    override fun onOptionsItemSelected(item: MenuItem): Boolean {
        return when(item.itemId){
            R.id.theme->{
                customDialogFunction()
                return true
            }else->
                return super.onOptionsItemSelected(item)
        }
    }



    override fun onDestroy() {
        super.onDestroy()
        binding=null
    }






}





