package com.adamarla.ids.data

/**
 * Created by adamarla on 10/25/17.
 */

data class Contribution(val recipientId: String, val contributorZip: String? = null,
                        val transactionDate: String? = null, val amount: Int)

data class ByZip(val recipientId: String, val contributorZip: String,
                       val runningMedian: Int, val runningCount: Int, val runningAmount: Int)

data class ByDate(val recipientId: String, val transactionDate: String,
                       val runningMedian: Int, val runningCount: Int, val runningAmount: Int)

