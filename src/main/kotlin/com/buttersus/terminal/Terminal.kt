package com.buttersus.terminal

import com.buttersus.extensions.sort

object Terminal {
    internal const val MAX_EXCHANGE_RATE_CHANGE = 0.05
    internal val balance: Map<Currency, Double> = Currency.values().associateWith { it.initialUserValue }

    // We need to store all possible pair relations
    private val exchangeRates: Map<Pair<Currency, Currency>, ExchangeRate> = mapOf(
        ExchangeRate(Currency.RUB, Currency.USD).getMapItem(),
        ExchangeRate(Currency.RUB, Currency.EUR).getMapItem(),
        ExchangeRate(Currency.USD, Currency.EUR).getMapItem(),
        ExchangeRate(Currency.USD, Currency.USDT).getMapItem(),
        ExchangeRate(Currency.USD, Currency.BTC).getMapItem(),
    )

    fun exchange(user: User, amount: Double, from: Currency, to: Currency): Unit? {
        val exchangeRate = exchangeRates[(from to to).sort()]
    }

    fun isExchangePossible(from: Currency, to: Currency): Boolean {
        return (from to to).sort() in exchangeRates && from != to && balance.getValue(to) > 0.0
    }
}