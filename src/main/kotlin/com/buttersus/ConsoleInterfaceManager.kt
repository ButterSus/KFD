package com.buttersus

import com.buttersus.extensions.indentLine
import com.buttersus.extensions.prettyFormat
import com.buttersus.terminal.Currency
import com.buttersus.terminal.Terminal
import com.buttersus.terminal.User
import com.buttersus.util.ExitException

object ConsoleInterfaceManager {
    private lateinit var user: User

    fun onInit() {
        println("Welcome to terminal!")
        user = User()
    }

    fun onLoop() {
        print("Enter an operation (or h for help): ")
        val match = when (val operation = readln().trim()) {
            "h" -> {
                StringBuilder()
                    .appendLine("Available operations:")
                    .indentLine("h - help")
                    .indentLine("b - balance")
                    .indentLine("c - print currency list")
                    .indentLine("d - print exchange rates")
                    .indentLine("e - exit")
                    .indentLine("ex[amount]<from>-[amount]<to> - exchange")
                    .appendLine("Example: ex1000USD-RUB; exRUB-250USD")
                    .toString()
                    .let(::print)
                return
            }

            "b" -> {
                // User balance
                val builder1 = StringBuilder()
                    .appendLine("Your balance:")
                for (currency in Currency.values()) {
                    builder1
                        .indentLine("${currency.name} - ${user.balance.getValue(currency).prettyFormat()}")
                }
                builder1.toString().let(::print)

                // Terminal balance
                val builder2 = StringBuilder()
                    .appendLine("Terminal balance:")
                for (currency in Currency.values()) {
                    builder2
                        .indentLine("${currency.name} - ${Terminal.balance.getValue(currency).prettyFormat()}")
                }
                builder2.toString().let(::print)
                return
            }

            "c" -> {
                // Currency list
                val builder = StringBuilder()
                    .appendLine("Available currencies:")
                for (currency in Currency.values()) {
                    builder
                        .indentLine(currency.name)
                }
                builder.toString().let(::print)
                return
            }

            "d" -> {
                // Exchange rates
                val builder = StringBuilder()
                    .appendLine("Exchange rates:")
                for (exchangeRate in Terminal.getExchangeRates()) {
                    builder
                        .indentLine("${exchangeRate.first.name} - ${exchangeRate.second.name} - ${exchangeRate.getRate().prettyFormat()}")
                        .indentLine("${exchangeRate.second.name} - ${exchangeRate.first.name} - ${exchangeRate.getInvertedRate().prettyFormat()}")
                }
                builder.toString().let(::print)
                return
            }

            "e" -> {
                throw ExitException()
            }

            else -> {
                Regex("ex(\\d+)?([a-zA-Z]+)-(\\d+)?([a-zA-Z]+)")
                    .matchEntire(operation)
                    ?: run {
                        println("Your operation \"${operation}\" is not valid")
                        return
                    }
            }
        }

        val amountFrom = match.groups[1]?.value?.let { getDoubleOrNull(it) ?: return }
        val fromCurrency = match.groups[2]!!.value.uppercase().let { getCurrencyOrNull(it) ?: return }
        val amountTo = match.groups[3]?.value?.let { getDoubleOrNull(it) ?: return }
        val toCurrency = match.groups[4]!!.value.uppercase().let { getCurrencyOrNull(it) ?: return }

        if (!Terminal.isExchangePossible(fromCurrency, toCurrency)) {
            println("Exchange between ${fromCurrency.name} and ${toCurrency.name} is not possible")
            return
        }

        when {
            ((amountFrom == null) == (amountTo == null)) -> {
                println("One of the amounts must be specified")
                return
            }

            amountFrom != null -> {
                Terminal.exchangeFrom(user, amountFrom, fromCurrency, toCurrency)
            }

            amountTo != null -> {
                Terminal.exchangeTo(user, amountTo, fromCurrency, toCurrency)
            }
        }
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
