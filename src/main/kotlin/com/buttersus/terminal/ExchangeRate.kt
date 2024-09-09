package com.buttersus.terminal

import com.buttersus.terminal.Terminal.MAX_EXCHANGE_RATE_CHANGE
import com.buttersus.extensions.sort

class ExchangeRate(
    private val first: Currency,
    private val second: Currency,
    private var rate: Double = second.initialTerminalValue / first.initialTerminalValue
) {
    internal fun exchange(amount: Double, from: Currency, to: Currency): Pair<Double, Double> {
        Terminal.balance
    }

    private fun update() {
        this.rate += this.rate * getRandom() * MAX_EXCHANGE_RATE_CHANGE
    }

    // Wrapper for random function
    private fun getRandom() = Math.random()

    // Method to get sorted key (so there's no need to store all possible pair relations)
    internal fun getMapItem(): Pair<Pair<Currency, Currency>, ExchangeRate> = Pair((first to second).sort(), this)
}
