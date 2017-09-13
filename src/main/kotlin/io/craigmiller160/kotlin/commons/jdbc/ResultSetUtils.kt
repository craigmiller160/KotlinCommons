package io.craigmiller160.kotlin.commons.jdbc

import java.sql.ResultSet
import kotlin.reflect.KClass

fun ResultSet.itr(): Iterable<ResultSetRecord> = ResultSetItr(this)

fun <R> ResultSet.useItr(block: (rs: Iterable<ResultSetRecord>) -> R): R = this.use { block(ResultSetItr(this)) }

class ResultSetItr internal constructor(private val resultSet: ResultSet) : Iterable<ResultSetRecord>{

    private val nameIndexMap = HashMap<String,Int>()

    init {
        val metaData = resultSet.metaData
        (1..metaData.columnCount).forEach { index ->
            val colName = metaData.getColumnName(index)
            nameIndexMap += colName.toUpperCase() to index
        }
    }

    override fun iterator(): Iterator<ResultSetRecord> {
        return object : Iterator<ResultSetRecord> {
            override fun hasNext(): Boolean {
                return resultSet.next()
            }

            override fun next(): ResultSetRecord {
                return ResultSetRecord(resultSet, nameIndexMap)
            }
        }
    }
}

data class ResultSetRecord internal constructor(private val resultSet: ResultSet, private val nameIndexMap: Map<String,Int>) {

    private val values = ArrayList<Any>()

    init {
        (1..nameIndexMap.size).forEach { index -> values += resultSet.getObject(index) }
    }

    operator fun get(name: String): Any{
        val index = nameIndexMap[name.toUpperCase()] ?: throw NoSuchElementException("No record in ResultSet for column name $name")
        return values[index - 1]
    }

    operator fun get(index: Int): Any{
        if(index > values.size || index == 0) throw NoSuchElementException("No record in ResultSet for column index $index")
        return values[index - 1]
    }

    operator fun <T : Any> get(name: String, type: KClass<T>): T{
        val value = get(name)
        return if(type.isInstance(value)) value as T else throw NoSuchElementException("No record in ResultSet for column name $name and type ${type.qualifiedName}")
    }

    operator fun <T : Any> get(index: Int, type: KClass<T>): T{
        val value = get(index)
        return if(type.isInstance(value)) value as T else throw NoSuchElementException("No record in ResultSet for column index $index and type ${type.qualifiedName}")
    }

}