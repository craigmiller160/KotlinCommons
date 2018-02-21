package io.craigmiller160.kotlin.commons.resource

import java.io.InputStream
import java.net.URL

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

}