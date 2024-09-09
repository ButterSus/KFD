@file:Suppress("SpellCheckingInspection")

package com.buttersus.terminal

data class User(
    internal val balance: MutableMap<Currency, Double> = Currency.values().associateWith { it.initialUserValue }.toMutableMap()
)
