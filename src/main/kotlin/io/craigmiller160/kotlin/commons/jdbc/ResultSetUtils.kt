package io.craigmiller160.kotlin.commons.jdbc

import java.sql.ResultSet

/**
 * An Iterable wrapper for the ResultSet class.
 * This allows for all the Iterable functions
 * to be called on the underlying ResultSet.
 *
 * @param resultSet the ResultSet to be wrapped.
 */
class ResultSetItr(private val resultSet: ResultSet) : Iterable<ResultSet>{
    override fun iterator(): Iterator<ResultSet> {
        return object : Iterator<ResultSet>{
            override fun hasNext(): Boolean {
                return resultSet.next()
            }

            override fun next(): ResultSet {
                return resultSet
            }
        }
    }
}

/**
 * Get an Iterable version of this ResultSet
 * so that it can be iterated over using
 * the Iterable functions.
 *
 * @return an Iterable version of this ResultSet.
 */
fun ResultSet.itr(): ResultSetItr{
    return ResultSetItr(this)
}

/**
 * Execute a function block with an Iterable
 * version of this ResultSet, so that the
 * function block can iterate over it using
 * the Iterable functions. When the function
 * block is finished, the ResultSet is closed
 * and the result of the function block is
 * returned.
 *
 * @param block the function to iterate over the ResultSet.
 * @param R the return type of the function block.
 * @return the result of the function block.
 */
fun <R> ResultSet.useItr(block: (rs: ResultSetItr) -> R): R{
    return this.use { block(ResultSetItr(this)) }
}