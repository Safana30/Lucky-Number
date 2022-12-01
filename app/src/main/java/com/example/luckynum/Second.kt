package com.example.luckynum

import android.content.SharedPreferences
import android.os.Bundle
import android.util.Log
import android.widget.*
import androidx.appcompat.app.AppCompatActivity
import androidx.lifecycle.lifecycleScope
import androidx.recyclerview.widget.GridLayoutManager
import com.example.luckynum.databinding.ActivitySecondBinding
import kotlinx.android.synthetic.main.activity_main.*
import kotlinx.android.synthetic.main.activity_second.*
import kotlinx.android.synthetic.main.recyclerview_items.*
import kotlinx.coroutines.GlobalScope
import kotlinx.coroutines.launch
import java.util.*
import kotlin.collections.ArrayList

class Second : AppCompatActivity(){
     private var binding: ActivitySecondBinding?=null

     lateinit var sharedPreferences: SharedPreferences


     override fun onCreate(savedInstanceState: Bundle?) {
      super.onCreate(savedInstanceState)
         binding= ActivitySecondBinding.inflate(layoutInflater)
         setContentView(binding?.root)


         sharedPreferences=getSharedPreferences("SHARED_PREF", 0)


             val gettingId=intent.getIntExtra("keytoId",0)
             if(gettingId==R.id.numtext1) {
                 val receivingData=intent.getStringExtra("key")
                 luckyNumber.text = receivingData.toString()
                 val dao =(application as NumberApp).db.numberDao()
                 getAllNum(dao)
             }
             if(gettingId==R.id.numtext2) {
                 val receivingData=intent.getStringExtra("key")
                 luckyNumber.text = receivingData.toString()
                 val dao =(application as NumberApp).db.numberDao()
                 getAllNum2(dao)
             }
             if(gettingId==R.id.numtext3) {
                 val receivingData=intent.getStringExtra("key")
                 luckyNumber.text = receivingData.toString()
                 val dao =(application as NumberApp).db.numberDao()
                 getAllNum3(dao)
             }

     }


     fun addNumtoData(numberDAO: NumberDAO) {
         GlobalScope.launch {

             var i1 = 0
             var i2 = 0
             var i3 = 0
             var i4 = 0
             var i5 = 0
             var i6 = 0
             var i7 = 0
             var i8 = 0
             var i9 = 0

             for (i in 1..9999) {
                 var result1 = 0
                 var temp1 = 0
                 temp1 = i
                 do {
                     result1 = result1 + (temp1 % 10)
                     temp1 = temp1 / 10
                 } while (temp1 != 0)
                 while (result1 >= 10) {
                     temp1 = result1
                     result1 = 0
                     do {
                         result1 = result1 + (temp1 % 10)
                         temp1 = temp1 / 10
                     } while (temp1 != 0)
                 }
                 if (result1 == 1) { i1 = i }
                 if (result1 == 2) { i2 = i }
                 if (result1 == 3) { i3 = i }
                 if (result1 == 4) { i4 = i }
                 if (result1 == 5) { i5 = i }
                 if (result1 == 6) { i6 = i }
                 if (result1 == 7) { i7 = i }
                 if (result1 == 8) { i8 = i }
                 if (result1 == 9) { i9 = i
                     numberDAO.insertNumber(NumbersEntity(i1, i2, i3, i4, i5, i6, i7, i8, i9)) }
             }
         }

     }




