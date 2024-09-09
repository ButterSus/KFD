package com.buttersus.extensions

import com.buttersus.terminal.Currency

// Can be optimized
fun Pair<Currency, Currency>.sort() = Pair(minOf(first, second), maxOf(first, second))
