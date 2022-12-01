package com.example.luckynum

import androidx.room.*

@Entity(tableName = "numbers")
  data class NumbersEntity (

    @PrimaryKey
    @ColumnInfo(name = "One")
    var num: Int,
    @ColumnInfo(name = "Two")
    var num2:Int,
    @ColumnInfo(name = "Three")
    var num3:Int,
    @ColumnInfo(name = "Four")
    var num4:Int,
    @ColumnInfo(name = "Five")
    var num5:Int,
    @ColumnInfo(name = "Six")
    var num6:Int,
    @ColumnInfo(name = "Seven")
    var num7:Int,
    @ColumnInfo(name = "Eight")
    var num8:Int,
    @ColumnInfo(name = "Nine")
    var num9:Int
)