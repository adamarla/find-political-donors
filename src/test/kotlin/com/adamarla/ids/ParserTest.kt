package com.adamarla.ids

import com.adamarla.ids.parse.FECRecordParser
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
                #SA01251735122|1141239|||2012520171368850783""".trimMargin("#"),
            "By Fields" to """
                #C00177436|N|M2|P|201702039042410894|15|IND|DEEHAN, WILLIAM N|ALPHARETTA|
                #GA|300047357|UNUM|SVP, SALES, CL|01312017|384||PR2283873845050|1147350||
                #P/R DEDUCTION (S192.00 BI-WEEKLY)|4020820171370029337""".trimMargin("#"))


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

    @Test
    fun parseCheckByField() {
        raw["By Fields"]?.let { text ->
            fecRecordParser.parse(text.replace("\n", ""))?.let {
                Assert.assertEquals("C00177436", it.recipientId)
                Assert.assertEquals("30004", it.contributorZip)
                Assert.assertEquals("01312017", it.transactionDate)
                Assert.assertEquals(384, it.amount)
            }
        }
    }

}