package io.craigmiller160.kotlin.commons.jdbc

import java.sql.Connection
import java.sql.PreparedStatement
import java.sql.ResultSet
import kotlin.properties.ReadWriteProperty
import kotlin.reflect.KProperty

var Connection.quickTimeout: Int by FieldProperty(0)

fun Connection.quickQuery(sql: String, vararg params: Any, block: (rs: ResultSet) -> Unit){
    this.prepareStatement(sql).use { stmt ->
        stmt.queryTimeout = this.quickTimeout
        params.forEachIndexed { i,p -> stmt.setObject(i + 1 ,p) }
        stmt.executeQuery().use { rs -> while(rs.next()){ block(rs) } }
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

private class FieldProperty<T>(defaultValue: T) : ReadWriteProperty<Connection,T>{

    private var backingField = defaultValue

    override fun getValue(thisRef: Connection, property: KProperty<*>): T {
        return backingField
    }

    override fun setValue(thisRef: Connection, property: KProperty<*>, value: T) {
        this.backingField = value
    }
}