     private fun getAllNum(numberDAO: NumberDAO){
         lifecycleScope.launch {
             numberDAO.getNumbers().collect{allNumbers->
                 val numbers=ArrayList<Int>()
                 for (i in allNumbers) {
                     binding?.RVtext?.layoutManager = GridLayoutManager(this@Second, 3)

                         if (luckyNumber.text.toString().equals("1")) {
                             numbers.add(i.num)
                         } else if (luckyNumber.text.toString().equals("2")) {
                             numbers.add(i.num2)
                         } else if (luckyNumber.text.toString().equals("3")) {
                             numbers.add(i.num3)
                         } else if (luckyNumber.text.toString().equals("4")) {
                             numbers.add(i.num4)
                         } else if (luckyNumber.text.toString().equals("5")) {
                             numbers.add(i.num5)
                         } else if (luckyNumber.text.toString().equals("6")) {
                             numbers.add(i.num6)
                         } else if (luckyNumber.text.toString().equals("7")) {
                             numbers.add(i.num7)
                         } else if (luckyNumber.text.toString().equals("8")) {
                             numbers.add(i.num8)
                         } else if (luckyNumber.text.toString().equals("9")) {
                             numbers.add(i.num9)
                         }
                 }
                 val numberadapter=numberAdapter(numbers)
                 binding?.RVtext?.adapter=numberadapter
             }
         }
     }

     private fun getAllNum2(numberDAO: NumberDAO){
         lifecycleScope.launch {
             numberDAO.getNumbers2().collect{allNumbers->
                 val numbers=ArrayList<Int>()
                 for (i in allNumbers) {
                     binding?.RVtext?.layoutManager = GridLayoutManager(this@Second, 3)

                     if (luckyNumber.text.toString().equals("1")) {
                         numbers.add(i.num)
                     } else if (luckyNumber.text.toString().equals("2")) {
                         numbers.add(i.num2)
                     } else if (luckyNumber.text.toString().equals("3")) {
                         numbers.add(i.num3)
                     } else if (luckyNumber.text.toString().equals("4")) {
                         numbers.add(i.num4)
                     } else if (luckyNumber.text.toString().equals("5")) {
                         numbers.add(i.num5)
                     } else if (luckyNumber.text.toString().equals("6")) {
                         numbers.add(i.num6)
                     } else if (luckyNumber.text.toString().equals("7")) {
                         numbers.add(i.num7)
                     } else if (luckyNumber.text.toString().equals("8")) {
                         numbers.add(i.num8)
                     } else if (luckyNumber.text.toString().equals("9")) {
                         numbers.add(i.num9)
                     }
                 }
                 val numberadapter=numberAdapter(numbers)
                 binding?.RVtext?.adapter=numberadapter
             }
         }
     }

     private fun getAllNum3(numberDAO: NumberDAO){
         lifecycleScope.launch {
             numberDAO.getNumbers3().collect{allNumbers->
                 val numbers=ArrayList<Int>()
                 for (i in allNumbers) {
                     binding?.RVtext?.layoutManager = GridLayoutManager(this@Second, 3)

                     if (luckyNumber.text.toString().equals("1")) {
                         numbers.add(i.num)
                     } else if (luckyNumber.text.toString().equals("2")) {
                         numbers.add(i.num2)
                     } else if (luckyNumber.text.toString().equals("3")) {
                         numbers.add(i.num3)
                     } else if (luckyNumber.text.toString().equals("4")) {
                         numbers.add(i.num4)
                     } else if (luckyNumber.text.toString().equals("5")) {
                         numbers.add(i.num5)
                     } else if (luckyNumber.text.toString().equals("6")) {
                         numbers.add(i.num6)
                     } else if (luckyNumber.text.toString().equals("7")) {
                         numbers.add(i.num7)
                     } else if (luckyNumber.text.toString().equals("8")) {
                         numbers.add(i.num8)
                     } else if (luckyNumber.text.toString().equals("9")) {
                         numbers.add(i.num9)
                     }
                 }
                 val numberadapter=numberAdapter(numbers)
                 binding?.RVtext?.adapter=numberadapter
             }
         }
     }

    override fun onResume() {
        super.onResume()
        if (sharedPreferences.getBoolean("firstrun", true)) {
            // Do first run stuff here then set 'firstrun' as false
            // using the following line to edit/commit prefs
            val dao =(application as NumberApp).db.numberDao()

                addNumtoData(dao)
            sharedPreferences.edit().putBoolean("firstrun", false).commit()
        }
    }

     override fun onDestroy() {
         super.onDestroy()
         binding=null
     }
}
