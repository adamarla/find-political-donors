package com.adamarla.ids

import com.adamarla.ids.data.FECRecordParser
import com.adamarla.ids.data.IContributionRecordParser
import com.adamarla.ids.processors.ByDateAggregator
import com.adamarla.ids.processors.ByZipAggregator
import com.adamarla.ids.processors.ContributionsDistiller
import java.io.File

fun main(args: Array<String>) {

    val bufferSize = if (args.size > 2 && args[1].toInt() > 0) args[1].toInt() else 1024

    val parser: IContributionRecordParser= FECRecordParser()
    val distiller = ContributionsDistiller(parser)
    distiller.aggregators + ByDateAggregator()
    distiller.aggregators + ByZipAggregator()

    val reader = File(args[0]).resolve("itcont.txt").bufferedReader(bufferSize = bufferSize)
    distiller.process(reader)

    File(args[1]).resolve("medianvals_by_zip.txt").bufferedWriter()
    println("Insights Data Science!")
}

