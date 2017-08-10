package io.craigmiller160.kotlin.commons.jdbc

import java.sql.ResultSet

fun ResultSet.itr(): Iterable<ResultSetRecord>{
    return ResultSetItr(this)
}

fun <R> ResultSet.useItr(block: (rs: Iterable<ResultSetRecord>) -> R): R{
    return this.use { block(ResultSetItr(this)) }
}

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

    //TODO make the getters operator functions

    fun get(name: String): Any{
        val index = nameIndexMap[name.toUpperCase()] ?: throw NoSuchElementException("No record in ResultSet for column name $name")
        return values[index - 1]
    }

    fun get(index: Int): Any{
        if(index > values.size || index == 0) throw NoSuchElementException("No record in ResultSet for column index $index")
        return values[index - 1]
    }

}