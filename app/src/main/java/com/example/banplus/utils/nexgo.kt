package com.example.banplus.utils

import android.content.Context
import android.graphics.Typeface
import android.widget.Toast
import com.example.banplus.Activity.getBanconporCodigo
import com.example.banplus.R
import com.example.banplus._interface.iCommerce
import com.example.banplus._interface.iTransaction
import com.example.banplus.db.schema.TransCount
import com.example.banplus.inject_dependency.HiltInjectApp
import com.nexgo.common.LogUtils
import com.nexgo.oaf.apiv3.device.printer.AlignEnum
import com.nexgo.oaf.apiv3.device.printer.GrayLevelEnum
import com.nexgo.oaf.apiv3.device.printer.Printer
import java.text.DecimalFormat

val FONT_SIZE_SMALL = 16
val FONT_SIZE_NORMAL = 24
val FONT_SIZE_BIG = 30
fun getSerialPost(context: Context): String {
    try {
        var deviceEngine = (context.applicationContext as HiltInjectApp).deviceEngine
        var deviceInfo = deviceEngine!!.deviceInfo
        return "${deviceInfo.sn}"
    } catch (e: Exception) {
        return ""
    }
}

fun PrintFacture(context: Context) {
    try {
        var deviceEngine = (context.applicationContext as HiltInjectApp).deviceEngine
        var printer: Printer? = deviceEngine!!.printer
        printer?.setTypeface(Typeface.DEFAULT)
        LogUtils.setDebugEnable(true)
        val forma = DecimalFormat("#,##0.00")
        val data = getDatetime()
        printer!!.initPrinter() //init printer
        printer!!.setTypeface(Typeface.DEFAULT) //change print type
        printer!!.setLetterSpacing(3) //change the line space between each line
        printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
        printer!!.appendPrnStr(
            "texttttt",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.rif)}: xxxxxxxxxxxxxxxxx",
            FONT_SIZE_NORMAL,
            AlignEnum.CENTER,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.fecha)}:",
            "xxxxxxfecha",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "${context.getString(R.string.hora)}:",
            "horaxxxxxxxx",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            context.getString(R.string.pago_plus),
            "",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.cantidad),
            "TOTAL RESP",
            FONT_SIZE_NORMAL,
            false
        )
        printer!!.appendPrnStr(
            context.getString(R.string.monto).capitalize(),
            "Bs. 000000000000",
            FONT_SIZE_NORMAL,
            false
        )

        printer!!.appendPrnStr(
            "",
            "",
            FONT_SIZE_BIG,
            false
        )


        printer!!.startPrint(true) { retCode ->
            if (retCode == -1005) {
                Toast.makeText(
                    context,
                    context.getString(R.string.no_papel_imprimir),
                    Toast.LENGTH_SHORT
                ).show()
            }
            if (retCode == 0) {
                Toast.makeText(
                    context,
                    "Imprimiendo",
                    Toast.LENGTH_SHORT
                ).show()
            }

        }
    } catch (e: Exception) {
        return
    }
}

fun printCardResponse(athis: Context, resp: iTransaction) {
    val status: Boolean = resp.state
    var deviceEngine = (athis.applicationContext as HiltInjectApp).deviceEngine
    var printer: Printer? = deviceEngine!!.printer
    val commerce = iCommerce.getCommerce(athis)
    val forma = DecimalFormat("#,##0.00")
    printer!!.initPrinter() //init printer
    printer!!.setTypeface(Typeface.DEFAULT) //change print type
    printer!!.setLetterSpacing(3) //change the line space between each line
    printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
    printer!!.appendPrnStr(
        "${commerce?.razonSocial}", FONT_SIZE_NORMAL, AlignEnum.CENTER, false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.rif)}: ${commerce?.tipo!!.capitalize()}-${commerce?.rif}",
        FONT_SIZE_NORMAL,
        AlignEnum.CENTER,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.fecha)}:", resp.fecha, FONT_SIZE_NORMAL, false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.hora)}:", resp.hora, FONT_SIZE_NORMAL, false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.cedula)}:",
        "${resp.tipo.capitalize()}-${ConverString("${resp.cedula}")}",
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.telefono)}:",
        ConverString("${resp.telefono}", init = 4, fin = 3),
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.banco)}:",
        "${getBanconporCodigo(resp.banco)}",
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.estado)}:",
        if (status) athis.getString(R.string.aprobado) else athis.getString(R.string.negada),
        FONT_SIZE_NORMAL,
        false
    )
    if (status) {
        printer!!.appendPrnStr(
            "${athis.getString(R.string.ref)}:", "${resp.ref}", FONT_SIZE_NORMAL, false
        )
    }
    printer!!.appendPrnStr(
        "${
            athis.getString(R.string.monto).capitalize()
        }: Bs. ${forma.format("${resp.monto}".toDouble())}",
        FONT_SIZE_BIG,
        AlignEnum.CENTER,
        false
    )
    printer!!.appendPrnStr(
        "", "", FONT_SIZE_BIG, false
    )
    printer!!.appendPrnStr(
        "", "", FONT_SIZE_BIG, false
    )

