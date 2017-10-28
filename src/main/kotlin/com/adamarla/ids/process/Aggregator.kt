package com.adamarla.ids.process

import com.adamarla.ids.data.Contribution
import com.adamarla.ids.data.RunningTotal
import java.io.BufferedWriter

abstract class Aggregator<out T>(val fileName: String) {

    protected abstract fun compositeKey(contribution: Contribution): Pair<String, String>

    abstract val sortedRecords: List<T>

    abstract fun add(contribution: Contribution)

    fun report(writer: BufferedWriter) {
        sortedRecords.map {
            writer.write(it.toString())
            writer.newLine()
        }
        writer.close()
    }

    protected fun updateTotals(amount: Int, key: Pair<String, String>): RunningTotal {
        var totals = runningTotals[key] ?: RunningTotal(0, 0)
        totals += RunningTotal(1, amount)
        runningTotals[key] = totals
        return totals
    }
    protected val runningTotals = mutableMapOf<Pair<String, String>, RunningTotal>() // (total, count)

}