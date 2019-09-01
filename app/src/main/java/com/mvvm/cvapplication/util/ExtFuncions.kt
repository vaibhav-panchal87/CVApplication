package com.mvvm.cvapplication.util

import java.text.SimpleDateFormat

/**
 * Extention funtion to convert Date format
 * */
fun String.convertYYYYMMDD_TO_DDMMMYYYY(): String {
    return try {
        val fromSDF = SimpleDateFormat("yyyy-MM-dd")
        val toSDF = SimpleDateFormat("dd-MMM-yyyy")

        toSDF.format(fromSDF.parse(this))
    } catch (ex: Exception) {
        this
    }

}