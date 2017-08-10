package io.craigmiller160.kotlin.commons.lang

fun String.isNumeric(): Boolean{
    return try{ this.toLong(); true } catch(e: NumberFormatException){ false }
}