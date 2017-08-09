package io.craigmiller160.kotlin.commons.io

import java.io.InputStream
import java.util.zip.ZipEntry
import java.util.zip.ZipInputStream

/**
 * Find a particular entry in the ZipInputStream,
 * and return the stream keyed to that entry.
 * If a matching entry wasn't found, null is
 * returned.
 *
 * All entries are safely closed after being checked
 * by the function argument.
 *
 * @accept the function that determines if the correct
 *          entry has been found.
 * @return the stream keyed to the entry being searched
 *          for, or null if no match was found.
 * @throws IOException if an IO error occurs.
 */
fun ZipInputStream.findEntry(accept: (entry: ZipEntry) -> Boolean): InputStream?{ //TODO need to test that the entry doesn't get closed before the stream is returned
    for(entry in this){
        if(accept(entry)){
            return this
        }
    }

    return null
}

/**
 * Execute an operation on each entry of the
 * ZipInputStream, using the function block
 * parameter. Each entry is safely closed
 * after running the function.
 *
 * @param block the function block to run on
 *          each entry.
 * @throws IOException if an IO error occurs.
 */
fun ZipInputStream.forEachEntry(block: (entry: ZipEntry, stream: InputStream) -> Unit) {
    for(entry in this){
        block(entry, this)
    }
}

/**
 * Iterate over all ZipEntry elements in
 * this ZipInputStream. Each entry is closed
 * before moving on to the next one.
 *
 * @return an Iterator of ZipEntry elements.
 */
operator fun ZipInputStream.iterator() = object : Iterator<ZipEntry> {
    var next: ZipEntry? = null

    override fun hasNext(): Boolean {
        if(next != null) closeEntry()
        next = nextEntry
        return next != null
    }

    override fun next(): ZipEntry {
        return next ?: throw NoSuchElementException("Next ZipEntry not available")
    }
}