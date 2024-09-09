@file:Suppress("SpellCheckingInspection")

package com.buttersus.terminal

class User(val name: String = DEFAULT_NAME) {
    private val balance: Map<Currency, Double> = Currency.values().associateWith { it.initialUserValue }

    companion object {
        const val DEFAULT_NAME = "Vasya Pupkin"
    }
}