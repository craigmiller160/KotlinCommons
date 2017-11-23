package io.craigmiller160.kotlin.commons.collection

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertNotNull

class CollectionUtilsTest {

    @Test
    fun testSequenceTransform(){
        val list = mutableListOf("One", "Two", "Three", "Four").asSequence()
        val result = list.transform { item -> "${item}A" }

        assertNotNull(result, "Result list is null")

        val itr = result.iterator()
        assertEquals("OneA", itr.next(), "Result list first item is wrong")
        assertEquals("TwoA", itr.next(), "Result list second item is wrong")
        assertEquals("ThreeA", itr.next(), "Result list third item is wrong")
        assertEquals("FourA", itr.next(), "Result list fourth item is wrong")
    }

    @Test
    fun testIterableTransform(){
        val list = mutableListOf("One", "Two", "Three", "Four")
        val result = list.transform { item -> "${item}A" }

        assertNotNull(result, "Result list is null")
        assertEquals(4, result.size, "Result list is wrong size")
        assertEquals("OneA", result[0], "Result list first item is wrong")
        assertEquals("TwoA", result[1], "Result list second item is wrong")
        assertEquals("ThreeA", result[2], "Result list third item is wrong")
        assertEquals("FourA", result[3], "Result list fourth item is wrong")
    }

}