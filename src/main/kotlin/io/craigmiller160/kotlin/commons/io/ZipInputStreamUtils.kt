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
fun ZipInputStream.findEntry(accept: (entry: ZipEntry) -> Boolean): InputStream?{
    var found = false
    var entry: ZipEntry? = null
    while({entry = this.nextEntry; entry }() != null){
        try{
            if(accept(entry as ZipEntry)){
                found = true
                return this
            }
        }
        finally{
            if(!found){
                this.closeEntry()
            }
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
    var entry: ZipEntry? = null
    while({entry = this.nextEntry; entry }() != null){
        try{
            block(entry as ZipEntry, this)
        }
        finally{
            this.closeEntry()
        }
    }
}