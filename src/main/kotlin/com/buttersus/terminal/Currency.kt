@file:Suppress("SpellCheckingInspection")

package com.buttersus.terminal

enum class Currency(
    // Since there are no static fields in Kotlin, we'll store them here
    val initialTerminalValue: Double,
    val initialUserValue: Double = 0.0
) {
    RUB(10_000.0, 1_000_000.0),
    USD(1000.0),
    EUR(1000.0),
    USDT(1000.0),
    BTC(1.5)
}