package com.adamarla.ids.data

/**
 * Created by adamarla on 10/26/17.
 */
interface IContributionRecordParser {

    fun parse(rawData: String): Contribution?

}