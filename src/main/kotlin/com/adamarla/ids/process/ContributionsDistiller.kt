package com.adamarla.ids.process

import com.adamarla.ids.parse.IContributionRecordParser
import java.io.BufferedReader
import java.io.File

/**
 * Created by adamarla on 10/25/17.
 */

class ContributionsDistiller(val parser: IContributionRecordParser) {

    var aggregators = mutableListOf<Aggregator<*>>()

    fun process(reader: BufferedReader) =
        reader.readLines()
            .map { line -> parser.parse(line) }
            .filter { it != null }
            .map { contribution ->
                aggregators.map { it.add(contribution!!) }
            }

    fun report(location: File) =
        aggregators.map {
            it.report(location.resolve(it.fileName).bufferedWriter())
        }

}

