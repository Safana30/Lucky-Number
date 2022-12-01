package com.example.luckynum

import android.app.Application

class NumberApp:Application() {
    val db by lazy {
        NumberDB.getDatabase(this)
    }
}