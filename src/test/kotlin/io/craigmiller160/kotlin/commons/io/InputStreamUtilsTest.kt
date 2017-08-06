package io.craigmiller160.kotlin.commons.io

import org.junit.Test
import kotlin.test.assertEquals

class InputStreamUtilsTest {

    private val text = "Hello World"

    @Test
    fun testReadText(){
        val stream = text.byteInputStream()
        val result = stream.readText()
        assertEquals(text, result, "Wrong text produced by function")
    }

    @Test
    fun testUseReadText(){
        val stream = text.byteInputStream()
        val result = stream.useReadText()
        assertEquals(text, result, "Wrong text produced by function")
    }

}