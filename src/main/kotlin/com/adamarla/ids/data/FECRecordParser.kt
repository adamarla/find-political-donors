package com.adamarla.ids.data

import java.time.format.DateTimeFormatter

/**
 * Created by adamarla on 10/26/17.
 */
class FECRecordParser: IContributionRecordParser {

    override fun parse(rawData: String): Contribution? {
        tokens = rawData.split('|')
        return if (isValid(tokens))
            Contribution(recipientId, contributorZip, transactionDate, amount)
        else null
    }

    private lateinit var tokens: List<String>

    private val recipientId
        get() = tokens[RECIPIENT_ID_IDX]
    private val contributorZip
        get() = if (tokens[CONTRIBUTOR_ZIP_IDX].matches(ZIP))
        tokens[CONTRIBUTOR_ZIP_IDX] else null
    private val transactionDate
        get() = try {
            DATE_FORMAT.parse(tokens[TRANSACTION_DT_IDX])
            tokens[TRANSACTION_DT_IDX]
        } catch(e: Exception) { null }
    private val amount
        get() = tokens[AMOUNT_IDX].toInt()

    fun isValid(tokens: List<String>) = (tokens.size == TOKENS_COUNT &&
                    tokens[RECIPIENT_ID_IDX].isNotBlank() &&
                    tokens[AMOUNT_IDX].toIntOrNull() != null &&
                    tokens[OTHER_ID_IDX].isBlank())

    companion object {
        private val TOKENS_COUNT = 21
        private val RECIPIENT_ID_IDX = 0
        private val CONTRIBUTOR_ZIP_IDX = 10
        private val TRANSACTION_DT_IDX = 13
        private val AMOUNT_IDX = 14
        private val OTHER_ID_IDX = 15 // Non-individual contributions
        private val DATE_FORMAT = DateTimeFormatter.ofPattern("ddMMyyyy")
        private val ZIP = Regex("""\d{5}""")
    }
}