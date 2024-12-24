package com.admin.utils

import java.time.LocalDate
import java.time.ZoneId
import java.time.ZonedDateTime
import java.time.format.DateTimeFormatter

class DateUtils {
    companion object {
        @JvmStatic
        fun convertToDate(dateString: String?): ZonedDateTime? {
            return try {
                dateString?.let {
                    ZonedDateTime.parse(it)
                }
            } catch (ex: Exception) {
                null
            }
        }

        @JvmStatic
        fun convertStringToDate(dateString: String?): ZonedDateTime? {
            return try {
                dateString?.let {
                    convertFromDateToZoneDateTime(it)
                }
            } catch (ex: Exception) {
                null
            }
        }

        private fun convertFromDateToZoneDateTime(dateString: String): ZonedDateTime {
            val formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd")
            val localDate: LocalDate = LocalDate.parse(dateString, formatter)
            return localDate.atStartOfDay(ZoneId.systemDefault())
        }

        fun convertZonedDateToLocalDateTime(zonedDateTime: ZonedDateTime?): String? {
            val formatter = DateTimeFormatter.ofPattern("MM-dd-yyyy HH:mm:ss")
            return zonedDateTime?.let { it.format(formatter).toString() }
        }
    }
}