package com.adamarla.ids

import com.adamarla.ids.parse.FECRecordParser
import com.adamarla.ids.parse.IContributionRecordParser
import com.adamarla.ids.process.ByDateAggregator
import com.adamarla.ids.process.ByZipAggregator
import com.adamarla.ids.process.ContributionsDistiller
import java.io.File

fun main(args: Array<String>) {

    if (args.size < 2) {
        printUsage()
        return
    }

    val parser: IContributionRecordParser = FECRecordParser()
    val distiller = ContributionsDistiller(parser)
    distiller.aggregators.add(ByDateAggregator())
    distiller.aggregators.add(ByZipAggregator())

    distiller.process(File(args[0]))
    distiller.report(File(args[1]))
}

fun printUsage() {
    println("""To run, specify input and output directories as
        |arguments to pass to the to the gradle script.
        |
        |gradle run -PinputDir=input -PoutputDir=output
    """.trimMargin())
}