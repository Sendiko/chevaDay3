package com.sendiko.ola.database

import androidx.room.Entity
import androidx.room.PrimaryKey

@Entity(tableName = "data_bmi")
data class BmiData(

    @PrimaryKey(autoGenerate = true)
    val id: Int = 0,

    val tanggal: String,
    val beratBadan: Int,
    val tinggiBadan: Int,
    val nilaiBmi: Int,
    val gender: String,
)
