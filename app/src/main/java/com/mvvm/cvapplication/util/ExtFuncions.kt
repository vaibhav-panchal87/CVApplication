package com.mvvm.cvapplication.util

import java.text.SimpleDateFormat
import java.util.*

/**
 * Extension function to convert Date format
 * */
fun String.convertToDDMMMYYYY(): String {
    return try {
        val fromSDF = SimpleDateFormat("yyyy-MM-dd",Locale.getDefault())
        val toSDF = SimpleDateFormat("dd-MMM-yyyy", Locale.getDefault())

        toSDF.format(fromSDF.parse(this) as Date)
    } catch (ex: Exception) {
        this
    }

}