package io.craigmiller160.kotlin.commons.text

import org.junit.Test
import kotlin.test.assertFalse
import kotlin.test.assertTrue

class StringUtilsTest {

    @Test
    fun testIsNumericTrue(){
        val text = "12345"
        assertTrue("The text is not numeric") { text.isNumeric() }
    }

    fun testIsNumericFalse(){
        val text = "Hello World"
        assertFalse("The text is numeric") { text.isNumeric() }
    }

}