package io.craigmiller160.kotlin.commons.math

import org.junit.Test
import kotlin.test.assertEquals

class MathFunctionsTest {

    @Test
    fun testFactorial(){
        val result = factorial(5)
        assertEquals(120, result, "Wrong result of factorial")
    }

    @Test
    fun testNumCombinations(){
        val result = numCombinations(8, 2)
        assertEquals(256, result, "Wrong result of numCombinations")
    }

}