package com.adamarla.ids.process

import com.adamarla.ids.data.ByZip
import com.adamarla.ids.data.Contribution

/**
 * Created by adamarla on 10/25/17.
 */
class ByZipAggregator: Aggregator<ByZip>("medianvals_by_zip.txt") {

    override fun compositeKey(contribution: Contribution) =
            Pair(contribution.recipientId, contribution.contributorZip!!)

    override val sortedRecords: List<ByZip>
        get() = records.toList()

    override fun add(contribution: Contribution) {
        contribution.contributorZip?.let {
            val totals = updateTotals(contribution.amount, compositeKey(contribution))
            records.add(ByZip(contribution.recipientId, it,
                    Math.round((totals.amount / totals.count).toFloat()), totals.count, totals.amount))
        }
    }

    private val records = mutableListOf<ByZip>()

}

