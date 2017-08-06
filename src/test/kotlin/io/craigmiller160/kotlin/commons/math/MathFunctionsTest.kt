package io.craigmiller160.kotlin.commons.math

import org.junit.Test
import kotlin.test.assertEquals

class MathFunctionsTest {

    @Test
    fun testFactorial(){
        val result = factorial(5)
        assertEquals(120, result, "Wrong result of factorial")
    }

}