//        if(status) {
//            printer!!.appendPrnStr(
//                "${getString(R.string.firma)}: __________________________",
//                FONT_SIZE_NORMAL,
//                AlignEnum.LEFT,
//                false
//            )
//            printer!!.appendPrnStr(
//                "",
//                "",
//                FONT_SIZE_BIG,
//                false
//            )
//            printer!!.appendPrnStr(
//                "${getString(R.string.cedula)}: _________________________",
//                FONT_SIZE_NORMAL,
//                AlignEnum.LEFT,
//                false
//            )
//        }
    printer!!.appendPrnStr(
        "", "", FONT_SIZE_BIG, false
    )
    printer!!.startPrint(true) { retCode ->
        if (retCode == -1005) {
            Toast.makeText(
                athis, "${athis.getString(R.string.no_papel_imprimir)}!!", Toast.LENGTH_SHORT
            ).show()
        }
        if (retCode == 0) {
            Toast.makeText(
                athis, "${athis.getString(R.string.imprimir)}", Toast.LENGTH_SHORT
            ).show()
        }

    }

}

fun onPrintReport(athis: Context, resp: TransCount) {
    var deviceEngine = (athis.applicationContext as HiltInjectApp).deviceEngine
    var printer: Printer? = deviceEngine!!.printer
    val commerce = iCommerce.getCommerce(athis)
    val forma = DecimalFormat("#,##0.00")
    val data = getDatetime()
    printer!!.initPrinter() //init printer
    printer!!.setTypeface(Typeface.DEFAULT) //change print type
    printer!!.setLetterSpacing(3) //change the line space between each line
    printer!!.setGray(GrayLevelEnum.LEVEL_2) //change print gray
    printer!!.appendPrnStr(
        "${commerce?.razonSocial}",
        FONT_SIZE_NORMAL,
        AlignEnum.CENTER,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.rif)}: ${commerce!!.tipo.capitalize()}-${commerce.rif}",
        FONT_SIZE_NORMAL,
        AlignEnum.CENTER,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.fecha)}:",
        data.fecha,
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        "${athis.getString(R.string.hora)}:",
        data.hora,
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        "",
        "",
        FONT_SIZE_NORMAL,
        false
    )

    printer!!.appendPrnStr(
        athis.getString(R.string.pago_plus),
        "",
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        athis.getString(R.string.cantidad),
        "${resp.total}",
        FONT_SIZE_NORMAL,
        false
    )
    printer!!.appendPrnStr(
        athis.getString(R.string.monto).capitalize(),
        "Bs. ${forma.format(resp.amount)}",
        FONT_SIZE_NORMAL,
        false
    )

    printer!!.appendPrnStr(
        "",
        "",
        FONT_SIZE_BIG,
        false
    )


    printer!!.startPrint(true) { retCode ->
        if (retCode == -1005) {
            Toast.makeText(
                athis,
                athis.getString(R.string.no_papel_imprimir),
                Toast.LENGTH_SHORT
            ).show()
        }
        if (retCode == 0) {
            Toast.makeText(
                athis,
                "Imprimiendo",
                Toast.LENGTH_SHORT
            ).show()
        }

    }
}
