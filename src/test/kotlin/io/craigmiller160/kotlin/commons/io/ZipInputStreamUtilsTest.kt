package io.craigmiller160.kotlin.commons.io

import org.junit.Before
import org.junit.Test
import java.io.InputStream
import java.util.zip.ZipInputStream
import kotlin.test.assertEquals
import kotlin.test.assertNotNull
import kotlin.test.assertNull

class ZipInputStreamUtilsTest {

    private lateinit var zip: InputStream

    @Before
    fun init(){
        zip = javaClass.classLoader.getResourceAsStream("io/craigmiller160/kotlin/commons/io/sample.zip")
    }

    @Test
    fun testForEachEntry(){
        ZipInputStream(zip).use { zipStream ->
            var count = 0
            zipStream.forEachEntry { entry, stream ->  println(entry.name); count++ }
            assertEquals(7, count, "Wrong number of iterations")
        }
    }

    @Test
    fun testFindEntrySuccess(){
        ZipInputStream(zip).use { zipStream ->
            val entryStream = zipStream.findEntry { "sample/" == it.name }
            assertNotNull(entryStream, "Entry Stream result was null")
        }
    }

    @Test
    fun testFindEntryFailure(){
        ZipInputStream(zip).use { zipStream ->
            val entryStream = zipStream.findEntry { "sampleABC/" == it.name }
            assertNull(entryStream, "Entry Stream result should have been null")
        }
    }

}