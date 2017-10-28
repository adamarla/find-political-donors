package com.adamarla.ids.process

import com.adamarla.ids.data.ByDate
import com.adamarla.ids.data.Contribution

/**
 * Created by adamarla on 10/25/17.
 */
class ByDateAggregator: Aggregator<ByDate>("medial_val_bydate.txt") {

    override fun compositeKey(contribution: Contribution) =
            Pair(contribution.recipientId, contribution.transactionDate!!)

    override val sortedRecords: List<ByDate>
        get() = this.runningTotals.keys.toList()
                .sortedWith(compareBy<Pair<String, String>> { it.first }.thenBy { toYearMonthDate(it.second) })
                .map { compositeKey ->
                        val (count, amount) = runningTotals[compositeKey]!!
                        ByDate(compositeKey.first, compositeKey.second, Math.round(amount/count.toFloat()), count, amount) }

    override fun add(contribution: Contribution) {
        contribution.transactionDate?.let {
            updateTotals(contribution.amount, compositeKey(contribution))
        }
    }

    private val toYearMonthDate
            get() = { ddMMyyyy: String -> "${ddMMyyyy.substring(4)}${ddMMyyyy.substring(0..3)}" }
}

