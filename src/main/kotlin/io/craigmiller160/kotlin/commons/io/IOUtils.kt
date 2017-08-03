package io.craigmiller160.kotlin.commons.io

import java.io.InputStream
import java.nio.charset.Charset

/**
 * Convert the InputStream completely into
 * a String.
 *
 * @param charset the character set to use.
 * @return a String with the contents of the stream.
 * @throws IOException if an IO error occurs.
 */
fun InputStream.asString(charset: Charset = Charsets.UTF_8): String{
    return this.bufferedReader(charset).readText()
}

/**
 * Convert the InputStream completely into
 * a String. The stream is safely closed after
 * this function completes.
 *
 * @param charset the character set to use.
 * @return a String with the contents of the stream.
 * @throws IOException if an IO error occurs.
 */
fun InputStream.useAsString(charset: Charset = Charsets.UTF_8): String{
    return this.use { it.bufferedReader(charset).readText() }
}