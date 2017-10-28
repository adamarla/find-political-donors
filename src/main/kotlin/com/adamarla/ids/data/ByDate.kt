package com.adamarla.ids.data

data class ByDate(val recipientId: String, val transactionDate: String,
                  val runningMedian: Int, val runningCount: Int, val runningAmount: Int) {
    override fun toString() = "${component1()}|${component2()}|${component3()}|${component4()}|${component5()}"
}