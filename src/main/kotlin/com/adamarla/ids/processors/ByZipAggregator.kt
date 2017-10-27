package com.adamarla.ids.processors

import com.adamarla.ids.data.Contribution
import java.io.BufferedWriter

/**
 * Created by adamarla on 10/25/17.
 */
class ByZipAggregator : Aggregator("median_val_byzip.txt") {
    override fun add(contribution: Contribution) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }

    override fun report(writer: BufferedWriter) {
        TODO("not implemented") //To change body of created functions use File | Settings | File Templates.
    }


}