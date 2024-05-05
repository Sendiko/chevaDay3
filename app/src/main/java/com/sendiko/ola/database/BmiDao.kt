package com.sendiko.ola.database

import androidx.room.Dao
import androidx.room.Delete
import androidx.room.Insert
import androidx.room.Query
import androidx.room.Update
import kotlinx.coroutines.flow.Flow

@Dao
interface BmiDao {

    @Insert
    suspend fun insert(bmiData: BmiData)

    @Update
    suspend fun update(bmiData: BmiData)

    @Delete
    suspend fun delete(bmiData: BmiData)

    @Query("SELECT * FROM data_bmi")
    fun getAll(): Flow<List<BmiData>>

}