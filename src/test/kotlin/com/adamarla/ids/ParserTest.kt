package com.adamarla.ids

import com.adamarla.ids.data.FECRecordParser
import org.junit.After
import org.junit.Assert
import org.junit.Before
import org.junit.Test

/**
 * Created by adamarla on 10/25/17.
 */
class ParserTest {

    val fecRecordParser = FECRecordParser()

    @Before
    fun setUp() {

    }

    @After
    fun tearDown() {

    }

    val raw = mapOf(
            "Rubbish" to """
                #C00629618|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES""",
            "Invalid" to """
                #C00629618|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES|
                #CA|90017|PRINCIPAL|DOUBLE NICKEL ADVISORS|01032017|40|H6CA34245|
                #SA01251735122|1141239|||2012520171368850783""".trimMargin("#"),
            "Valid" to """
                #C00629618|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES|
                #CA|90017|PRINCIPAL|DOUBLE NICKEL ADVISORS|01032017|40||
                #SA01251735122|1141239|||2012520171368850783""".trimMargin("#"),
            "Bad Zip" to """
                #C00629618|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES|
                #CA|9017|PRINCIPAL|DOUBLE NICKEL ADVISORS|01032017|40||
                #SA01251735122|1141239|||2012520171368850783""".trimMargin("#"),
            "Bad Date" to """"
                #C00629618|N|TER|P|201701230300133512|15C|IND|PEREZ, JOHN A|LOS ANGELES|
                #CA|90017|PRINCIPAL|DOUBLE NICKEL ADVISORS|71032017|40||
                #SA01251735122|1141239|||2012520171368850783""".trimMargin("#"))



    @Test
    fun parseRubbish() {
        raw["Rubbish"]?.let {
            Assert.assertNull(fecRecordParser.parse(it.replace("\n", "")))
        }
    }

    @Test
    fun parseInvalid() {
        raw["Invalid"]?.let {
            Assert.assertNull(fecRecordParser.parse(it.replace("\n", "")))
        }
    }

    @Test
    fun parseValid() {
        raw["Valid"]?.let {
            Assert.assertNotNull(fecRecordParser.parse(it.replace("\n", "")))
        }
    }

    @Test
    fun parseBadZip() {
        raw["Bad Zip"]?.let {
            val contribution = fecRecordParser.parse(it.replace("\n", ""))
            Assert.assertNotNull(contribution)
            Assert.assertNull(contribution?.contributorZip)
        }
    }

    @Test
    fun parseBadDate() {
        raw["Bad Date"]?.let {
            val contribution = fecRecordParser.parse(it.replace("\n", ""))
            Assert.assertNotNull(contribution)
            Assert.assertNull(contribution?.transactionDate)
        }
    }

}