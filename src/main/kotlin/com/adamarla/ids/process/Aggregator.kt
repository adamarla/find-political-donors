package com.adamarla.ids.process

import com.adamarla.ids.data.Contribution
import com.adamarla.ids.data.RunningTotal
import java.io.BufferedWriter

abstract class Aggregator<T>(val fileName: String) {

    val count: Int
        get() = records.size

    abstract fun add(contribution: Contribution)

    fun report(writer: BufferedWriter) {
        val sorted = sort(records)
        sorted.map {
            writer.write(it.toString())
            writer.newLine()
        }
    }

    protected abstract fun compositeKey(contribution: Contribution): String

    protected val records = mutableListOf<T>()
    protected open fun sort(list: MutableList<T>): List<T> = list.toList()
    protected fun updateTotals(amount: Int, key: String): RunningTotal {
        var totals = runningTotals[key] ?: RunningTotal(0, 0)
        totals += RunningTotal(1, amount)
        runningTotals[key] = totals
        return totals
    }
    private val runningTotals = mutableMapOf<String, RunningTotal>() // (total, count)

}