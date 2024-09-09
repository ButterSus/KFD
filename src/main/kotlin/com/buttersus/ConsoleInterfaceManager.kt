package com.buttersus

import com.buttersus.terminal.Currency
import com.buttersus.terminal.Terminal
import com.buttersus.terminal.User
import java.util.*

object ConsoleInterfaceManager {
    fun onInit() {
        println("Welcome to terminal!")
        println("We assume your name is ${User.DEFAULT_NAME}")
    }

    fun onLoop() {
        val from: Currency
        while (true) {
            println("Type currency you want to exchange from: ")
            val currencyFromString = readln().uppercase(Locale.getDefault())
            from = getCurrencyOrNull(currencyFromString) ?: continue
            break
        }

        val to: Currency
        while (true) {
            println("Type currency you want to exchange to: ")
            val currencyToString = readln().uppercase(Locale.getDefault())
            to = getCurrencyOrNull(currencyToString) ?: continue
            break
        }

        // Check if we can exchange from -> to
        if (!Terminal.isExchangePossible(from, to)) {
            println("You can't exchange from ${from.name} to ${to.name}; There is no such exchange rate")
            return
        }

        val amount: Double
        while (true) {
            println("Type amount of $to you want to exchange: ")
            val amountString = readln()
            amount = getDoubleOrNull(amountString) ?: continue
            if (!Terminal.isAmountPossible)
            break
        }

        Terminal.exchange(User(), amount, from, to) ?: println("Exchange failed")
    }

    private fun getCurrencyOrNull(currencyString: String): Currency? {
        val currencyOrNull = Currency.values().find { it.name == currencyString }
        if (currencyOrNull == null) {
            println("Your currency \"${currencyString}\" doesn't exist")
            println("See list of available currencies:\n${Currency.values().joinToString(",\n") { it.name }}")
        }
        return currencyOrNull
    }

    private fun getDoubleOrNull(amountString: String): Double? {
        val amountOrNull = amountString.toDoubleOrNull()
        if (amountOrNull == null) {
            println("Your amount \"${amountString}\" is not a number")
        }
        return amountOrNull
    }

    fun onExit() {
        println("Bye!")
    }
}
