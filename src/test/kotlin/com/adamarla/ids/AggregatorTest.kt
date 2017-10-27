package com.adamarla.ids

import com.adamarla.ids.data.Contribution
import com.adamarla.ids.processors.ByDateAggregator
import com.adamarla.ids.processors.ByZipAggregator
import org.junit.Assert
import org.junit.Test

/**
 * Created by adamarla on 10/25/17.
 */
class AggregatorTest {

    private val inputs = listOf(
            Contribution("A", "10019", "20120902", 10),
            Contribution("A", "10019", "20120902", 10),
            Contribution("A", "10019", "20120902", 10),
            Contribution("A", "10019", "20120902", 10),
            Contribution("A", "10019", "20120902", 10)
    )

    @Test
    fun processByDate() {
        Assert.assertNotNull(ByDateAggregator())
    }

    @Test
    fun processByZip() {
        Assert.assertNotNull(ByZipAggregator())
    }

}