package com.tistory.jeongs0222.namdaein.utils

import java.text.SimpleDateFormat
import java.util.*


object DateUtil {

    fun bringDate(): String {
        val now = System.currentTimeMillis()

        val date = Date(now)

        val sdf = SimpleDateFormat("yy.MM.dd HH:mm")

        return sdf.format(date)
    }
}