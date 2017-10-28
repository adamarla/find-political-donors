package com.adamarla.ids.data

data class ByZip(val recipientId: String, val contributorZip: String,
                 val runningMedian: Int, val runningCount: Int, val runningAmount: Int) {
    override fun toString() = "${component1()}|${component2()}|${component3()}|${component4()}|${component5()}"
}