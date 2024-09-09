package com.buttersus.terminal

import com.buttersus.terminal.Terminal.MAX_EXCHANGE_RATE_CHANGE
import com.buttersus.extensions.sort

// Low-precision exchange rate (real one is much higher)
class ExchangeRate(
    val first: Currency,
    val second: Currency,
    private var rate: Double = second.initialTerminalValue / first.initialTerminalValue
) {
    // Calculate how much can be exchanged
    internal fun getExchangePairFrom(user: User, amountFrom: Double, from: Currency, to: Currency): Pair<Double, Double> {
        val directedRate = getDirectedRate(from, to)
        val fromAmount = minOf(
            user.balance.getValue(from),  // How much can give
            Terminal.balance.getValue(to) / directedRate,  // How much terminal can give
            amountFrom  // How much user wants
        )
        val toAmount = fromAmount * directedRate
        return Pair(fromAmount, toAmount)
    }

    internal fun getExchangePairTo(user: User, amountTo: Double, from: Currency, to: Currency): Pair<Double, Double> {
        val directedRate = getDirectedRate(from, to)
        val fromAmount = minOf(
            user.balance.getValue(from),  // How much user can give
            Terminal.balance.getValue(to) / directedRate,  // How much terminal can give
            amountTo / directedRate  // How much user wants
        )
        val toAmount = fromAmount * directedRate
        return Pair(fromAmount, toAmount)
    }

    private fun getDirectedRate(from: Currency, to: Currency) = when {
        from == first && to == second -> rate
        from == second && to == first -> 1.0 / rate
        else -> throw IllegalArgumentException()
    }

    internal fun update() {
        this.rate += this.rate * getRandom() * MAX_EXCHANGE_RATE_CHANGE
    }

    // Wrapper for random function
    private fun getRandom() = Math.random()

    // Method to get sorted key (so there's no need to store all possible pair relations)
    internal fun getMapItem(): Pair<Pair<Currency, Currency>, ExchangeRate> = Pair((first to second).sort(), this)

    // Getters
    fun getRate() = this.rate
    fun getInvertedRate() = 1.0 / this.rate
}
