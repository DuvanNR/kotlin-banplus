package com.example.banplus.utils

fun isString(chars: String): String? {
    if(chars.matches("^[a-zA-Z]*$".toRegex())) {
        return chars
    }
    return null
}
fun isNumber(chars: String): String? {
    if(chars.matches("^.[0-9]*$".toRegex())) {
        return chars
    }
    return null
}
