package com.acdicoding.vianrd.footballfinalsub.utils

import java.text.SimpleDateFormat
import java.util.*

class DateStringFormat {

    companion object {

        const val DATE_FORMAT_FULL_DATE = "E, dd MMM yyyy"
        const val DATE_FORMAT_YEAR_FIRST = "yyyy-MM-dd"
        const val DATE_FORMAT_TIME_LOCAL = "HH:mm"
        const val DATE_FORMAT_TIME = "HH:mm:ssXXX"

        private fun stringToDate(strDate: String, formatInput: String): Date {
            return if (strDate.isNullOrEmpty()) {
                Calendar.getInstance().time
            } else {
                SimpleDateFormat(formatInput, Locale.getDefault()).parse(strDate)
            }
        }

        private fun dateToString(date: Date, formatOutput: String): String {
            return SimpleDateFormat(formatOutput, Locale.getDefault()).format(date)
        }

        fun reformatStringDate(stringDate: String, formatInput: String, formatOutput: String): String {
            return dateToString(
                stringToDate(
                    stringDate,
                    formatInput
                ), formatOutput
            )
        }
    }

}