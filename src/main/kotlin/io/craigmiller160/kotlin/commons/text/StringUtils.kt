package io.craigmiller160.kotlin.commons.text

fun String.isNumeric(): Boolean = try{ this.toLong(); true } catch(e: NumberFormatException){ false }