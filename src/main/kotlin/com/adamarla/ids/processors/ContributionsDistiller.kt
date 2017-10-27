package com.adamarla.ids.processors

import com.adamarla.ids.data.Contribution
import com.adamarla.ids.data.IContributionRecordParser
import java.io.BufferedReader
import java.io.BufferedWriter
import java.io.File

/**
 * Created by adamarla on 10/25/17.
 */

class ContributionsDistiller(val parser: IContributionRecordParser) {

    lateinit var aggregators: MutableList<Aggregator>

    fun process(reader: BufferedReader) =
        reader.readLines()
            .map { line -> parser.parse(line) }
            .filter { it != null }
            .map { contribution ->
                aggregators.map { it.add(contribution!!) }
            }

    fun report(location: File) {
        aggregators.map {
            it.report(location.resolve(it.fileName).bufferedWriter())
        }
    }

}

abstract class Aggregator(val fileName: String) {

    abstract fun add(contribution: Contribution)

    abstract fun report(writer: BufferedWriter)

}


