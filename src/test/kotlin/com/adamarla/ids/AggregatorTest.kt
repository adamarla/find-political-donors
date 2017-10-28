package com.adamarla.ids

import com.adamarla.ids.data.ByDate
import com.adamarla.ids.data.ByZip
import com.adamarla.ids.data.Contribution
import com.adamarla.ids.process.ByDateAggregator
import com.adamarla.ids.process.ByZipAggregator
import org.junit.Assert
import org.junit.Test

/**
 * Created by adamarla on 10/25/17.
 */
class AggregatorTest {

    private val contributions = listOf(
            Contribution("C00629618", "90017", "01032017", 40),
            Contribution("C00629618", "90017", null, 140), // bad date
            Contribution("C00629618", null, "11032017", 10), // bad zip
            Contribution("C00233444", "10018", "01032017", 750),
            Contribution("C00233444", null, null, 140), // bad date bad zip
            Contribution("C00233444", null, "03262017", 200), // bad zip
            Contribution("C00233444", "90210", "03262017", 100),
            Contribution("C00629618", "90017", "01032017", 90)
    )

    private val expectedAggregateByZip = arrayOf(
            ByZip("C00629618", "90017", 40, 1, 40),
            ByZip("C00629618", "90017", 90, 2, 180),
            ByZip("C00233444", "10018", 750, 1, 750),
            ByZip("C00233444", "90210", 100, 1, 100),
            ByZip("C00629618", "90017", 90, 3, 270)
    )

    private val expectedAggregateByDate = arrayOf(
            ByDate("C00233444", "01032017", 750, 1, 750),
            ByDate("C00233444", "03262017", 150, 2, 300),
            ByDate("C00629618", "01032017", 65, 2, 130),
            ByDate("C00629618", "11032017", 10, 1, 10)
    )

    @Test
    fun aggregateByZip() {
        val byZipAggregator = ByZipAggregator()
        Assert.assertNotNull(byZipAggregator)
        contributions.map { byZipAggregator.add(it) }
        Assert.assertArrayEquals(byZipAggregator.sortedRecords.toTypedArray(), expectedAggregateByZip)
    }

    @Test
    fun aggregateByDate() {
        val byDateAggregator = ByDateAggregator()
        Assert.assertNotNull(byDateAggregator)
        contributions.map { byDateAggregator.add(it) }
        Assert.assertArrayEquals(byDateAggregator.sortedRecords.toTypedArray(), expectedAggregateByDate)
    }

}