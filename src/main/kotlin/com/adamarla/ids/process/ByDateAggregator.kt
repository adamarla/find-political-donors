package com.adamarla.ids.process

import com.adamarla.ids.data.ByDate
import com.adamarla.ids.data.Contribution

/**
 * Created by adamarla on 10/25/17.
 */
class ByDateAggregator: Aggregator<ByDate>("medial_val_bydate.txt") {

    override fun compositeKey(contribution: Contribution): String =
            "${contribution.recipientId}-${contribution.transactionDate}"

    override fun add(contribution: Contribution) {
        contribution.transactionDate?.let {
            val totals = updateTotals(contribution.amount, compositeKey(contribution))
            records.add(ByDate(contribution.recipientId, it,
                    Math.round((totals.amount / totals.count).toFloat()), totals.count, totals.amount))
        }
    }

    override fun sort(list: MutableList<ByDate>): List<ByDate> =
        list.sortedWith( compareBy<ByDate>{ it.recipientId } .thenBy { it.yyyyMMdd })

}

