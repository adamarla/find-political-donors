package com.adamarla.ids

import com.adamarla.ids.data.Contribution
import com.adamarla.ids.process.ByDateAggregator
import org.junit.Assert
import org.junit.Test

/**
 * Created by adamarla on 10/25/17.
 */
class AggregatorTest {

    private val contributions = listOf(
            Contribution("C00629618", "90017", "01032017", 40),
            Contribution("C00629618", "90210", "21082017", 140),
            Contribution("C00629618", "07310", "11032017", 10),
            Contribution("C00342344", "10018", "01032017", 40),
            Contribution("C00234343", "10018", "21082017", 140),
            Contribution("C00629618", "90210", "11032017", 10),
            Contribution("C00233444", "10019", "13062017", 200)
    )

    @Test
    fun aggregateByDate() {
        val byDateAggregator = ByDateAggregator()
        Assert.assertNotNull(byDateAggregator)
        contributions.map { byDateAggregator.add(it) }
        Assert.assertTrue(byDateAggregator.count == 4)
    }

    @Test
    fun aggregateByZip() {
        val byDateAggregator = ByDateAggregator()
        Assert.assertNotNull(byDateAggregator)
        contributions.map { byDateAggregator.add(it) }
        Assert.assertTrue(byDateAggregator.count == 4)
    }

}