package io.craigmiller160.kotlin.commons.jdbc

import io.craigmiller160.kotlin.commons.extension.ExtensionProperty
import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

var Connection.quickTimeout: Int by ExtensionProperty(0)

fun Connection.quickQuery(sql: String, vararg params: Any, block: (rs: ResultSet) -> Unit){
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        params.forEachIndexed { i,p -> stmt.setObject(i + 1, p) }
        stmt.executeQuery().use { rs -> while(rs.next()){ block(rs) } }
    }
}

fun <R> Connection.quickQueryItr(sql: String, vararg params: Any, block: (rs: Iterable<ResultSetRecord>) -> R): R{
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        params.forEachIndexed { i, p -> stmt.setObject(i + 1, p) }
        return stmt.executeQuery().useItr(block)
    }
}

fun Connection.quickUpdate(sql: String, vararg params: Any): Int{
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        params.forEachIndexed { i,p -> stmt.setObject(i + 1, p) }
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

class BatchStmt internal constructor(val stmt: PreparedStatement) {
    fun addBatch(vararg params: Any){
        params.forEachIndexed { i,p -> stmt.setObject(i + 1, p) }
        stmt.addBatch()
    }
}