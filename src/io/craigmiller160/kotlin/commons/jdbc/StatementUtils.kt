package io.craigmiller160.kotlin.commons.jdbc

import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Statement

/**
 * Perform a quick SQL update with the provided
 * query String.
 *
 * @param query the SQL for an update.
 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
 *         or (2) 0 for SQL statements that return nothing.
 */
fun Statement.quickUpdate(query: String): Int{
    return this.executeUpdate(query)
}

/**
 * Perform a quick SQL update with the provided
 * query String, and close this statement after
 * it is finished.
 *
 * @param query the SQL for an update.
 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
 *         or (2) 0 for SQL statements that return nothing.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun Statement.useQuickUpdate(query: String): Int{
    return this.use { this.executeUpdate(query) }
}

/**
 * Perform a quick SQL query with the provided
 * query String, and execute the function block
 * provided to manipulate the ResultSet. The result
 * of the function handling the ResultSet is returned.
 *
 * @param query the SQL for a query.
 * @param block the function to handle the ResultSet.
 * @param R the return type of the function block.
 * @return the result of the function block.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun <R> Statement.quickQuery(query: String, block: (rs: ResultSet) -> R): R{
    return block(this.executeQuery(query))
}

/**
 * Perform a quick SQL query with the provided
 * query String, and execute the function block
 * provided to manipulate the ResultSet. The
 * Statement is closed upon completion of the
 * function block, and the result of the block
 * is returned.
 *
 * @param query the SQL for a query.
 * @param block the function to handle the ResultSet.
 * @param R the return type of the function block.
 * @return the result of the function block.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun <R> Statement.useQuickQuery(query: String, block: (rs: ResultSet) -> R): R{
    return this.use { block(this.executeQuery(query)) }
}

/**
 * Perform a quick SQL query with the provided query String,
 * and execute the function block provided to manipulate
 * the ResultSet. An Iterable version of the ResultSet is
 * provided to the function block so that the functions
 * of Iterable can be used on it. The Statement and the
 * ResultSet are closed upon completion of the function
 * block, and the block's result is returned.
 *
 * @param query the SQL for a query.
 * @param block the function to handle the Iterable ResultSet.
 * @param R the return type of the function block.
 * @return the result of the function block.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun <R> Statement.useQuickQueryItr(query: String, block: (rs: ResultSetItr) -> R): R{
    return this.use { this.executeQuery(query).useItr(block) }
}

/**
 * Perform a quick SQL update. If there are parameters
 * passed in, they will be applied to the PreparedStatement
 * in the order they are provided. Upon completion, the
 * Statement is closed.
 *
 * Please note that this function relies on PreparedStatement.setObject(Int,Any),
 * so if the JDBC Driver being used can't handle dynamically assigning
 * types to the parameters this will fail.
 *
 * @param params the optional parameters for the SQL statement.
 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
 *         or (2) 0 for SQL statements that return nothing.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun PreparedStatement.useQuickUpdate(vararg params: Any): Int{
    return this.use { this.quickUpdate(*params) }
}

/**
 * Perform a quick SQL update. If there are parameters
 * passed in, they will be applied to the PreparedStatement
 * in the order they are provided.
 *
 * Please note that this function relies on PreparedStatement.setObject(Int,Any),
 * so if the JDBC Driver being used can't handle dynamically assigning
 * types to the parameters this will fail.
 *
 * @param params the optional parameters for the SQL statement.
 * @return either (1) the row count for SQL Data Manipulation Language (DML) statements
 *         or (2) 0 for SQL statements that return nothing.
 * @throws SQLException if there is an error, either with the
 *         database or the statement.
 */
fun PreparedStatement.quickUpdate(vararg params: Any): Int{
    addParams(this, *params)
    return this.executeUpdate()
}

/**
 * Perform a quick SQL query. If there are parameters
 * passed in, they will be applied to the PreparedStatement
 * in the order they are provided. A ResultSet is returned.
 *
 * Please note that this function relies on PreparedStatement.setObject(Int,Any),
 * so if the JDBC Driver being used can't handle dynamically assigning
 * types to the parameters this will fail.
 *
 * @param params the optional parameters for the SQL statement.
 * @return the ResultSet.
 */
fun PreparedStatement.quickQuery(vararg params: Any): ResultSet {
    addParams(this, *params)
    return this.executeQuery()
}

/**
 * Perform a quick SQL query. If there are parameters
 * passed in, they will be applied to the PreparedStatement
 * in the order they are provided. The function block is
 * executed to handle the ResultSet returned by the query,
 * and the result of the function is returned.
 *
 * @param params the optional parameters for the SQL statement.
 * @return the result of the function block.
 */
fun <R> PreparedStatement.useQuickQuery(vararg params: Any, block: (rs: ResultSet) -> R): R {
    return this.use { block(this.quickQuery(*params)) }
}

/**
 * Perform a quick SQL query. If there are parameters
 * passed in, they will be applied to the PreparedStatement
 * in the order they are provided. The function block is
 * executed to handle an Iterable version of the ResultSet,
 * and the result of the function is returned.
 *
 * @param params the optional parameters for the SQL statement.
 * @return the result of the function block.
 */
fun <R> PreparedStatement.useQuickQueryItr(vararg params: Any, block: (rs: ResultSetItr) -> R): R {
    return this.use { this.quickQuery(*params).useItr { block(it) } }
}

private fun addParams(stmt: PreparedStatement, vararg params: Any){
    if(params.isNotEmpty()){
        (0..params.size - 1).forEach { stmt.setObject(it + 1, params[it]) }
    }
}