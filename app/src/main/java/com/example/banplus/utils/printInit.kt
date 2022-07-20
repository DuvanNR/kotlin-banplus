package com.example.banplus.utils

import android.graphics.Typeface
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer

fun printInit(printer:Printer):Printer {
    printer!!.initPrinter() //init printer
    printer!!.setTypeface(Typeface.DEFAULT) //change print type
    printer!!.setLetterSpacing(3) //change the line space between each line
    printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
    return printer
}