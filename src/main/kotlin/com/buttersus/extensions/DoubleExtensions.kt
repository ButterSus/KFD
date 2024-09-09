package com.buttersus.extensions

fun Double.prettyFormat(): String {
    val formatter = java.text.NumberFormat.getIntegerInstance()
    formatter.minimumFractionDigits = 10
    formatter.maximumFractionDigits = 10
    return formatter.format(this)
}
