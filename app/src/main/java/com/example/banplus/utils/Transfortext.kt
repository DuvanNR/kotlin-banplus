package com.example.banplus.utils

import androidx.compose.ui.graphics.Color
import androidx.compose.ui.text.AnnotatedString
import androidx.compose.ui.text.SpanStyle
import androidx.compose.ui.text.input.OffsetMapping
import androidx.compose.ui.text.input.TransformedText
import androidx.compose.ui.text.input.VisualTransformation

const val mask = "xx xxx xx xx"
fun mobileNumberFilter(text: AnnotatedString): TransformedText {
    // change the length
    val trimmed =
        if (text.text.length >= 9) text.text.substring(0..8) else text.text

    val annotatedString = AnnotatedString.Builder().run {
        for (i in trimmed.indices) {
            append(trimmed[i])
            if (i == 1 || i == 4 || i == 6) {
                append(" ")
            }
        }
        pushStyle(SpanStyle(color = Color.Gray))
        append(mask.takeLast(mask.length - length))
        toAnnotatedString()
    }

    val phoneNumberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset + 1
            if (offset <= 6) return offset + 2
            if (offset <= 9) return offset + 3
            return 12
        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <= 1) return offset
            if (offset <= 4) return offset - 1
            if (offset <= 6) return offset - 2
            if (offset <= 9) return offset - 3
            return 9
        }
    }

    return TransformedText(annotatedString, phoneNumberOffsetTranslator)
}


class MaskTransformation() : VisualTransformation {
    override fun filter(text: AnnotatedString): TransformedText {
        return maskFilter(text)
    }
}


fun maskFilter(text: AnnotatedString): TransformedText {

    // NNNNN-NNN
    val trimmed = if (text.text.length >= 8) text.text.substring(0..7) else text.text
    var out = ""
    for (i in trimmed.indices) {
        out += trimmed[i]
        if (i==4) out += "-"
    }

    val numberOffsetTranslator = object : OffsetMapping {
        override fun originalToTransformed(offset: Int): Int {
            if (offset <= 4) return offset
            if (offset <= 8) return offset +1
            return 9

        }

        override fun transformedToOriginal(offset: Int): Int {
            if (offset <=5) return offset
            if (offset <=9) return offset -1
            return 8
        }
    }

    return TransformedText(AnnotatedString(out), numberOffsetTranslator)
}


