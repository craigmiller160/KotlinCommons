package io.craigmiller160.kotlin.commons.jdbc

import org.junit.Test
import kotlin.test.assertEquals
import kotlin.test.assertFalse
import kotlin.test.assertNotNull
import kotlin.test.assertTrue

class ResultSetUtilsTest : JdbcTestCommons() {

    private val firstNames = listOf("Bob", "John", "Clark", "Clark")

    @Test
    fun testItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.itr().forEachIndexed { index, record ->
                recordCount++
                testRecord(index, record)
            }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertFalse("ResultSet should not be closed") { rs.isClosed }
        }
    }

    fun testRecord(index: Int, record: ResultSetRecord){
        val name = firstNames[index]
        assertEquals(name, record[2], "Wrong value for first_name with index getter")
        assertEquals(name, record[2, String::class], "Wrong value for first_name with index getter with type parameter")
        assertEquals(String::class, record[2, String::class].javaClass.kotlin, "Wrong type of value returned by index getter with type parameter")
        assertEquals(name, record["first_name"], "Wrong value for first_name with named getter")
        assertEquals(name, record["first_name", String::class], "Wrong value for first_name with named getter with type parameter")
        assertEquals(String::class, record["first_name", String::class].javaClass.kotlin, "Wrong type of value returned by named getter with type parameter")
    }

    @Test
    fun testUseItr(){
        var recordCount = 0
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id ASC")
            rs.useItr { it.forEachIndexed { index, record ->
                recordCount++
                testRecord(index, record)
            } }
            assertEquals(4, recordCount, "Wrong number of records iterated over")
            assertTrue("ResultSet should be closed") { rs.isClosed }
        }
    }

    @Test
    fun testUseItrWithReturn(){
        conn.createStatement().use { stmt ->
            val rs = stmt.executeQuery("SELECT * FROM people ORDER BY person_id")
            val result = rs.useItr { itr -> itr.maxBy { record -> record[1, Long::class] } }
            assertNotNull(result, "Max result is null")
            assertEquals(4.toLong(), result!![1], "Wrong max result returned")
        }
    }

}