package com.adamarla.ids

import com.adamarla.ids.parse.FECRecordParser
import com.adamarla.ids.parse.IContributionRecordParser
import com.adamarla.ids.process.ByDateAggregator
import com.adamarla.ids.process.ByZipAggregator
import com.adamarla.ids.process.ContributionsDistiller
import java.io.File

fun main(args: Array<String>) {

    val bufferSize = if (args.size > 2 && args[1].toInt() > 0) args[1].toInt() else 1024

    val parser: IContributionRecordParser = FECRecordParser()
    val distiller = ContributionsDistiller(parser)
    distiller.aggregators + ByDateAggregator()
    distiller.aggregators + ByZipAggregator()

    val reader = File(args[0]).resolve("itcont.txt").bufferedReader(bufferSize = bufferSize)
    distiller.process(reader)
    distiller.report(File(args[1]))

    println("Insights Data Engineering!")
}

