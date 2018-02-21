package io.craigmiller160.kotlin.commons.resource

import io.craigmiller160.kotlin.commons.io.readText
import io.craigmiller160.kotlin.commons.io.useReadText
import java.io.InputStream
import java.net.URL
import java.nio.charset.Charset
import java.nio.charset.StandardCharsets

/**
 * A simple group of shortcut functions to loading
 * resources from the classpath.
 */
object Resource {

    /**
     * Get a resource on the classpath as a URL.
     *
     * @param name the path to the resource.
     * @return the URL for the resource.
     */
    fun getResource(name: String): URL = Resource::class.java.classLoader.getResource(name)

    /**
     * Get a resource on the classpath as an InputStream.
     *
     * @param name the path to the resource.
     * @return the InputStream for the resource.
     */
    fun getResourceAsStream(name: String): InputStream = Resource::class.java.classLoader.getResourceAsStream(name)

    /**
     * Get a resource on the classpath as a String. This
     * expects the resource to be purely text content.
     *
     * @param name the path to the resource.
     * @charset the character encoding of the resource,
     *          defaults to UTF-8.
     * @return the String of the resource.
     */
    fun getResourceAsString(name: String, charset: Charset = StandardCharsets.UTF_8): String = getResourceAsStream(name).useReadText(charset)

}