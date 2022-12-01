package com.example.luckynum

import android.widget.TwoLineListItem
import androidx.room.*
import kotlinx.coroutines.flow.Flow
import java.util.concurrent.Flow as Flow1

@Dao
interface NumberDAO {

    @Insert(onConflict = OnConflictStrategy.REPLACE)
    suspend fun insertNumber(numbers: NumbersEntity)
    @Delete
    suspend fun deleteNumber(numbers: NumbersEntity)


    @Query("SELECT * FROM numbers WHERE One BETWEEN 1 AND 99" )
    fun getNumbers():Flow<List<NumbersEntity>>

    @Query("SELECT * FROM numbers WHERE One BETWEEN 100 AND 999 " )
    fun getNumbers2():Flow<List<NumbersEntity>>

    @Query("SELECT * FROM numbers WHERE One BETWEEN 1000 AND 9999" )
    fun getNumbers3():Flow<List<NumbersEntity>>

}