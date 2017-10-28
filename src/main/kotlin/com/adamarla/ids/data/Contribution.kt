package com.adamarla.ids.data

/**
 * Created by adamarla on 10/25/17.
 */

data class Contribution(val recipientId: String, val contributorZip: String? = null,
                        val transactionDate: String? = null, val amount: Int)