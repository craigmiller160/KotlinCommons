package io.craigmiller160.kotlin.commons.resource

import java.io.InputStream
import java.net.URL

object Resource {

    fun getResource(name: String): URL = Resource::class.java.classLoader.getResource(name)

    fun getResourceAsStream(name: String): InputStream = Resource::class.java.classLoader.getResourceAsStream(name)

}