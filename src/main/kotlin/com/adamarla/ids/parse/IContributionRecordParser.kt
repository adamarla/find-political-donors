package com.adamarla.ids.parse

import com.adamarla.ids.data.Contribution

/**
 * Created by adamarla on 10/26/17.
 */
interface IContributionRecordParser {

    fun parse(rawData: String): Contribution?

}