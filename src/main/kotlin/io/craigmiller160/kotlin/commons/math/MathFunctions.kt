package io.craigmiller160.kotlin.commons.math

fun factorial(value: Long): Long = factorialRecursive(value, value)

private fun factorialRecursive(factor: Long, value: Long): Long{
    val newValue = value - 1
    return if(newValue > 0) factorialRecursive(factor * newValue, newValue) else factor
}

fun numCombinations(numRepetition: Int, numChoices: Int): Long = Math.pow(numChoices.toDouble(), numRepetition.toDouble()).toLong()