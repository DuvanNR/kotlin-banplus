package com.example.banplus.utils

fun ConverString(e:String, init:Int = 3, fin: Int= 2): String {
        return "${e.substring(0,init)}***${e.substring(e.length - fin)}"
}

