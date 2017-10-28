package com.adamarla.ids.process

import com.adamarla.ids.data.ByZip
import com.adamarla.ids.data.Contribution

/**
 * Created by adamarla on 10/25/17.
 */
class ByZipAggregator: Aggregator<ByZip>("median_val_byzip.txt") {

    override fun compositeKey(contribution: Contribution) =
            "${contribution.recipientId}-${contribution.contributorZip}"

    override fun add(contribution: Contribution) {
        contribution.contributorZip?.let {
            val totals = updateTotals(contribution.amount, compositeKey(contribution))
            records.add(ByZip(contribution.recipientId, it,
                    Math.round((totals.amount / totals.count).toFloat()), totals.count, totals.amount))
        }
    }

}

