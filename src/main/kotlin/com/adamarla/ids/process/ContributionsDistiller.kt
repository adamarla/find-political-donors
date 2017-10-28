package com.adamarla.ids.process

import com.adamarla.ids.parse.IContributionRecordParser
import java.io.File

/**
 * Created by adamarla on 10/25/17.
 */

class ContributionsDistiller(val parser: IContributionRecordParser) {

    var aggregators = mutableListOf<Aggregator<*>>()

    fun process(location: File, bufferSize: Int?) =
        location.resolve("itcont.txt").bufferedReader(bufferSize = bufferSize?: DEFAULT_BUFFER_SIZE).readLines()
            .map { line -> parser.parse(line) }
            .filter { it != null } // invalid inputs filtered out here
            .map { contribution ->
                aggregators.map { it.add(contribution!!) }
            }

    fun report(location: File) =
        aggregators.map {
            it.report(location.resolve(it.fileName).bufferedWriter())
        }

}

