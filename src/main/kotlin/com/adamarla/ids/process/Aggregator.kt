package com.adamarla.ids.process

import com.adamarla.ids.data.Contribution
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

    fun medianValue(list: List<Int>): Int {
        val sorted = list.sorted()
        return if (sorted.size % 2 == 0) Math.round(((sorted[sorted.size/2]+sorted[sorted.size/2-1])*1f)/2)
        else sorted[sorted.size/2]
    }

    protected fun updateAmounts(amount: Int, key: Pair<String, String>): List<Int> {
        if (amounts[key] == null)
            amounts[key] = mutableListOf()
        amounts[key]?.add(amount)
        return amounts[key]!!
    }
    protected val amounts = mutableMapOf<Pair<String, String>, MutableList<Int>>()

}