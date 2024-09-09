package com.buttersus.terminal

import com.buttersus.extensions.sort

object Terminal {
    internal const val MAX_EXCHANGE_RATE_CHANGE = 0.05
    internal val balance: MutableMap<Currency, Double> = Currency.values().associateWith { it.initialTerminalValue }.toMutableMap()

    // We need to store all possible pair relations
    private val exchangeRates: Map<Pair<Currency, Currency>, ExchangeRate> = mapOf(
        ExchangeRate(Currency.RUB, Currency.USD).getMapItem(),
        ExchangeRate(Currency.RUB, Currency.EUR).getMapItem(),
        ExchangeRate(Currency.USD, Currency.EUR).getMapItem(),
        ExchangeRate(Currency.USD, Currency.USDT).getMapItem(),
        ExchangeRate(Currency.USD, Currency.BTC).getMapItem(),
    )

    fun exchangeFrom(user: User, amountFrom: Double, from: Currency, to: Currency) {
        // Checking if exchange is possible
        val exchangeRate = exchangeRates.getValue((from to to).sort())
        if (amountFrom < 0) throw IllegalArgumentException()

        // Calculate how many can be exchanged && update balance
        val (fromDelta, toDelta) = exchangeRate.getExchangePairFrom(user, amountFrom, from, to)
        updateBalance(user, fromDelta, from, toDelta, to)

        // Update exchange rate
        exchangeRate.update()
    }

    fun exchangeTo(user: User, amountTo: Double, from: Currency, to: Currency) {
        // Checking if exchange is possible
        val exchangeRate = exchangeRates.getValue((from to to).sort())
        if (amountTo < 0) throw IllegalArgumentException()

        // Calculate how many can be exchanged
        val (fromDelta, toDelta) = exchangeRate.getExchangePairTo(user, amountTo, from, to)
        updateBalance(user, fromDelta, from, toDelta, to)

        // Update exchange rate
        exchangeRate.update()
    }

    private fun updateBalance(user: User, fromDelta: Double, from: Currency, toDelta: Double, to: Currency) {
        user.balance[from] = user.balance.getValue(from) - fromDelta
        user.balance[to] = user.balance.getValue(to) + toDelta
        this.balance[from] = this.balance.getValue(from) + fromDelta
        this.balance[to] = this.balance.getValue(to) - toDelta
    }

    // Methods
    fun isExchangePossible(from: Currency, to: Currency): Boolean {
        return (from to to).sort() in exchangeRates.keys && from != to && this.balance.getValue(to) > 0.0
    }

    fun getExchangeRates(): List<ExchangeRate> = exchangeRates.values.toList()
}