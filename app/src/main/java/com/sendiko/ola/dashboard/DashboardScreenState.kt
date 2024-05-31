package com.sendiko.ola.dashboard

import com.sendiko.ola.database.BmiData

data class DashboardScreenState(
    val bmiList: List<BmiData> = emptyList()
)
