package com.buttersus.extensions

fun StringBuilder.indentLine(text: String, level: Int = 1): StringBuilder {
    val indentPrefix = " ".repeat(level * 4)
    return this.appendLine("$indentPrefix$text")
}