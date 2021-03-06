package io.craigmiller160.kotlin.commons.jdbc

import io.craigmiller160.kotlin.commons.extension.ExtensionProperty
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import java.sql.Types

var Connection.quickTimeout: Int by ExtensionProperty(0)

fun Connection.quickQuery(sql: String, vararg params: Any?, block: (rs: ResultSet) -> Unit){
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        handleParams(stmt, *params)
        stmt.executeQuery().use { rs -> while(rs.next()){ block(rs) } }
    }
}

fun <R> Connection.quickQueryItr(sql: String, vararg params: Any?, block: (rs: Iterable<ResultSetRecord>) -> R): R{
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        handleParams(stmt, *params)
        return stmt.executeQuery().useItr(block)
    }
}

fun Connection.quickUpdate(sql: String, vararg params: Any?): Int{
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        handleParams(stmt, *params)
        return stmt.executeUpdate()
    }
}

fun Connection.quickBatch(sql: String, block: BatchStmt.() -> Unit): IntArray{
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        BatchStmt(stmt).block()
        return stmt.executeBatch()
    }
}

//TODO add unit tests for these
fun Connection.parseAndExecuteScript(script: String): IntArray{
    return parseAndExecuteScript(script.lineSequence())
}

fun Connection.parseAndExecuteScript(lines: Sequence<String>): IntArray{
    val queries = ArrayList<String>()
    var builder = StringBuilder()
    var delimiter = ";"
    lines.forEach { line ->
        if(!line.trim().isBlank()){
            if(line.trim().startsWith("delimiter", true)){
                delimiter = line.toUpperCase().replace("DELIMITER", "").trim()
            }
            else if(line.trim().endsWith(delimiter)){
                builder.appendln(line.trim().replace(delimiter, ""))
                queries += builder.toString()
                builder = StringBuilder()
            }
            else{
                builder.appendln(line)
            }
        }
    }

    val results = IntArray(queries.size)

    queries.forEachIndexed { index, query -> this.prepareStatement(query).use { stmt -> results[index] = stmt.executeUpdate() } }
    return results
}

class BatchStmt internal constructor(val stmt: PreparedStatement) {
    fun addBatch(vararg params: Any?){
        handleParams(stmt, *params)
        stmt.addBatch()
    }
}

internal fun handleParams(stmt: PreparedStatement, vararg params: Any?){
    var index = 0
    params.forEach { param ->
        if(param != null){
            if (param is List<*>) {
                param.forEach { value ->
                    stmt.setObject(index + 1, value)
                    index++
                }

                if (param.size > 0) {
                    index--
                }
            }
            else {
                stmt.setObject(index + 1, param)
            }
        }
        else{
            stmt.setNull(index + 1, Types.JAVA_OBJECT)
        }
        index++
    }
}