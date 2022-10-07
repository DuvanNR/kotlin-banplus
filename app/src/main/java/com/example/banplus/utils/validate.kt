package com.example.banplus.utils

import java.text.DecimalFormat

fun isString(chars: String): String? {
    if (chars.matches("^[a-zA-Z]*$".toRegex())) {
        return chars
    }
    return null
}

fun isNumber(chars: String): String? {
    if (chars.matches("^.[0-9]*$".toRegex())) {
        return chars
    }
    return null
}

fun ConvertToPrice(num: String): String {
    try {
        val forma = DecimalFormat("#,##0.00")
        return "${forma.format("${num}".toDouble())}"
    } catch (err: Exception) {
        return num
    }

}